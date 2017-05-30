package miniProjetInterne;

import java.util.*;

public class Formation {
	private int id;
	private OrganismeFormation organismeFormation;
	private int duree;
	private double coutHT;
	private Set<Competence> competencesDeveloppees = new HashSet<Competence>();
	
	public Formation() 
	{
		super();
	}
	
	public Formation(int id, OrganismeFormation organismeFormation, int duree, double coutHT) 
	{
		super();
		this.id = id;
		this.organismeFormation = organismeFormation;
		this.duree = duree;
		this.coutHT = coutHT;
		this.competencesDeveloppees = competencesDeveloppees;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public OrganismeFormation getOrganismeFormation() {
		return organismeFormation;
	}
	public void setOrganismeFormation(OrganismeFormation organismeFormation) {
		this.organismeFormation = organismeFormation;
	}
	public int getDuree() {
		return duree;
	}
	public void setDuree(int duree) {
		this.duree = duree;
	}
	public double getCoutHT() {
		return coutHT;
	}
	public void setCoutHT(double coutHT) {
		this.coutHT = coutHT;
	}
	public Set<Competence> getCompetencesDeveloppees() {
		return competencesDeveloppees;
	}
	public void setCompetencesDeveloppees(Set<Competence> competencesDeveloppees) {
		this.competencesDeveloppees = competencesDeveloppees;
	}
	
	public void setCompetences(String competences) {
		for(String s : competences.split(","))
		{
			competencesDeveloppees.add( Competence.getCompetenceByName(s.trim()) );
		}
	}
}
