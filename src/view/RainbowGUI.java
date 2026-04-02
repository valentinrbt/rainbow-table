package src.view;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.*;
import src.Compression;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RainbowGUI extends JFrame {

    /**
     * Constructeur de la classe RainbowGUI qui crée une interface graphique.
     *
     * @param l La longueur spécifiée pour la génération des mots de passe.
     * @param tabCouleur La liste des couleurs utilisées dans le processus de compression.
     * @param p La profondeur du processus de compression.
     */
    public RainbowGUI(int l, ArrayList<String> tabCouleur, int p) {
        setTitle("RAINBOW TABLE");
        setSize(600, 500);
        setResizable(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int choice = JOptionPane.showConfirmDialog(RainbowGUI.this, "Voulez-vous vraiment quitter ?",
                        "Quitter ?", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    dispose();
                }
            }
        });

        JPanel mainPanel = new JPanel(new GridLayout(4, 1, 0, 20)); // 3 lignes, 1 colonne, espacement vertical de 20
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Génération de votre fichier de mots de passe en cours...");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        mainPanel.add(titleLabel);
        add(mainPanel);
        setLocationRelativeTo(null);
        setVisible(true);
        repaint();
        SwingUtilities.invokeLater(() -> {

            Compression pwd = new Compression(l, tabCouleur, p);
            pwd.comprimer();
            JButton retour = new JButton("Retour");
            retour.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new Menu();
                    dispose();
                }
            });
            mainPanel.add(retour);
            titleLabel.setText("Génération terminée");
            repaint();
        });
    }
}