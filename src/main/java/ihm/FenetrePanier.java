package ihm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import java.awt.Color;

public class FenetrePanier extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public FenetrePanier() {
		setTitle("Ô fromage - Panier");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel.add(panel_6);
		panel_6.setLayout(new GridLayout(3, 1, 0, 0));
		
		JLabel Transporteur = new JLabel("Transporteur");
		panel_6.add(Transporteur);
		
		JComboBox comboBox = new JComboBox();
		panel_6.add(comboBox);
		
		JLabel lblNewLabel_12 = new JLabel("Frais de port offert dès 120€ d'achat");
		panel_6.add(lblNewLabel_12);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_2.add(panel_3);
		panel_3.setLayout(new GridLayout(4, 2, 0, 0));
		
		JLabel SousTotalHT = new JLabel("Sous total HT");
		panel_3.add(SousTotalHT);
		
		JLabel PrixHT = new JLabel("126.00€");
		PrixHT.setHorizontalAlignment(SwingConstants.TRAILING);
		panel_3.add(PrixHT);
		
		JLabel TVA = new JLabel("TVA (20%)");
		panel_3.add(TVA);
		
		JLabel PrixTVA = new JLabel("25.20€");
		PrixTVA.setHorizontalAlignment(SwingConstants.TRAILING);
		panel_3.add(PrixTVA);
		
		JLabel FraisDePort = new JLabel("Frais de port");
		panel_3.add(FraisDePort);
		
		JLabel PrixFraisDePort = new JLabel("Gratuit");
		PrixFraisDePort.setHorizontalAlignment(SwingConstants.TRAILING);
		panel_3.add(PrixFraisDePort);
		
		JLabel TotalTTC = new JLabel("Total TTC");
		panel_3.add(TotalTTC);
		
		JLabel PrixTTC = new JLabel("151.20€");
		PrixTTC.setHorizontalAlignment(SwingConstants.TRAILING);
		panel_3.add(PrixTTC);
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel ViderPanier = new JLabel("Vider le panier");
		ViderPanier.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_4.add(ViderPanier);
		
		JLabel ValiderPanier = new JLabel("Valider le panier");
		ValiderPanier.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_4.add(ValiderPanier);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Récapitulatif");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.WEST);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null},
				{null},
				{null},
			},
			new String[] {
				"New column"
			}
		));
		scrollPane.setViewportView(table);
		
		JPanel panel_5 = new JPanel();
		scrollPane.setColumnHeaderView(panel_5);
	}

}
