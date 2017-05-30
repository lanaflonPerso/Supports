package miniProjetInterne;

import java.util.Set;
import java.util.TreeSet;

public class PersonneEnReconversion extends Personne {
	private Set<Competence> competencesEnDeveloppement;
	
	public PersonneEnReconversion() {
		super();
		competencesEnDeveloppement = new TreeSet<Competence>();
	}
	
	public PersonneEnReconversion(int id, long numeroSecu, String nom, String prenom) {
		super(id, numeroSecu, nom, prenom);
		competencesEnDeveloppement = new TreeSet<Competence>();
	}

	public Set<Competence> getCompetencesEnDeveloppement() {
		return competencesEnDeveloppement;
	}
	
	public void setCompetences(String competences) {
		for(String s : competences.split(","))
		{
			competencesEnDeveloppement.add( Competence.getCompetenceByName(s.trim()) );
		}
	}
}
