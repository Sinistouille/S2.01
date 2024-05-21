package ihm;

import javax.swing.*;

public class Fenetre extends JFrame {
    public static void main(String[] args) {
        new Fenetre();
    }
    public Fenetre() {
        this.setTitle("Fromagerie");
        this.setSize(1000, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setContentPane(new JPanel());
        this.setVisible(true);
    }
}
