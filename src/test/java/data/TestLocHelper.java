package data;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestLocHelper {

	private LocHelper a;

	@Test
	public void testAcceuilDoc() {
		assertEquals(this.a.accueilLoc("vitrine"), "src/main/resources/images/accueil/vitrine.jpg");
	}

	@Test
	public void testResourceLoc() {
		assertEquals(this.a.resourceLoc("fromages"), "src/main/resources/fromages");
	}

	@Test
	public void testProjetLoc() {
		assertEquals(this.a.projetLoc("java"), "src/main/java");
	}

	@Test
	public void testDataLoc() {
		assertEquals(this.a.dataLoc("data.json"), "src/main/data/data.json");
	}

}
