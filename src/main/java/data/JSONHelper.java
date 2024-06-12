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
            FileWriter f = new FileWriter(LocHelper.dataLoc(chemin));
            f.write(json.toString(4));
            //f.flush();
            f.close();
        }
        catch (IOException e){
            System.out.println("Erreur lors de la sauvegarde du fichier JSON");
        }
    }
}
