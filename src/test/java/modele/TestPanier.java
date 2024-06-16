package modele;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestPanier {

	Panier panier = new Panier();
	FromageALaCoupe fromage = new FromageALaCoupe("f");
	Article article1 = new Article(this.fromage, "ff", 10f);
	Article article2 = new Article(this.fromage, "a", 2f);

	@Before
	public void setUp() {
		panier = new Panier();
		fromage = new FromageALaCoupe("f");
		article1 = new Article(fromage, "ff", 10f);
		article2 = new Article(fromage, "a", 2f);
	}

	@Test
	public void testAjouterArticle() {
		article1.setQuantitéEnStock(10);
		panier.ajouterArticle(article1, 1);
		assertTrue(panier.getPanier().containsKey(article1));
		assertEquals(1, panier.getPanier().size());

		panier.ajouterArticle(article1, -1);
		assertEquals(0, panier.getPanier().size());
		assertFalse(panier.getPanier().containsKey(article1));
	}

	@Test
	public void testAjouterArticleSansStock() {
		article1.setQuantitéEnStock(0);
		panier.ajouterArticle(article1, 1);
		assertFalse(panier.getPanier().containsKey(article1));
		assertEquals(0, panier.getPanier().size());
	}

	@Test
	public void testRetirerArticle() {
		article1.setQuantitéEnStock(10);
		panier.ajouterArticle(article1, 1);
		panier.retirerArticle(article1);
		assertEquals(0, panier.getPanier().size());

		panier.ajouterArticle(article1, 1);
		panier.ajouterArticle(article1, -1);
		assertEquals(0, panier.getPanier().size());
	}

	@Test
	public void testAjouterArticleChangeStock() {
		article1.setQuantitéEnStock(10);
		panier.ajouterArticle(article1, 5);
		assertEquals(5, article1.getQuantitéEnStock());
	}

	@Test
	public void testGetPrixHT() {
		article1.setQuantitéEnStock(10);
		panier.ajouterArticle(article1, 2);
		assertEquals(20.0f, panier.getPrixHT(), 0.001);
	}

	@Test
	public void testGetPrixTTC() {
		article1.setQuantitéEnStock(10);
		panier.ajouterArticle(article1, 2);
		assertEquals(24.0f, panier.getPrixTTC(), 0.001);
	}

	@Test
	public void testMontantTVA() {
		article1.setQuantitéEnStock(10);
		panier.ajouterArticle(article1, 2);
		assertEquals(4.0f, panier.montantTVA(), 0.001);
	}

	@Test
	public void testViderPanier() {
		article1.setQuantitéEnStock(10);
		panier.ajouterArticle(article1, 2);
		panier.viderPanier();
		assertEquals(0, panier.getPanier().size());
	}

	@Test
	public void testToString() {
		article1.setQuantitéEnStock(10);
		panier.ajouterArticle(article1, 2);
		String expectedString = "Panier numéro " + panier.getUUID() + "\n" +
				"Prix total du Panier : 20.0\n" +
				"Nombre d'articles : 1\n" +
				"f ff quantité : 2 Prix Unitaire : 10.0 Total : 20.0\n";
		assertEquals(expectedString, panier.toString());
	}

	@Test
	public void testSetAndGetLivreur() {
		panier.setLivreur(Livreur.DHL);
		assertEquals(Livreur.DHL, panier.getLivreur());
	}
}