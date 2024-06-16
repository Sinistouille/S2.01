package modele;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
			(expected = IllegalArgumentException.class)
	public void TestPréempterQuantitéValeurSupérieure() {
		this.a.setQuantitéEnStock(12);
		this.a.préempterQuantité(14);
	}

	@Test
			(expected = IllegalArgumentException.class)
	public void TestPréempterQuantitéValeurNegative() {
		this.a.setQuantitéEnStock(12);
		this.a.préempterQuantité(-1);
	}

	@Test
			(expected = IllegalArgumentException.class)
	public void TestRendreQuantiteValeurNegative() {
		this.a.rendreQuantité(-4);
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
		assertEquals(4, this.a.getQuantitePanier());
	}

	@Test
	public void testToStringPrix() {
		assertEquals(this.a.toStringPrix(), "10.0 €");
	}

	@Test
	public void testTabQuantite(){
		this.a.setQuantitéEnStock(12);
		Integer[] t = new Integer[13];
		for (int i = 0; i < 13; i++) {
			t[i] = i;
		}
        assertEquals(this.a.tabQuantite(), t);
	}

	@Test
	public void testToStringAvecStock(){
		this.a.setQuantitéEnStock(12);
		assertEquals(this.a.toStringAvecStock(), "f, ff, Prix TTC : 10.0 €, Quantité en stock : 12");
	}

	@Test
	public void testToString(){
		assertEquals(this.a.toString(), "f, ff, Prix TTC : 10.0 €");

	}

	@Test
	public void testGetCode(){
		int c = (f.getDésignation() + this.a.getClé()).hashCode();
		assertEquals(this.a.getCode(), c);
	}

	@Test
	public void testGetFromage(){
		assertEquals(this.a.getFromage(), f);
	}

	@Test
	public void testGetClé(){
		assertEquals(this.a.getClé(), "ff");
	}
}
