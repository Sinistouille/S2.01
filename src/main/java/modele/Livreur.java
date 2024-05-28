package modele;

public enum Livreur {
	COLISSIMO("COLISSIMO",3,8),
	CHRONOPOST("CHRONOPOST",1,14),
	DPD("DPD",2,10),
	MondialRelay("MondialRelay",4,7),
	RelaisColis("RelaisColis",4,7),
	DHL("DHL",2,11),
	FedEx("FedEx",15,5),
	UPS("UPS",20,3);
	
	private String nom;

	private int delaislivraisons;
	private int prixlivraisons;
	
	private Livreur(String nom, int delais, int prix) {
		this.nom = nom;
		this.delaislivraisons = delais;
		this.prixlivraisons = prix;
	}
	public String getNom() {
		return nom;
	}
	public int getPrixlivraisons() {
		return prixlivraisons;
	}
	public int getDelaislivraisons() {
		return delaislivraisons;
	}
}
