package ihm;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import data.FormatHelper;
import data.FileHelper;
import modele.*;

public class FenetreSelection extends JFrame {

    private static final long serialVersionUID = 1L;
    private final JButton boutonAfficherPanier;
    private final JButton boutonQuitter;
    private JPanel panelTypeFromage;
    private JPanel panelLivraisonGratuite;
    private JPanel panelBoutons;
    private JPanel panelListe;
    private JPanel panelPrincipal;
    private JTable tableFromages;
    private Fromages listeFromages;
    private JCheckBox[] checkboxs_types_fromages = new JCheckBox[3];
    private Panier panier;
    private JLabel labelImageFromage;
    private JLabel labelLivraisonGratuite;
    private JPanel panelSelection;
    private JPanel panelLeft;
    private JPanel panelTitre;
    private JLabel labelTitre;


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    FenetreSelection frame = new FenetreSelection();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public FenetreSelection() {

        this.setupVariables();

        this.setTitle("Ô fromage - Liste Fromages");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Centrage de la fenêtre
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int height = (int)dimension.getHeight();
        int width  = (int)dimension.getWidth();
        this.setBounds((width-600)/2, (height-500)/2, 600, 500);

        // Panel Principal Initialization
        panelPrincipal = new JPanel();
        panelPrincipal.setBackground(new Color(255, 255, 255));
        panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
        panelPrincipal.setLayout(new BorderLayout(0, 0));
        setContentPane(panelPrincipal);

        // Panel Boutons Initialization
        panelBoutons = new JPanel();
        panelBoutons.setOpaque(false);
        panelBoutons.setLayout(new GridLayout(0, 2, 10, 0));
        panelPrincipal.add(panelBoutons, BorderLayout.SOUTH);

        // Panel Selection Initialization
        panelSelection = new JPanel();
        panelSelection.setOpaque(false);
        panelSelection.setLayout(new GridLayout(0, 2, 10, 0));
        panelPrincipal.add(panelSelection, BorderLayout.CENTER);

        // Panel Type Fromage Initialization
        panelTypeFromage = new JPanel();
        panelTypeFromage.setOpaque(false);
        panelTypeFromage.setLayout(new GridLayout(1, 3, 0, 0));

        JCheckBox checkbox_Chevre = new JCheckBox("Chévre");
        panelTypeFromage.add(checkbox_Chevre);
        checkboxs_types_fromages[0] = checkbox_Chevre;

        JCheckBox checkbox_Brebis = new JCheckBox("Brebis");
        panelTypeFromage.add(checkbox_Brebis);
        checkboxs_types_fromages[1] = checkbox_Brebis;

        JCheckBox checkbox_Vache = new JCheckBox("Vache");
        panelTypeFromage.add(checkbox_Vache);
        checkboxs_types_fromages[2] = checkbox_Vache;

        // Label Image Fromage Initialization
        labelImageFromage = new JLabel();
        labelImageFromage.setVerticalAlignment(SwingConstants.BOTTOM);
        labelImageFromage.setHorizontalAlignment(SwingConstants.LEFT);

        // Panel Left Initialization
        panelLeft = new JPanel();
        panelLeft.setOpaque(false);
        panelLeft.setLayout(new GridLayout(4, 0, 0, 0));
        panelLeft.add(labelImageFromage);
        panelLeft.add(panelTypeFromage);

        // Table Fromages Initialization
        tableFromages = new JTable();
        JScrollPane scrollPaneFromages = new JScrollPane();
        scrollPaneFromages.setViewportView(tableFromages);

        // Panel Liste Fromages Initialization
        panelListe = new JPanel();
        panelListe.setLayout(new BorderLayout(0, 0));
        panelListe.add(scrollPaneFromages, BorderLayout.CENTER);

        panelSelection.add(panelLeft);
        panelSelection.add(panelListe);

        // Label Livraison Gratuite Initialization
        labelLivraisonGratuite = new JLabel("Il reste 120.00 euros avant la livraison gratuite");
        labelLivraisonGratuite.setHorizontalAlignment(SwingConstants.CENTER);

        // Panel Livraison Gratuite Initialization
        panelLivraisonGratuite = new JPanel();
        panelLivraisonGratuite.setOpaque(false);
        panelLivraisonGratuite.setLayout(new GridLayout(0, 1, 0, 0));
        panelLivraisonGratuite.add(labelLivraisonGratuite);
        panelLeft.add(panelLivraisonGratuite);

        // Panel Boutons Initialization
        boutonAfficherPanier = new JButton("Panier");
        boutonAfficherPanier.setBackground(new Color(255, 255, 255));
        panelBoutons.add(boutonAfficherPanier);

        boutonQuitter = new JButton("Quitter");
        boutonQuitter.setBackground(new Color(255, 128, 128));
        panelBoutons.add(boutonQuitter);

        panelTitre = new JPanel();
        panelTitre.setBackground(new Color(255, 255, 255));

        labelTitre = new JLabel("Ô'Fromage");
        labelTitre.setFont(new Font("Arial", Font.BOLD, 24));
        panelTitre.add(labelTitre);

        panelPrincipal.add(panelTitre, BorderLayout.NORTH);

        //Listeners pour les boutons
        this.addListeners(boutonQuitter, boutonAfficherPanier);
        this.updateLivraisonGratuite();
        this.reload();
        this.setupIcons();
    }

