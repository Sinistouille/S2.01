package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import data.FormatHelper;
import data.JSONHelper;
import data.LocHelper;
import modele.Article;
import modele.GenerationFromages;
import modele.Livreur;
import modele.Panier;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FenetrePanier extends JFrame {

	private static final long serialVersionUID = 1L;
	private final JButton boutonValiderPanier;
	private final JButton boutonEnregistrerPanier;
	private final JComboBox<Livreur> comboBoxLivreurs;
	private final JButton boutonChargerPanier;
	private JPanel contentPane;

	private int nombreElement = 14;
	private JButton boutonViderPanier;
	private JPanel contenuPanier;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public FenetrePanier(Panier panier) {
		this.setTitle("Ô fromage - Panier");
		System.out.println(panier);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBounds(100, 100, 600, 500);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		this.setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panelPrincipal = new JPanel();
		this.contentPane.add(panelPrincipal, BorderLayout.SOUTH);
		panelPrincipal.setLayout(new GridLayout(0, 3, 5, 0));

		JPanel panelLivraison = new JPanel();
		panelLivraison.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));
		panelPrincipal.add(panelLivraison);
		panelLivraison.setLayout(new GridLayout(3, 1, 0, 0));
		//combobox with all livreur
		comboBoxLivreurs = new JComboBox<>(Livreur.values());
		comboBoxLivreurs.setSelectedIndex(0);
		comboBoxLivreurs.setBackground(new Color(255, 255, 255));

		JLabel labelTransporteur = new JLabel("Transporteur");
		panelLivraison.add(labelTransporteur);
		panelLivraison.add(comboBoxLivreurs);

		JLabel labelFraisDePortOffert = new JLabel("Frais de port offert dès 120€ d'achat");
		panelLivraison.add(labelFraisDePortOffert);

		JPanel panelDetailsPrix = new JPanel();
		panelPrincipal.add(panelDetailsPrix);
		panelDetailsPrix.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));
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

		JLabel labelFraisDePort = new JLabel("Frais de port");
		panelDetailsPrix.add(labelFraisDePort);

		JLabel labelPrixFraisDePort = new JLabel("Gratuit");
		labelPrixFraisDePort.setHorizontalAlignment(SwingConstants.TRAILING);
		panelDetailsPrix.add(labelPrixFraisDePort);

		JLabel TotalTTC = new JLabel("Total TTC");
		panelDetailsPrix.add(TotalTTC);

		JLabel PrixTTC = new JLabel();
		PrixTTC.setHorizontalAlignment(SwingConstants.TRAILING);
		panelDetailsPrix.add(PrixTTC);

		JPanel panelPrixBoutons = new JPanel();
		panelPrincipal.add(panelPrixBoutons);
		panelPrixBoutons.setLayout(new GridLayout(2, 1, 0, 5));

		boutonValiderPanier = new JButton("Valider le panier");
		panelPrixBoutons.add(boutonValiderPanier);
		boutonValiderPanier.setBackground(new Color(255, 255, 255));
		boutonViderPanier = new JButton("Vider le panier");
		panelPrixBoutons.add(boutonViderPanier);
		boutonViderPanier.setBackground(new Color(255, 128, 128));


		JPanel panelTitre = new JPanel();
		this.contentPane.add(panelTitre, BorderLayout.NORTH);
		panelTitre.setLayout(new BorderLayout(0, 0));

		JLabel Recapitulatif = new JLabel("Récapitulatif");
		Recapitulatif.setHorizontalAlignment(SwingConstants.CENTER);
		panelTitre.add(Recapitulatif);

		boutonEnregistrerPanier = new JButton("Enregistrer le Panier");
		boutonEnregistrerPanier.setBackground(new Color(192, 192, 192));
		panelTitre.add(boutonEnregistrerPanier, BorderLayout.EAST);

		boutonChargerPanier = new JButton("Charger le panier");
		boutonChargerPanier.setBackground(new Color(192, 192, 192));
		panelTitre.add(boutonChargerPanier, BorderLayout.WEST);

		JScrollPane scrollPane = new JScrollPane();
		this.contentPane.add(scrollPane, BorderLayout.CENTER);

		contenuPanier = new JPanel();
		scrollPane.setViewportView(contenuPanier);
		contenuPanier.setLayout(new GridLayout(this.nombreElement, 0, 0, 0));
		this.afficherTableau(panier, contenuPanier);
		this.affichePrix(panier, labelMontantTVA, labelPrixHT, PrixTTC, labelPrixFraisDePort, (Livreur) comboBoxLivreurs.getSelectedItem());
		addListeners(panier,labelMontantTVA,labelPrixHT,PrixTTC, labelPrixFraisDePort);

	}

	private void addListeners(Panier panier, JLabel labelMontantTVA, JLabel labelPrixHT, JLabel PrixTTC, JLabel labelFraisDePort) {
		boutonViderPanier.addActionListener(e -> {
			panier.viderPanier();
			Livreur livreur = (Livreur) comboBoxLivreurs.getSelectedItem();
			this.raffraichirFenetre(panier, labelMontantTVA, labelPrixHT, PrixTTC, labelFraisDePort, livreur);
		});

		boutonEnregistrerPanier.addActionListener(e -> panier.savePanier());

		boutonChargerPanier.addActionListener(e -> {
			panier.loadPanier(GenerationFromages.générationBaseFromages());
			Livreur livreur = (Livreur) comboBoxLivreurs.getSelectedItem();
			this.raffraichirFenetre(panier, labelMontantTVA, labelPrixHT, PrixTTC, labelFraisDePort, livreur);
		});
		comboBoxLivreurs.addActionListener(e -> {
			Livreur livreur = (Livreur) comboBoxLivreurs.getSelectedItem();
			this.raffraichirFenetre(panier, labelMontantTVA, labelPrixHT, PrixTTC, labelFraisDePort, livreur);
		});
		boutonValiderPanier.addActionListener(e -> {
			Livreur livreur = (Livreur) comboBoxLivreurs.getSelectedItem();
			panier.setLivreur(livreur);
			new FenetreInformations(panier).setVisible(true);
		});
		boutonEnregistrerPanier.addActionListener(e -> {
			panier.savePanier();
		});
	}

	private void raffraichirFenetre(Panier panier, JLabel labelMontantTVA, JLabel labelPrixHT, JLabel PrixTTC, JLabel labelFraisDePort, Livreur livreur) {
		affichePrix(panier, labelMontantTVA, labelPrixHT, PrixTTC, labelFraisDePort, livreur);
		afficherTableau(panier, contenuPanier);
	}

	private void affichePrix(Panier panier, JLabel labelMontantTVA, JLabel labelPrixHT, JLabel PrixTTC, JLabel labelFraisDePort, Livreur livreur) {
		labelMontantTVA.setText(panier.montantTVA() + "€");
		PrixTTC.setText(panier.getPrixTTC() + "€");
		labelPrixHT.setText(panier.getPrixHT() + "€");
		labelFraisDePort.setText(FormatHelper.DecimalFormat(livreur.getPrixlivraisons(panier.getPrixHT())) + "€");
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
		LocHelper.displayImage(labelImageFromage, article.getFromage(),100,100);

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

		JLabel prixTotalLigneFromage = new JLabel(article.getPrixHT() * quantite + "€");
		nouvelleLigneFromage.add(prixTotalLigneFromage);

		JLabel SupprimerElement = new JLabel("New label");
		nouvelleLigneFromage.add(SupprimerElement);
	}
}
