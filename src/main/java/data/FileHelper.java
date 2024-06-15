package data;

import javax.swing.*;
import java.awt.*;

public class FileHelper {
    public static String fromageLoc(String nomFromage){
        return resourceLoc("images/fromages/hauteur200/" + nomFromage);
    }
    public static ImageIcon setIcon(String chemin, int width, int height) {
        Image image = new ImageIcon(chemin).getImage();
        image = image.getScaledInstance(width,height, Image.SCALE_DEFAULT);
        return new ImageIcon(image);
    }

    public static String accueilLoc(String nomImage){
        return resourceLoc("images/accueil/" + nomImage);
    }

    public static String logoLoc(String nomImage){
        return resourceLoc("images/logo/" + nomImage);
    }

    public static String resourceLoc(String chemin){
        return projetLoc("resources/" + chemin);
    }
    public static String projetLoc(String chemin){
        return "src/main/" + chemin;
    }
    public static String dataLoc(String chemin){
        return projetLoc("data/" + chemin);
    }

}
