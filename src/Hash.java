package src;

import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;

/**
 * La classe Hash est utilisée pour créer un hash à partir d'un mot de passe.
 */
public class Hash {

    private String password;

    /**
     * Constructeur de la classe Hash.
     *
     * @param password Le mot de passe à hasher.
     */
    public Hash(String password){
        this.password = password;
    }

    /**
     * Retourne le mot de passe associé à l'objet Hash.
     *
     * @return Le mot de passe.
     */
    public String getPassword(){
        return this.password;
    }

    /**
     * Crée et retourne le hash SHA-256 du mot de passe.
     *
     * @return Le hash SHA-256 du mot de passe.
     */
    public String creeHash(){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sha = new StringBuilder();
            for (byte b : digest) {
                sha.append(String.format("%02x", b));
            }
            return(sha.toString().toLowerCase());
        } catch (Exception e) {
            return(e.getMessage());
        }
    }
}