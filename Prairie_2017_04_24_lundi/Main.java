import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		showListTime();
	}
	
	public static void showListTime() {
		List<Integer> liste;
		
		int nb = 100;
		for(int i = 0; i < 4 ; ++i)
		{
			System.out.println("Nb : " + nb);
			for(int a = 0; a < 2; ++a)
			{
				liste = a == 0 ? new ArrayList<Integer>() : new LinkedList<Integer>();
				add_integer(liste,nb);
				mesurerTempsSomme(liste);
				
			}
			nb *= 10;
		}
	}

	public static void add_integer(List<Integer> liste, int nb) {
		for(int i = 0; i < nb ; ++i)
		{
			liste.add(i);
		}
	}

	private static void mesurerTempsSomme(List<Integer> liste)
	{
		long a = 0;
		long begin = System.nanoTime();
		for(int i = 0; i < liste.size(); ++i)
		{
			a += liste.get(i);
		}
		long duration = System.nanoTime() - begin;
		System.out.println("" + duration + " => " + a);
	}
	
	public static int minimum(List<Integer> liste)
	{
		return -1;
	}
	
	public static int maximum(List<Integer> liste)
	{
		return -1;
	}

	public static int rechercheDichotomique(List<Integer> liste, Integer i)
	{
		return -1;
	}

	public static List<Integer> fusionTriee(
			List<Integer> liste1, 
			List<Integer> liste2
			)
	{
		return null;
	}
	
	public static void comparaisonTriee(
			List<Integer> liste1, 
			List<Integer> liste2, 
			List<Integer> liste_commun,
			List<Integer> liste_unique_1,
			List<Integer> liste_unique_2
			)
	{
		
	}
}
