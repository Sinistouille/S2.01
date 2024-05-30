package modele;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;

import java.io.FileNotFoundException;
import java.util.Map;

public class GenerationFacture {

    public void genererFacture(Panier panier, String fichierPDF) {
        try {
            PdfWriter writer = new PdfWriter(fichierPDF);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            // Ajouter les informations de la facture
            document.add(new Paragraph("Facture"));
            document.add(new Paragraph(" ")); // Ligne vide

            // Ajouter les articles du panier
            Table table = new Table(UnitValue.createPercentArray(new float[]{4, 1, 1, 1}));
            table.addHeaderCell("Fromage");
            table.addHeaderCell("Quantité");
            table.addHeaderCell("Prix Unitaire");
            table.addHeaderCell("Total");

            float totalGeneral = 0;

            for (Map.Entry<Article, Integer> entry : panier.getPanier().entrySet()) {
                Article article = entry.getKey();
                int quantite = entry.getValue();
                float total = quantite * article.getPrixTTC();
                totalGeneral += total;

                table.addCell(article.getFromage().getDésignation());
                table.addCell(String.valueOf(quantite));
                table.addCell(String.valueOf(article.getPrixTTC()));
                table.addCell(String.valueOf(total));
            }

            document.add(table);

            document.add(new Paragraph(" ")); // Ligne vide

            // Ajouter le total général
            document.add(new Paragraph("Total Général: " + totalGeneral + " €"));

            document.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public static void main (String args[] ) {
    	
    }
}
