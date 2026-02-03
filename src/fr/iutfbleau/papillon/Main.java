package fr.iutfbleau.papillon;

/**
 * Classe principale de l'application
 */
public class Main {
    /**
     * Constructeur par défaut
     */
    public Main(){}
    /**
     * Début du programme
     * @param args non utilisés
     */
    public static void main(String[] args) {
        VuePapillon vp = new VuePapillon();
        vp.setAlwaysOnTop(true);
        vp.setVisible(true);
    }
}