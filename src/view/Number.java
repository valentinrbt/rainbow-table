package src.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class Number extends JFrame {
    ArrayList<String> tabCouleurC;

    /**
     * Constructeur de la classe Number qui crée une interface graphique pour permettre à l'utilisateur de spécifier la taille du fichier à générer.
     *
     * @param tabCouleur La liste des couleurs utilisées dans le processus de compression.
     */
    public Number(ArrayList<String> tabCouleur) {

        tabCouleurC = tabCouleur;
        setTitle("RAINBOW TABLE");
        setSize(600, 500);
        setResizable(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int choice = JOptionPane.showConfirmDialog(Number.this, "Voulez-vous vraiment quitter ?",
                        "Quitter ?", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    dispose();
                }
            }
        });

        setLocationRelativeTo(null);
        JPanel boutonsPanel = new JPanel(new GridLayout(4, 1, 0, 10));
        JLabel titreLabel = new JLabel("Taille du fichier");
        titreLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titreLabel.setHorizontalAlignment(JLabel.CENTER);
        boutonsPanel.add(titreLabel, BorderLayout.NORTH);

        JTextField lignes = new JTextField(10);
        JTextField profondeur = new JTextField(10);

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Profondeur : "));
        inputPanel.add(lignes);
        inputPanel.add(new JLabel("Nombre de lignes: "));
        inputPanel.add(profondeur);
        boutonsPanel.add(inputPanel);

        lignes.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                lignes.setForeground(Color.BLACK);

            }

            @Override
            public void focusLost(FocusEvent e) {
                if (!isNumeric(lignes.getText())) {
                    lignes.setText("");
                }
            }
        });

        profondeur.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                profondeur.setForeground(Color.BLACK);

            }

            @Override
            public void focusLost(FocusEvent e) {
                if (!isNumeric(profondeur.getText())) {
                    profondeur.setText("");
                }
            }
        });

        JButton Rech = new JButton("Génération");
        Rech.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int l = Integer.parseInt(lignes.getText());
                int p = Integer.parseInt(profondeur.getText());
                if (l > 0 && p > 0) {
                    new RainbowGUI(l, tabCouleurC, p);
                    dispose();
                }

            }
        });
        boutonsPanel.add(Rech);

        JButton retour = new JButton("Retour");
        retour.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Menu();
                dispose();
            }
        });
        boutonsPanel.add(retour);
        add(boutonsPanel);
        setVisible(true);

    }

    /**
     * Vérifie si une chaîne de caractères est numérique.
     *
     * @param str La chaîne de caractères à vérifier.
     * @return true si la chaîne est numérique, false sinon.
     */
    public static boolean isNumeric(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}