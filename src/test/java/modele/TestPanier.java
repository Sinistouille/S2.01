package modele;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestPanier {

	Panier test = new Panier();
	FromageALaCoupe f = new FromageALaCoupe("f");
	Article a = new Article(this.f, "ff", 10f);
	Article b = new Article(this.f, "a", 2f);

	@Test
	public void testAjouterArticle() {
		this.test.ajouterArticle(this.a, 1);
		int taille = 0;
		for (Article a : this.test.getPanier().keySet()) {
			taille += 1;
		}
		assertEquals(taille, 1);
	}

	@Test
	public void TestRetirerArticle() {
		this.test.ajouterArticle(this.a, 1);
		this.test.ajouterArticle(this.b, 1);
		this.test.retirerArticle(this.b);
		int taille = 0;
		for (Article a : this.test.getPanier().keySet()) {
			taille += 1;
		}
		assertEquals(taille, 1);
	}
}