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
    private JRadioButton creditCardButton;
    private JRadioButton paypalButton;
    private JRadioButton checkButton;
    private JCheckBox newsletterCheckBox;
    private JButton validateButton;
    private JButton boutonAnnuler;
    private JPanel leftPanelInfo;
    private JPanel rightPanelInfo;
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
        JPanel panneauPrincipal = new JPanel();
        panneauPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panneauPrincipal.setLayout(new BoxLayout(panneauPrincipal, BoxLayout.Y_AXIS));

        // Panneau des informations
        JPanel panelInformations = new JPanel(new BorderLayout(0, 0));
        panneauPrincipal.add(panelInformations);

        // Titre
        JLabel titre = new JLabel("FenetreInformations");
        titre.setHorizontalAlignment(SwingConstants.CENTER);
        titre.setFont(new Font("Arial", Font.BOLD, 24));
        titre.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelInformations.add(titre, BorderLayout.NORTH);

        // Panneau des champs de formulaire
        JPanel panelChamps = new JPanel(new GridLayout(0, 2, 0, 0));
        panelInformations.add(panelChamps, BorderLayout.CENTER);

        leftPanelInfo = new JPanel(new GridLayout(0, 1, 0, 0));
        rightPanelInfo = new JPanel(new GridLayout(0, 1, 0, 0));
        panelChamps.add(leftPanelInfo);
        panelChamps.add(rightPanelInfo);

        // Groupe de boutons pour les méthodes de paiement
        ButtonGroup paymentGroup = new ButtonGroup();

        // Panneau pour l'abonnement à la newsletter
        JPanel newsletterPanel = new JPanel(new GridLayout(3, 1, 0, 0));
        panneauPrincipal.add(newsletterPanel);

        // Panneau des méthodes de paiement
        JPanel paymentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
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
        newsletterPanel.add(paymentPanel);

        // Case à cocher pour l'abonnement à la newsletter
        newsletterCheckBox = new JCheckBox("S'abonner à la Newsletter");
        newsletterPanel.add(newsletterCheckBox);

        // Panneau des boutons de validation
        JPanel panelBoutons = new JPanel(new GridLayout(0, 2, 10, 0));
        newsletterPanel.add(panelBoutons);

        validateButton = new JButton("Valider");
        validateButton.setBackground(new Color(255, 255, 255));
        panelBoutons.add(validateButton);


        this.addListeners(panier, panelBoutons);

        // Add panel to frame
        this.setContentPane(panneauPrincipal);
        createPanelInformations();
    }

    private void addListeners(Panier panier, JPanel panel) {
        // Event listeners
        validateButton.addActionListener(e -> {
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
        createRowPanel("Civilité", createTitlePanel(), leftPanelInfo, rightPanelInfo);
        createRowPanel("Prénom", fieldFirstName = new JTextField(20), leftPanelInfo, rightPanelInfo);
        createRowPanel("Nom", fieldName = new JTextField(20), leftPanelInfo, rightPanelInfo);
        createRowPanel("Adresse Email", fieldEmail = new JTextField(20), leftPanelInfo, rightPanelInfo);
        createRowPanel("Numéro de téléphone fixe", fieldPhone = new JTextField(20), leftPanelInfo, rightPanelInfo);
        createRowPanel("Numéro de téléphone Portable", fieldMobilePhone = new JTextField(20), leftPanelInfo, rightPanelInfo);
        createRowPanel("Adresse", fieldAdresse = new JTextField(20), leftPanelInfo, rightPanelInfo);
        createRowPanel("Complément d'adresse", fieldComplementAdresse = new JTextField(20), leftPanelInfo, rightPanelInfo);
        createRowPanel("Code postal", fieldPostalCode = new JTextField(20), leftPanelInfo, rightPanelInfo);
        createRowPanel("Ville", fieldCity = new JTextField(20), leftPanelInfo, rightPanelInfo);
        createRowPanel("Pays", comboBoxCountry = new JComboBox<>(new String[]{"France"}), leftPanelInfo, rightPanelInfo);
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