import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;


public class TestCaseAlgorithmes extends TestCase {
	
	private static List<Integer> getAsListInteger(Integer[] tab)
	{
		List<Integer> liste = new ArrayList();
		for(Integer i : tab) liste.add(i);	
		return liste;
	}
	
	public void test_qui_marche()
	{
		assert(true);
	}
	
	public void testMinimum()
	{
		List<Integer> liste = getAsListInteger(new Integer[]{5,4,3,2,1});
		assertEquals(1, Main.minimum(liste));
		liste = getAsListInteger(new Integer[]{20,16,19,17,18});
		assertEquals(16, Main.minimum(liste));
		liste = getAsListInteger(new Integer[]{100, 99, 98, 97, 96, 95, 94, 93, 92, 91, 90});
		assertEquals(90, Main.minimum(liste));
	}

	public void testMaximum()
	{
		List<Integer> liste = getAsListInteger(new Integer[]{5,4,3,2,1,6});
		assertEquals(6, Main.maximum(liste));
		liste = getAsListInteger(new Integer[]{20,16,19,17,18});
		assertEquals(20, Main.maximum(liste));
		liste = getAsListInteger(new Integer[]{100, 99, 98, 97, 96, 95, 94, 93, 92, 91, 90});
		assertEquals(100, Main.maximum(liste));
	}
	
	public void testRechercheDichotomique()
	{
		List<Integer> liste = getAsListInteger(new Integer[]{1,7,16,25,200,906});
		for(int i = 0; i < liste.size(); ++i)
		{
			assertEquals(i, Main.rechercheDichotomique(liste, liste.get(i)) );
		}
		
		liste = new ArrayList<Integer>();
		Main.add_integer(liste, 1000*1000);
		
		for(int i = 0; i < liste.size(); ++i)
		{
			assertEquals(i, Main.rechercheDichotomique(liste, liste.get(i)) );
		}
	}

	public void testFusionTriee()
	{
		List<Integer> liste1 = getAsListInteger(new Integer[]{1,2,3});
		List<Integer> liste2 = getAsListInteger(new Integer[]{4,5,6});
		
		assertEquals( getAsListInteger(new Integer[]{1,2,3,4,5,6}),
				      Main.fusionTriee(liste1, liste2) );
		assertEquals( getAsListInteger(new Integer[]{1,2,3,4,5,6}),
			      	  Main.fusionTriee(liste2,liste1) );
		assertEquals( getAsListInteger(new Integer[]{1,1,2,2,3,3}),
			      	  Main.fusionTriee(liste1,liste1) );
		
		
		liste1 = getAsListInteger(new Integer[]{1,7,16,25,200,906});
		liste2 = getAsListInteger(new Integer[]{2,3, 4, 5,  6,  7, 8});
		
		assertEquals( 
			getAsListInteger(new Integer[]{1,2,3,4,5,6,7,7,8,16,25,200,906}),
			Main.fusionTriee(liste1, liste2)
				);
	}

	public static void testComparaisonTriee()
	{
		List<Integer> liste1 = getAsListInteger(new Integer[]{1,7,16,25,200,906});
		List<Integer> liste2 = getAsListInteger(new Integer[]{1,2,16,24,25,600});
		List<Integer> liste_commun = new ArrayList<Integer>();
		List<Integer> liste_unique_1 = new ArrayList<Integer>();
		List<Integer> liste_unique_2 = new ArrayList<Integer>();
		
		Main.comparaisonTriee(liste1, liste2, liste_commun, liste_unique_1, liste_unique_2);
		
		assertEquals( getAsListInteger(new Integer[]{1,16,25}), liste_commun );
		assertEquals( getAsListInteger(new Integer[]{7,200,906}), liste_unique_1 );
		assertEquals( getAsListInteger(new Integer[]{2, 24,600}), liste_unique_2 );
		
		liste_commun = new ArrayList<Integer>();
		liste_unique_1 = new ArrayList<Integer>();
		liste_unique_2 = new ArrayList<Integer>();
		
		Main.comparaisonTriee(liste2, liste1, liste_commun, liste_unique_1, liste_unique_2);
		
		assertEquals( getAsListInteger(new Integer[]{1,16,25}), liste_commun );
		assertEquals( getAsListInteger(new Integer[]{7,200,906}), liste_unique_2 );
		assertEquals( getAsListInteger(new Integer[]{2, 24,600}), liste_unique_1 );
	}
}
