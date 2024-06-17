package modele;

public class Article implements Comparable<Article> {

	private Fromage fromage;
	private String clé;
	private float prixHT;
	private int quantitéEnStock;
	private int code;
	private int QuantiteMax;

	public Article(Fromage fromage, String clé, float prixHT, int quantitéEnStock) {
		this.fromage = fromage;
		this.clé = clé;
		this.prixHT = prixHT;
		this.quantitéEnStock = quantitéEnStock;
		this.QuantiteMax = quantitéEnStock;
		this.code = (fromage.getDésignation() + clé).hashCode();
	}

	public Article(Fromage fromage, String clé, float prixHT) {
		this.fromage = fromage;
		this.clé = clé;
		this.prixHT = prixHT;
		this.quantitéEnStock = 0;
        this.code = (fromage.getDésignation() + clé).hashCode();
	}

	public Fromage getFromage() {
		return this.fromage;
	}

	public float getPrixHT() {
		return this.prixHT;
	}

	public int getQuantitéEnStock() {
		return this.quantitéEnStock;
	}

	public String getClé() {
		return this.clé;
	}

	public void préempterQuantité(int q) throws IllegalArgumentException {
		if (q > this.quantitéEnStock || q < 0) {
			throw new IllegalArgumentException("Quantité insuffisante en stock");
		}
		this.quantitéEnStock -= q;
	}

	public void rendreQuantité(int q) throws IllegalArgumentException{
		if (q < 0) {
			throw new IllegalArgumentException("Quantité négative");
		}
		this.quantitéEnStock += q;
	}

	public void setQuantitéEnStock(int quantitéEnStock) {
		this.quantitéEnStock = quantitéEnStock;
		this.QuantiteMax = quantitéEnStock;
	}

	public int getQuantitePanier() {
		return this.QuantiteMax - this.quantitéEnStock;
	}

	@Override
	public String toString() {
		if (this.clé.equals("")) {
			return this.fromage.getDésignation() + ", Prix TTC : " + this.getPrixHT() + " €";
		} else {
			return this.fromage.getDésignation() + ", " + this.clé + ", Prix TTC : " + this.getPrixHT() + " €";
		}
	}

	public String toStringPrix() {
		return this.getPrixHT() + " €";
	}

	public String toStringAvecStock() {
		return this + ", Quantité en stock : " + this.quantitéEnStock;
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