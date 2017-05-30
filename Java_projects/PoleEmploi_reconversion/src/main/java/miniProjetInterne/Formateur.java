package miniProjetInterne;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Formateur extends Personne {
	private Set<Competence> expertise;
	
	public Formateur() {
		super();
		expertise = new TreeSet<Competence>();
	}
	
	public Formateur(int id, long numeroSecu, String nom, String prenom) {
		super(id, numeroSecu, nom, prenom);
		expertise = new TreeSet<Competence>();
	}

	public Set<Competence> getCompetences() {
		return expertise;
	}

	public void setCompetences(String competences) {
		for(String s : competences.split(","))
		{
			expertise.add( Competence.getCompetenceByName(s.trim()) );
		}
	}
}
