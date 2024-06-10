package ihm;

import java.awt.*;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import data.FormatHelper;
import data.ImageHelper;
import modele.*;

public class ListeFromage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel PanneauPrincipal;
	private JTable Fromages;
	private Fromages listeFromages;
	private JCheckBox[] checkboxs_types_fromages = new JCheckBox[3];
	private Panier panier = new Panier();
	private JLabel labelImageFromage;
	private JLabel Livraison_Gratuite;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					ListeFromage frame = new ListeFromage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public ListeFromage() {
		this.panier = new Panier();
		this.Livraison_Gratuite = new JLabel("Il reste 120.00 euros avant la livraison gratuite");
		this.setTitle("Ô fromage - Liste Fromages");
		this.listeFromages = GenerationFromages.générationBaseFromages();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//get the window width and height
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)dimension.getHeight();
		int width  = (int)dimension.getWidth();
		this.setBounds((width-600)/2, (height-500)/2, 600, 500);
		this.PanneauPrincipal = new JPanel();
		this.PanneauPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));

		// Panel principal
		this.setContentPane(this.PanneauPrincipal);

		Image image = new ImageIcon(ImageHelper.accueilLoc("vitrine")).getImage();
		image = image.getScaledInstance(200, 200, Image.SCALE_DEFAULT);
		this.updateLivraisonGratuite();
		PanneauPrincipal.setLayout(new BorderLayout(0, 0));

		JPanel panelSelection = new JPanel();
		PanneauPrincipal.add(panelSelection);
		panelSelection.setLayout(new GridLayout(0, 2, 10, 0));

		JPanel panel_Principal = new JPanel();
		panelSelection.add(panel_Principal);
		panel_Principal.setLayout(new GridLayout(4, 0, 0, 0));

		this.labelImageFromage = new JLabel("");
		this.labelImageFromage.setIcon(new ImageIcon(image));
		panel_Principal.add(this.labelImageFromage);

		JPanel Type_Fromage = new JPanel();
		panel_Principal.add(Type_Fromage);
		Type_Fromage.setLayout(new GridLayout(1, 3, 0, 0));

		JCheckBox checkbox_Chevre = new JCheckBox("Chévre");
		Type_Fromage.add(checkbox_Chevre);
		this.checkboxs_types_fromages[0] = checkbox_Chevre;
		JCheckBox checkbox_Brebis = new JCheckBox("Brebis");
		Type_Fromage.add(checkbox_Brebis);
		this.checkboxs_types_fromages[1] = checkbox_Brebis;
		JCheckBox checkbox_Vache = new JCheckBox("Vache");
		Type_Fromage.add(checkbox_Vache);
		this.checkboxs_types_fromages[2] = checkbox_Vache;

		JPanel panel = new JPanel();
		panel_Principal.add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		Livraison_Gratuite.setBackground(new Color(0, 128, 255));
		panel.add(Livraison_Gratuite);
		Livraison_Gratuite.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel panelListe = new JPanel();
		panelSelection.add(panelListe);
		panelListe.setLayout(new BorderLayout(0, 0));

		JScrollPane ListeFromages = new JScrollPane();
		panelListe.add(ListeFromages, BorderLayout.CENTER);

		this.Fromages = new JTable();
		this.Fromages.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Fromage f = listeFromages.getFromage(Fromages.getValueAt(Fromages.getSelectedRow(), 0).toString());
				ImageHelper.displayImage(labelImageFromage, f);
				if (e.getClickCount() == 2 && !e.isConsumed()) {
					e.consume();
					FicheProduit p = new FicheProduit(panier,f, ListeFromage.this);
					p.setVisible(true);
				}
			}

		});
		ListeFromages.setViewportView(this.Fromages);

		JPanel panelBoutons = new JPanel();
		PanneauPrincipal.add(panelBoutons, BorderLayout.SOUTH);
		panelBoutons.setLayout(new GridLayout(0, 2, 10, 0));

		JButton boutonAfficherPanier = new JButton("Panier");
		panelBoutons.add(boutonAfficherPanier);
		boutonAfficherPanier.setBackground(new Color(0, 128, 255));

		JButton BoutonQuitter = new JButton("Quitter");
		panelBoutons.add(BoutonQuitter);
		BoutonQuitter.setBackground(new Color(255, 128, 128));
		BoutonQuitter.addActionListener(e -> System.exit(0));
		boutonAfficherPanier.addActionListener(e -> {
			FenetrePanier p = new FenetrePanier(ListeFromage.this.panier);
			p.setVisible(true);
		});
		this.setupCheckbox();
		this.reload();
	}

	public static void afficherPanier(Panier p) {
		System.out.println("SYSTEM.OUT : Panier");
		for (Article a : p.getPanier().keySet()) {
			System.out.println("SYSTEM.OUT Panier : " + a.getFromage().getDésignation() + " " + a.getClé() + " quantité : " + p.getPanier().get(a));
		}
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
			c.addChangeListener(e -> ListeFromage.this.reload());
		}
	}
	public void updateLivraisonGratuite() {
		if(this.panier.getPrix() >= 120) {
			this.Livraison_Gratuite.setText("Livraison gratuite");
		}
		else {
			this.Livraison_Gratuite.setText("Il manque " + FormatHelper.df.format(120 - this.panier.getPrix()) + " euros avant la livraison gratuite");
		}
	}
}
