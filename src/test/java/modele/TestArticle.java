package modele;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestArticle {

	FromageALaCoupe f = new FromageALaCoupe("f");
	Article a = new Article(this.f, "ff", 10f);
	Article b = new Article(this.f, "a", 2f);

	@Test
	public void TestSetQuantitéStock() {
		this.a.setQuantitéEnStock(12);
		assertEquals(this.a.getQuantitéEnStock(), 12);
	}

	@Test
	public void TestPréempterQuantité() {
		this.a.setQuantitéEnStock(12);
		this.a.préempterQuantité(4);
		assertEquals(this.a.getQuantitéEnStock(), 8);
	}

	@Test
	public void TestRendreQuantité() {
		this.a.setQuantitéEnStock(12);
		this.a.rendreQuantité(4);
		assertEquals(this.a.getQuantitéEnStock(), 16);
	}

	@Test
	public void TestQuantitéPanier() {
		this.a.setQuantitéEnStock(12);
		this.a.préempterQuantité(4);
		assertEquals(this.a.getQuantitéPanier(), 4);
	}

	@Test
	public void testToStringPrix() {
		assertEquals(this.a.toStringPrix(), "10.0 €");
	}

}
