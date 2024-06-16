package ihm;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import data.FormatHelper;
import data.FileHelper;
import modele.*;

public class FenetrePanier extends JFrame {

	private static final long serialVersionUID = 1L;
	private final JButton boutonValiderPanier;
	private final JButton boutonEnregistrerPanier;
	private final JComboBox<Livreur> comboBoxLivreurs;
	private final JButton boutonChargerPanier;
	private final FenetreSelection fen;
	private final JLabel labelMontantTVA;
	private final JLabel labelPrixFraisDePort;
	private final JLabel labelPrixHT;
	private final JLabel labelPrixTTC;
	private final JLabel labelFraisDePort;
	private Panier panier;
	private JPanel panelPrincipal;

	private JButton boutonViderPanier;
	private JPanel panelContenuPanier;
	private Fromages listeFromages;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public FenetrePanier(Panier panier, FenetreSelection fenetreSelection, Fromages listeFromages) {
		fen = fenetreSelection;
		this.listeFromages = listeFromages;
		this.panier = panier;
		this.setTitle("Ô fromage - Panier");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		//Centrage de la fenêtre
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)dimension.getHeight();
		int width  = (int)dimension.getWidth();
		this.setBounds((width-800)/2, (height-500)/2, 800, 500);

		// Content Pane Initialization
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelPrincipal.setLayout(new BorderLayout(0, 0));
		setContentPane(panelPrincipal);

		// Panel Principal Initialization
		JPanel panelDetails = new JPanel();
		panelPrincipal.add(panelDetails, BorderLayout.SOUTH);
		panelDetails.setLayout(new GridLayout(0, 3, 5, 0));

