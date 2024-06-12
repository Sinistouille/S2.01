package ihm;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import modele.Panier;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.print.PrinterException;

public class FenetreCommande extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenetreCommande frame = new FenetreCommande(new Panier());
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
	public FenetreCommande(Panier panier) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 548, 372);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		textField = new JTextArea(panier.toString());
		panel_1.add(textField, BorderLayout.CENTER);
		textField.setColumns(10);

		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));

		JButton boutonImprimer = new JButton("Imprimer");
		boutonImprimer.addActionListener(e -> {

		});
		boutonImprimer.setBackground(new Color(255, 255, 255));
		boutonImprimer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel_2.add(boutonImprimer);

		JButton boutonPdf = new JButton("PDF");
		boutonPdf.setBackground(new Color(255, 255, 255));
		panel_2.add(boutonPdf);

		JPanel panel = new JPanel();
		panel_1.add(panel, BorderLayout.NORTH);
		panel.setBackground(new Color(255, 255, 255));
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignOnBaseline(true);

		JLabel lblNewLabel = new JLabel("Récapitulatif de votre commande");
		panel.add(lblNewLabel);
		boutonImprimer.addActionListener(e -> {
            try {
                textField.print();
            } catch (PrinterException ex) {
                throw new RuntimeException(ex);
            }
        });
	}

}
