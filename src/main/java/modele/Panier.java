package modele;

import java.util.Map;
import java.util.TreeMap;

public class Panier {
	
	private Map<Article,Integer> panier;
	
	public Panier() {
		panier = new TreeMap<>();
	}
	
	/*
	 * ajouterArticle : ajoute au panier l'article a de la quantité q
	 */
	public void ajouterArticle(Article a, int q) {
		if(this.panier.get(a) - q <= 0) {
			this.panier.remove(a);
		}
		else {
			this.panier.put(a,q);
		}
	}
	
	/*
	 * retirerArticle : retire un article a de la liste this.panier
	 */
	public int retirerArticle(Article a) {
		return this.panier.remove(a);
	}
	
	/*
	 * getPanier : retourne le panier, this.panier
	 */
	public Map<Article,Integer> getPanier() {
		return this.panier;
	}
	
	/*
	 * getPrix : retourne le prix du panier
	 * parcourt la liste des articles et multilplie le prix de chaque par la quantité
	 */
	public float getPrix() {
		float prix = 0;
		for(Article a : panier.keySet()) {
			prix += a.getPrixTTC() * panier.get(a);
		}
		return prix;
	}
}