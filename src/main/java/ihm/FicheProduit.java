package ihm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import data.FormatHelper;
import modele.Article;
import modele.Fromage;
import data.ImageHelper;
import modele.Panier;

public class FicheProduit extends JFrame {

	private JLabel nomFromageLabel;
	private JLabel poidsLabel;
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
	private String descriptionFromage;
	private double prixUnitaire = 5.20;

	public FicheProduit(Panier panier, Fromage fromage, ListeFromage listeFromage) {
		this.setTitle("Ô fromage - Fiche produit");
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)dimension.getHeight();
		int width  = (int)dimension.getWidth();
		this.setBounds((width-600)/2, (height-500)/2, 600, 500);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(new BorderLayout());

		this.nomFromageLabel = new JLabel(fromage.getDésignation());
		this.nomFromageLabel.setFont(new Font("Arial", Font.BOLD, 24));

		this.poidsLabel = new JLabel("Poids");
		this.poidsLabel.setFont(new Font("Arial", Font.ITALIC, 18));

		// Panneau de sélection de l'article
		JPanel panneauPoids = new JPanel(new GridLayout(3, 1));
		this.articleComboBox = new JComboBox<>(fromage.getArticles());
		this.articleComboBox.setSelectedItem(fromage.getArticles().get(0));
		panneauPoids.add(this.articleComboBox);


		this.updateModelQuantiteComboBox();

		this.labelImageFromage = new JLabel(new ImageIcon("lien"));
		ImageHelper.displayImage(labelImageFromage, fromage);

		this.descriptionFromage = fromage.getDescription();
		this.descriptionArea = new JTextArea(this.getDescriptionFromage());
		this.descriptionArea.setLineWrap(true);
		this.descriptionArea.setWrapStyleWord(true);
		this.descriptionArea.setEditable(false);
		
		JScrollPane descriptionScrollPane = new JScrollPane(this.descriptionArea);

		// Panneau supérieur avec le nom du fromage et le poids
		JPanel panneauHaut = new JPanel(new BorderLayout());
		panneauHaut.add(this.nomFromageLabel, BorderLayout.NORTH);
		panneauHaut.add(this.poidsLabel, BorderLayout.SOUTH);



		// Panneau de sélection du prix et de la quantité
		JPanel panneauPrix = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panneauPrix.add(new JLabel("Quantité "));
		panneauPrix.add(this.quantiteComboBox);

		JPanel panneauMilieu = new JPanel(new GridLayout(3, 1));
		panneauMilieu.add(panneauPoids);
		
		this.prixUnitaireLabel = new JLabel("Prix unitaire : " + "€");
		panneauPoids.add(prixUnitaireLabel);
		panneauMilieu.add(panneauPrix);
		updatePrixUnitaire();

		// Panneau d'image
		JPanel panneauImage = new JPanel(new BorderLayout());
		panneauImage.add(this.labelImageFromage, BorderLayout.CENTER);

		// Panneau principal combinant panneauAction et panneauImage
		JPanel panneauPrincipal = new JPanel(new BorderLayout());
		panneauPrincipal.add(panneauHaut, BorderLayout.NORTH);
		panneauPrincipal.add(panneauMilieu, BorderLayout.WEST);
		panneauPrincipal.add(panneauImage, BorderLayout.CENTER);

		// Panneau de description
		JPanel panneauDescription = new JPanel(new BorderLayout());
		panneauDescription.setBorder(BorderFactory.createTitledBorder("Description"));
		panneauDescription.add(descriptionScrollPane, BorderLayout.CENTER);

		// Ajout des panneaux au frame
		this.getContentPane().add(panneauPrincipal, BorderLayout.CENTER);
		this.getContentPane().add(panneauDescription, BorderLayout.SOUTH);

		this.totalPrixLabel = new JLabel("Total : " + this.calculerTotalPrix(fromage) + "€");
		this.ajouterPanierButton = new JButton("Ajouter le panier");
		this.annulerButton = new JButton("Annuler");

		addListeners(panier, fromage, listeFromage);
		// Panneau pour le prix total, le bouton ajouter au panier et le bouton annuler
		JPanel panneauAction = new JPanel(new GridLayout(3, 1));
		panneauDescription.add(panneauAction, BorderLayout.EAST);
		panneauAction.add(this.totalPrixLabel);
		panneauAction.add(this.ajouterPanierButton);
		panneauAction.add(this.annulerButton);

		// Mise à jour du prix total lorsque la quantité ou le poids change
		this.quantiteComboBox.addActionListener(e -> this.updateTotalPrix(fromage));
	}

	private void addListeners(Panier panier, Fromage fromage, ListeFromage listeFromage) {
		this.ajouterPanierButton.addActionListener(e -> {
            // Action lors du clic sur le bouton 'Ajouter le panier'
			//article sélectionné par la combobx
            Article article = fromage.getArticles().get(articleComboBox.getSelectedIndex());
			//quantité sélectionnée par la combobox
            int quantite = (int) quantiteComboBox.getSelectedItem();
			//ajout de l'article au panier
            panier.ajouterArticle(article, quantite);
			//mise à jour de la comboBox de quantité
			updateModelQuantiteComboBox();
			System.out.println("SYSTEM.OUT : Ajout de " + quantite + " " + article.getFromage().getDésignation() + ", " + article.getClé() + " au panier");
			ListeFromage.afficherPanier(panier);
			//mise à jour des frais de livraisons
			listeFromage.updateLivraisonGratuite();

        });
		this.articleComboBox.addActionListener (e -> {
			updatePrixUnitaire();
			updateModelQuantiteComboBox();
		});

		this.annulerButton.addActionListener(e -> {
            // Action lors du clic sur le bouton 'Annuler'
            dispose();
        });
	}

	private void updatePrixUnitaire() {
		Article a = (Article) articleComboBox.getSelectedItem();
		prixUnitaireLabel.setText("Prix unitaire : " + a.getPrixTTC() + "€");
		poidsLabel.setText(a.getClé());
	}

	private String getDescriptionFromage() {
		// Mettre à jour cette méthode pour renvoyer la description en fonction du
		// fromage sélectionné
		return this.descriptionFromage;
	}

	private String calculerTotalPrix(Fromage fromage) {
		Article a = fromage.getArticles().get(articleComboBox.getSelectedIndex());
		int quantite = (int) quantiteComboBox.getSelectedItem();
		FormatHelper.df.format(a.getPrixTTC() * quantite);
		return FormatHelper.df.format(a.getPrixTTC() * quantite);
	}

	private void updateTotalPrix(Fromage fromage) {
		this.totalPrixLabel.setText("Total : " + this.calculerTotalPrix(fromage) + "€");
	}

	private void updateModelQuantiteComboBox(){
		this.quantiteComboBox.setModel(new DefaultComboBoxModel<>(((Article) this.articleComboBox.getSelectedItem()).tabQuantite()));
	}

}
