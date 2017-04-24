import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*List<Integer> liste = new ArrayList<Integer>();
		add_integer(liste,10);
		est_triee(liste);*/
		
		int n = 25;
		
		int[][] echiquier = jeuDesReinesSolveurs(n);
		for(int i = 0; i < n; ++i)
		{
			for(int j = 0; j < n; ++j)
			{
				System.out.print(" " + (
						echiquier[i][j] == 1 ? 'X' : '.') );
			}
			System.out.println();
		}
		
	}
	
	public static void showListTime() {
		List<Integer> liste;
		
		int nb = 100;
		for(int i = 0; i < 4 ; i = i + 1 )
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
			a = a + liste.get(i);
		}
		long duration = System.nanoTime() - begin;
		System.out.println("" + duration + " => " + a);
	}
	
	public static boolean est_triee(List<Integer> liste)
	{
		for(int i = 0; i < liste.size() - 1; ++i)
		{
			if( liste.get(i + 1) < liste.get(i) )
			{
				return false;
			}
		}
		return true;
	}
	
	// Prend en argument une liste triée et renvoit une copie sans 
	// les doublons
	public static List<Integer> dedoublonner(List<Integer> liste)
	{
		List<Integer> liste_dedoublonnee = new ArrayList<Integer>();
		// Le premier élément ne peut pas être déjà dans la liste ...
		liste_dedoublonnee.add(liste.get(0));
		Integer dernier_ajoute = liste.get(0);
		
		// On parcourt tous les éléments sauf le premier 
		for(int i = 1; i < liste.size(); ++i)
		{
			// Si il est différent du dernier ajouté, je le rajoute
			// et il devient le dernier ajouté
			if( dernier_ajoute != liste.get(i) )
			{
				dernier_ajoute = liste.get(i);
				liste_dedoublonnee.add(dernier_ajoute);
			}
		}
		return liste_dedoublonnee;
	}
	
	// La fonction prend en entrée une liste d'entier
	// et renvoit le plus petit élément.
	// Elle provoque une erreur si la liste est vide
	public static int minimum(List<Integer> liste)
	{
		// On initialise au premier élément
		int minimum = liste.get(0);
		for(int i = 0; i < liste.size(); ++i)
		{
			// Si on trouve un élément plus petit que
			// le minimum courant, il devient le minimum
			if( liste.get(i) < minimum )
			{
				minimum = liste.get(i);		
			}
		}
		return minimum;
	}
	
	// La fonction prend en entrée une liste d'entier
	// et renvoit le plus grand élément.
	// Elle provoque une erreur si la liste est vide
	public static int maximum(List<Integer> liste)
	{
		// On initialise au premier élément
		int maximum = liste.get(0);
		for(int i = 0; !(i == liste.size()); ++i)
		{
			// Si on trouve un élément plus petit que
			// le maximum courant, il devient le maximum
			if( liste.get(i) > maximum )
			{
				maximum = liste.get(i);		
			}
		}
		return maximum;
	}

	// Cette fonction prend en argument une liste qui doit 
	// être triée, et cherche la position d'un élément.
	// Elle renvoit la position de cet élément, ou -1 si
	// l'élément n'a pas été trouvé
	public static int rechercheDichotomique(
			List<Integer> liste, 
			Integer i)
	{
		// On définit l'intervalle de recherche de la
		// façon suivante
		// [ borneInferieure, borneSuperieure [ 
		int borneinf = 0, borneSuperieure = liste.size();
		
		// On continue tant que l'intervalle de recherche 
		// contient plus d'un élément
		while(borneSuperieure - borneinf > 1)
		{
			// A chaque iteration, on coupe en deux l'intervalle
			// de recherche ...
			int milieu = ((borneinf+borneSuperieure)/2);
			// Si l'élément du milieu est plus grand que l'élément
			// recherché, on cherche dans :
			//     [ borneInferieure, milieu [ 
			if( liste.get( milieu ) > i ) borneSuperieure = milieu;
			// Sinon dans
			//     [ milieu, borneSuperieure [
			else borneinf = milieu;
		}
		// Lorsque le domaine de recherche est réduit à un 
		// élément, si c'est celui-ci, on le renvoit
		if( liste.get(borneinf) == i) return borneinf;
		// Sinon, c'est que l'élément n'est pas là
		return -1;
	}

	// On cherche ici à fusionner deux listes, supposées
	// chacune triée, en une seule nouvelle liste
	public static List<Integer> fusionTriee(
			List<Integer> liste1, 
			List<Integer> liste2
			)
	{
		// On définit des positions dans les listes
		int position_1 = 0, position_2 = 0;
		// On créé notre liste
		List<Integer> liste = new ArrayList<Integer>();
		
		// On boucle tant que l'on n'est pas arrivé au 
		// bout des 2 listes
		// Le || signifie "ou" au sens logique
		while( position_1 != liste1.size() 
			|| position_2 != liste2.size() )
		{
			// Si on est arrivé à la fin de la liste 2
			// (et pas à la fin de la liste car sinon on
			// entrerait pas dans la boucle)
			if( position_1 == liste1.size() )
			{
				liste.add( liste2.get(position_2) );
				position_2++;
			}
			// Sinon si l'on est arrivé à la fin de la 
			// liste 2 ou que l'élément de la liste 1 est
			// plus petit 
			else if( position_2 == liste2.size()  )
			{
				liste.add( liste1.get(position_1) );
				position_1++;
			}
			// Sinon si l'on est arrivé à la fin de la 
			// liste 2 ou que l'élément de la liste 1 est
			// plus petit 
			else if( liste1.get(position_1) < liste2.get(position_2) )
			{
				liste.add( liste1.get(position_1) );
				position_1++;
			}
			// Dernier cas, on est au bout d'aucune des 
			// deux listes, et l'élément de la liste 2
			// est inférieure ou égal
			else if( liste1.get(position_1) > liste2.get(position_2) )
			{
				liste.add( liste2.get(position_2) );
				position_2++;
			}
			else // Cas d'égalité
			{
				liste.add( liste2.get(position_2) );
				position_1++;
				position_2++;
			}
		}
		return liste;
	}
	
	// On prend en entrée 5 listes, les 2 premières sont
	// les données d'entrée, les 3 suivantes des résultats
	public static void comparaisonTriee(
			List<Integer> liste1, 
			List<Integer> liste2, 
			List<Integer> liste_commun,
			List<Integer> liste_unique_1,
			List<Integer> liste_unique_2
			) throws Exception
	{
		// Les 3 listes de résultats sont censées être vides
		if( !liste_commun.isEmpty() || 
			!liste_unique_1.isEmpty() || 
			!liste_unique_2.isEmpty() )
		{
			throw new Exception();
		}
		
		int position_1 = 0, position_2 = 0;
		// On boucle tant que l'on n'est pas arrivé au 
		// bout des 2 listes
		// Le || signifie "ou" au sens logique
		while( position_1 != liste1.size() 
			|| position_2 != liste2.size() )
		{
			// Si on est arrivé à la fin de la liste 2
			if( position_1 == liste1.size() )
			{
				liste_unique_2.add( liste2.get(position_2) );
				position_2++;
			}
			// Sinon si l'on est arrivé à la fin de la 
			// liste 2 ou que l'élément de la liste 1 est
			// plus petit 
			else if( position_2 == liste2.size() )
			{
				liste_unique_1.add( liste1.get(position_1) );
				position_1++;
			}
			// Sinon si l'on est arrivé à la fin de la 
			// liste 2 ou que l'élément de la liste 1 est
			// plus petit 
			else if( liste1.get(position_1) < liste2.get(position_2) )
			{
				liste_unique_1.add( liste1.get(position_1) );
				position_1++;
			}
			// Dernier cas, on est au bout d'aucune des 
			// deux listes, et l'élément de la liste 2
			// est inférieure ou égal
			else if ( liste1.get(position_1) > liste2.get(position_2) )
			{
				liste_unique_2.add( liste2.get(position_2) );
				position_2++;
			}
			else 
			{
				liste_commun.add( liste2.get(position_2) );
				position_1++;
				position_2++;
			}
		}
	}

	// Tri rapide
	public static void triRapide(List<Integer> liste)
	{
		triRapide(liste, 0, liste.size());
	}

	private static void echangerElements(List<Integer> liste, int element1, int element2)
	{
		Integer temp = liste.get(element1);
		liste.set(element1, liste.get(element2) );
		liste.set(element2, temp );
	}
	
	private static void triRapide(List<Integer> liste, int debut, int fin) {
		int pivot = liste.get(debut);
		int iterateur_depuis_debut = debut + 1;
		int iterateur_depuis_fin = fin - 1;
		while( iterateur_depuis_fin >= iterateur_depuis_debut )
		{
			if( liste.get(iterateur_depuis_debut) >= pivot && liste.get(iterateur_depuis_fin) < pivot )
			{
				echangerElements( liste, iterateur_depuis_debut, iterateur_depuis_fin );
			}
			
			if( liste.get(iterateur_depuis_debut) < pivot )
			{
				++iterateur_depuis_debut;
			}
			
			if( liste.get(iterateur_depuis_fin) >= pivot )
			{
				--iterateur_depuis_fin;
			}
		}
		echangerElements( liste, pivot, iterateur_depuis_fin );
	}
	
	private static int[][] copieProfonde(int[][] original)
	{
		int[][] result = new int[original.length][];
		for(int i = 0; i < original.length ; ++i)
		{
			result[i] = new int[original[i].length];
			for(int j = 0; j < original[i].length ; ++j)
			{
				result[i][j] = original[i][j];
			}
		}
		return result;
	}
	
	private static int[][] jeuDesReinesSolveurs( int n )
	{
		int[][] echiquier = null;
		echiquier = new int[n][n];
		for(int i = 0; i < n; ++i)
		{
			for(int j = 0; j < n; ++j)
			{
				echiquier[i][j] = 0;
			}
		}
		return jeuDesReinesSolveurs( echiquier, 0 );
	}

	private static int[][] jeuDesReinesSolveurs(
			// echiquier avec 1 des reines, -1 les cases qui se font 
			// manger, 0 les cases vides et non menacées
			int[][] echiquier, 
			// l'étape est le numéro de la reine que je veux rajouter
			// 0 pour la première, 1 pour la deuxième ...
			int etape) 
	{
		// Si j'ai attenint la longueur de l'échiquier
		if ( etape == echiquier.length ) return echiquier;
		
		// Pour toutes les cases de la ligne
		for(int k = 0 ; k < echiquier[etape].length ; ++k)
		{
			// Je regarde les cases qui ne sont pas sous la
			// menace d'une reine ...
			if( echiquier[etape][k] == 0 )
			{
				// Je fais une copie pour ne pas perdre l'état
				// actuel de l'échiquier
				int[][] echiquier_copie = copieProfonde(echiquier);
				// Je rajoute la reine 
				echiquier_copie[etape][k] = 1;
				// Je note toutes les cases où je ne peux plus mettre de reine
				rendreCasesImpossibles(echiquier_copie,etape,k);
				// J'essaie de mettre la reine suivante ...
				int[][] echiquier_resolu = jeuDesReinesSolveurs( 
						echiquier_copie, 
						etape + 1 );
				if( echiquier_resolu != null)
				{
					return echiquier_resolu;
				}
			}
		}
		
		return null;
	}

	private static void rendreCasesImpossibles(
			int[][] echiquier, 
			int etape,
			int k) {
		for(int i = etape + 1; i < echiquier.length ; ++i)
		{
			echiquier[i][k] = - 1;
			if( k + etape - i >= 0 ) echiquier[i][k + etape - i] = - 1;
			if( k - etape + i < echiquier.length )  echiquier[i][k - etape + i] = - 1;
		}
	}
}
