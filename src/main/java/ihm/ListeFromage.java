package ihm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

public class ListeFromage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField Image_Fromage;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.WEST);
		panel.setLayout(new GridLayout(4, 1, 0, 0));
		
		Image_Fromage = new JTextField();
		panel.add(Image_Fromage);
		Image_Fromage.setColumns(10);
		
		JPanel Type_Fromage = new JPanel();
		panel.add(Type_Fromage);
		Type_Fromage.setLayout(new GridLayout(1, 3, 0, 0));
		
		JCheckBox Chévre = new JCheckBox("Chévre");
		Type_Fromage.add(Chévre);
		
		JCheckBox Brebis = new JCheckBox("Brebis");
		Type_Fromage.add(Brebis);
		
		JCheckBox Vache = new JCheckBox("Vache");
		Type_Fromage.add(Vache);
		
		JButton Panier = new JButton("New button");
		panel.add(Panier);
		
		JLabel Livraison_Gratuite = new JLabel("New label");
		Livraison_Gratuite.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(Livraison_Gratuite);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JScrollPane Liste_Fromage = new JScrollPane();
		Liste_Fromage.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel_1.add(Liste_Fromage);
	}

}
