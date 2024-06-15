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
	private JLabel prixUnitaireLabel;
	private JComboBox<Integer> quantiteComboBox = new JComboBox<>();
	private JTextArea descriptionArea;
	private JLabel totalPrixLabel;
	private JButton ajouterPanierButton;
	private JButton annulerButton;
	private JLabel labelImageFromage;
	private JComboBox<Article> articleComboBox;

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
		JPanel panneauPrincipal = new JPanel(new BorderLayout());
		this.setContentPane(panneauPrincipal);
		// Panneau de sélection de l'article
		JPanel panneauPoids = new JPanel(new GridLayout(0, 1));

		// Panneau de sélection du prix et de la quantité
		JPanel panneauPrix = new JPanel();
		panneauPrix.setBorder(new EmptyBorder(0, 5, 0, 0));
		panneauPrix.setLayout(new GridLayout(4, 2, 0, 0));

		this.prixUnitaireLabel = new JLabel("Prix unitaire : " + "€");
		panneauPrix.add(prixUnitaireLabel);

		this.articleComboBox = new JComboBox<>(fromage.getArticles());
		articleComboBox.setBackground(new Color(255, 255, 255));
		panneauPrix.add(articleComboBox);
		this.articleComboBox.setSelectedItem(fromage.getArticles().get(0));

		JLabel label = new JLabel("Quantité ");
		panneauPrix.add(label);

		quantiteComboBox.setBackground(new Color(255, 255, 255));
		panneauPrix.add(this.quantiteComboBox);

		// Panneau du milieu
		JPanel panneauMilieu = new JPanel(new GridLayout(3, 1));
		panneauMilieu.add(panneauPoids);
		panneauMilieu.add(panneauPrix);
		panneauPrincipal.add(panneauMilieu, BorderLayout.WEST);

		// Panneau principal combinant panneauAction et panneauImage
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(0, 5, 5, 0));
		panneauMilieu.add(panel);
		panel.setLayout(new GridLayout(3, 0, 0, 5));

		this.totalPrixLabel = new JLabel();
		panel.add(totalPrixLabel);

		this.ajouterPanierButton = new JButton("Ajouter au panier");
		panel.add(ajouterPanierButton);
		ajouterPanierButton.setBackground(new Color(255, 255, 255));

		this.annulerButton = new JButton("Annuler");
		panel.add(annulerButton);
		annulerButton.setBackground(new Color(255, 128, 128));

		// Panneau d'image
		JPanel panneauImage = new JPanel();
		panneauPrincipal.add(panneauImage, BorderLayout.CENTER);
		panneauImage.setLayout(new BorderLayout(0, 0));

		JPanel panelInformationsFromage = new JPanel();
		panneauImage.add(panelInformationsFromage, BorderLayout.CENTER);
		panelInformationsFromage.setLayout(new GridLayout(0, 1, 0, 0));

		this.labelImageFromage = new JLabel(new ImageIcon("lien"));
		panelInformationsFromage.add(labelImageFromage);

		// Panneau pour la description
		JPanel panelDescription = new JPanel();
		panelInformationsFromage.add(panelDescription);
		panelDescription.setBorder(new EmptyBorder(0, 10, 10, 0));
		panelDescription.setLayout(new GridLayout(2, 1, 0, 0));

		JLabel lblNewLabel = new JLabel("Description");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panelDescription.add(lblNewLabel);

		this.descriptionArea = new JTextArea(fromage.getDescription());
		descriptionArea.setTabSize(40);
		panelDescription.add(descriptionArea);
		this.descriptionArea.setLineWrap(true);
		this.descriptionArea.setWrapStyleWord(true);
		this.descriptionArea.setEditable(false);

		// Panneau pour la désignation du fromage
		JPanel panelDesignationFromage = new JPanel();
		panneauImage.add(panelDesignationFromage, BorderLayout.NORTH);
		panelDesignationFromage.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel nomFromageLabel = new JLabel(fromage.getDésignation());
		nomFromageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nomFromageLabel.setFont(new Font("Arial", Font.BOLD, 24));
		panelDesignationFromage.add(nomFromageLabel);

		JLabel poidsLabel = new JLabel(fromage.getArticles().get(0).getClé());
		poidsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		poidsLabel.setFont(new Font("Arial", Font.ITALIC, 18));
		panelDesignationFromage.add(poidsLabel);

		this.updateModelQuantiteComboBox();
		updatePrixUnitaire();
		this.setIconFromage(fromage);

		addListeners(panier, fromage, fenetreSelection);
		// Mise à jour du prix total lorsque la quantité ou le poids change
	}

	private void setIconFromage(Fromage fromage) {
		labelImageFromage.setIcon(new ImageIcon(FileHelper.fromageLoc(fromage.getNomImage() + ".jpg")));
	}

	private void addListeners(Panier panier, Fromage fromage, FenetreSelection fenetreSelection) {
		this.ajouterPanierButton.addActionListener(e -> {
			// Action lors du clic sur le bouton 'Ajouter le panier'
			//article sélectionné par la combobx
			Article article = fromage.getArticles().get(articleComboBox.getSelectedIndex());
			//quantité sélectionnée par la combobox
			int quantite = quantiteComboBox.getSelectedIndex();
			//ajout de l'article au panier
			panier.ajouterArticle(article, quantite);
			//mise à jour de la comboBox de quantité
			updateModelQuantiteComboBox();
			//mise à jour des frais de livraisons
			fenetreSelection.updateLivraisonGratuite();

		});

		this.quantiteComboBox.addActionListener(e -> this.updateTotalPrix(fromage));

		this.articleComboBox.addActionListener (e -> {
			updatePrixUnitaire();
			updateModelQuantiteComboBox();
			updateTotalPrix(fromage);
		});

		this.annulerButton.addActionListener(e -> {
			// Action lors du clic sur le bouton 'Annuler'
			dispose();
		});
	}

	private void updatePrixUnitaire() {
		Article a = (Article) articleComboBox.getSelectedItem();
		prixUnitaireLabel.setText("Prix unitaire : " + a.getPrixHT() + "€");
	}

	private String calculerTotalPrix(Fromage fromage) {
		Article a = fromage.getArticles().get(articleComboBox.getSelectedIndex());
		int quantite = (int) quantiteComboBox.getSelectedItem();
		return FormatHelper.DecimalFormat(a.getPrixHT() * quantite);
	}

	private void updateTotalPrix(Fromage fromage) {
		this.totalPrixLabel.setText("Total : " + this.calculerTotalPrix(fromage) + "€");
	}

	private void updateModelQuantiteComboBox(){
		this.quantiteComboBox.setModel(new DefaultComboBoxModel<>(((Article) this.articleComboBox.getSelectedItem()).tabQuantite()));
	}

}
