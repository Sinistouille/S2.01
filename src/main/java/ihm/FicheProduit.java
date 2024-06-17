package ihm;

import java.awt.*;

import javax.swing.*;

import data.FormatHelper;
import modele.Article;
import modele.Fromage;
import data.FileHelper;
import modele.Panier;
import javax.swing.border.EmptyBorder;

public class FicheProduit extends JFrame {
	private ButtonGroup groupePoids;
	private JLabel labelPrixUnitaire;
	private JComboBox<Integer> comboBoxQuantite = new JComboBox<>();
	private JTextArea textAreaDescription;
	private JLabel labelPrixTotal;
	private JButton boutonAjouterPanier;
	private JButton boutonAnnuler;
	private JLabel labelImageFromage;
	private JComboBox<Article> comboBoxArticle;

	private String nomFromage = "Nom";
	private double prixUnitaire = 5.20;

	public FicheProduit(Panier panier, Fromage fromage, FenetreSelection fenetreSelection) {
		this.setTitle("Ô fromage - Fiche produit");
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)dimension.getHeight();
		int width  = (int)dimension.getWidth();
		this.setBounds((width-600)/2, (height-500)/2, 600, 500);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Ajout des panneaux au frame
		JPanel panelPrincipal = new JPanel(new BorderLayout());
		this.setContentPane(panelPrincipal);
		// Panneau de sélection de l'article
		JPanel panelPoids = new JPanel(new GridLayout(0, 1));

		// Panneau de sélection du prix et de la quantité
		JPanel panelPrix = new JPanel();
		panelPrix.setBorder(new EmptyBorder(0, 5, 0, 0));
		panelPrix.setLayout(new GridLayout(4, 2, 0, 0));

		this.labelPrixUnitaire = new JLabel("Prix unitaire : " + "€");
		panelPrix.add(labelPrixUnitaire);

		this.comboBoxArticle = new JComboBox<>(fromage.getArticles());
		comboBoxArticle.setBackground(new Color(255, 255, 255));
		panelPrix.add(comboBoxArticle);
		this.comboBoxArticle.setSelectedItem(fromage.getArticles().get(0));

		JLabel labelQuantite = new JLabel("Quantité ");
		panelPrix.add(labelQuantite);

		comboBoxQuantite.setBackground(new Color(255, 255, 255));
		panelPrix.add(this.comboBoxQuantite);

		// Panneau du milieu
		JPanel panelArticle = new JPanel(new GridLayout(3, 1));
		panelArticle.add(panelPoids);
		panelArticle.add(panelPrix);
		panelPrincipal.add(panelArticle, BorderLayout.WEST);

		// Panneau principal combinant panneauAction et panneauImage
		JPanel panelAjout = new JPanel();
		panelAjout.setBorder(new EmptyBorder(0, 5, 5, 0));
		panelArticle.add(panelAjout);
		panelAjout.setLayout(new GridLayout(3, 0, 0, 5));

		this.labelPrixTotal = new JLabel();
		panelAjout.add(labelPrixTotal);

		this.boutonAjouterPanier = new JButton("Ajouter au panier");
		panelAjout.add(boutonAjouterPanier);
		boutonAjouterPanier.setBackground(new Color(255, 255, 255));

		this.boutonAnnuler = new JButton("Annuler");
		panelAjout.add(boutonAnnuler);
		boutonAnnuler.setBackground(new Color(255, 128, 128));

		// Panneau d'image
		JPanel panelFromage = new JPanel();
		panelPrincipal.add(panelFromage, BorderLayout.CENTER);
		panelFromage.setLayout(new BorderLayout(0, 0));

		JPanel panelInformationsFromage = new JPanel();
		panelFromage.add(panelInformationsFromage, BorderLayout.CENTER);
		panelInformationsFromage.setLayout(new GridLayout(0, 1, 0, 0));

		this.labelImageFromage = new JLabel(new ImageIcon("lien"));
		panelInformationsFromage.add(labelImageFromage);

