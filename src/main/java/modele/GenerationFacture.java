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

    public void genererFacture(Panier panier, Livreur livreur, Contact contact) {
        String dest = "facture.pdf";
        try {
            PdfWriter writer = new PdfWriter(dest);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            // Ajouter les informations de contact
            document.add(new Paragraph("Facture"));
            document.add(new Paragraph("Nom: " + contact.getNom()));
            document.add(new Paragraph("Adresse: " + contact.getAdresse()));
            document.add(new Paragraph("Téléphone: " + contact.getTelephone()));
            document.add(new Paragraph("Email: " + contact.getEmail()));

            document.add(new Paragraph(" ")); // Ligne vide

            // Ajouter les articles du panier
            Table table = new Table(UnitValue.createPercentArray(new float[]{4, 1, 1, 1}));
            table.addHeaderCell("Fromage");
            table.addHeaderCell("Quantité");
            table.addHeaderCell("Prix Unitaire");
            table.addHeaderCell("Total");

            double totalGeneral = 0;

            for (Map.Entry<Fromage, Integer> entry : panier.getArticles().entrySet()) {
                Fromage fromage = entry.getKey();
                int quantite = entry.getValue();
                double total = quantite * fromage.getPrix();
                totalGeneral += total;

                table.addCell(fromage.getNom());
                table.addCell(String.valueOf(quantite));
                table.addCell(String.valueOf(fromage.getPrix()));
                table.addCell(String.valueOf(total));
            }

            document.add(table);

            document.add(new Paragraph(" ")); // Ligne vide

            // Ajouter les frais de livraison et le total général
            document.add(new Paragraph("Frais de livraison: " + livreur.getFraisLivraison()));
            totalGeneral += livreur.getFraisLivraison();
            document.add(new Paragraph("Total Général: " + totalGeneral));

            document.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
