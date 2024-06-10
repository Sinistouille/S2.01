package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import data.FormatHelper;
import data.ImageHelper;
import data.JSONHelper;
import modele.Article;
import modele.Livreur;
import modele.Panier;

public class FenetrePanier extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private int nombreElement = 14;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public FenetrePanier(Panier panier) {
		this.setTitle("Ô fromage - Panier");
		ListeFromage.afficherPanier(panier);
		JSONHelper.saveJSON(JSONHelper.savePanier(panier), "panier.json");
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setBounds(100, 100, 600, 500);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		this.setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		this.contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(0, 3, 0, 0));

		JPanel panelLivraison = new JPanel();
		panelLivraison.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));
		panel.add(panelLivraison);
		panelLivraison.setLayout(new GridLayout(3, 1, 0, 0));
		//combobox with all livreur
		JComboBox<Livreur> comboBoxLivreurs = new JComboBox<>(Livreur.values());
		comboBoxLivreurs.addActionListener(e -> {
			Livreur livreur = (Livreur) comboBoxLivreurs.getSelectedItem();
			this.reAfficheLivraison(livreur);
		});

		JLabel labelTransporteur = new JLabel("Transporteur");
		panelLivraison.add(labelTransporteur);
		panelLivraison.add(comboBoxLivreurs);

		JLabel labelFraisDePort = new JLabel("Frais de port offert dès 120€ d'achat");
		panelLivraison.add(labelFraisDePort);

		JPanel panelMilieu = new JPanel();
		panel.add(panelMilieu);

		JPanel panelPrixBoutons = new JPanel();
		panel.add(panelPrixBoutons);
		panelPrixBoutons.setLayout(new GridLayout(2, 0, 0, 0));

		JPanel panelDetailsPrix = new JPanel();
		panelDetailsPrix.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));
		panelPrixBoutons.add(panelDetailsPrix);
		panelDetailsPrix.setLayout(new GridLayout(4, 2, 0, 0));

		JLabel SousTotalHT = new JLabel("Sous total HT");
		panelDetailsPrix.add(SousTotalHT);

		JLabel labelPrixHT = new JLabel();
		labelPrixHT.setHorizontalAlignment(SwingConstants.TRAILING);
		panelDetailsPrix.add(labelPrixHT);

		JLabel labelTauxTVA = new JLabel("TVA (20%)");
		panelDetailsPrix.add(labelTauxTVA);

		JLabel labelMontantTVA = new JLabel();
		labelMontantTVA.setHorizontalAlignment(SwingConstants.TRAILING);
		panelDetailsPrix.add(labelMontantTVA);

		JLabel FraisDePort = new JLabel("Frais de port");
		panelDetailsPrix.add(FraisDePort);

		JLabel PrixFraisDePort = new JLabel("Gratuit");
		PrixFraisDePort.setHorizontalAlignment(SwingConstants.TRAILING);
		panelDetailsPrix.add(PrixFraisDePort);

		JLabel TotalTTC = new JLabel("Total TTC");
		panelDetailsPrix.add(TotalTTC);

		JLabel PrixTTC = new JLabel();
		PrixTTC.setHorizontalAlignment(SwingConstants.TRAILING);
		panelDetailsPrix.add(PrixTTC);

		JPanel panelBoutonsPanier = new JPanel();
		panelPrixBoutons.add(panelBoutonsPanier);
		panelBoutonsPanier.setLayout(new GridLayout(0, 2, 0, 0));

		JButton boutonValiderPanier = new JButton("Valider le panier");
		panelBoutonsPanier.add(boutonValiderPanier);
		boutonValiderPanier.addActionListener(e -> {
			new Livraison(panier).setVisible(true);
		});

		JButton boutonViderPanier = new JButton("Vider le panier");
		panelBoutonsPanier.add(boutonViderPanier);

		JPanel panel_1 = new JPanel();
		this.contentPane.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BorderLayout(0, 0));

		JLabel Recapitulatif = new JLabel("Récapitulatif");
		Recapitulatif.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(Recapitulatif);

		JScrollPane scrollPane = new JScrollPane();
		this.contentPane.add(scrollPane, BorderLayout.CENTER);

		JPanel ContenuPanier = new JPanel();
		scrollPane.setViewportView(ContenuPanier);
		ContenuPanier.setLayout(new GridLayout(this.nombreElement, 0, 0, 0));
		afficherTableau(panier, ContenuPanier);
		boutonViderPanier.addActionListener(e -> {
			panier.viderPanier();
			affichePrix(panier, labelMontantTVA, labelPrixHT, PrixTTC);
			this.afficherTableau(panier, ContenuPanier);
		});
		affichePrix(panier, labelMontantTVA, labelPrixHT, PrixTTC);
	}
	private void reAfficheLivraison(Livreur livreur) {
		
	}
	
	private void addListeners() {
		
	}

	private void affichePrix(Panier panier, JLabel labelMontantTVA, JLabel labelPrixHT, JLabel PrixTTC) {
		labelMontantTVA.setText(FormatHelper.df.format(panier.getPrix()*0.2) + "€");
		PrixTTC.setText(panier.getPrix() + FormatHelper.df.format(panier.getPrix()*0.2) + "€");
		labelPrixHT.setText(panier.getPrix() + "€");
	}

	private void afficherTableau(Panier panier, JPanel contenuPanier) {
		contenuPanier.removeAll();
		contenuPanier.repaint();
		for (Article a : panier.getPanier().keySet()) {
			this.ligneTableau(contenuPanier, a, panier.getPanier().get(a));
		}
	}
	private void ligneTableau(JPanel tablePanier, Article article, int quantite) {
		//création de la ligne
		JPanel nouvelleLigneFromage = new JPanel();
		tablePanier.add(nouvelleLigneFromage);
		nouvelleLigneFromage.setLayout(new GridLayout(0, 5, 0, 0));

		//image du fromage
		JLabel labelImageFromage = new JLabel();
		nouvelleLigneFromage.add(labelImageFromage);
		ImageHelper.displayImage(labelImageFromage, article.getFromage(),100,100);

		JPanel TexteElement = new JPanel();
		nouvelleLigneFromage.add(TexteElement);
		TexteElement.setLayout(new GridLayout(3, 0, 0, 0));

		JLabel titreFromage = new JLabel(article.getFromage().getDésignation() + ", " + article.getFromage().getTypeFromage().getTypeDeLait());
		TexteElement.add(titreFromage);

		JLabel PoidsElement = new JLabel(article.getClé());
		TexteElement.add(PoidsElement);

		JLabel PrixUnitElement = new JLabel(article.toStringPrix());
		TexteElement.add(PrixUnitElement);

		JTextField QuantiteElement = new JTextField();
		QuantiteElement.setText(String.valueOf(quantite));
		QuantiteElement.setBorder(null);
		QuantiteElement.setBackground(null);
		nouvelleLigneFromage.add(QuantiteElement);

		JLabel prixTotalLigneFromage = new JLabel(article.getPrixTTC() * quantite + "€");
		nouvelleLigneFromage.add(prixTotalLigneFromage);

		JLabel SupprimerElement = new JLabel("New label");
		nouvelleLigneFromage.add(SupprimerElement);
	}

}
