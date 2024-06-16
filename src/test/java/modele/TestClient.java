package modele;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestClient {

    private Client client;

    @Before
    public void setUp() {
        client = new Client("Doe", "John");
    }

    @Test
    public void testConstructor() {
        assertEquals("Doe", client.getNom());
        assertEquals("John", client.getPrenom());
        assertEquals("", client.getAdresse());
        assertEquals("", client.getVille());
        assertEquals("", client.getCodePostal());
        assertEquals("", client.getEmail());
        assertEquals("", client.getCountry());
        assertEquals("", client.getPhone());
        assertEquals("", client.getMobilePhone());
        assertEquals("", client.getComplementAdresse());
    }

    @Test
    public void testSetAndGetAdresse() {
        client.setAdresse("123 Main St");
        assertEquals("123 Main St", client.getAdresse());
    }

    @Test
    public void testSetAndGetVille() {
        client.setVille("Springfield");
        assertEquals("Springfield", client.getVille());
    }

    @Test
    public void testSetAndGetCodePostal() {
        client.setCodePostal("12345");
        assertEquals("12345", client.getCodePostal());
    }

    @Test
    public void testSetAndGetEmail() {
        client.setEmail("john.doe@example.com");
        assertEquals("john.doe@example.com", client.getEmail());
    }

    @Test
    public void testSetAndGetCountry() {
        client.setCountry("USA");
        assertEquals("USA", client.getCountry());
    }

    @Test
    public void testSetAndGetPhone() {
        client.setPhone("555-1234");
        assertEquals("555-1234", client.getPhone());
    }

    @Test
    public void testSetAndGetMobilePhone() {
        client.setMobilePhone("555-5678");
        assertEquals("555-5678", client.getMobilePhone());
    }

    @Test
    public void testSetAndGetComplementAdresse() {
        client.setComplementAdresse("Apt 4B");
        assertEquals("Apt 4B", client.getComplementAdresse());
    }

    @Test
    public void testToString() {
        client.setAdresse("123 Main St");
        client.setComplementAdresse("Apt 4B");
        client.setPhone("555-1234");
        client.setMobilePhone("555-5678");
        client.setEmail("john.doe@example.com");
        client.setVille("Springfield");
        client.setCodePostal("12345");
        client.setCountry("USA");

        String expectedString = "Prénom : John Nom : Doe\n" +
                "Téléphone : \n fixe : 555-1234\n portable : 555-5678\n" +
                "Email : john.doe@example.com\n" +
                "Adresse : 123 Main St Apt 4B\n" +
                "Ville : Springfield Code Postal : 12345 Pays : USA\n";

        assertEquals(expectedString, client.toString());
    }
}
