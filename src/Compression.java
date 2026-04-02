package src;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * La classe Compression représente le processus de compression des mots de passe et de leurs hashs associés.
 */
public class Compression {

    private int longueur;
    private ArrayList<String> tabCouleur;
    private File file;
    private int profondeur;

    /**
     * Constructeur de la classe Compression.
     *
     * @param profondeur La profondeur du processus de compression.
     * @param tabCouleur La liste des couleurs utilisées dans le processus de compression.
     * @param longueur Le nombre de lignes du fichier.
     */
    public Compression(int profondeur,ArrayList<String> tabCouleur,int longueur){
        this.profondeur = profondeur;
        this.tabCouleur = tabCouleur;
        this.longueur = longueur;
        this.file = new File("mdp.txt");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        }
        catch (IOException e) {
            e.printStackTrace();

        }
    }

    /**
     * Comprime les mots de passe en utilisant le processus de compression spécifié.
     */
    public void comprimer(){
        try {

            FileWriter fw = new FileWriter(file.getAbsoluteFile(), false);
            BufferedWriter bw = new BufferedWriter(fw);

            //écrite sur le document texte la profondeur et les différentes couleurs utilisé
            bw.write(Integer.toString(this.profondeur)+" [");
            int indiceCouleur = 0;
            for (String couleur : this.tabCouleur) {
                bw.write(couleur);
                if (indiceCouleur < this.tabCouleur.size() - 1) {
                    bw.write(",");
                    indiceCouleur++;
                }
            }
            bw.write("]\n");

            //alphabet utilisé dans les mots de passe génére
            String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789[]^_!\"#$%&\'()*+,-./:;{}<>=|~?";

            // crée le premier mot de passe de chaque ligne et l'envoie dans la fonction générer
            for(int i = 0; i< longueur;i++){
                Random rand = new Random();
                int longueurMotDePasse = rand.nextInt(15)+8;
                String mdp="";
                for(int z = 0; z < longueurMotDePasse; z++) {
                    char charactere = alphabet.charAt(rand.nextInt(alphabet.length()));
                    mdp += charactere;
                }
                generer(mdp, bw);
            }
            bw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Génère un mot de passe compressé et l'écrit dans le fichier de sortie.
     *
     * @param motDePasse Le mot de passe d'origine.
     * @param bw Le BufferedWriter utilisé pour écrire dans le fichier de sortie.
     */
    public void generer(String motDePasse, BufferedWriter bw){
        //génere le hash correspondant au mot de passe envoyer dans la fonction
        String mdp = motDePasse;
        Hash hashable = new Hash(mdp);
        String hash = hashable.creeHash();

        Rainbow rainbow = new Rainbow();
        int indiceCouleur = 0;
        //effectue un nombre de fois égal à la profondeur -1 de transformation 
        for(int i = 0 ; i<this.profondeur-1;i++){
            String hashReduc = rainbow.ApplicationColor(this.tabCouleur.get(indiceCouleur),hash);
            Generate generate = new Generate(hashReduc);
            mdp = generate.generatePassword();
            hashable = new Hash(mdp);
            hash = hashable.creeHash();
            if(indiceCouleur>=this.tabCouleur.size()-1){
                indiceCouleur = 0;
            }
            else{
                indiceCouleur = indiceCouleur +1;
            }
        }
        try {
            bw.write(motDePasse + " " + hash+"\n");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}