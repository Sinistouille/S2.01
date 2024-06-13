package ihm;

import java.awt.*;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import data.FormatHelper;
import data.LocHelper;
import modele.*;

public class FenetreSelection extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel panelTypeFromage;
    private JPanel panelLivraisonGratuite;
    private JPanel panelBoutons;
    private JPanel panelListe;
    private JPanel PanneauPrincipal;
    private JTable Fromages;
    private Fromages listeFromages;
    private JCheckBox[] checkboxs_types_fromages = new JCheckBox[3];
    private Panier panier;
    private JLabel labelImageFromage;
    private JLabel labelLivraisonGratuite;
    private JPanel panelSelection;
    private JPanel panelLeft;


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

        //Panel principal
        this.PanneauPrincipal = new JPanel();
        this.PanneauPrincipal.setBackground(new Color(255, 255, 255));
        this.PanneauPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.PanneauPrincipal.setLayout(new BorderLayout(0, 0));
        this.setContentPane(this.PanneauPrincipal);

        //Panel boutons
        panelBoutons = new JPanel();
        panelBoutons.setOpaque(false);
        panelBoutons.setLayout(new GridLayout(0, 2, 10, 0));
        this.PanneauPrincipal.add(this.panelBoutons, BorderLayout.SOUTH);

        //Panel selection
        panelSelection = new JPanel();
        panelSelection.setOpaque(false);
        panelSelection.setLayout(new GridLayout(0, 2, 10, 0));
        this.PanneauPrincipal.add(this.panelSelection, BorderLayout.CENTER);

        //Panel contenant les checkbox pour trier les fromages selon leur type
        panelTypeFromage = new JPanel();
        panelTypeFromage.setOpaque(false);
        panelTypeFromage.setLayout(new GridLayout(1, 3, 0, 0));

        //Checkbox pour les types de fromage
        JCheckBox checkbox_Chevre = new JCheckBox("Chévre");
        panelTypeFromage.add(checkbox_Chevre);
        this.checkboxs_types_fromages[0] = checkbox_Chevre;
        JCheckBox checkbox_Brebis = new JCheckBox("Brebis");
        panelTypeFromage.add(checkbox_Brebis);
        this.checkboxs_types_fromages[1] = checkbox_Brebis;
        JCheckBox checkbox_Vache = new JCheckBox("Vache");
        panelTypeFromage.add(checkbox_Vache);
        this.checkboxs_types_fromages[2] = checkbox_Vache;

        //Label contenant l'image du fromage sélectionné
        this.labelImageFromage = new JLabel();
        labelImageFromage.setHorizontalAlignment(SwingConstants.CENTER);
        
        panelLeft = new JPanel();
        panelLeft.setOpaque(false);
        panelLeft.setLayout(new GridLayout(4, 0, 0, 0));
        panelLeft.add(this.labelImageFromage);
        panelLeft.add(panelTypeFromage);


        //Table des fromages
        this.Fromages = new JTable();
        JScrollPane scrollPaneFromages = new JScrollPane();
        scrollPaneFromages.setViewportView(this.Fromages);

        //PanelListeFromages
        panelListe = new JPanel();
        panelListe.setLayout(new BorderLayout(0, 0));
        panelListe.add(scrollPaneFromages, BorderLayout.CENTER);

        panelSelection.add(panelLeft);
        panelSelection.add(panelListe);

        this.labelLivraisonGratuite = new JLabel("Il reste 120.00 euros avant la livraison gratuite");
        labelLivraisonGratuite.setHorizontalAlignment(SwingConstants.CENTER);
        //PanelSelection


        //Panel contenant le label pour la livraison gratuite
        panelLivraisonGratuite = new JPanel();
        panelLivraisonGratuite.setOpaque(false);
        panelLivraisonGratuite.setLayout(new GridLayout(0, 1, 0, 0));
        panelLivraisonGratuite.add(labelLivraisonGratuite);
        panelLeft.add(panelLivraisonGratuite);

        //PanelBoutons

        JButton boutonAfficherPanier = new JButton("Panier");
        boutonAfficherPanier.setBackground(new Color(255, 255, 255));
        panelBoutons.add(boutonAfficherPanier);
        JButton BoutonQuitter = new JButton("Quitter");
        BoutonQuitter.setBackground(new Color(255, 128, 128));
        panelBoutons.add(BoutonQuitter);

        //Listeners pour les boutons
        this.addListeners(BoutonQuitter, boutonAfficherPanier);
        this.setupLabelImageFromage();
        this.updateLivraisonGratuite();
        this.reload();
    }

    private void setupVariables() {
        this.panier = new Panier();
        this.listeFromages = GenerationFromages.générationBaseFromages();
    }

    private void setupLabelImageFromage() {
        Image image = new ImageIcon(LocHelper.accueilLoc("vitrine")).getImage();
        image = image.getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        this.labelImageFromage.setIcon(new ImageIcon(image));
    }

    private void addListeners(JButton BoutonQuitter, JButton boutonAfficherPanier) {
        this.Fromages.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Fromage f = listeFromages.getFromage(Fromages.getValueAt(Fromages.getSelectedRow(), 0).toString());
                LocHelper.displayImage(labelImageFromage, f);
                if (e.getClickCount() == 2 && !e.isConsumed()) {
                    e.consume();
                    FicheProduit p = new FicheProduit(panier,f, FenetreSelection.this);
                    p.setVisible(true);
                }
            }

        });
        BoutonQuitter.addActionListener(e -> System.exit(0));
        boutonAfficherPanier.addActionListener(e -> {
            FenetrePanier p = new FenetrePanier(FenetreSelection.this.panier);
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
        this.Fromages.setModel(model);
    }

    private void setupCheckbox() {

        for (JCheckBox c : this.checkboxs_types_fromages) {
            c.setSelected(true);
            c.addChangeListener(e -> FenetreSelection.this.reload());
            c.setOpaque(false);
        }
    }
    public void updateLivraisonGratuite() {
        if(this.panier.getPrixHT() >= 120) {
            this.labelLivraisonGratuite.setText("Livraison gratuite");
        }
        else {
            this.labelLivraisonGratuite.setText("Il manque " + FormatHelper.df.format(120 - this.panier.getPrixHT()) +  " euros avant la livraison gratuite");
        }
    }
}