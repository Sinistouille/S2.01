package modele;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.TabAlignment;
import data.LocHelper;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

public class GenerationFacture {

    public static void genererFacture(Panier panier, Client client) {
        try {
            Locale locale = new Locale("fr", "FR");
            DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, locale);
            String date = dateFormat.format(new Date());
            System.out.print(date);
            Document doc = new Document(new PdfDocument(new PdfWriter(LocHelper.dataLoc("facture.pdf"))));
            Paragraph p = new Paragraph();
            try {
                p.add(new Image(ImageDataFactory.create(LocHelper.accueilLoc("vitrine"))).scaleAbsolute(150, 150));
                p.add(new Tab());
                p.addTabStops(new TabStop(1000, TabAlignment.RIGHT));
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
            p.add(new Paragraph("Commande du " + date + "\n"));
            p.add(new Paragraph("Société : La Fromagerie"));
            doc.add(p);
            Div div = new Div();
            div.addStyle(new Style().setFontSize(10));
            div.add(new Paragraph("Liste des articles : "));
            for(Article a : panier.getPanier().keySet()) {
                div.add(new Paragraph(a.getFromage().getDésignation() + "," + a.getClé() + "    Prix Unitaire " + a.getPrixHT() + "€" + "    Quantité " + panier.getPanier().get(a) + "    Total " + a.getPrixHT() * panier.getPanier().get(a) + "€"));
            }
            doc.add(div);
            doc.add(new Paragraph("Prix HT : " + panier.getPrixHT()));
            doc.add(new Paragraph("Montant TVA : " + panier.getPrixTTC()));
            doc.add(new Paragraph("Prix TTC : " + panier.getPrixTTC()));
            doc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
