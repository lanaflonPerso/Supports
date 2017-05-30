package miniProjetInterne;

import java.util.*;

public class SessionFormation {
	private int id;
	private Date dateDebut;
	private Date dateFin;
	private Formation formation;
	private int capacite;
	private List<Personne> inscrits;
	
	public SessionFormation()
	{
		
	}
	
	public SessionFormation(int id, Date dateDebut, Date dateFin, Formation formation, int capacite,
			List<Personne> inscrits) {
		super();
		this.id = id;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.formation = formation;
		this.capacite = capacite;
		this.inscrits = inscrits;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	public Date getDateFin() {
		return dateFin;
	}
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
	public Formation getFormation() {
		return formation;
	}
	public void setFormation(Formation formation) {
		this.formation = formation;
	}
	public int getCapacite() {
		return capacite;
	}
	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}
	public List<Personne> getInscrits() {
		return inscrits;
	}
	public void setInscrits(List<Personne> inscrits) {
		this.inscrits = inscrits;
	}
	
	
}
