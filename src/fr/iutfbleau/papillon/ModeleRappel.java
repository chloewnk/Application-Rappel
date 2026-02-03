package fr.iutfbleau.papillon;


/**
 * Classe qui réuni tous les arguments nécessaires à la création d'un rappel
 */
public class ModeleRappel {
    /**
     * Le numero qui permet d'identifier un rappel
     */
    private int id;
    /**
     * Le titre du rappel
     */
    private String titre;
    /**
     * La description du rappel
     */
    private String description;
    /**
     * L'index de la couleur
     */
    private int couleurIndex;
    /**
     * Le rang du rappel
     */
    private int rang;

    /**
     * Construit un Rappel stocké en BD
     * @param id Identifiant unique du rappel de la BD
     * @param titre Titre du rappel
     * @param couleurIndex Index de couleur associé au rappel (permet de récupérer une couleur via ModeleGestionIndexEtCouleur)
     * @param description La description complète du rappel
     * @param rang Le rang du rappel choisi par l'utilisateur
     */
    public ModeleRappel(int id, String titre, int couleurIndex, String description, int rang){
        this.id = id;
        this.titre = titre;
        this.couleurIndex = couleurIndex;
        this.description = description;
        this.rang = rang;
    }

    /**
     * Cette méthode permet de récupérer l'ID d'un rappel
     * 
     * @return id
     */
    public int getID() {
        return id;
    }

    /**
     * Cette méthode permet de récupérer le titre d'un rappel
     * 
     * @return titre
     */
    public String getTitre() {
        return titre;
    }

    /**
     * Cette méthode permet de récupérer la description d'un rappel
     * 
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Cette méthode permet de récupérer la couleur d'un rappel
     * 
     * @return couleur
     */

    public int getCouleurIndex(){
        return couleurIndex;
    }

    /**
    * Cette méthode permet de récupérer le rang d'un rappel
    * 
    * @return rang
    */
    public int getRang(){
        return rang;
    }

    /**
     * Cette méthode permet de modifier le rang d'un rappel
     * 
     * @param rang le nouveau rang du rappel
     */
    public void setRang(int rang) {
        this.rang = rang;
    }

}