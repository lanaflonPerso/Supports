package Modele;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

/* 
 * 
# Journee n°1 : modele de donnée

On souhaite définir un programme d'aide à la reconversion, Pour cela, on commence par définir le modèle de donnée suivant :

On manipulera :

- des Personnes caractérisées par :
    - un id interne (int)
    - un numéro de sécurité sociale
    - un nom (String)
    - un prénom (String)

- Un statut est caractérisé par :
    - un id interne (int)
    - un nom (String)
    - un bool assujetiALaTVA (boolean)

- Un organisme de formation est caractérisé par :
    - un id interne (int)
    - un nom (String)
    - un statut (Statut)
    - un responsable (Personne)

- Une formation est caractérisée par :
    - un id interne (int)
    - un nombre de jour (int)
    - un coût (int)
    - un organisme de formation (OrganismeDeFormation)

- Une session de formation est caractérisée par :
    - un id interne (int)
    - une date de début (Date : des fonctions setDateDebut sont fournies)
    - une date de fin (Date : des fonctions setDateFin sont fournies)
    - une formation (Formation)
    - un formateur (Personne)

## Exercice n°1 : class Person

Q01_01 : Ecrire la classe personne, avec ses 3 attributs (on nommera le numéro de sécurité sociale numeroSecuriteSociale), les getters et les setters associés.  
Q01_02 : Ecrire deux constructeur, un par défaut initialisant tout à null ou -1, et un prenant en argument les 4 attributs dans l'ordre indiqué ci-dessus.

Pourquoi est-ce une mauvaise idée de représenter le numéro de sécurité sociale par un int ?

## Exercice n°2 : 

Q02_01 : Ecrire une methode estMasculin qui ne prend par d'argument et renvoit true si la personne est un homme, false sinon.  
Q02_02 : Ecrire une fonction qui renvoit le département de naissance de la personne, sous forme d'entier. 
Q02_03 : Définir une méthode toString(), qui convertit la personne sous la forme de la String suivante :  

    {prenom} {nom} : {numeroSecuriteSociale}

## Exercice n°3 :

Q03_01 : Compléter la méthode  statique readFromFileLine, qui prend en argument une ligne du fichier personne.txt, et renvoit un nouvel objet de type Personne.
    Par exemple la ligne :  

        0 Jean Dupont 123456789000000
       
    Sera transformé en un objet de type Personne d'id 0, de prénom Jean et de nom Dupont.
    On utilisera pour cela les méthodes très classiques :

        Integer.parseInt()
        Long.parseLong()
        String.split()




    

 *      
 *      

 */

public class Personne {
	private int id;
	private long numeroDeSecuriteSociale;
	private String nom;
	private String prenom;
	
	public static Personne readFromFileLine(String fileLine)
	{
		return null;
	}
	
	public static List<Personne> readAllFromFiles(String fileName) throws IOException
	{
		List<Personne> result = new ArrayList<Personne>();
		List<String> liste_lignes = FileUtils.readLines(new File(fileName));
		for(String ligne : liste_lignes)
		{
			if(ligne.split(" ").length != 4) break;
			result.add(readFromFileLine(
					ligne
					));
		}
		return result;
	}
	
	
}
