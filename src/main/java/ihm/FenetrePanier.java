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
	private JPanel contentPane;

	private JButton boutonViderPanier;
	private JPanel contenuPanier;
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
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		// Panel Principal Initialization
		JPanel panelPrincipal = new JPanel();
		contentPane.add(panelPrincipal, BorderLayout.SOUTH);
		panelPrincipal.setLayout(new GridLayout(0, 3, 5, 0));

		// Panel Livraison Initialization
		JPanel panelLivraison = new JPanel();
		panelLivraison.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));
		panelLivraison.setLayout(new GridLayout(3, 1, 0, 0));
		panelPrincipal.add(panelLivraison);

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
		panelPrincipal.add(panelDetailsPrix);

		JLabel SousTotalHT = new JLabel("Sous total HT");
		labelPrixHT = new JLabel();
		JLabel labelTauxTVA = new JLabel("TVA (20%)");
		labelMontantTVA = new JLabel();
		labelFraisDePort = new JLabel("Frais de port");
		labelPrixFraisDePort = new JLabel("Gratuit");
		JLabel TotalTTC = new JLabel("Total TTC");
		labelPrixTTC = new JLabel();

		labelPrixHT.setHorizontalAlignment(SwingConstants.TRAILING);
		labelMontantTVA.setHorizontalAlignment(SwingConstants.TRAILING);
		labelPrixFraisDePort.setHorizontalAlignment(SwingConstants.TRAILING);
		labelPrixTTC.setHorizontalAlignment(SwingConstants.TRAILING);

		panelDetailsPrix.add(SousTotalHT);
		panelDetailsPrix.add(labelPrixHT);
		panelDetailsPrix.add(labelTauxTVA);
		panelDetailsPrix.add(labelMontantTVA);
		panelDetailsPrix.add(labelFraisDePort);
		panelDetailsPrix.add(labelPrixFraisDePort);
		panelDetailsPrix.add(TotalTTC);
		panelDetailsPrix.add(labelPrixTTC);

		// Panel Prix Boutons Initialization
		JPanel panelPrixBoutons = new JPanel();
		panelPrixBoutons.setLayout(new GridLayout(2, 1, 0, 5));
		panelPrincipal.add(panelPrixBoutons);

		boutonValiderPanier = new JButton("Valider le panier");
		boutonValiderPanier.setBackground(Color.WHITE);
		boutonViderPanier = new JButton("Vider le panier");
		boutonViderPanier.setBackground(new Color(255, 128, 128));

		panelPrixBoutons.add(boutonValiderPanier);
		panelPrixBoutons.add(boutonViderPanier);

		// Panel Titre Initialization
		JPanel panelTitre = new JPanel();
		panelTitre.setBorder(new EmptyBorder(0, 0, 5, 0));
		contentPane.add(panelTitre, BorderLayout.NORTH);
		panelTitre.setLayout(new BorderLayout(0, 0));

		JLabel Recapitulatif = new JLabel("Récapitulatif");
		Recapitulatif.setHorizontalAlignment(SwingConstants.CENTER);
		panelTitre.add(Recapitulatif, BorderLayout.CENTER);

		boutonEnregistrerPanier = new JButton("Enregistrer le Panier");
		boutonEnregistrerPanier.setBackground(new Color(192, 192, 192));
		panelTitre.add(boutonEnregistrerPanier, BorderLayout.EAST);

		boutonChargerPanier = new JButton("Charger le panier");
		boutonChargerPanier.setBackground(new Color(192, 192, 192));
		panelTitre.add(boutonChargerPanier, BorderLayout.WEST);

		// Scroll Pane Initialization
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		contenuPanier = new JPanel();
		scrollPane.setViewportView(contenuPanier);
		int cols = Math.max(panier.getPanier().size(), 3);
		contenuPanier.setLayout(new GridLayout(cols, 1, 0, 0));

		//Fonctions implémentées
		this.afficherTableau(panier, contenuPanier);
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
		afficherTableau(panier, contenuPanier);
		fen.updateLivraisonGratuite();
	}

	private void affichePrix() {
		labelMontantTVA.setText(panier.montantTVA() + "€");
		labelPrixTTC.setText(panier.getPrixTTC() + "€");
		labelPrixHT.setText(panier.getPrixHT() + "€");
		Livreur livreur = (Livreur) comboBoxLivreurs.getSelectedItem();
		labelPrixFraisDePort.setText(FormatHelper.DecimalFormat(livreur.getPrixlivraisons(panier.getPrixHT())) + "€");
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
		JPanel panelNouvelleLigneFromage = new JPanel();
		tablePanier.add(panelNouvelleLigneFromage);
		panelNouvelleLigneFromage.setLayout(new GridLayout(0, 5, 0, 0));
		panelNouvelleLigneFromage.setSize(100, 100);
		//image du fromage
		JLabel labelImageFromage = new JLabel();
		panelNouvelleLigneFromage.add(labelImageFromage);
		labelImageFromage.setIcon(FileHelper.setIcon(FileHelper.fromageLoc(article.getFromage().getNomImage() + ".jpg"),100,100));

		JPanel panelTexteElement = new JPanel();
		panelNouvelleLigneFromage.add(panelTexteElement);
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

				if (q <= 0) {
					panier.ajouterArticle(article, q-quantitePanier);
					JOptionPane.showMessageDialog(this, "L'article a été supprimé du panier", "Article supprimé", JOptionPane.INFORMATION_MESSAGE);
					System.out.println("SYSTEM.OUT : suppression de l'article du Panier");

				}else if(article.getQuantitéEnStock() >= q - quantitePanier){
					textFieldQuantiteElement.setText(String.valueOf(q));
					panier.ajouterArticle(article, q - quantitePanier);
					JOptionPane.showMessageDialog(this, "Quantité bien modifié à " + q, "Quantité modifiée", JOptionPane.INFORMATION_MESSAGE);
					System.out.println("SYSTEM.OUT : modification de la quantite du Panier en : " + (q - quantitePanier));
				} else {
					textFieldQuantiteElement.setText(String.valueOf(quantitePanier));
					JOptionPane.showMessageDialog(this, "Il n'y a que " + (quantitePanier + quantiteStock) + " unités disponibles", "Quantité en stock insuffisante", JOptionPane.ERROR_MESSAGE);
					System.out.println("SYSTEM.OUT : Modif invalide");
				}
				this.raffraichirFenetre();
			}
			catch (NumberFormatException ex)
			{
				textFieldQuantiteElement.setText(String.valueOf(FenetrePanier.this.panier.getPanier().get(article)));
			}
		});
		panelNouvelleLigneFromage.add(textFieldQuantiteElement);

		JLabel labelPrixTotalLigneFromage = new JLabel(article.getPrixHT() * quantite + "€");
		panelNouvelleLigneFromage.add(labelPrixTotalLigneFromage);

		JLabel labelSupprimerElement = new JLabel("New label");
		panelNouvelleLigneFromage.add(labelSupprimerElement);
	}
}