    private void setupVariables() {
        this.panier = new Panier();
        this.listeFromages = GenerationFromages.loadFromages("fromages.json");
    }


    private void setupIcons(){
        labelImageFromage.setIcon(FileHelper.setIcon(FileHelper.accueilLoc("vitrine.jpg"),148,100));
        boutonAfficherPanier.setIcon(FileHelper.setIcon(FileHelper.logoLoc("panier.png"), 20, 20));
        boutonQuitter.setIcon(FileHelper.setIcon(FileHelper.logoLoc("quitter.png"), 20, 20));
    }

    private void addListeners(JButton BoutonQuitter, JButton boutonAfficherPanier) {
        this.tableFromages.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Fromage f = listeFromages.getFromage(tableFromages.getValueAt(tableFromages.getSelectedRow(), 0).toString());
                labelImageFromage.setIcon(FileHelper.setIcon(FileHelper.fromageLoc(f.getNomImage() + ".jpg"), 148, 100));
                if (e.getClickCount() == 2 && !e.isConsumed()) {
                    e.consume();
                    FicheProduit p = new FicheProduit(panier,f, FenetreSelection.this);
                    p.setVisible(true);
                }
            }

        });
        BoutonQuitter.addActionListener(e -> System.exit(0));
        boutonAfficherPanier.addActionListener(e -> {
            FenetrePanier p = new FenetrePanier(FenetreSelection.this.panier, FenetreSelection.this, FenetreSelection.this.listeFromages);
            p.setVisible(true);
        });
        setupCheckbox();
    }

    private void reload() {
        DefaultTableModel model = new DefaultTableModel() {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                // all cells false
                return false;
            }
        };
        List<TypeLait> laits = new ArrayList<>();
        if (this.checkboxs_types_fromages[0].isSelected()) {
            laits.add(TypeLait.CHEVRE);
        }
        if (this.checkboxs_types_fromages[1].isSelected()) {
            laits.add(TypeLait.BREBIS);
        }
        if (this.checkboxs_types_fromages[2].isSelected()) {
            laits.add(TypeLait.VACHE);
        }
        model.setDataVector(this.listeFromages.arrayFromages(laits), new String[] { "Fromages" });
        this.tableFromages.setModel(model);
    }

    private void setupCheckbox() {

        for (JCheckBox c : this.checkboxs_types_fromages) {
            c.setSelected(true);
            c.addChangeListener(e -> FenetreSelection.this.reload());
            c.setOpaque(false);
        }
    }
    public void updateLivraisonGratuite() {
        if(this.panier.getPrixHT() >= 120f) {
            this.labelLivraisonGratuite.setText("Livraison gratuite");
        }
        else {
            this.labelLivraisonGratuite.setText("Il manque " + FormatHelper.DecimalFormat(120f - this.panier.getPrixHT()) +  " euros avant la livraison gratuite");
        }
    }
}