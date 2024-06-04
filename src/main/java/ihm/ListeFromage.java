package ihm;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;
import java.util.List;
import modele.*;

public class ListeFromage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private Fromages listeFromages;
    private JCheckBox[] checkboxs = new JCheckBox[3];
    private Panier panier = new Panier();
	private JLabel imageFromage;

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

		//Panel principal
		this.setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
				
		JPanel panel_Principal = new JPanel();
		contentPane.add(panel_Principal, BorderLayout.WEST);
		panel_Principal.setLayout(new GridLayout(4, 0, 0, 0));
				
		imageFromage = new JLabel("");
		panel_Principal.add(imageFromage);
				
		JPanel Type_Fromage = new JPanel();
		panel_Principal.add(Type_Fromage);
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
		panel_Principal.add(boutonPanier);
		boutonPanier.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
			FenetrePanier p = new FenetrePanier(panier);
			p.setVisible(true);
		}
	});
		JLabel Livraison_Gratuite = new JLabel("Il manque " + this.panier.getPrix() + " euros avant la livraison gratuite");
		Livraison_Gratuite.setHorizontalAlignment(SwingConstants.CENTER);
		panel_Principal.add(Livraison_Gratuite);
		
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
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Fromage f = listeFromages.getFromage(table.getValueAt(table.getSelectedRow(),0).toString());
				displayImg(f);
				if (e.getClickCount() == 2 && !e.isConsumed()) {
                    e.consume();
                    System.out.println(table.getValueAt(table.getSelectedRow(), 0));
                    FicheProduit p = new FicheProduit(f);
    				p.setVisible(true);
    			}
			}

			
		});
		scrollPane.setViewportView(table);
		setupCheckbox();
		reload();
	}
	
	private void displayImg(Fromage f) {
		String img_path = "src/main/resources/images/fromages/hauteur200/" + f.getNomImage() + ".jpg";
		Image image = new ImageIcon(img_path).getImage();
		image = image.getScaledInstance(150, 150, Image.SCALE_DEFAULT);
		this.imageFromage.setIcon(new ImageIcon(image));
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
        if (this.checkboxs[0].isSelected()) {
            laits.add(TypeLait.CHEVRE);
        }
        if (this.checkboxs[1].isSelected()) {
            laits.add(TypeLait.BREBIS);
        }
        if (this.checkboxs[2].isSelected()) {
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
        for (JCheckBox c : this.checkboxs) {
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
