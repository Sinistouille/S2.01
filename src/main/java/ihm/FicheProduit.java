package ihm;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import modele.Article;
import modele.Panier;

public class FicheProduit extends JFrame {

	private JLabel nomFromageLabel;
	private JLabel poidsLabel;
	private JRadioButton coupe250g;
	private JRadioButton coupe500g;
	private JRadioButton coupe1kg;
	private ButtonGroup groupePoids;
	private JLabel prixUnitaireLabel;
	private JComboBox<Integer> quantiteComboBox;
	private JTextArea descriptionArea;
	private JLabel totalPrixLabel;
	private JButton ajouterPanierButton;
	private JButton annulerButton;
	private JLabel imageFromage;

	private String nomFromage = "Nom";
	private String descriptionFromage = "Description.";
	private double prixUnitaire = 5.20;

	public FicheProduit(Panier panier, Article article) {
		this.setTitle("Ô fromage - Fiche produit");
		this.setSize(700, 600);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.getContentPane().setLayout(new BorderLayout());

		this.nomFromageLabel = new JLabel(this.nomFromage);
		this.nomFromageLabel.setFont(new Font("Arial", Font.BOLD, 24));

		this.poidsLabel = new JLabel("Poids");
		this.poidsLabel.setFont(new Font("Arial", Font.ITALIC, 18));

		this.coupe250g = new JRadioButton("à la coupe - 250g", true);
		this.coupe500g = new JRadioButton("à la coupe - 500g");
		this.coupe1kg = new JRadioButton("à la coupe - 1kg");
		this.groupePoids = new ButtonGroup();
		this.groupePoids.add(this.coupe250g);
		this.groupePoids.add(this.coupe500g);
		this.groupePoids.add(this.coupe1kg);

		this.prixUnitaireLabel = new JLabel("Prix unitaire : €");
		this.quantiteComboBox = new JComboBox<>(new Integer[] { 1, 2, 3, 4, 5 });
		this.quantiteComboBox.setSelectedIndex(1); // Quantité par défaut est 2

		this.imageFromage = new JLabel(new ImageIcon("lien"));

		this.descriptionArea = new JTextArea(this.getDescriptionFromage());
		this.descriptionArea.setLineWrap(true);
		this.descriptionArea.setWrapStyleWord(true);
		this.descriptionArea.setEditable(false);
		JScrollPane descriptionScrollPane = new JScrollPane(this.descriptionArea);

		// Panneau supérieur avec le nom du fromage et le poids
		JPanel panneauHaut = new JPanel(new BorderLayout());
		panneauHaut.add(this.nomFromageLabel, BorderLayout.NORTH);
		panneauHaut.add(this.poidsLabel, BorderLayout.SOUTH);

		// Panneau de sélection du poids
		JPanel panneauPoids = new JPanel(new GridLayout(3, 1));
		panneauPoids.add(this.coupe250g);
		panneauPoids.add(this.coupe500g);
		panneauPoids.add(this.coupe1kg);

		// Panneau de sélection du prix et de la quantité
		JPanel panneauPrix = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panneauPrix.add(new JLabel("Quantité "));
		panneauPrix.add(this.quantiteComboBox);

		JPanel panneauMilieu = new JPanel(new GridLayout(3, 1));
		panneauMilieu.add(panneauPoids);
		panneauMilieu.add(this.prixUnitaireLabel);
		panneauMilieu.add(panneauPrix);

		// Panneau d'image
		JPanel panneauImage = new JPanel(new BorderLayout());
		panneauImage.add(this.imageFromage, BorderLayout.CENTER);

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

		this.totalPrixLabel = new JLabel("Total : " + this.calculerTotalPrix() + "€");
		this.ajouterPanierButton = new JButton("Ajouter le panier");
		this.annulerButton = new JButton("Annuler");

		this.ajouterPanierButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Action lors du clic sur le bouton 'Ajouter le panier'
				panier.ajouterArticle(article, 1);
			}
		});

		this.annulerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Action lors du clic sur le bouton 'Annuler'
				System.exit(0);
			}
		});
		// Panneau pour le prix total, le bouton ajouter au panier et le bouton annuler
		JPanel panneauAction = new JPanel(new GridLayout(3, 1));
		panneauDescription.add(panneauAction, BorderLayout.EAST);
		panneauAction.add(this.totalPrixLabel);
		panneauAction.add(this.ajouterPanierButton);
		panneauAction.add(this.annulerButton);

		// Mise à jour du prix total lorsque la quantité ou le poids change
		this.quantiteComboBox.addActionListener(e -> this.updateTotalPrix());
		this.coupe250g.addActionListener(e -> this.updateTotalPrix());
		this.coupe500g.addActionListener(e -> this.updateTotalPrix());
		this.coupe1kg.addActionListener(e -> this.updateTotalPrix());
	}

	private String getDescriptionFromage() {
		// Mettre à jour cette méthode pour renvoyer la description en fonction du
		// fromage sélectionné
		return this.descriptionFromage;
	}

	private double calculerTotalPrix() {
		int quantite = (Integer) this.quantiteComboBox.getSelectedItem();
		double prixPoidsSelectionne = this.prixUnitaire;
		if (this.coupe500g.isSelected()) {
			prixPoidsSelectionne = this.prixUnitaire * 2; // Prix pour 500g
		} else if (this.coupe1kg.isSelected()) {
			prixPoidsSelectionne = this.prixUnitaire * 4; // Prix pour 1kg
		}
		return quantite * prixPoidsSelectionne;
	}

	private void updateTotalPrix() {
		this.totalPrixLabel.setText("Total : " + this.calculerTotalPrix() + "€");
	}
}
