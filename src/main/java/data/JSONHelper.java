package data;

import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONHelper {
    public static void saveJSON(JSONObject json, String chemin){
        try {
            FileWriter f = new FileWriter(FileHelper.dataLoc(chemin));
            f.write(json.toString(4));
            //f.flush();
            f.close();
        }
        catch (IOException e){
            System.out.println("Erreur lors de la sauvegarde du fichier JSON");
        }
    }
    public static JSONObject loadJSON(String chemin){
        JSONObject j;
        try {
            String content = new String(Files.readAllBytes(Paths.get(FileHelper.dataLoc(chemin))));
            j = new JSONObject(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return j;
    }
}
