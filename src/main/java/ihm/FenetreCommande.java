package ihm;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import data.FileHelper;
import data.JSONHelper;
import modele.Client;
import modele.GenerationFacture;
import modele.Panier;
import org.json.JSONObject;

import java.awt.print.PrinterException;

public class FenetreCommande extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea textAreaCommande;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
            try {
                FenetreCommande frame = new FenetreCommande(new Panier(), new Client("Prank", "Ratio"));
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
	}

	/**
	 * Create the frame.
	 */
	public FenetreCommande(Panier panier, Client client) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)dimension.getHeight();
		int width  = (int)dimension.getWidth();
		setBounds((width-550)/2, (height-400)/2, 550, 400);

		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BorderLayout(0, 0));
		setContentPane(panelPrincipal);

		textAreaCommande = new JTextArea(panier.toString() + "\n" + client.toString());
		panelPrincipal.add(textAreaCommande, BorderLayout.CENTER);
		textAreaCommande.setColumns(10);

		JPanel panelBoutons = new JPanel();
		panelPrincipal.add(panelBoutons, BorderLayout.SOUTH);
		panelBoutons.setLayout(new GridLayout(0, 2, 0, 0));

		JButton boutonImprimer = new JButton("Imprimer");
		boutonImprimer.setBackground(new Color(255, 255, 255));
		boutonImprimer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panelBoutons.add(boutonImprimer);

		JButton boutonPdf = new JButton("PDF");
		boutonPdf.setBackground(new Color(255, 255, 255));
		panelBoutons.add(boutonPdf);

		JPanel panelTitre = new JPanel();
		panelPrincipal.add(panelTitre, BorderLayout.NORTH);
		panelTitre.setBackground(new Color(255, 255, 255));

		JLabel labelTitre = new JLabel("Récapitulatif de votre commande");
		panelTitre.add(labelTitre);
		this.addListeners(panier, client, boutonImprimer, boutonPdf);
	}

	private void addListeners(Panier panier, Client client, JButton boutonImprimer, JButton boutonPdf) {
		boutonImprimer.addActionListener(e -> {
            try {
                textAreaCommande.print();
            } catch (PrinterException ex) {
                throw new RuntimeException(ex);
            }
        });
		boutonPdf.addActionListener(e -> {
			GenerationFacture.genererFacture(panier, client);
		});
	}
}