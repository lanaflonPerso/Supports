package Modele;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

/* Exercice 1 :
 * 		creez la classe PersonneCorrection, avec les attributs:
 * 		- un id (int)
 *      - un numero_de_securite_sociale (long)
 *      - un nom (String)
 *      - un prénom (String)
 *      
 *      

 */

public class PersonneCorrection {
	private int id;
	private long numeroDeSecuriteSociale;
	private String nom;
	private String prenom;
	
	public boolean getSexe()
	{
		return estMasculin();
	}
	
	
	public static PersonneCorrection readFromFileLine(String fileLine)
	{
		String[] splitString = fileLine.split(" ");
		
		return new PersonneCorrection(
				Integer.parseInt(splitString[0]),
				Long.parseLong(splitString[3]),
				splitString[2],
				splitString[1]
				);
	}
	
	public static List<PersonneCorrection> readAllFromFiles(String fileName) throws IOException
	{
		List<PersonneCorrection> result = new ArrayList<PersonneCorrection>();
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
	
	public PersonneCorrection()
	{
		
	}
	
	public PersonneCorrection(
			int id, 
			long numeroDeSecuriteSociale, 
			String nom, 
			String prenom )
	{
		this.id = id;
		this.numeroDeSecuriteSociale = numeroDeSecuriteSociale;
		this.nom = nom;
		this.prenom = prenom;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getNumeroDeSecuriteSociale() {
		return numeroDeSecuriteSociale;
	}
	public void setNumeroDeSecuriteSociale(long numero_de_securite_sociale) {
		this.numeroDeSecuriteSociale = numeroDeSecuriteSociale;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public boolean estMasculin()
	{
		return ("" + 
	this.getNumeroDeSecuriteSociale()
		).substring(0, 1).equals("1");
	}
	
	public int getNumeroDepartement()
	{
		return (int) (this.getNumeroDeSecuriteSociale() / 
				100000000L) % 100;
	}
	
	public String toString()
	{
		return String.format("%s %s : %s", 
				this.getPrenom(), 
				this.getNom(),
				this.getNumeroDeSecuriteSociale());
	}
	
}
