package JavaLesson;

/*
 * Q1 : compléter la fonction Q01_creer_array pour qu'elle renvoit un 
 *      array d'entier contenant un entier, 13
 * 
 * Q2 : compléter la fonction Q02_creer_array qui ne prend pas d'argument
 *      et renvoit un array d'entier contenant les nombres 11, 12, 13
 *      
 * Q3 : créer une fonction Q03_creer_array qui prend en argument un entier
 *      n et renvoit un tableau d'entier contenant n fois le nombre 0.
 *      Par exemple :
 *      
 *      	Q03_creer_array(5) renvoit [0, 0, 0, 0, 0]
 * 
 * Q4 : créer une fonction Q04_creer_array qui prend en 
 * 	    argument deux entier n puis k
 *      et renvoit un tableau d'entier contenant n fois le nombre k.
 *      Par exemple :
 *      
 *      	Q04_creer_array(2,7) renvoit [7, 7]
 * Copyright : Nicolas Rousset, 2017, www.aenori.org
 */

public class Array {
	public static void main( String[] args )
	{
		Q02_creer_array();
	}
	
	public static int[] Q01_creer_array()
	{
		return null;
	}
	
	public static int[] Q02_creer_array()
	{
		int[] tab  = new int[]{1,2,7,9};
		int[] tab2 = new int[]{3,4,8,9};
		int[] resultat = new int[tab.length + tab2.length];
		int pos  = 0, pos2 = 0;
		for(int i = 0; i < resultat.length ; ++i )
		{
			if( pos == tab.length )
			{
				resultat[i] = tab2[pos2++];
			}
			else if( pos2 == tab.length 
				  || tab[pos] < tab2[pos2] )
			{
				resultat[i] = tab[pos++];
			}
			else
			{
				resultat[i] = tab2[pos2++];
			}
		}
		
		return tab;
	}
	
	public static int[] Q023_creer_array()
	{
		int[] tab = new int[3];
		for(int i = 0; i < 3 ; ++i)
		{
    		tab[i] = 11 + i;
		}
		return tab;
	}
	
	public static int[] Q03_creer_array(int n)
	{
		return null;
	}
}
