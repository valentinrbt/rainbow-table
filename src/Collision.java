package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Collision {
    private ArrayList col = new ArrayList();

    public Collision(){}

    /**
     * Cette fonction regarde si il y a des collisions de hash dans le fichier "mdp.txt".
     */
    public void collisionDansFichier() {
        try (BufferedReader br = new BufferedReader(new FileReader("mdp.txt"))) {
            String line; 
            if ((line = br.readLine()) != null) {
                String[] partie = line.split(" "); 
                if (partie.length >= 2) {
                } else {
                    System.out.println("Erreur de format dans la première ligne : " + line);
                }
                // fin de l'extraction de la premiére ligne
            } 
            else {
                System.out.println("Le fichier est vide");
            }
            int ligne = 1;
            int i = 0;
            while ((line = br.readLine()) != null) { // Lire les lignes suivantes
                String[] partie = line.split(" ");
                if (partie.length >= 2) {
                    String hash = partie[1];
                    if(col.contains(hash)){
                        ligne+=1;
                        i+=1;
                        System.out.println("Collision trouvée : " + hash+" première apparition ligne :  "+(col.indexOf(hash)+2) +" et à la ligne : "+ligne );
                    }
                    else{
                        ligne+=1;
                        col.add(hash);
                    }
                } else {
                    System.out.println("Erreur de format dans la ligne : " + line);
                }
            }
            if (i == 0){
                System.out.println("Aucune collision trouvée");
            }      
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Collision c = new Collision();
        c.collisionDansFichier();
    }
}