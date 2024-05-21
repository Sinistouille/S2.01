package ihm;

import javax.swing.*;

public class Fenetre extends JFrame {
    public Fenetre() {
        this.setTitle("Fromagerie");
        this.setSize(400, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setContentPane(new JPanel());
        this.setVisible(true);
    }
}
