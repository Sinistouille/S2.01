package modele;

public class Client {

    private String nom;
    private String prenom;
    private String adresse;
    private String ville;
    private String codePostal;
    private String email;
    private String country;
    private String phone;
    private String mobilePhone;
    private String complementAdresse;

    public Client(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = "";
        this.ville = "";
        this.codePostal = "";
        this.email = "";
        this.country = "";
        this.phone = "";
        this.mobilePhone = "";
        this.complementAdresse = "";
    }


    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public String getComplementAdresse() {
        return complementAdresse;
    }

    public void setComplementAdresse(String complementAdresse) {
        this.complementAdresse = complementAdresse;
    }
    public String toString(){
        String client = "Prénom : " + this.prenom + " Nom : " + this.nom + "\n";
        client += "Téléphone : \n fixe : " + this.phone + "\n portable : " + this.mobilePhone + "\n";
        client += "Email : " + this.email + "\n";
        client += "Adresse : " + this.adresse + " " + complementAdresse + "\n";
        client += "Ville : " + this.ville + " Code Postal : " +this.codePostal + " Pays : " + this.country + "\n";
        return client;
    }
}