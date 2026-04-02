package src.view;

import javax.swing.*;
import src.Hash;
import src.HashCompare;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class Menu extends JFrame {

    private JButton recherche;
    private JButton hash;

    Font f = new Font("", Font.PLAIN, 40);
    ArrayList<String> tabCouleur = new ArrayList<>();

    /**
     * Constructeur de la classe Menu qui crée une interface graphique pour afficher
     * le menu principal de l'application.
     */
    public Menu() {
        // Définition des propriétés de la fenêtre

        setTitle("RAINBOW TABLE");
        setSize(600, 500);
        setResizable(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int choice = JOptionPane.showConfirmDialog(Menu.this, "Voulez-vous vraiment quitter ?",
                        "Quitter ?", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    dispose();
                }
            }
        });

        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridLayout(5, 1, 0, 20)); // 3 lignes, 1 colonne, espacement vertical de 20
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("RAINBOW TABLE");
        titleLabel.setFont(f);
        titleLabel.setForeground(Color.RED);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        mainPanel.add(titleLabel);

        // Création du bouton "Jouer"
        JButton genButton = new JButton("Générer");
        genButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changerPanneau();
            }
        });

        mainPanel.add(genButton);
        recherche = new JButton("Rechercher");
        recherche.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changerPanneau2();
            }
        });
        mainPanel.add(recherche);

        hash = new JButton("Découvrir le hash");
        hash.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changerPanneau3();
            }
        });
        mainPanel.add(hash);

        JButton quitterButton = new JButton("Quitter");
        quitterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(Menu.this, "Souhaitez-vous quitter ?",
                        "Quitter ?", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            dispose();
                        }
                    });
                }
            }
        });
        mainPanel.add(quitterButton);

        add(mainPanel);
        setVisible(true);
    }

    /**
     * Change le panneau actuel par un nouveau panneau permettant à l'utilisateur
     * de spécifier un mot de passe à transformer en hash.
     */
    private void changerPanneau3() {
        getContentPane().removeAll();
        JPanel nouvJPanel = new JPanel();
        nouvJPanel.setLayout(new BorderLayout());
        nouvJPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titreLabel = new JLabel("Transformation en Hash");
        titreLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titreLabel.setHorizontalAlignment(JLabel.CENTER);
        nouvJPanel.add(titreLabel, BorderLayout.NORTH);

        JPanel boutonsPanel = new JPanel(new GridLayout(3, 1, 0, 10));

        TextField emp = new TextField("Entrez un mot de passe");

        emp.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

                if (emp.getText().equals("Entrez un mot de passe")) {
                    emp.setForeground(Color.BLACK);

                    emp.setText("");
                }
                if (emp.getText().equals("Longueur du mot de passe doit être entre 8 et 32 caractères")) {
                    emp.setForeground(Color.BLACK);

                    emp.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (emp.getText().isEmpty()) {
                    emp.setText("Entrez un mot de passe");
                    emp.setForeground(Color.RED);

                }
            }
        });

        boutonsPanel.add(emp);

        JButton transform = new JButton("Transformer");
        transform.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (emp.getText().equals("Longueur du mot de passe doit être entre 8 et 32 caractères")) {
                    emp.setText("Entrez un mot de passe");
                }
                if (emp.getText().equals("Entrez un mot de passe")) {
                    emp.setText("Entrez un mot de passe");

                } else if (emp.getText().length() >= 8 && emp.getText().length() <= 32) {
                    Hash h = new Hash(emp.getText());
                    emp.setText(h.creeHash());
                    emp.setForeground(Color.BLUE);

                } else {
                    emp.setText("Longueur du mot de passe doit être entre 8 et 32 caractères");
                    emp.setForeground(Color.RED);

                }
            }
        });
        boutonsPanel.add(transform);

        JButton retour = new JButton("Retour");
        retour.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Menu();
                dispose();
            }
        });
        boutonsPanel.add(retour);

        nouvJPanel.add(boutonsPanel, BorderLayout.CENTER);
        getContentPane().add(nouvJPanel);
        revalidate();
        repaint();
    }

    /**
     * Change le panneau pour permettre à l'utilisateur de spécifier une empreinte à
     * rechercher.
    */
    private void changerPanneau2() {
        getContentPane().removeAll();
        JPanel nouvJPanel = new JPanel();
        nouvJPanel.setLayout(new BorderLayout());
        nouvJPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titreLabel = new JLabel("Recherche avec empreinte");
        titreLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titreLabel.setHorizontalAlignment(JLabel.CENTER);
        nouvJPanel.add(titreLabel, BorderLayout.NORTH);

        JPanel boutonsPanel = new JPanel(new GridLayout(3, 1, 0, 10));

        TextField emp = new TextField("Entrez une empreinte");

        emp.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                emp.setForeground(Color.BLACK);

                if (emp.getText().equals("Entrez une empreinte")) {
                    emp.setText("");
                } else {
                    if (emp.getText().equals("Longueur de l'empreinte différente de 64 carractères")) {
                        emp.setText("");

                    }
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (emp.getText().isEmpty()) {
                    emp.setText("Entrez une empreinte");
                } else {
                    if (emp.getText().length() != 64) {
                        emp.setText("Longueur de l'empreinte différente de 64 carractères");
                        emp.setForeground(Color.RED);

                    }
                }

            }
        });

        boutonsPanel.add(emp);

        JButton Rech = new JButton("Recherche");
        Rech.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (emp.getText().length() == 64) {
                    new Search(emp.getText());
                    dispose();
                } else {
                    emp.setText("Longueur de l'empreinte différente de 64 carractères");
                    emp.setForeground(Color.RED);

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

        nouvJPanel.add(boutonsPanel, BorderLayout.CENTER);
        getContentPane().add(nouvJPanel);
        revalidate();
        repaint();
    }

    /**
     * Change le panneau pour permettre à l'utilisateur de sélectionner les
     * couleurs.
     */
    private void changerPanneau() {
        getContentPane().removeAll();
        JPanel nouvJPanel = new JPanel();
        nouvJPanel.setLayout(new BorderLayout());
        nouvJPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titreLabel = new JLabel("Couleurs arc");
        titreLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titreLabel.setHorizontalAlignment(JLabel.CENTER);
        nouvJPanel.add(titreLabel, BorderLayout.NORTH);

        // Ajout de la liste de boutons
        JPanel boutonsPanel = new JPanel(new GridLayout(4, 2, 0, 10));

        JToggleButton Vert = new JToggleButton("VERT");

        Vert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Vert.isSelected()) {
                    tabCouleur.add("Green");
                } else {
                    tabCouleur.remove("Green");
                }
            }
        });

        boutonsPanel.add(Vert);

        JToggleButton bleu = new JToggleButton("BLEU");

        bleu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (bleu.isSelected()) {
                    tabCouleur.add("Blue");
                } else {
                    tabCouleur.remove("Blue");
                }
            }
        });

        boutonsPanel.add(bleu);

        JToggleButton violet = new JToggleButton("VIOLET");
        violet.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (violet.isSelected()) {
                    tabCouleur.add("Purple");
                } else {
                    tabCouleur.remove("Purple");
                }

            }
        });
        boutonsPanel.add(violet);

        JToggleButton Jaune = new JToggleButton("JAUNE");
        Jaune.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Jaune.isSelected()) {
                    tabCouleur.add("Yellow");
                } else {
                    tabCouleur.remove("Yellow");
                }

            }
        });
        boutonsPanel.add(Jaune);

        JToggleButton Oran = new JToggleButton("ORANGE");
        Oran.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Oran.isSelected()) {
                    tabCouleur.add("Orange");
                } else {
                    tabCouleur.remove("Orange");
                }

            }
        });
        boutonsPanel.add(Oran);
        JToggleButton ROUGE = new JToggleButton("ROUGE");
        ROUGE.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (ROUGE.isSelected()) {
                    tabCouleur.add("Red");
                } else {
                    tabCouleur.remove("Red");
                }

            }
        });
        boutonsPanel.add(ROUGE);

        JButton Vali = new JButton("Valider");
        Vali.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (tabCouleur.size() > 0) {
                    new Number(tabCouleur);
                    dispose();
                }

            }
        });

        boutonsPanel.add(Vali);

        JButton retour = new JButton("Retour");
        retour.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Menu();
                dispose();
            }
        });
        boutonsPanel.add(retour);

        nouvJPanel.add(boutonsPanel, BorderLayout.CENTER);
        getContentPane().add(nouvJPanel);
        revalidate();
        repaint();
    }

    public JButton getBoutonRecherche() {
        return this.recherche;
    }

    public static void main(String[] args) {
        new Menu();
    }
}