package ModeleTest;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

import org.apache.commons.io.DirectoryWalker;
import org.apache.commons.io.FileUtils;
import org.junit.FixMethodOrder;

import JavaTestUtils.TestCaseWithStdOutCatch;
import JavaTestUtils.TestUtils;
import Modele.Personne;
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
		return new Personne(0, 123456789000000L, "Dupont", "Jean" );
	}
	
	private Personne getElodieMartin()
	{
		return new Personne(1, 278456789000000L, "Martin", "Elodie");
	}
	
	public void test_Q01_01_Field_Prenom()
	{
		__test_private_string_field("prenom", String.class);
		__test_getter( Personne.class.getMethods(), "getPrenom", String.class );
		__test_setter( Personne.class.getMethods(), "setPrenom", String.class );
		assertEquals( getJeanDupont().getPrenom(), "Jean" );
		assertEquals( getElodieMartin().getPrenom(), "Elodie" );
	}

	public void test_Q01_02_Field_Nom()
	{
		__test_private_string_field("nom", String.class);
		__test_getter( Personne.class.getMethods(), "getNom", String.class );
		__test_setter( Personne.class.getMethods(), "setNom", String.class );
		assertEquals( getJeanDupont().getNom(), "Dupont" );
		assertEquals( getElodieMartin().getNom(), "Martin" );
		
	}

	public void test_Q01_03_Field_NumeroSecuriteSociale()
	{
		__test_private_string_field("numeroDeSecuriteSociale", long.class);
		__test_getter( Personne.class.getMethods(), "getNumeroDeSecuriteSociale", long.class );
		__test_setter( Personne.class.getMethods(), "setNumeroDeSecuriteSociale", long.class );
		assertEquals( getJeanDupont().getNumeroDeSecuriteSociale(),   123456789000000L );
		assertEquals( getElodieMartin().getNumeroDeSecuriteSociale(), 278456789000000L );
	}

	public void test_Q02_01_EstMasculin()
	{
		assertTrue( getJeanDupont().estMasculin() );
		assertFalse( getElodieMartin().estMasculin() );
	}
	
	public void test_Q02_02_numero_de_departement()
	{
		assertTrue( getJeanDupont().getNumeroDepartement()   == 23 );
		assertTrue( getElodieMartin().getNumeroDepartement() == 78 );
	}
	
	public void test_Q02_03_to_string()
	{
		assertEquals(getJeanDupont().toString()   , "Jean Dupont : 123456789000000");
		assertEquals( getElodieMartin().toString(), "Elodie Martin : 278456789000000" );
	}
	
	public void __test_Personne_equals( Personne ref, Personne aTester )
	{
		assertEquals( 
				"Le prenom devrait être " + ref.getPrenom(),
				ref.getPrenom(),
				aTester.getPrenom());
		assertEquals( 
				"Le nom devrait être " + ref.getNom(),
				ref.getNom(),
				aTester.getNom());
		assertEquals( 
				"L'id devrait être " + ref.getId(),
				ref.getId(),
				aTester.getId());
		assertEquals( 
				"Le numero de sécu devrait être " + ref.getNumeroDeSecuriteSociale(),
				ref.getNumeroDeSecuriteSociale(),
				aTester.getNumeroDeSecuriteSociale());
	}
	
	public void test_Q03_01_01_readFromFileLine()
	{
		Personne memoryJeanDupont   = getJeanDupont();
		Personne fromFileJeanDupont = Personne.readFromFileLine(
				"0 Jean Dupont 123456789000000");
		__test_Personne_equals( memoryJeanDupont, fromFileJeanDupont );
		
		Personne memory2   = getElodieMartin();
		Personne fromFile2 = Personne.readFromFileLine(
				"1 Elodie Martin 278456789000000");
		__test_Personne_equals( memory2, fromFile2 );
	}
	
	public void test_Q03_01_02_readFromFileLine() throws IOException
	{
		List<Personne> liste_personne = Personne.readAllFromFiles("ressource/Exemple_personnes.txt");
	}
}
