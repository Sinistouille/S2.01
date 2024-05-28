package ihm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modele.Fromages;
import ihm.FenetrePanier;
import modele.GenerationFromages;
import modele.Panier;
import modele.TypeLait;

import javax.swing.JScrollBar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListeFromage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private Fromages listeFromages;
	private Panier panier;
	private JCheckBox[] checkboxs = new JCheckBox[3];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
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

	/**
	 * Create the frame.
	 */
	public ListeFromage() {
		setTitle("Ô fromage - Liste Fromages");
		this.listeFromages = GenerationFromages.générationBaseFromages();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.WEST);
		panel.setLayout(new GridLayout(4, 0, 0, 0));
		
		JLabel ImageFromage = new JLabel("");
		panel.add(ImageFromage);
		
		JPanel Type_Fromage = new JPanel();
		panel.add(Type_Fromage);
		Type_Fromage.setLayout(new GridLayout(1, 3, 0, 0));
		
		JCheckBox checkbox_Chevre = new JCheckBox("Chévre");
		Type_Fromage.add(checkbox_Chevre);
		this.checkboxs[0] = checkbox_Chevre;
		JCheckBox checkbox_Brebis = new JCheckBox("Brebis");
		Type_Fromage.add(checkbox_Brebis);
		this.checkboxs[1] = checkbox_Brebis;
		JCheckBox checkbox_Vache = new JCheckBox("Vache");
		Type_Fromage.add(checkbox_Vache);
		this.checkboxs[2] = checkbox_Vache;
		
		JButton boutonPanier = new JButton("Panier");
		panel.add(boutonPanier);
		boutonPanier.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FenetrePanier p = new FenetrePanier();
				p.setVisible(true);
				
			}
		});
		JLabel Livraison_Gratuite = new JLabel("Somme a payer avant livraison");
		Livraison_Gratuite.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(Livraison_Gratuite);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.SOUTH);
		
		JButton BoutonQuitter = new JButton("Quitter");
		BoutonQuitter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		panel_2.add(BoutonQuitter);
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		setupCheckbox();
		reload();
	}
	
	private void reload() {
		DefaultTableModel model = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		List<TypeLait> laits = new ArrayList<>();
		if(this.checkboxs[0].isSelected()) {
			laits.add(TypeLait.CHEVRE);
		}
		if(this.checkboxs[1].isSelected()) {
			laits.add(TypeLait.BREBIS);
		}
		if(this.checkboxs[2].isSelected()) {
			laits.add(TypeLait.VACHE);
		}
		model.setDataVector(
				this.listeFromages.arrayFromages(laits),
				new String[] {
				"Fromages"
		});
		this.table.setModel(model);
	}
	private void setupCheckbox() {
		for(JCheckBox c : this.checkboxs) {
			c.setSelected(true);
			c.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {
					reload();
				}
			});
		}
	}
}
