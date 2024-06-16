package ihm;

import modele.Client;
import modele.Panier;

import javax.swing.*;
import java.awt.*;

public class FenetreInformations extends JFrame {

    private JTextField fieldFirstName;
    private JTextField fieldName;
    private JTextField fieldEmail;
    private JTextField fieldPhone;
    private JTextField fieldMobilePhone;
    private JTextField fieldAdresse;
    private JTextField fieldComplementAdresse;
    private JTextField fieldPostalCode;
    private JTextField fieldCity;
    private JComboBox<String> comboBoxCountry;
    private JRadioButton radioBoxCreditCard;
    private JRadioButton radioBoxPaypal;
    private JRadioButton radioBoxCheque;
    private JCheckBox checkBoxNewsLetter;
    private JButton boutonValider;
    private JButton boutonAnnuler;
    private JPanel panelInfoLeft;
    private JPanel panelInfoRight;
    private Client client;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FenetreInformations(new Panier()).setVisible(true));
    }

    public FenetreInformations(Panier panier) {
        setTitle("Ô fromage FenetreInformations");
        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int height = (int)dimension.getHeight();
        int width  = (int)dimension.getWidth();
        this.setBounds((width-600)/2, (height-500)/2, 600, 700);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Initialisation du panneau principal
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));

        // Panneau des informations
        JPanel panelInformations = new JPanel(new BorderLayout(0, 0));
        panelPrincipal.add(panelInformations);

        // Titre
        JLabel labelTitre = new JLabel("FenetreInformations");
        labelTitre.setHorizontalAlignment(SwingConstants.CENTER);
        labelTitre.setFont(new Font("Arial", Font.BOLD, 24));
        labelTitre.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelInformations.add(labelTitre, BorderLayout.NORTH);

        // Panneau des champs de formulaire
        JPanel panelChamps = new JPanel(new GridLayout(0, 2, 0, 0));
        panelInformations.add(panelChamps, BorderLayout.CENTER);

        panelInfoLeft = new JPanel(new GridLayout(0, 1, 0, 0));
        panelInfoRight = new JPanel(new GridLayout(0, 1, 0, 0));
        panelChamps.add(panelInfoLeft);
        panelChamps.add(panelInfoRight);

        // Groupe de boutons pour les méthodes de paiement
        ButtonGroup paymentGroup = new ButtonGroup();

        // Panneau pour l'abonnement à la newsletter
        JPanel panelPaiement = new JPanel(new GridLayout(3, 1, 0, 0));
        panelPrincipal.add(panelPaiement);

        // Panneau des méthodes de paiement
        JPanel panelMoyenPaiement = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelMoyenPaiement.setBorder(BorderFactory.createTitledBorder("Moyen de paiement"));
        radioBoxCreditCard = new JRadioButton("Carte de crédit");
        radioBoxPaypal = new JRadioButton("Paypal");
        radioBoxCheque = new JRadioButton("Paiement par chèque");
        paymentGroup.add(radioBoxCreditCard);
        paymentGroup.add(radioBoxPaypal);
        paymentGroup.add(radioBoxCheque);
        panelMoyenPaiement.add(radioBoxCreditCard);
        panelMoyenPaiement.add(radioBoxPaypal);
        panelMoyenPaiement.add(radioBoxCheque);
        panelPaiement.add(panelMoyenPaiement);

        // Case à cocher pour l'abonnement à la newsletter
        checkBoxNewsLetter = new JCheckBox("S'abonner à la Newsletter");
        panelPaiement.add(checkBoxNewsLetter);

        // Panneau des boutons de validation
        JPanel panelBoutons = new JPanel(new GridLayout(0, 2, 10, 0));
        panelPaiement.add(panelBoutons);

        boutonValider = new JButton("Valider");
        boutonValider.setBackground(new Color(255, 255, 255));
        panelBoutons.add(boutonValider);


        this.addListeners(panier, panelBoutons);

        // Add panel to frame
        this.setContentPane(panelPrincipal);
        createPanelInformations();
    }

    private void addListeners(Panier panier, JPanel panel) {
        // Event listeners
        boutonValider.addActionListener(e -> {
            // Action lors du clic sur le bouton 'Valider'
            this.client = this.getInformations();
            new FenetreCommande(panier, client).setVisible(true);
        });
        boutonAnnuler = new JButton("Annuler");
        panel.add(boutonAnnuler);
        boutonAnnuler.setBackground(new Color(255, 128, 128));

        boutonAnnuler.addActionListener(e -> {
            // Action lors du clic sur le bouton 'Annuler'
            dispose();
        });
    }

    private Client getInformations(){
        Client client = new Client(fieldName.getText(),fieldFirstName.getText());
        client.setAdresse(fieldAdresse.getText());
        client.setVille(fieldCity.getText());
        client.setMobilePhone(fieldMobilePhone.getText());
        client.setPhone(fieldPhone.getText());
        client.setCodePostal(fieldPostalCode.getText());
        client.setEmail(fieldEmail.getText());
        client.setCountry((String) comboBoxCountry.getSelectedItem());
        client.setComplementAdresse(fieldComplementAdresse.getText());
        return client;
    }

    private void createPanelInformations() {
        // Adding components to formPanel
        createRowPanel("Civilité", createTitlePanel(), panelInfoLeft, panelInfoRight);
        createRowPanel("Prénom", fieldFirstName = new JTextField(20), panelInfoLeft, panelInfoRight);
        createRowPanel("Nom", fieldName = new JTextField(20), panelInfoLeft, panelInfoRight);
        createRowPanel("Adresse Email", fieldEmail = new JTextField(20), panelInfoLeft, panelInfoRight);
        createRowPanel("Numéro de téléphone fixe", fieldPhone = new JTextField(20), panelInfoLeft, panelInfoRight);
        createRowPanel("Numéro de téléphone Portable", fieldMobilePhone = new JTextField(20), panelInfoLeft, panelInfoRight);
        createRowPanel("Adresse", fieldAdresse = new JTextField(20), panelInfoLeft, panelInfoRight);
        createRowPanel("Complément d'adresse", fieldComplementAdresse = new JTextField(20), panelInfoLeft, panelInfoRight);
        createRowPanel("Code postal", fieldPostalCode = new JTextField(20), panelInfoLeft, panelInfoRight);
        createRowPanel("Ville", fieldCity = new JTextField(20), panelInfoLeft, panelInfoRight);
        createRowPanel("Pays", comboBoxCountry = new JComboBox<>(new String[]{"France"}), panelInfoLeft, panelInfoRight);
    }

    private void createRowPanel(String labelText, JComponent field, JPanel leftPanel, JPanel rightPanel) {
        JLabel labelCivilite = new JLabel(labelText + " : ");

        JPanel leftline = new JPanel();
        leftline.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JPanel rightline = new JPanel();
        rightline.setLayout(new FlowLayout(FlowLayout.LEFT));
        //lblCivilit.setAlignmentX(Component.LEFT_ALIGNMENT);

        leftline.add(labelCivilite);
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