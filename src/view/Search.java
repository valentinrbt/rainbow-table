package src.view;

import javax.swing.*;
import src.HashCompare;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class Search extends JFrame {

    /**
     * Constructeur de la classe Search qui crée une fenêtre de recherche pour
     * afficher le résultat de la recherche du mot de passe.
     * 
     * @param Hash Le nom du mot de passe recherché, ou null s'il n'est pas trouvé.
     */
    public Search(String Hash) {
        setTitle("RAINBOW TABLE");
        setSize(600, 500);
        setResizable(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int choice = JOptionPane.showConfirmDialog(Search.this, "Voulez-vous vraiment quitter ?",
                        "Quitter ?", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    dispose();
                }
            }
        });

        JPanel mainPanel = new JPanel(new GridLayout(4, 1, 0, 20)); // 3 lignes, 1 colonne, espacement vertical de 20
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Recherche du mot de passe en cours...");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        mainPanel.add(titleLabel);
        add(mainPanel);

        setLocationRelativeTo(null);
        setVisible(true);
        repaint();
        SwingUtilities.invokeLater(() -> {

            HashCompare comp = new HashCompare(Hash);
            String nom = comp.rechercheMdp();
            if (nom == null) {
                titleLabel.setText("Votre mot de passe est introuvable");
            } else {
                titleLabel.setText("Votre mot de passe est " + nom);
            }

            JButton retour = new JButton("Retour");
            retour.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new Menu();
                    dispose();
                }
            });
            mainPanel.add(retour);
            repaint();
        });

    }
}