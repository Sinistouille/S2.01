package modele;

import data.FormatHelper;
import data.JSONHelper;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

public class Panier {

    private Map<Article, Integer> panier;
    private int UUID;
    private static int nbInstances = 0;
    /*
     * Constructeur de la classe Panier
     */

    public Panier() {
        this.panier = new TreeMap<>();
        nbInstances++;
        this.UUID = (int) Math.pow(2, nbInstances) + nbInstances;
    }

    /*
     * ajouterArticle : ajoute au panier l'article a de la quantité q
     */
    public void ajouterArticle(Article a, int q) {
        int quantite = this.panier.getOrDefault(a, 0);
        if (q < 0) {
            a.rendreQuantité(q);
        } else {
            a.préempterQuantité(q);
        }
        if (quantite + q > 0) {
            this.panier.put(a, quantite + q);
            System.out.println("SYSTEM.OUT : Bien ajouté au panier : " + a.getFromage().getDésignation() + " " + a.getClé() + " quantité : " + this.panier.get(a));
        } else if(quantite + q < 0){
            this.retirerArticle(a);
        }
        JSONHelper.saveJSON(this.saveJSON(),"panier.json");
    }

    /*
     * retirerArticle : retire un article a de la liste this.panier
     */
    public void retirerArticle(Article a) {
        this.panier.remove(a);
        JSONHelper.saveJSON(this.saveJSON(),"panier.json");
    }

    /*
     * getPanier : retourne le panier, this.panier
     */
    public Map<Article, Integer> getPanier() {
        return this.panier;
    }

    /*
     * getPrix : retourne le prix du panier parcourt la liste des articles et
     * multilplie le prix de chaque par la quantité
     */
    public float getPrix() {
        float prix = 0;
        for (Article a : this.panier.keySet()) {
            prix += a.getPrixTTC() * this.panier.get(a);
        }
        return Float.parseFloat(FormatHelper.DecimalFormat(prix));
    }

    /*
     * viderPanier : vide le panier
     */
    public void viderPanier() {
        for (Article a : this.panier.keySet()) {
            a.rendreQuantité(this.panier.get(a));
        }
        this.panier.clear();
        JSONHelper.saveJSON(this.saveJSON(),"panier.json");
    }

    /*
     * getUUID : retourne l'identifiant du panier
     */
    public int getUUID() {
        return this.UUID;
    }

    /*
        * toString : affiche le panier
        * @return String
     */
    public String toString(){
        String affichage = "Panier numéro " + this.getUUID() + "\n";
        affichage += "Prix total du Panier : " + this.getPrix() + "\n";
        affichage += "Nombre d'articles : " + this.getPanier().size() + "\n";
        for (Article a : this.panier.keySet()) {
                affichage += a.getFromage().getDésignation() + " " + a.getClé() + " quantité : " + this.panier.get(a) + " Prix Unitaire : " + a.getPrixTTC() + " Total : " + FormatHelper.DecimalFormat(a.getPrixTTC() * this.panier.get(a)) + "\n";
        }
        return affichage;
    }

    public JSONObject saveJSON(){
        JSONObject json = new JSONObject();
        String tva = Double.toString(this.getPrix() / 1.2);
        json.put("PrixTotal", this.getPrix());
        JSONObject jsonListeArticles = new JSONObject();
        for(Article a : this.getPanier().keySet()){
            JSONObject jsonArticle = new JSONObject();
            jsonArticle.put("Fromage",a.getFromage().getDésignation());
            jsonArticle.put("TypeVente",a.getClé());
            jsonArticle.put("Prix",a.getPrixTTC());
            jsonArticle.put("Quantité",this.getPanier().get(a));
            jsonArticle.put("TypeLait", a.getFromage().getTypeFromage());
            jsonListeArticles.put(String.valueOf(a.getCode()),jsonArticle);
        }
        json.put("Articles",jsonListeArticles);
        json.put("Panier", this.getUUID());
        return json;
    }
}