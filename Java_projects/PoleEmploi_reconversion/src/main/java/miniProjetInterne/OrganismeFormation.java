package miniProjetInterne;

public class OrganismeFormation implements ObjectWithId  {
	private int id;
	private String nom;
	private long numeroSiret;
	private Statut statut;
	private Personne responsable;
	
	public OrganismeFormation()
	{
		super();
	}
	
	public OrganismeFormation(int id, String nom, long numeroSiret, Statut statut, Personne responsable) {
		super();
		this.id = id;
		this.nom = nom;
		this.numeroSiret = numeroSiret;
		this.statut = statut;
		this.responsable = responsable;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public long getNumeroSiret() {
		return numeroSiret;
	}
	public void setNumeroSiret(long numeroSiret) {
		this.numeroSiret = numeroSiret;
	}
	public Statut getStatut() {
		return statut;
	}
	public void setStatut(Statut statut) {
		this.statut = statut;
	}
	public Personne getResponsable() {
		return responsable;
	}
	public void setResponsable(Personne responsable) {
		this.responsable = responsable;
	}
	
	
	
}
