package modele;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TabAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import data.FileHelper;

import javax.swing.text.StyleConstants;
import java.awt.*;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class GenerationFacture {

    public static void genererFacture(Panier panier, Client client) {
        try {
            Locale locale = new Locale("fr", "FR");
            DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, locale);
            String date = dateFormat.format(new Date());
            System.out.print(date);
            Document doc = new Document(new PdfDocument(new PdfWriter(FileHelper.dataLoc("facture.pdf"))));
            Table tableHeader = new Table(new float[]{500, 500});
            try {
                tableHeader.addCell(new Cell().add(new Image(ImageDataFactory.create(FileHelper.accueilLoc("vitrine.jpg")))
                                .scaleAbsolute(150, 150))
                        .setHorizontalAlignment(HorizontalAlignment.LEFT)
                        .setBorder(Border.NO_BORDER));

            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }


            tableHeader.addCell(new Cell().add(divSociete())
                    .setTextAlignment(TextAlignment.RIGHT)
                    .setBorder(Border.NO_BORDER));
            tableHeader.setBorder(Border.NO_BORDER);
            doc.add(tableHeader);
            doc.add(infoClient(client));
            doc.add(new Paragraph("Facture n°" + Math.round(Math.pow(Math.random()*100,panier.getUUID())) + " du " + date).setMarginTop(15f));
            Div div = new Div().addStyle(new Style().setFontSize(15).setBold()).setMarginTop(20f);
            div.add(new Paragraph("Liste des articles : "));
            doc.add(div);
            Table table = new Table(5, true);
            table.addHeaderCell(new Cell().add(new Paragraph("Désignation")
                            .addStyle(new Style().setBold()))
                    .setBorder(Border.NO_BORDER)
                    .setFontColor(new DeviceRgb(255, 255, 255))
                    .setBackgroundColor(new DeviceRgb(0, 0, 0)));
            table.addHeaderCell(new Cell().add(new Paragraph("Article")
                    .addStyle(new Style().setBold()))
                    .setBorder(Border.NO_BORDER)
                    .setFontColor(new DeviceRgb(255, 255, 255))
                    .setBackgroundColor(new DeviceRgb(0, 0, 0)));
            table.addHeaderCell(new Cell().add(new Paragraph("Prix Unitaire")
                    .addStyle(new Style().setBold()))
                    .setBorder(Border.NO_BORDER)
                    .setFontColor(new DeviceRgb(255, 255, 255))
                    .setBackgroundColor(new DeviceRgb(0, 0, 0)));
            table.addHeaderCell(new Cell().add(new Paragraph("Quantité")
                    .addStyle(new Style().setBold()))
                    .setBorder(Border.NO_BORDER)
                    .setFontColor(new DeviceRgb(255, 255, 255))
                    .setBackgroundColor(new DeviceRgb(0, 0, 0)));
            table.addHeaderCell(new Cell().add(new Paragraph("Prix Total")
                            .addStyle(new Style().setBold()))
                    .setBorder(Border.NO_BORDER)
                    .setFontColor(new DeviceRgb(255, 255, 255))
                    .setBackgroundColor(new DeviceRgb(0, 0, 0)));

            for(Article a : panier.getPanier().keySet()) {
                addLigne(panier, a, table);
            }
            doc.add(table.setBorder(Border.NO_BORDER));
            Div divPrix = new Div().setMarginTop(100f);

            divPrix.add(new Paragraph("Prix HT : " + panier.getPrixHT()).setFixedLeading(8f));
            divPrix.add(new Paragraph("Montant TVA : " + panier.getPrixTTC()).setFixedLeading(8f));
            divPrix.add(new Paragraph("Prix TTC : " + panier.getPrixTTC()).setFixedLeading(8f));
            doc.add(divPrix);
            doc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Div divSociete() {
        Div societe = new Div();
        societe.add(new Paragraph("La Fromagerie").setFixedLeading(8f));
        societe.add(new Paragraph("12 rue du Chapelet").setFixedLeading(8f));
        societe.add(new Paragraph("BP 12345").setFixedLeading(8f));
        societe.add(new Paragraph("Agen").setFixedLeading(8f));
        societe.add(new Paragraph("47001").setFixedLeading(8f));
        societe.add(new Paragraph("France").setFixedLeading(8f));
        societe.add(new Paragraph("05 53 47 11 11").setFixedLeading(8f));
        societe.add(new Paragraph("fromages@lait.com").setFixedLeading(8f));
        return societe;
    }

    private static void addLigne(Panier panier, Article a, Table table) {
        table.addCell(new Cell().add(
                new Paragraph(a.getFromage().getDésignation())
        ).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(
                new Paragraph(a.getClé())
        ).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(
                new Paragraph(a.getPrixHT() + "€")
        ).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(
                new Paragraph(String.valueOf(panier.getPanier().get(a)))
        ).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(
                new Paragraph(a.getPrixHT() * panier.getPanier().get(a) + "€")
        ).setBorder(Border.NO_BORDER));
    }

    public static Div infoClient(Client client){
        Div divClient = new Div();
        divClient = divClient.addStyle(new Style().setFontSize(12));
        divClient.add(new Paragraph(client.getNom() + " " + client.getPrenom()).setFixedLeading(8));
        divClient.add(new Paragraph(client.getAdresse() + " " + client.getComplementAdresse()).setFixedLeading(8));
        divClient.add(new Paragraph(client.getCodePostal() + " " + client.getVille()).setFixedLeading(8));
        divClient.add(new Paragraph(client.getPays()).setFixedLeading(8));
        divClient.add(new Paragraph(client.getMobilePhone()).setFixedLeading(8));
        divClient.add(new Paragraph(client.getPhone()).setFixedLeading(8));
        divClient.add(new Paragraph(client.getEmail()).setFixedLeading(8));
        return divClient;
    }

    public static void testFacture(){
        Client client = new Client("Doe", "John");
        client.setAdresse("123 Main St");
        client.setVille("Springfield");
        client.setCodePostal("12345");
        client.setEmail("johndoe@gmail.com");
        client.setCountry("France");
        client.setMobilePhone("06 06 06 06 06");
        client.setPhone("05 05 05 05 05");
        client.setComplementAdresse("Batiment A");
        Panier panier = new Panier();
        Fromages fromages = GenerationFromages.loadFromages("fromages.json");
        panier.ajouterArticle(fromages.getFromages().get(1).getArticles().get(1), 1);
        panier.ajouterArticle(fromages.getFromages().get(2).getArticles().get(1), 1);
        panier.ajouterArticle(fromages.getFromages().get(3).getArticles().get(1), 1);
        genererFacture(panier, client);
    }
    public static void main(String[] args) {
        testFacture();
    }
}
