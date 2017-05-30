package miniProjetInterne;

public class Personne {
	public Personne() {
		super();
	}
	
	public Personne(int id, long numeroSecu, String nom, String prenom) {
		super();
		this.id = id;
		this.numeroSecu = numeroSecu;
		this.nom = nom;
		this.prenom = prenom;
	}
	
	private int id;
	private long numeroSecu;
	private String nom;
	private String prenom;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getNumeroSecu() {
		return numeroSecu;
	}
	public void setNumeroSecuriteSociale(long numeroSecu) {
		this.numeroSecu = numeroSecu;
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
	
	
}
