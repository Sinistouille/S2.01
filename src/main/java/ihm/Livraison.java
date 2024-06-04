package ihm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Livraison extends JFrame {

    private JTextField prenomField;
    private JTextField nomField;
    private JTextField emailField;
    private JTextField phoneField;
    private JTextField mobileField;
    private JTextField addressField;
    private JTextField address2Field;
    private JTextField postalCodeField;
    private JTextField cityField;
    private JComboBox<String> countryComboBox;
    private JRadioButton creditCardButton;
    private JRadioButton paypalButton;
    private JRadioButton checkButton;
    private JCheckBox newsletterCheckBox;
    private JButton validateButton;
    private JButton cancelButton;
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Livraison().setVisible(true);
            }
        });
    }

    public Livraison() {
        setTitle("Ô fromage Livraison");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        JPanel PanneauPrincipal = new JPanel();
        PanneauPrincipal.setLayout(new BoxLayout(PanneauPrincipal, BoxLayout.Y_AXIS));
        PanneauPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Title
        JLabel Titre = new JLabel("Livraison");
        Titre.setFont(new Font("Arial", Font.BOLD, 24));
        Titre.setAlignmentX(Component.LEFT_ALIGNMENT);
        PanneauPrincipal.add(Titre);

        // Panel for form fields
        JPanel Informations = new JPanel();
        Informations.setLayout(new BoxLayout(Informations, BoxLayout.Y_AXIS));
        Informations.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Adding components to formPanel
        Informations.add(createRowPanel("Titre", createTitlePanel()));
        Informations.add(createRowPanel("Prénom", prenomField = new JTextField(20)));
        Informations.add(createRowPanel("Nom", nomField = new JTextField(20)));
        Informations.add(createRowPanel("Adresse Email", emailField = new JTextField(20)));
        Informations.add(createRowPanel("Numéro de téléphone fixe", phoneField = new JTextField(20)));
        Informations.add(createRowPanel("Numéro de téléphone Portable", mobileField = new JTextField(20)));
        Informations.add(createRowPanel("Adresse", addressField = new JTextField(20)));
        Informations.add(createRowPanel("Complément d'adresse", address2Field = new JTextField(20)));
        Informations.add(createRowPanel("Code postal", postalCodeField = new JTextField(10)));
        Informations.add(createRowPanel("Ville", cityField = new JTextField(10)));
        Informations.add(createRowPanel("Pays", countryComboBox = new JComboBox<>(new String[]{"France"})));

        PanneauPrincipal.add(Informations);

        // Payment method panel
        JPanel paymentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        paymentPanel.setBorder(BorderFactory.createTitledBorder("Moyen de paiement"));
        creditCardButton = new JRadioButton("Carte de crédit");
        paypalButton = new JRadioButton("Paypal");
        checkButton = new JRadioButton("Paiement par chèque");
        ButtonGroup paymentGroup = new ButtonGroup();
        paymentGroup.add(creditCardButton);
        paymentGroup.add(paypalButton);
        paymentGroup.add(checkButton);
        paymentPanel.add(creditCardButton);
        paymentPanel.add(paypalButton);
        paymentPanel.add(checkButton);
        PanneauPrincipal.add(paymentPanel);

        // Newsletter subscription checkbox
        JPanel newsletterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        newsletterCheckBox = new JCheckBox("S'abonner à la Newsletter");
        newsletterPanel.add(newsletterCheckBox);
        PanneauPrincipal.add(newsletterPanel);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        validateButton = new JButton("Valider");
        cancelButton = new JButton("Annuler");
        buttonPanel.add(validateButton);
        buttonPanel.add(cancelButton);
        PanneauPrincipal.add(buttonPanel);

        // Add panel to frame
        getContentPane().add(PanneauPrincipal, BorderLayout.CENTER);

        // Event listeners
        validateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action lors du clic sur le bouton 'Valider'
                JOptionPane.showMessageDialog(Livraison.this, "Commande validée !");
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action lors du clic sur le bouton 'Annuler'
                System.exit(0);
            }
        });
    }

    private JPanel createRowPanel(String labelText, JComponent field) {
        JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblCivilit = new JLabel("Civilité : ");
        lblCivilit.setAlignmentX(Component.LEFT_ALIGNMENT);
        rowPanel.add(lblCivilit);
        rowPanel.add(field);
        System.out.println("moi");
        return rowPanel;

    }

    private JPanel createTitlePanel() {
        JPanel CivilitéPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        JRadioButton mrButton = new JRadioButton("Mr");
        JRadioButton mmeButton = new JRadioButton("Mme");
        JRadioButton mlleButton = new JRadioButton("Mlle");
        ButtonGroup titleGroup = new ButtonGroup();
        titleGroup.add(mrButton);
        titleGroup.add(mmeButton);
        titleGroup.add(mlleButton);
        CivilitéPanel.add(mrButton);
        CivilitéPanel.add(mmeButton);
        CivilitéPanel.add(mlleButton);
        return CivilitéPanel;
    }
}
