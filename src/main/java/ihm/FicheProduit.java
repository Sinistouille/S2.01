package ihm;

package ihm;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FicheProduit extends JFrame {

    private JLabel NomFromage;
    private JLabel PoidsFromage;
    private JRadioButton cut250g;
    private JRadioButton cut500g;
    private JRadioButton cut1kg;
    private ButtonGroup weightGroup;
    private JLabel PrixUnitaire;
    private JComboBox<Integer> Quantité;
    private JTextArea descriptionArea;
    private JLabel Total;
    private JButton AjouterAuPanier;
    private JButton Annuler;
    private JLabel ImageFromage;

    private String fromageName = ".";
    private String fromageDescription = ".";
    private double unitPrice = 0;
    private JLabel label;

    public FicheProduit() {
        setTitle("Ô fromage - Fiche produit");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        NomFromage = new JLabel("");
        NomFromage.setFont(new Font("Arial", Font.BOLD, 24));
        PoidsFromage = new JLabel("");
        PoidsFromage.setFont(new Font("Arial", Font.ITALIC, 18));

        cut250g = new JRadioButton("à la coupe - 250g", true);
        cut500g = new JRadioButton("à la coupe - 500g");
        cut1kg = new JRadioButton("à la coupe - 1kg");
        weightGroup = new ButtonGroup();
        weightGroup.add(cut250g);
        weightGroup.add(cut500g);
        weightGroup.add(cut1kg);
        Quantité = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5});
        Quantité.setSelectedIndex(1); // default quantity is 2

        ImageFromage = new JLabel(new ImageIcon("path_to_image/roquefort.png"));

        descriptionArea = new JTextArea(forFromageDescription());
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setEditable(false);
        JScrollPane descriptionScrollPane = new JScrollPane(descriptionArea);

        Total = new JLabel("Total : " + calculateTotalPrice() + "€");
        AjouterAuPanier = new JButton("Ajouter le panier");
        Annuler = new JButton("Annuler");

        AjouterAuPanier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action when 'Ajouter le panier' button is clicked
            }
        });

        Annuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action when 'Annuler' button is clicked
                System.exit(0);
            }
        });

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(NomFromage, BorderLayout.NORTH);
        topPanel.add(PoidsFromage, BorderLayout.CENTER);

        JPanel Poids = new JPanel(new GridLayout(3, 1));
        Poids.add(cut250g);
        Poids.add(cut500g);
        Poids.add(cut1kg);

        JPanel Prix = new JPanel();
        SpringLayout sl_Prix = new SpringLayout();
        sl_Prix.putConstraint(SpringLayout.NORTH, Quantité, 228, SpringLayout.NORTH, Prix);
        sl_Prix.putConstraint(SpringLayout.WEST, Quantité, 65, SpringLayout.WEST, Prix);
        sl_Prix.putConstraint(SpringLayout.SOUTH, Quantité, 456, SpringLayout.NORTH, Prix);
        sl_Prix.putConstraint(SpringLayout.EAST, Quantité, 130, SpringLayout.WEST, Prix);
        Prix.setLayout(sl_Prix);
        
        label = new JLabel("");
        sl_Prix.putConstraint(SpringLayout.NORTH, label, 0, SpringLayout.NORTH, Prix);
        sl_Prix.putConstraint(SpringLayout.WEST, label, 65, SpringLayout.WEST, Prix);
        sl_Prix.putConstraint(SpringLayout.SOUTH, label, 228, SpringLayout.NORTH, Prix);
        sl_Prix.putConstraint(SpringLayout.EAST, label, 130, SpringLayout.WEST, Prix);
        Prix.add(label);
        JLabel label_1 = new JLabel("Quantité ");
        sl_Prix.putConstraint(SpringLayout.NORTH, label_1, 228, SpringLayout.NORTH, Prix);
        sl_Prix.putConstraint(SpringLayout.WEST, label_1, 0, SpringLayout.WEST, Prix);
        sl_Prix.putConstraint(SpringLayout.SOUTH, label_1, 456, SpringLayout.NORTH, Prix);
        sl_Prix.putConstraint(SpringLayout.EAST, label_1, 65, SpringLayout.WEST, Prix);
        Prix.add(label_1);
        Prix.add(Quantité);

        JPanel middlePanel = new JPanel(new BorderLayout());
        middlePanel.add(Poids, BorderLayout.NORTH);
        middlePanel.add(Prix, BorderLayout.CENTER);
        
                PrixUnitaire = new JLabel("Prix unitaire : ");
                Prix.add(PrixUnitaire);
                sl_Prix.putConstraint(SpringLayout.NORTH, PrixUnitaire, 0, SpringLayout.NORTH, Prix);
                sl_Prix.putConstraint(SpringLayout.WEST, PrixUnitaire, 0, SpringLayout.WEST, Prix);
                sl_Prix.putConstraint(SpringLayout.SOUTH, PrixUnitaire, 228, SpringLayout.NORTH, Prix);

        JPanel descriptionPanel = new JPanel(new BorderLayout());
        descriptionPanel.add(descriptionScrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(Total, BorderLayout.WEST);
        bottomPanel.add(AjouterAuPanier, BorderLayout.CENTER);
        bottomPanel.add(Annuler, BorderLayout.EAST);

        getContentPane().add(topPanel, BorderLayout.NORTH);
        getContentPane().add(middlePanel, BorderLayout.WEST);
        getContentPane().add(ImageFromage, BorderLayout.CENTER);
        getContentPane().add(descriptionPanel, BorderLayout.SOUTH);
        getContentPane().add(bottomPanel, BorderLayout.SOUTH);

        // Update total price when quantity or weight changes
        Quantité.addActionListener(e -> updateTotalPrice());
        cut250g.addActionListener(e -> updateTotalPrice());
        cut500g.addActionListener(e -> updateTotalPrice());
        cut1kg.addActionListener(e -> updateTotalPrice());
    }

    private String forFromageDescription() {
        // Update this method to return the description based on selected cheese
        return fromageDescription;
    }

    private double calculateTotalPrice() {
        int quantity = (Integer) Quantité.getSelectedItem();
        double selectedWeightPrice = unitPrice;
        if (cut500g.isSelected()) {
            selectedWeightPrice = 10.40; // Example price for 500g
        } else if (cut1kg.isSelected()) {
            selectedWeightPrice = 20.80; // Example price for 1kg
        }
        return quantity * selectedWeightPrice;
    }

    private void updateTotalPrice() {
        Total.setText("Total : " + calculateTotalPrice() + "€");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new FicheProduit().setVisible(true);
            }
        });
    }
}
