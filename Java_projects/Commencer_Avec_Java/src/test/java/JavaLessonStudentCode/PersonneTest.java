package JavaLessonStudentCode;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.junit.FixMethodOrder;

import JavaTestUtils.TestCaseWithStdOutCatch;
import JavaTestUtils.TestUtils;
import junit.framework.TestCase;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonneTest extends TestCaseWithStdOutCatch {

	public PersonneTest( String testName )
	{
		super( testName );
	}

	private void __test_private_string_field(String fieldName, Class<?> type)
	{
		Field prenomField = TestUtils.getFieldByName(Personne.class, fieldName);
		assertNotNull(
				"Attribut " + fieldName + " non trouvé dans la classe Personne",
				prenomField );
		if( (prenomField.getModifiers() & Modifier.PRIVATE) == 0)
		{
			fail("L'attribut " + fieldName + " doit être private");
		}

		if( !type.equals( prenomField.getType() ) )
		{
			fail("L'attribut " + fieldName + " doit être de type " + type.getName() );
		}

	}
	private void __test_getter(Method[] methods, String string, Class<?> class_) {
		for(Method method: methods)
		{
			if( method.getName().equals( string ) )
			{
				assertTrue(
						"Le getter " + string + " ne doit pas avoir d'argument", 
						method.getParameterTypes().length == 0 );
				assertTrue(
						"Le getter " + string + " doit renvoyer " + class_.getName(), 
						method.getReturnType().equals(class_) );
				return;
			}
		}	
		fail("Le getter " + string + " n'a pas été trouvé.");
	}
	private void __test_setter(Method[] methods, String string, Class<?> class_) {
		for(Method method: methods)
		{
			if( method.getName().equals( string ) )
			{
				assertTrue(
						"Le setter " + string + " doit avoir exactement un argument", 
						method.getParameterTypes().length == 1 );
				assertTrue(
						"Le l'argument du setter " + string + " doit être de type " + class_.getName(), 
						method.getParameterTypes()[0].equals(class_) );
				assertTrue(
						"Le setter " + string + " ne doit rien renvoyer ", 
						method.getReturnType().equals(void.class) );
				return;
			}
		}	
		fail("Le getter " + string + " n'a pas été trouvé.");
	}
	private Personne getJeanDupont()
	{
		return new Personne("Dupont", "Jean", 25);
	}
	private Personne getJacquesMartin()
	{
		return new Personne("Martin", "Jacques", 55);
	}
	
	public void test_Q01_Field_Prenom()
	{
		__test_private_string_field("prenom", String.class);
	}

	public void test_Q02_Field_Nom()
	{
		__test_private_string_field("nom", String.class);
	}

	public void test_Q03_Field_Age()
	{
		__test_private_string_field("age", int.class);
	}

	public void test_Q04_Getters()
	{
		Method[] methods = Personne.class.getMethods();
		__test_getter( methods, "getNom", String.class );
		__test_getter( methods, "getPrenom", String.class );
		__test_getter( methods, "getAge", int.class );
	}

	public void test_Q05_Set_Age()
	{
		Method[] methods = Personne.class.getMethods();
		__test_setter( methods, "setAge", int.class );
	}

	public void test_Q06_Constructeur_3_arguments()
	{
		String[] s = new String[]{"Kenobi", "Obi-Wan", "Skywalker", "Anakin"};
		int[] ages = new int[]{25,16}; 
		for(int i = 0; i < 2 ; ++i)
		{
			String nom = s[2*i];
			String prenom = s[2*i + 1];
			Personne personne = new Personne(nom, prenom, ages[i]);
			assertFalse("Le premier argument doit être le nom",
					    personne.getNom().equals(prenom) );
			assertEquals("Le premier argument doit être le nom",
					     nom,
					     personne.getNom() );
			assertFalse("Le deuxième argument doit être le prénom",
					    personne.getPrenom().equals(nom) );
			assertEquals("Le deuxième argument doit être le prénom",
						 prenom,
						 personne.getPrenom() );
			assertEquals("L'age est incorrect", ages[i], personne.getAge());
		}

	}

	public void test_Q07_Constructeur_2_arguments()
	{
		String[] s = new String[]{"Kenobi", "Obi-Wan", "Skywalker", "Anakin"};

		for(int i = 0; i < 2 ; ++i)
		{
			String nom = s[2*i];
			String prenom = s[2*i + 1];
			Personne personne = new Personne(nom, prenom);
			assertFalse("Le premier argument doit être le nom",
					    personne.getNom().equals(prenom) );
			assertEquals("Le premier argument doit être le nom",
					     nom,
					     personne.getNom() );
			assertFalse("Le deuxième argument doit être le prénom",
					    personne.getPrenom().equals(nom) );
			assertEquals("Le deuxième argument doit être le prénom",
						 prenom,
						 personne.getPrenom() );
			assertEquals("L'age est incorrect", -1, personne.getAge());
		}

	}

	public void test_Q08_final_Field()
	{
		Field prenomField = TestUtils.getFieldByName(Personne.class, "prenom");
		assertTrue("L'attribut prenom n'est pas final",
				   (prenomField.getModifiers() & Modifier.FINAL) == Modifier.FINAL );
		Field nomField = TestUtils.getFieldByName(Personne.class, "nom");
		assertTrue("L'attribut nom n'est pas final",
				   (nomField.getModifiers() & Modifier.FINAL) == Modifier.FINAL );
	}

	public void test_Q09_dire_bonjour() throws IOException
	{
		Personne jeanDupont = this.getJeanDupont();
		setUpOutContent();
		jeanDupont.direBonjour();
		assertEquals(
				"Bonjour, je m'appelle Jean Dupont",
				outContent.toString().trim() );
		setUpOutContent();
		Personne jacquesMartin = this.getJacquesMartin();
		jacquesMartin.direBonjour();
		assertEquals(
				"Bonjour, je m'appelle Jacques Martin",
				outContent.toString().trim() );
	}
}
