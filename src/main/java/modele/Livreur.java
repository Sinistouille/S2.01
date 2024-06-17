package modele;

public enum Livreur {
	COLISSIMO("COLISSIMO",3,0.08f),
	CHRONOPOST("CHRONOPOST",1,0.14f),
	DPD("DPD",2,0.1f),
	MondialRelay("MondialRelay",4,0.07f),
	RelaisColis("RelaisColis",4,0.07f),
	DHL("DHL",2,0.11f),
	FedEx("FedEx",15,0.05f),
	UPS("UPS",20,0.03f),
	CHRONOFRESH("CHRONOFRESH",1,0.15f);

	private String nom;

	private int delaislivraisons;
	private float prixlivraisons;

	Livreur(String nom, int delais, float prix) {
		this.nom = nom;
		this.delaislivraisons = delais;
		this.prixlivraisons = prix;
	}
	public String getNom() {
		return nom;
	}

	public float getPrixlivraisons(float prixPanier) {
		return prixPanier > 120 ? 0 : prixlivraisons * prixPanier;
	}
	public int getDelaislivraisons() {
		return delaislivraisons;
	}
}