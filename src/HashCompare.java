package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * La classe HashCompare est utilisée pour comparer un hash avec ceux stockés dans le fichier mdp.txt
 * et récupérer le mot de passe correspondant.
 */
public class HashCompare {

    private String hashToCompare;
    private String hash;
    private ArrayList<String> usedColor;
    private int profondeur;

    
    /**
     * Constructeur de la classe HashCompare.
     *
     * @param hash Le hash à comparer.
     */
    public HashCompare(String hash){
        this.hashToCompare = hash;
        this.hash=hash;
        this.usedColor = new ArrayList<>();
        this.profondeur = 0;

    }

    /**
     * Retourne le hash associé à l'objet HashCompare.
     *
     * @return Le hash à comparer.
     */
    public String getHash(){
        return this.hash;
    }

    /**
     * Compare le hash avec ceux stockés dans le fichier mdp.txt
     * et récupère le mot de passe correspondant s'il existe.
     *
     * @param mdp Le mot de passe à comparer.
     * @return Le mot de passe correspondant au hash ou null s'il n'y en a pas.
     */
    public String trouveMdp(String mdp){
        String password=mdp;
        Hash hashable = new Hash(password);
        String hash = hashable.creeHash();
        if(this.hashToCompare.equals(hash)){
            return password;
        }
        else{
            int coul = 0;
            for(int i = 0 ; i < this.profondeur; i++){   
                Rainbow rainbow = new Rainbow();
                String hashReduc = rainbow.ApplicationColor(this.usedColor.get(coul),hash);
                Generate generate = new Generate(hashReduc);
                password = generate.generatePassword();
                hashable = new Hash(password);
                hash = hashable.creeHash();
                if(this.hashToCompare.equals(hash)){
                    return password;
                }
                if(coul>=this.usedColor.size()-1){
                    coul = 0;
                }
                else{
                    coul = coul +1;
                }
            }
            return null;
        } 
    }

    /**
     * Génère un nouveau hash en appliquant une réduction de hash basée sur la couleur fournie.
     *
     * @param couleur La couleur spécifiée pour la réduction de hash.
     * @param hash Le hash d'entrée à partir duquel générer un nouveau hash.
     * @return Le nouveau hash généré après l'application de la réduction de hash.
     */
    public String hashIsNot(String couleur,String hash){
        Rainbow rainbow = new Rainbow();
        String hashReduc = rainbow.ApplicationColor(couleur,hash);
        Generate generate = new Generate(hashReduc);
        String tmpMdp = generate.generatePassword();
        Hash hashable = new Hash(tmpMdp);
        hash = hashable.creeHash();
        return hash;
    }

    /**
     * Cette fonction recherche dans le fichier "mdp.txt" le mot de passe associé à un hash donné.
     *
     * @param rechercheHash Le hash à rechercher dans le fichier "mdp.txt".
     * @return Le mot de passe associé au hash recherché, ou null s'il n'est pas trouvé.
     */
    public String recupHashFichier(String rechercheHash) {
        try (BufferedReader br = new BufferedReader(new FileReader("mdp.txt"))) {
            String line; 
            if ((line = br.readLine()) != null) {
                String[] partie = line.split(" "); 
                if (partie.length >= 2) {
                    int profondeur = Integer.parseInt(partie[0]);
                    String couleurs = partie[1];
                    couleurs = couleurs.replaceAll("\\[","");
                    couleurs = couleurs.replaceAll("\\]","");
                    String[] couls = couleurs.split(",");
                    this.profondeur=profondeur;
                    for(int i = 0 ; i < couls.length ; i++){
                        if (!this.usedColor.contains(couls[i])) {
                            this.usedColor.add(couls[i]);
                        }
                    }
                } else {
                    System.out.println("Erreur de format dans la première ligne : " + line);
                    return null;
                }
                // fin de l'extraction de la premiére ligne
            } 
            else {
                System.out.println("Le fichier est vide");
                return null;
            }
            while ((line = br.readLine()) != null) { // Lire les lignes suivantes
                String[] partie = line.split(" ");
                if (partie.length >= 2) {
                    String password = partie[0];
                    String hash = partie[1];
                    if(rechercheHash.equals(hash)){
                        String mdp = trouveMdp(password);
                        return mdp;
                    }        
                } else {
                    System.out.println("Erreur de format dans la ligne : " + line);
                    return null;
                }
            }      
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Recherche le mot de passe associé à un hash.
     *
     * @return Le mot de passe associé au hash, ou null s'il n'est pas trouvé.
     */
    public String rechercheMdp() {
        String mdp1lgn = recupHashFichier(this.hashToCompare);
        if (mdp1lgn != null) {
            return mdp1lgn;
        }
        ArrayList<String> listeHash = new ArrayList<String>();
        for (int j = 0; j < this.usedColor.size(); j++) {
            listeHash.add(this.hashToCompare);
        }
        int coul = 0;
        for (int i = 0; i < this.profondeur; i++) {
            for (int j = 0; j < this.usedColor.size(); j++) {
                listeHash.set(j, hashIsNot(this.usedColor.get(coul), listeHash.get(j)));
                String tmp = recupHashFichier(listeHash.get(j));
                if (tmp != null) {
                    return tmp;
                }
                coul = (coul + 1) % this.usedColor.size();
            }
            coul = (coul + 1) % this.usedColor.size();
        }
        return null;
    }
}