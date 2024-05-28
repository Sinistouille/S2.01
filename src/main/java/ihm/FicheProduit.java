package ihm;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public FicheProduit() {
        setTitle("Ô fromage - Fiche produit");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        nomFromageLabel = new JLabel(nomFromage);
        nomFromageLabel.setFont(new Font("Arial", Font.BOLD, 24));

        poidsLabel = new JLabel("Poids");
        poidsLabel.setFont(new Font("Arial", Font.ITALIC, 18));

        coupe250g = new JRadioButton("à la coupe - 250g", true);
        coupe500g = new JRadioButton("à la coupe - 500g");
        coupe1kg = new JRadioButton("à la coupe - 1kg");
        groupePoids = new ButtonGroup();
        groupePoids.add(coupe250g);
        groupePoids.add(coupe500g);
        groupePoids.add(coupe1kg);

        prixUnitaireLabel = new JLabel("Prix unitaire : €");
        quantiteComboBox = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5});
        quantiteComboBox.setSelectedIndex(1); // Quantité par défaut est 2

        imageFromage = new JLabel(new ImageIcon("lien"));

        descriptionArea = new JTextArea(getDescriptionFromage());
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setEditable(false);
        JScrollPane descriptionScrollPane = new JScrollPane(descriptionArea);

        // Panneau supérieur avec le nom du fromage et le poids
        JPanel panneauHaut = new JPanel(new BorderLayout());
        panneauHaut.add(nomFromageLabel, BorderLayout.NORTH);
        panneauHaut.add(poidsLabel, BorderLayout.SOUTH);

        // Panneau de sélection du poids
        JPanel panneauPoids = new JPanel(new GridLayout(3, 1));
        panneauPoids.add(coupe250g);
        panneauPoids.add(coupe500g);
        panneauPoids.add(coupe1kg);

        // Panneau de sélection du prix et de la quantité
        JPanel panneauPrix = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panneauPrix.add(new JLabel("Quantité "));
        panneauPrix.add(quantiteComboBox);

        JPanel panneauMilieu = new JPanel(new GridLayout(3, 1));
        panneauMilieu.add(panneauPoids);
        panneauMilieu.add(prixUnitaireLabel);
        panneauMilieu.add(panneauPrix);

        // Panneau d'image
        JPanel panneauImage = new JPanel(new BorderLayout());
        panneauImage.add(imageFromage, BorderLayout.CENTER);

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
        getContentPane().add(panneauPrincipal, BorderLayout.CENTER);
        getContentPane().add(panneauDescription, BorderLayout.SOUTH);
        
                totalPrixLabel = new JLabel("Total : " + calculerTotalPrix() + "€");
                ajouterPanierButton = new JButton("Ajouter le panier");
                annulerButton = new JButton("Annuler");
                
                        ajouterPanierButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                // Action lors du clic sur le bouton 'Ajouter le panier'
                            }
                        });
                        
                                annulerButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        // Action lors du clic sur le bouton 'Annuler'
                                        System.exit(0);
                                    }
                                });
                                
                                        // Panneau pour le prix total, le bouton ajouter au panier et le bouton annuler
                                        JPanel panneauAction = new JPanel(new GridLayout(3, 1));
                                        panneauDescription.add(panneauAction, BorderLayout.EAST);
                                        panneauAction.add(totalPrixLabel);
                                        panneauAction.add(ajouterPanierButton);
                                        panneauAction.add(annulerButton);

        // Mise à jour du prix total lorsque la quantité ou le poids change
        quantiteComboBox.addActionListener(e -> updateTotalPrix());
        coupe250g.addActionListener(e -> updateTotalPrix());
        coupe500g.addActionListener(e -> updateTotalPrix());
        coupe1kg.addActionListener(e -> updateTotalPrix());
    }

    private String getDescriptionFromage() {
        // Mettre à jour cette méthode pour renvoyer la description en fonction du fromage sélectionné
        return descriptionFromage;
    }

    private double calculerTotalPrix() {
        int quantite = (Integer) quantiteComboBox.getSelectedItem();
        double prixPoidsSelectionne = prixUnitaire;
        if (coupe500g.isSelected()) {
            prixPoidsSelectionne = prixUnitaire * 2; // Prix pour 500g
        } else if (coupe1kg.isSelected()) {
            prixPoidsSelectionne = prixUnitaire * 4; // Prix pour 1kg
        }
        return quantite * prixPoidsSelectionne;
    }

    private void updateTotalPrix() {
        totalPrixLabel.setText("Total : " + calculerTotalPrix() + "€");
    }
}
