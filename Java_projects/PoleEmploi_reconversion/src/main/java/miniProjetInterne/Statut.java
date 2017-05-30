package miniProjetInterne;

public class Statut {
	
	private int id;
	private String nom;
	private boolean assujettiTVA;
	
	public Statut() {
		super();
	}
	
	public Statut(int id, String nom, boolean assujettiTVA) {
		super();
		this.id = id;
		this.nom = nom;
		this.assujettiTVA = assujettiTVA;
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
	public boolean isAssujettiTVA() {
		return assujettiTVA;
	}
	public void setAssujettiTVA(boolean assujettiTVA) {
		this.assujettiTVA = assujettiTVA;
	}
	
	
}
