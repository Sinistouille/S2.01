package ihm;

import modele.Panier;

import javax.swing.*;
import java.awt.*;

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
    private JButton boutonAnnuler;
    private JPanel leftPanelInfo;
    private JPanel rightPanelInfo;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Livraison(new Panier()).setVisible(true);
            }
        });
    }

    public Livraison(Panier panier) {
        setTitle("Ô fromage Livraison");
        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int height = (int)dimension.getHeight();
        int width  = (int)dimension.getWidth();
        this.setBounds((width-600)/2, (height-500)/2, 600, 700);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        JPanel PanneauPrincipal = new JPanel();
        PanneauPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));;
        PanneauPrincipal.setLayout(new BoxLayout(PanneauPrincipal, BoxLayout.Y_AXIS));

        JPanel panelInformations = new JPanel();
        PanneauPrincipal.add(panelInformations);
        panelInformations.setLayout(new BorderLayout(0, 0));

        // Title
        JLabel Titre = new JLabel("Livraison");
        panelInformations.add(Titre, BorderLayout.NORTH);
        Titre.setHorizontalAlignment(SwingConstants.CENTER);
        Titre.setFont(new Font("Arial", Font.BOLD, 24));
        Titre.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Panel for form fields
        JPanel panelChamps = new JPanel();
        panelInformations.add(panelChamps, BorderLayout.CENTER);
        leftPanelInfo = new JPanel();
        rightPanelInfo = new JPanel();
        leftPanelInfo.setLayout(new GridLayout(0, 1, 0, 0));
        rightPanelInfo.setLayout(new GridLayout(0, 1, 0, 0));

        // Adding components to formPanel
        createRowPanel("Civilité", createTitlePanel(), leftPanelInfo, rightPanelInfo);
        createRowPanel("Prénom", prenomField = new JTextField(20), leftPanelInfo, rightPanelInfo);
        panelChamps.setLayout(new GridLayout(0, 2, 0, 0));
        createRowPanel("Nom", nomField = new JTextField(20), leftPanelInfo, rightPanelInfo);
        createRowPanel("Adresse Email", emailField = new JTextField(20), leftPanelInfo, rightPanelInfo);
        createRowPanel("Numéro de téléphone fixe", phoneField = new JTextField(20), leftPanelInfo, rightPanelInfo);
        createRowPanel("Numéro de téléphone Portable", mobileField = new JTextField(20), leftPanelInfo, rightPanelInfo);
        createRowPanel("Adresse", addressField = new JTextField(20), leftPanelInfo, rightPanelInfo);
        createRowPanel("Complément d'adresse", address2Field = new JTextField(20), leftPanelInfo, rightPanelInfo);
        createRowPanel("Code postal", postalCodeField = new JTextField(20), leftPanelInfo, rightPanelInfo);
        createRowPanel("Ville", cityField = new JTextField(20), leftPanelInfo, rightPanelInfo);
        createRowPanel("Pays", countryComboBox = new JComboBox<>(new String[]{"France"}), leftPanelInfo, rightPanelInfo);

        panelChamps.add(leftPanelInfo);
        panelChamps.add(rightPanelInfo);

        ButtonGroup paymentGroup = new ButtonGroup();

        // Newsletter subscription checkbox
        JPanel newsletterPanel = new JPanel();
        PanneauPrincipal.add(newsletterPanel);
        newsletterPanel.setLayout(new GridLayout(3, 1, 0, 0));

        // Payment method panel
        JPanel paymentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        newsletterPanel.add(paymentPanel);
        paymentPanel.setBorder(BorderFactory.createTitledBorder("Moyen de paiement"));
        creditCardButton = new JRadioButton("Carte de crédit");
        paypalButton = new JRadioButton("Paypal");
        checkButton = new JRadioButton("Paiement par chèque");
        paymentGroup.add(creditCardButton);
        paymentGroup.add(paypalButton);
        paymentGroup.add(checkButton);
        paymentPanel.add(creditCardButton);
        paymentPanel.add(paypalButton);
        paymentPanel.add(checkButton);
        newsletterCheckBox = new JCheckBox("S'abonner à la Newsletter");
        newsletterPanel.add(newsletterCheckBox);

        JPanel panel = new JPanel();
        newsletterPanel.add(panel);
        panel.setLayout(new GridLayout(0, 2, 10, 0));
        validateButton = new JButton("Valider");
        panel.add(validateButton);
        validateButton.setBackground(new Color(255, 255, 255));

        // Event listeners
        validateButton.addActionListener(e -> {
            // Action lors du clic sur le bouton 'Valider'
            JOptionPane.showMessageDialog(Livraison.this, "Commande validée !");
        });
        boutonAnnuler = new JButton("Annuler");
        panel.add(boutonAnnuler);
        boutonAnnuler.setBackground(new Color(255, 128, 128));

        boutonAnnuler.addActionListener(e -> {
            // Action lors du clic sur le bouton 'Annuler'
            dispose();
        });

        // Add panel to frame
        getContentPane().add(PanneauPrincipal, BorderLayout.CENTER);
    }

    private void createRowPanel(String labelText, JComponent field, JPanel leftPanel, JPanel rightPanel) {
        JLabel lblCivilit = new JLabel(labelText + " : ");

        JPanel leftline = new JPanel();
        leftline.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JPanel rightline = new JPanel();
        rightline.setLayout(new FlowLayout(FlowLayout.LEFT));
        //lblCivilit.setAlignmentX(Component.LEFT_ALIGNMENT);

        leftline.add(lblCivilit);
        rightline.add(field);
        leftPanel.add(leftline);
        rightPanel.add(rightline);
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