		// Panel Livraison Initialization
		JPanel panelLivraison = new JPanel();
		panelLivraison.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));
		panelLivraison.setLayout(new GridLayout(3, 1, 0, 0));
		panelDetails.add(panelLivraison);

		comboBoxLivreurs = new JComboBox<>(Livreur.values());
		comboBoxLivreurs.setSelectedIndex(0);
		comboBoxLivreurs.setBackground(Color.WHITE);

		JLabel labelTransporteur = new JLabel("Transporteur");
		JLabel labelFraisDePortOffert = new JLabel("Frais de port offert dès 120€ d'achat");

		panelLivraison.add(labelTransporteur);
		panelLivraison.add(comboBoxLivreurs);
		panelLivraison.add(labelFraisDePortOffert);

		// Panel Details Prix Initialization
		JPanel panelDetailsPrix = new JPanel();
		panelDetailsPrix.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));
		panelDetailsPrix.setLayout(new GridLayout(4, 2, 0, 0));
		panelDetails.add(panelDetailsPrix);

		JLabel labelTotalHT = new JLabel("Sous total HT");
		labelPrixHT = new JLabel();
		JLabel labelTauxTVA = new JLabel("TVA (20%)");
		labelMontantTVA = new JLabel();
		labelFraisDePort = new JLabel("Frais de port");
		labelPrixFraisDePort = new JLabel("Gratuit");
		JLabel labelTotalTTC = new JLabel("Total TTC");
		labelPrixTTC = new JLabel();

		labelPrixHT.setHorizontalAlignment(SwingConstants.TRAILING);
		labelMontantTVA.setHorizontalAlignment(SwingConstants.TRAILING);
		labelPrixFraisDePort.setHorizontalAlignment(SwingConstants.TRAILING);
		labelPrixTTC.setHorizontalAlignment(SwingConstants.TRAILING);

		panelDetailsPrix.add(labelTotalHT);
		panelDetailsPrix.add(labelPrixHT);
		panelDetailsPrix.add(labelTauxTVA);
		panelDetailsPrix.add(labelMontantTVA);
		panelDetailsPrix.add(labelFraisDePort);
		panelDetailsPrix.add(labelPrixFraisDePort);
		panelDetailsPrix.add(labelTotalTTC);
		panelDetailsPrix.add(labelPrixTTC);

		// Panel Prix Boutons Initialization
		JPanel panelPanierBoutons = new JPanel();
		panelPanierBoutons.setLayout(new GridLayout(2, 1, 0, 5));
		panelDetails.add(panelPanierBoutons);

		boutonValiderPanier = new JButton("Valider le panier");
		boutonValiderPanier.setBackground(Color.WHITE);
		boutonViderPanier = new JButton("Vider le panier");
		boutonViderPanier.setBackground(new Color(255, 128, 128));

		panelPanierBoutons.add(boutonValiderPanier);
		panelPanierBoutons.add(boutonViderPanier);

		// Panel Titre Initialization
		JPanel panelTitre = new JPanel();
		panelTitre.setBorder(new EmptyBorder(0, 0, 5, 0));
		panelPrincipal.add(panelTitre, BorderLayout.NORTH);
		panelTitre.setLayout(new BorderLayout(0, 0));

		JLabel labelRecapitulatif = new JLabel("Récapitulatif");
		labelRecapitulatif.setHorizontalAlignment(SwingConstants.CENTER);
		panelTitre.add(labelRecapitulatif, BorderLayout.CENTER);

		boutonEnregistrerPanier = new JButton("Enregistrer le Panier");
		boutonEnregistrerPanier.setBackground(new Color(192, 192, 192));
		panelTitre.add(boutonEnregistrerPanier, BorderLayout.EAST);

		boutonChargerPanier = new JButton("Charger le panier");
		boutonChargerPanier.setBackground(new Color(192, 192, 192));
		panelTitre.add(boutonChargerPanier, BorderLayout.WEST);

		// Scroll Pane Initialization
		JScrollPane scrollPaneArticles = new JScrollPane();
		panelPrincipal.add(scrollPaneArticles, BorderLayout.CENTER);

		panelContenuPanier = new JPanel();
		scrollPaneArticles.setViewportView(panelContenuPanier);
		panelContenuPanier.setLayout(new GridLayout(Math.max(panier.getPanier().size(), 3), 1, 0, 0));

		//Fonctions implémentées
		this.afficherTableau(panier);
		this.affichePrix();
		this.addListeners();
		this.setIcons();

	}

	private void setIcons() {
		boutonViderPanier.setIcon(FileHelper.setIcon(FileHelper.logoLoc("vider.png"), 35, 35));
	}

	private void addListeners() {
		boutonViderPanier.addActionListener(e -> {
			if(JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment vider le panier ?", "Confirmation", JOptionPane.YES_NO_OPTION) == 0){
				panier.viderPanier();
				this.raffraichirFenetre();
			}
		});

		boutonEnregistrerPanier.addActionListener(e -> panier.savePanier());

		boutonChargerPanier.addActionListener(e -> {
			panier.loadPanier(listeFromages);
			this.raffraichirFenetre();
			//display a jdialog with "panier chargé"
			JOptionPane.showMessageDialog(this, "Panier chargé");
		});
		comboBoxLivreurs.addActionListener(e -> {
			this.raffraichirFenetre();
		});
		boutonValiderPanier.addActionListener(e -> {
			Livreur livreur = (Livreur) comboBoxLivreurs.getSelectedItem();
			panier.setLivreur(livreur);
			new FenetreInformations(panier).setVisible(true);
		});
		boutonEnregistrerPanier.addActionListener(e -> {
			panier.savePanier();
			JOptionPane.showMessageDialog(this, "Panier enregistré");
		});
	}

	private void raffraichirFenetre() {
		affichePrix();
		panelContenuPanier.setLayout(new GridLayout(Math.max(panier.getPanier().size(), 3), 1, 0, 0));
		afficherTableau(panier);
		fen.updateLivraisonGratuite();
	}

	private void affichePrix() {
		labelMontantTVA.setText(panier.montantTVA() + "€");
		labelPrixTTC.setText(panier.getPrixTTC() + "€");
		labelPrixHT.setText(panier.getPrixHT() + "€");
		Livreur livreur = (Livreur) comboBoxLivreurs.getSelectedItem();
		labelPrixFraisDePort.setText(FormatHelper.DecimalFormat(livreur.getPrixlivraisons(panier.getPrixHT())) + "€");
	}

	private void afficherTableau(Panier panier) {
		panelContenuPanier.removeAll();
		panelContenuPanier.repaint();
		for (Article a : panier.getPanier().keySet()) {
			this.ligneTableau(a, panier.getPanier().get(a));
		}
	}

	private void ligneTableau(Article article, int quantite) {
		//création de la ligne
		JPanel panelNouvelleLigneArticle = new JPanel();
		panelContenuPanier.add(panelNouvelleLigneArticle);
		panelNouvelleLigneArticle.setLayout(new GridLayout(0, 5, 0, 0));
		panelNouvelleLigneArticle.setSize(100, 100);
		//image du fromage
		JLabel labelImageFromage = new JLabel();
		panelNouvelleLigneArticle.add(labelImageFromage);
		labelImageFromage.setIcon(FileHelper.setIcon(FileHelper.fromageLoc(article.getFromage().getNomImage() + ".jpg"),100,100));

		JPanel panelTexteElement = new JPanel();
		panelNouvelleLigneArticle.add(panelTexteElement);
		panelTexteElement.setLayout(new GridLayout(3, 0, 0, 0));
		panelTexteElement.setSize(100, 100);

		JLabel labelTitreFromage = new JLabel(article.getFromage().getDésignation() + ", " + article.getFromage().getTypeFromage().getTypeDeLait());
		panelTexteElement.add(labelTitreFromage);

		JLabel labelPoidsElement = new JLabel(article.getClé());
		panelTexteElement.add(labelPoidsElement);

		JLabel labelPrixUnitElement = new JLabel(article.toStringPrix());
		panelTexteElement.add(labelPrixUnitElement);

		JTextField textFieldQuantiteElement = new JTextField();
		textFieldQuantiteElement.setText(String.valueOf(quantite));
		textFieldQuantiteElement.setBorder(null);
		textFieldQuantiteElement.setBackground(null);
		textFieldQuantiteElement.addActionListener(e -> {
			String field = textFieldQuantiteElement.getText();
			try
			{
				int q = Integer.parseInt(field);
				int quantitePanier = panier.getPanier().get(article);
				int quantiteStock = article.getQuantitéEnStock();

				System.out.println("SYSTEM.OUT : quantitePanier : " + quantitePanier + "; QuantiteEnStock : " + quantiteStock + "; variation : " + (q-quantitePanier));

				if (q <= 0) {//suppression de l'article du panier car quantité nulle ou négative
					panier.ajouterArticle(article, q-quantitePanier);
					JOptionPane.showMessageDialog(this, "L'article a été supprimé du panier", "Article supprimé", JOptionPane.INFORMATION_MESSAGE);
					//System.out.println("SYSTEM.OUT : suppression de l'article du Panier");

				}else if(article.getQuantitéEnStock() >= q - quantitePanier){ //modification de la quantité de l'article dans le panier car la quantité est inférieure ou égale au stock disponible
					textFieldQuantiteElement.setText(String.valueOf(q));
					panier.ajouterArticle(article, q - quantitePanier);
					JOptionPane.showMessageDialog(this, "Quantité bien modifié à " + q, "Quantité modifiée", JOptionPane.INFORMATION_MESSAGE);
					//System.out.println("SYSTEM.OUT : modification de la quantite du Panier en : " + (q - quantitePanier));
				} else { //quantité supérieure à la quantité en stock
					textFieldQuantiteElement.setText(String.valueOf(quantitePanier));
					JOptionPane.showMessageDialog(this, "Il n'y a que " + (quantitePanier + quantiteStock) + " unités disponibles", "Quantité en stock insuffisante", JOptionPane.ERROR_MESSAGE);
					//System.out.println("SYSTEM.OUT : Modif invalide");
				}
				this.raffraichirFenetre();
			}
			catch (NumberFormatException ex)
			{
				textFieldQuantiteElement.setText(String.valueOf(FenetrePanier.this.panier.getPanier().get(article)));
			}
		});
		panelNouvelleLigneArticle.add(textFieldQuantiteElement);

		JLabel labelPrixTotalLigneArticle = new JLabel(article.getPrixHT() * quantite + "€");
		panelNouvelleLigneArticle.add(labelPrixTotalLigneArticle);

		JPanel panelBouton = new JPanel();
		panelNouvelleLigneArticle.add(panelBouton);
		panelBouton.setLayout(new GridBagLayout());
		JButton boutonSupprimerLigne = new JButton("Supprimer la ligne");
		boutonSupprimerLigne.addActionListener(e -> {
			panier.retirerArticle(article);
			this.raffraichirFenetre();
		});
		boutonSupprimerLigne.setBackground(new Color(255, 120, 120));
		//text color white
		boutonSupprimerLigne.setForeground(Color.WHITE);
		boutonSupprimerLigne.setIcon(FileHelper.setIcon(FileHelper.logoLoc("supprimer.png"), 20, 20));
		panelBouton.add(boutonSupprimerLigne);
	}
}