		// Panneau pour la description
		JPanel panelDescription = new JPanel();
		panelInformationsFromage.add(panelDescription);
		panelDescription.setBorder(new EmptyBorder(0, 10, 10, 0));
		panelDescription.setLayout(new GridLayout(2, 1, 0, 0));

		JLabel labelDescription = new JLabel("Description");
		labelDescription.setHorizontalAlignment(SwingConstants.CENTER);
		panelDescription.add(labelDescription);

		this.textAreaDescription = new JTextArea(fromage.getDescription());
		textAreaDescription.setTabSize(40);
		panelDescription.add(textAreaDescription);
		this.textAreaDescription.setLineWrap(true);
		this.textAreaDescription.setWrapStyleWord(true);
		this.textAreaDescription.setEditable(false);

		// Panneau pour la désignation du fromage
		JPanel panelDesignationFromage = new JPanel();
		panelFromage.add(panelDesignationFromage, BorderLayout.NORTH);
		panelDesignationFromage.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel labelPoids = new JLabel(fromage.getDésignation());
		labelPoids.setHorizontalAlignment(SwingConstants.CENTER);
		labelPoids.setFont(new Font("Arial", Font.BOLD, 24));
		panelDesignationFromage.add(labelPoids);

		JLabel poidsLabel = new JLabel(fromage.getArticles().get(0).getClé());
		poidsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		poidsLabel.setFont(new Font("Arial", Font.ITALIC, 18));
		panelDesignationFromage.add(poidsLabel);

		this.updateModelQuantiteComboBox();
		this.updatePrixUnitaire();
		this.setIconFromage(fromage);
		this.addListeners(panier, fromage, fenetreSelection);
		// Mise à jour du prix total lorsque la quantité ou le poids change
	}

	private void setIconFromage(Fromage fromage) {
		labelImageFromage.setIcon(new ImageIcon(FileHelper.fromageLoc(fromage.getNomImage() + ".jpg")));
	}

	private void addListeners(Panier panier, Fromage fromage, FenetreSelection fenetreSelection) {
		this.boutonAjouterPanier.addActionListener(e -> {
			// Action lors du clic sur le bouton 'Ajouter le panier'
			//article sélectionné par la combobx
			Article article = fromage.getArticles().get(comboBoxArticle.getSelectedIndex());
			//quantité sélectionnée par la combobox
			int quantite = comboBoxQuantite.getSelectedIndex();
			//ajout de l'article au panier
			panier.ajouterArticle(article, quantite);
			//mise à jour de la comboBox de quantité
			updateModelQuantiteComboBox();
			//mise à jour des frais de livraisons
			fenetreSelection.updateLivraisonGratuite();

		});

		this.comboBoxQuantite.addActionListener(e -> this.updateTotalPrix(fromage));

		this.comboBoxArticle.addActionListener (e -> {
			updatePrixUnitaire();
			updateModelQuantiteComboBox();
			updateTotalPrix(fromage);
		});

		this.boutonAnnuler.addActionListener(e -> {
			// Action lors du clic sur le bouton 'Annuler'
			dispose();
		});
	}

	private void updatePrixUnitaire() {
		Article a = (Article) comboBoxArticle.getSelectedItem();
		labelPrixUnitaire.setText("Prix unitaire : " + a.getPrixHT() + "€");
	}

	private String calculerTotalPrix(Fromage fromage) {
		Article a = fromage.getArticles().get(comboBoxArticle.getSelectedIndex());
		int quantite = (int) comboBoxQuantite.getSelectedItem();
		return FormatHelper.DecimalFormat(a.getPrixHT() * quantite);
	}

	private void updateTotalPrix(Fromage fromage) {
		this.labelPrixTotal.setText("Total : " + this.calculerTotalPrix(fromage) + "€");
	}

	private void updateModelQuantiteComboBox(){
		this.comboBoxQuantite.setModel(new DefaultComboBoxModel<>(((Article) this.comboBoxArticle.getSelectedItem()).tabQuantite()));
	}

}
