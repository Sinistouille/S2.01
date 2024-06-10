package data;

import modele.Article;
import modele.Panier;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;

public class JSONHelper {
    public static void saveJSON(JSONObject json, String chemin){
        try {
            FileWriter f = new FileWriter(ImageHelper.dataLoc(chemin));
            f.write(json.toString(4));
            //f.flush();
            f.close();
        }
        catch (IOException e){
            System.out.println("Erreur lors de la sauvegarde du fichier JSON");
        }
    }

    public static JSONObject savePanier(Panier panier){
        JSONObject json = new JSONObject();
        String tva = Double.toString(panier.getPrix() * 0.2);
        BigDecimal bd = new BigDecimal(tva);
        json.put("PrixTotal", panier.getPrix());
        JSONObject jsonListeArticles = new JSONObject();
        for(Article a : panier.getPanier().keySet()){
            JSONObject jsonArticle = new JSONObject();
            jsonArticle.put("Fromage",a.getFromage().getDésignation());
            jsonArticle.put("TypeVente",a.getClé());
            jsonArticle.put("Prix",a.getPrixTTC());
            jsonArticle.put("Quantité",panier.getPanier().get(a));
            jsonArticle.put("TypeLait", a.getFromage().getTypeFromage());
            jsonListeArticles.put(String.valueOf(a.getCode()),jsonArticle);
        }
        json.put("Articles",jsonListeArticles);
        json.put("Panier", panier.getUUID());
        return json;
    }
}
