package src;

/**
 * La classe Rainbow est utilisée pour effectuer des réductions de hash en fonction de couleurs spécifiques.
 */
public class Rainbow {

    /**
     * Constructeur par défaut de la classe Rainbow.
     */
    public Rainbow(){}

    /**
     * Prend les 32 premiers caractères du hash.
     *
     * @param hash Le hash d'entrée.
     * @return Une réduction du hash en prenant les 32 premiers caractères.
     */
    public String Purple(String hash){
        String reductionPurple="";
        reductionPurple = hash.substring(0, 32);
        return reductionPurple;
    }

    /**
     * Prend les 32 derniers caractères du hash.
     *
     * @param hash Le hash d'entrée.
     * @return Une réduction du hash en prenant les 32 derniers caractères.
     */
    public String Blue(String hash){
        String reductionBlue="";
        reductionBlue= hash.substring(32, hash.length());
        return reductionBlue;
    }

    /**
     * Prend les caractères de la position 8 à 21 et de la position 23 à 29 du hash.
     *
     * @param hash Le hash d'entrée.
     * @return Une réduction du hash en prenant certains caractères spécifiques.
     */
    public String Green(String hash){
        String reductionGreen="";
        reductionGreen=hash.substring(8, 21);
        reductionGreen+=hash.substring(23, 29);
        return reductionGreen;
    }

    /**
     * Prend tous les caractères sauf 'e', 'm', 'a', 'w', 'l', 'n' et 't' du hash.
     *
     * @param hash Le hash d'entrée.
     * @return Une réduction du hash en excluant certains caractères.
     */
    public String Yellow(String hash){
        String reductionYellow="";
        for( int i = 0 ; i < hash.length();i++){
            if(hash.charAt(i)!='m' && hash.charAt(i)!='a' && hash.charAt(i)!='e' && hash.charAt(i)!='l' && hash.charAt(i)!='w' && hash.charAt(i)!='n' && hash.charAt(i)!='t'){
                reductionYellow+=hash.charAt(i);
            }
        }
        return reductionYellow;
    }

    /**
     * Prend les caractères du hash aux positions impaires.
     *
     * @param hash Le hash d'entrée.
     * @return Une réduction du hash en prenant les caractères aux positions impaires.
     */
    public String Orange(String hash){
        String reductionOrange = "";
        for(int i = 0 ; i < hash.length() ; i++){
            if(!(i%2==0)){
                reductionOrange+=hash.charAt(i);
            }
        }
        return reductionOrange;
    }

    /**
     * Prend les caractères du hash aux positions paires.
     *
     * @param hash Le hash d'entrée.
     * @return Une réduction du hash en prenant les caractères aux positions paires.
     */
    public String Red(String hash){
        String reductionRed="";
        for(int i = 0 ; i < hash.length() ; i++){
            if(i%2==0){
                reductionRed+=hash.charAt(i);
            }
        }
        return reductionRed;
    }

    /**
     * Applique une réduction de hash en fonction de la couleur spécifiée.
     *
     * @param color La couleur spécifiée.
     * @param hash Le hash d'entrée.
     * @return Une réduction du hash en fonction de la couleur spécifiée.
     */
    public String ApplicationColor(String color, String hash){
        switch (color) {
            case "Green":
                return Green(hash);
            case "Blue":
                return Blue(hash);
            case "Purple":
                return Purple(hash);
            case "Yellow":
                return Yellow(hash);
            case "Orange":
                return Orange(hash);  
            case "Red":
                return Red(hash);
            default:
                return "None";
        }
    }
}