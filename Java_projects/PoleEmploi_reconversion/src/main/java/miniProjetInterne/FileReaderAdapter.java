package miniProjetInterne;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.io.FileUtils;

public class FileReaderAdapter {
	private static final DateFormat formatDate = new SimpleDateFormat("yyyy-M-d", Locale.ENGLISH);

	public static void main(String[] args) throws Exception
	{
		FileReaderAdapter fileReaderAdapter = new FileReaderAdapter();
		List<Statut> listeStatut            = fileReaderAdapter.readFromFile(
				"ressource/statuts.csv", Statut.class);
		List<Personne> listePersonnes         = fileReaderAdapter.readFromFile(
				"ressource/personnes.csv", Personne.class);
		List<Formateur> listeFormateurs         = fileReaderAdapter.readFromFile(
				"ressource/formateurs.csv", Formateur.class);
		List<PersonneEnReconversion> listePersonneEnReconversions = fileReaderAdapter.readFromFile(
				"ressource/personneEnReconversions.csv", PersonneEnReconversion.class);
		List<OrganismeFormation> listeCentreDeFormation = fileReaderAdapter.readFromFile(
				"ressource/organismeFormations.csv", OrganismeFormation.class);
		List<Formation> listeFormations = fileReaderAdapter.readFromFile(
				"ressource/formations.csv", Formation.class);
		List<SessionFormation> listeSessionFormations = fileReaderAdapter.readFromFile(
				"ressource/sessionFormations.csv", SessionFormation.class);
		
		
		for(OrganismeFormation organismeFormation : listeCentreDeFormation)
		{
			System.out.println( organismeFormation.getResponsable().getNom() );
			System.out.println( organismeFormation.getStatut().getNom() );
		}
	}

	private static String getSetterNameFromColumn(String column)
	{
		return "set" + column.substring(0,1).toUpperCase() + column.substring(1);
	}

	private Map<Class, Map<Integer,Object>> mapClassIdToInstance = new HashMap();;

	private void addClassIdObject(Object o, Class class_, Integer id)
	{
		if( mapClassIdToInstance ==  null ) mapClassIdToInstance = new HashMap();
		if( !mapClassIdToInstance.containsKey(class_) )
		{
			mapClassIdToInstance.put(class_, new TreeMap());
		}
		mapClassIdToInstance.get(class_).put(id, o);
	}

	private <T> void applySetter(T object, String column, Method setter) throws Exception {
		Class setterParameterClass = setter.getParameterTypes()[0];

		if(        setterParameterClass.equals(long.class) 
				|| setterParameterClass.equals(Long.class) )
		{
			setter.invoke(object, Long.parseLong(column));
		}
		else if(    setterParameterClass.isAssignableFrom(int.class) 
				|| setterParameterClass.isAssignableFrom(Integer.class) )
		{
			setter.invoke(object, Integer.parseInt(column));
		}
		else if (  setterParameterClass.isAssignableFrom(double.class) 
				|| setterParameterClass.isAssignableFrom(Double.class) )
		{
			setter.invoke(object, Double.parseDouble(column));
		}
		else if (  setterParameterClass.isAssignableFrom(boolean.class) 
				|| setterParameterClass.isAssignableFrom(Boolean.class) )
		{
			setter.invoke(object, Boolean.parseBoolean(column));
		}
		else if (  setterParameterClass.isAssignableFrom(Date.class) )
		{
			setter.invoke(object, formatDate.parse(column) );
		}
		else if (  setterParameterClass.isAssignableFrom(String.class) )
		{
			setter.invoke(object, column );
		}
		else
		{
			int id = Integer.parseInt(column);
			if( id == -1 )
			{
				setter.invoke(object, -1);
			}
			else
			{
				if( !mapClassIdToInstance.containsKey(setterParameterClass) || 
						!mapClassIdToInstance.get(setterParameterClass).containsKey(id) )
				{
					throw new Exception(
							String.format(
									"Erreur, impossible de trouver un objet de type %s avec l'id %s en lisant l'objet de type %s"
									, setterParameterClass.getName(), id, object.getClass().getName() ) );
				}
				setter.invoke(object, mapClassIdToInstance.get(setterParameterClass).get(id) );
			}
		}

	}

	public <T> List<T> readFromFile(String fileName, Class<T> classe) throws Exception
	{
		List<T> result = new ArrayList();

		List<String> liste_lignes = (new FileUtils()).readLines(new File(fileName));
		List<Method> setterList = new ArrayList();
		Method methodGetId = null;
		for(Method method : classe.getMethods())
		{
			if(method.getName().equals("getId"))
			{
				methodGetId = method;
				break;
			}
		}
		int nbLigne = 0;
		for(String ligne : liste_lignes)
		{
			if( nbLigne == 0 )
			{

				String[] splitLine = ligne.split(";");
				for(int i = 0; i < splitLine.length; ++i)
				{
					String column = splitLine[i].trim();
					String setterName = getSetterNameFromColumn(column);
					Method method = getMethodByName(classe, setterName, column);

					setterList.add(method);
				}
			}
			else
			{
				T object = null;

				try {
					object = classe.newInstance();
				} catch (InstantiationException | IllegalAccessException e) {
					String msg = String.format(
							"Erreur : pour la classe %s vous devez définir un " +
									"un constructeur par défaut", classe.getName());
					System.err.println(msg);
					e.printStackTrace();
					throw new Exception(msg);
				}

				String[] splitLine = ligne.split(";");
				for(int i = 0; i < splitLine.length; ++i)
				{
					String column = splitLine[i].trim();
					applySetter(object, column, setterList.get(i));
					if( methodGetId != null )
					{
						addClassIdObject( object, classe, (Integer)methodGetId.invoke(object, null) );
					}
				}
				result.add(object);
			}

			nbLigne++;
		}
		return result;
	}

	private Method getMethodByName(Class classe, String setterName, String column) throws Exception {
		for(Method method : classe.getMethods())
		{
			if(method.getName().equals(setterName))
			{
				return method;
			}
		}
		String errorMsg = "Erreur, pour la colonne " + column + " le setter " +
				setterName + " n'a pas été trouvé.";
		System.err.println(errorMsg);
		throw new Exception(errorMsg);

	}


}
