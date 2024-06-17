package data;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import javax.swing.*;
import java.awt.*;

public class TestFileHelper {

	@Test
	public void testAccueilDoc() {
		assertEquals(FileHelper.accueilLoc("vitrine"), "src/main/resources/images/accueil/vitrine");
	}

	@Test
	public void testResourceLoc() {
		assertEquals(FileHelper.resourceLoc("fromages"), "src/main/resources/fromages");
	}

	@Test
	public void testProjetLoc() {
		assertEquals(FileHelper.projetLoc("java"), "src/main/java");
	}

	@Test
	public void testDataLoc() {
		assertEquals(FileHelper.dataLoc("data.json"), "src/main/data/data.json");
	}

	@Test
	public void testFromageLoc() {
		assertEquals(FileHelper.fromageLoc("fromage"), "src/main/resources/images/fromages/hauteur200/fromage");
	}
	@Test
	public void testLogoLoc(){
		assertEquals(FileHelper.logoLoc("logo"), "src/main/resources/images/logo/logo");

	}

}
