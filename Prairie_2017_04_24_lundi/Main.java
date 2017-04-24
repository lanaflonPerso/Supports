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
		int mini = liste.get(0);
		for(Integer i : liste)
		{
			if( mini > i )
			{
				mini = i;
			}
		}
		return mini;
	}
	
	public static int maximum(List<Integer> liste)
	{
		int maxi = liste.get(0);
		for(Integer i : liste)
		{
			if( maxi < i )
			{
				maxi = i;
			}
		}
		return maxi;
	}

	public static int rechercheDichotomique(List<Integer> liste, Integer i)
	{
		int a = 0, b = liste.size();
		while(b - a != 1)
		{
			int c = (a+b)/2;
			if( liste.get(c) > i) b = c;
			else a = c;
		}
		if( liste.get(a).equals(i))
		{
			return a;
		}
		return -1;
	}

	public static List<Integer> fusionTriee(
			List<Integer> liste1, 
			List<Integer> liste2
			)
	{
		List<Integer> liste_fusionnee = new ArrayList();
		int pos2 = 0;
		for(Integer i : liste1)
		{
			while(pos2 < liste2.size() && liste2.get(pos2) < i)
			{
				liste_fusionnee.add(liste2.get(pos2++));
			}
			liste_fusionnee.add(i);
		}
		
		for(;pos2 < liste2.size(); ++pos2)
		{
			liste_fusionnee.add(liste2.get(pos2));
		}
		return liste_fusionnee;
	}
	
	public static void comparaisonTriee(
			List<Integer> liste1, 
			List<Integer> liste2, 
			List<Integer> liste_commun,
			List<Integer> liste_unique_1,
			List<Integer> liste_unique_2
			)
	{
		int pos2 = 0;
		for(Integer i : liste1)
		{
			while(pos2 < liste2.size() && liste2.get(pos2) < i)
			{
				liste_unique_2.add(liste2.get(pos2++));
			}
			
			if( pos2 < liste2.size() && liste2.get(pos2) == i )
			{
				liste_commun.add(liste2.get(pos2++));
			}
			else
			{
				liste_unique_1.add(i);
			}
		}
		
		for(;pos2 < liste2.size(); ++pos2)
		{
			liste_unique_2.add(liste2.get(pos2));
		}
	}
}
