package fr.iutfbleau.papillon;

import java.awt.Color;

/**
 * Modèle créé pour deux raisons principales : <br>
 *      1. La correspondance entre les index de données et les chaînes de caractères correspondantes <br>
 * -> Utilisé dans le cas des listes déroulantes (de couleur ou d'importance), cela permet de faire correspondre un index avec la valeur correspondante
 *<br>
 *      2. Permettre une gestion plus pratique des couleur et de l'affichage sur les différentes fenêtres<br>
 * -> Utilisé pour la correspondance entre une couleur sous forme de chaine de caractères et le type Color de Java<br>
 */
public class ModeleGestionIndexEtCouleur {

    /**
     * Constructeur par défaut
     */
    public ModeleGestionIndexEtCouleur(){}


    /**
    * Convertit un numéro de couleur (stocké dans la BD par exemple) en la chaîne de caractères correspondante
    * @param index le numéro de la couleur
    * @return la couleur correspondante sous forme de chaîne de caractères
    */
    public static String getChaineFromIndexCouleur(int index) {
        switch(index) {
            case 0:
                return "Jaune";
            case 1:
                return "Orange";
            case 2:
                return "Bleu";
            case 3:
                return "Violet";
            case 4:
                return "Vert";
            default:
                return "Jaune";
        }
    }



    /**
    * Cette méthode prend en paramètre la couleur du rappel sous forme de chaîne de caractères, et renvoie l'index correspondant
    * @param couleur la chaine de caractères représentant la couleur du rappel
    * @return l'entier correspondante à cette couleur si l'entrée est valide, ou bien 1 comme valeur par défaut
    */
    public static int getIndexFromCouleur(String couleur) {
        switch (couleur) {
                case "Jaune":
                    return 0;
                case "Orange":
                    return 1;
                case "Bleu":
                    return 2;
                case "Violet":
                    return 3;
                case "Vert":
                    return 4;
                default:
                    return 0;
        }
    }


    // === PARTIE GESTION COULEUR ===


    /**
     * Retourne la couleur correpondant à un thème donné
     * @param theme le nom du thème
     * @return la couleur associée au thème (type Color)
     */
    public static Color getColorFromTheme(String theme) {
        switch(theme) {
            case "Jaune":
                return new Color(0xDCF941);
            case "Orange":
                return new Color(0xFA9641);
            case "Bleu":
                return new Color(0x3A83FA);
            case "Violet":
                return new Color(0xDD3FFA);
            case "Vert":
                return new Color(0x0FA854);
            default:
                return new Color(0xDCF941);
        }
    }

    /**
     * Renvoie la couleur correspondante à un index donné
     * @param index indice de la couleur (stocké dans la BD)
     * @return la couleur associé à l'indice
     */
    public static Color getColorFromIndex(int index) {
        switch(index) {
            case 0:
                return new Color(0xDCF941);
            case 1:
                return new Color(0xFA9641);
            case 2:
                return new Color(0x3A83FA);
            case 3:
                return new Color(0xDD3FFA);
            case 4:
                return new Color(0x0FA854);
            default:
                return new Color(0xDCF941);
        }
    }
}
