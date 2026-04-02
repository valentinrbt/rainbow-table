package src;

import java.util.Random;

/**
 * La classe Generate est utilisée pour générer un mot de passe.
 */
public class Generate {
    private String hashpw;

    /**
     * Constructeur de la classe Generate.
     *
     * @param hash Le hash.
     */
    public Generate(String hash) {
        this.hashpw = hash;
    }

    /**
     * Génère un mot de passe aléatoire basé sur la valeur de hachage du mot de passe.
     *
     * @return Le mot de passe généré aléatoirement.
     */
    public String generatePassword() {
        long seed = 0;
        for (char c : this.hashpw.toCharArray()) {
            seed = 31L*seed + c;
        }
        Random generator = new Random(seed);
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789[]^_!\"#$%&\'()*+,-./:;{}<>=|~?";
        int n = generator.nextInt(32)+8;//gen nub enter 1-32
        String mdp="";
        for(int z = 0; z < n; z++) {
            char c = alphabet.charAt(generator.nextInt(alphabet.length()));
            mdp += c;
        }
        return(mdp);
    }
}