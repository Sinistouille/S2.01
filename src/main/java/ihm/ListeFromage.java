package ihm;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
import javax.swing.JCheckBox;
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

        // Panel principal
        this.setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JPanel panel_Principal = new JPanel();
        contentPane.add(panel_Principal, BorderLayout.WEST);
        panel_Principal.setLayout(new GridLayout(4, 0, 0, 0));

        // Ajout des éléments restants ici...

        JScrollPane scrollPane = new JScrollPane();
        panel_1.add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                int col = table.columnAtPoint(e.getPoint());
                if (row >= 0 && col >= 0) {
                    FicheProduit ficheProduit = new FicheProduit();
                    ficheProduit.setVisible(true);
                    // Vous pouvez passer des informations à la page FicheProduit en fonction de la ligne cliquée
                    // Par exemple : ficheProduit.setFromage(listeFromages.getFromageAt(row));
                }
            }
        });
        scrollPane.setViewportView(table);
        setupCheckbox();
        reload();
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
