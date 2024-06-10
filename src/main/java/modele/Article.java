package modele;

public class Article implements Comparable<Article> {

	private Fromage fromage;
	private String clé;
	private float prixTTC;
	private int quantitéEnStock;
	private int code;
	private int quantitéPanier;

	public Article(Fromage fromage, String clé, float prixTTC) {
		this.fromage = fromage;
		this.clé = clé;
		this.prixTTC = prixTTC;
		this.quantitéEnStock = 0;
		this.code = (fromage.getDésignation() + clé).hashCode();
	}

	public Fromage getFromage() {
		return this.fromage;
	}

	public float getPrixTTC() {
		return this.prixTTC;
	}

	public int getQuantitéEnStock() {
		return this.quantitéEnStock;
	}

	public String getClé() {
		return this.clé;
	}

	public void préempterQuantité(int q) {
		this.quantitéEnStock -= q;
	}

	public void rendreQuantité(int q) {
		this.quantitéEnStock += q;
	}

	public void setQuantitéEnStock(int quantitéEnStock) {
		this.quantitéEnStock = quantitéEnStock;
		this.quantitéPanier = quantitéEnStock;
	}

	public int getQuantitéPanier() {
		return this.quantitéPanier - this.quantitéEnStock;
	}

	@Override
	public String toString() {
		if (this.clé.equals("")) {
			return this.fromage.getDésignation() + ", Prix TTC : " + this.getPrixTTC() + " €";
		} else {
			return this.fromage.getDésignation() + ", " + this.clé + ", Prix TTC : " + this.getPrixTTC() + " €";
		}
	}

	public String toStringPrix() {
		return this.getPrixTTC() + " €";
	}

	public String toStringAvecStock() {
		return this.toString() + ", Quantité en stock : " + this.quantitéEnStock;
	}

	@Override
	public boolean equals(Object obj) {
		Article other = (Article) obj;
		return (this.fromage.equals(other.fromage) && this.clé.equals(other.clé));
	}

	@Override
	public int compareTo(Article o) {
		// TODO Auto-generated method stub
		return this.code - o.code;
	}

	public int getCode() {
		return this.code;
	}

	public Integer[] tabQuantite() {
		Integer[] tab = new Integer[this.quantitéEnStock + 1];
		for (int i = 0; i < this.quantitéEnStock + 1; i++) {
			tab[i] = i;
		}
		return tab;
	}

}