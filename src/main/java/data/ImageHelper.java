package data;

import modele.Fromage;

import javax.swing.*;
import java.awt.*;

public class ImageHelper {
    public static String fromageLoc(String nomFromage){
        return resourceLoc("images/fromages/hauteur200/" + nomFromage + ".jpg");
    }
    public static void displayImage(JLabel labelImage, Fromage f, int width, int height) {
        String img_path = fromageLoc(f.getNomImage());
        Image image = new ImageIcon(img_path).getImage();
        image = image.getScaledInstance(width,height, Image.SCALE_DEFAULT);
        labelImage.setIcon(new ImageIcon(image));
    }
    public static void displayImage(JLabel labelImage, Fromage f) {
        displayImage(labelImage, f, 200, 200);
    }
    public static String accueilLoc(String nomImage){
        return resourceLoc("images/accueil/" + nomImage + ".jpg");
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
