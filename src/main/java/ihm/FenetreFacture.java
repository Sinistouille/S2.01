/*
package ihm;

import ihm.FenetreFacture;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.rendering.ImageType;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FenetreFacture extends JFrame {
    */
/**
	 * 
	 *//*

	private static final long serialVersionUID = 1L;
	private String pdfPath;

    public FenetreFacture(String pdfPath) {
        this.pdfPath = pdfPath;
        setTitle("Visualisation de la facture PDF");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        try {
            PDDocument document = PDDocument.load(new File(pdfPath));
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            int pageCount = document.getNumberOfPages();

            JPanel Facture = new JPanel();
            Facture.setLayout(new BoxLayout(Facture, BoxLayout.Y_AXIS));

            for (int i = 0; i < pageCount; i++) {
                BufferedImage image = pdfRenderer.renderImageWithDPI(i, 300, ImageType.RGB);
                ImageIcon icon = new ImageIcon(image);
                JLabel label = new JLabel(icon);
                Facture.add(label);
            }

            JScrollPane scrollFacture = new JScrollPane(Facture);
            getContentPane().add(scrollFacture);

            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        	FenetreFacture viewer = new FenetreFacture("facture.pdf");
            viewer.setVisible(true);
        });
    }
}
*/
