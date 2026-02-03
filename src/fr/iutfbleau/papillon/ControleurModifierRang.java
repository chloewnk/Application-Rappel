package fr.iutfbleau.papillon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Contrôleur qui permet de modifier le rang d'un rappel spécifique dans la base de données.
 * Permet d'augmenter ou de diminuer le rang d'un rappel et de mettre à jour la vue
 */
class ControleurModifierRang implements ActionListener {

    /**
     * Le rappel concerné par la modification
     */
    private ModeleRappel rappel;
    /**
     * Direction du changement de rang : "plus" pour augmenter, "moins" pour diminuer
     */
    private String direction;
    /**
     * La vue parente de la fenêtre modifiée
     */
    private VueAfficherRappel vueAffichage;
    
    /**
     * Constructeur du contrôleur de modification de rang
     * @param rappel le modèle du rappel à modifier
     * @param direction la direction du changement de rang ("plus" ou "moins")
     * @param vueAffichage la vue parente de la fenêtre de modification
     */
    public ControleurModifierRang(ModeleRappel rappel, String direction, VueAfficherRappel vueAffichage) {
        this.rappel = rappel;
        this.direction = direction;
        this.vueAffichage = vueAffichage;
    }

    /**
     * Calcule le nouveau rang, échange les rappels dans la base de données, rafraîchit la vue principale et ferme la fenêtre de modification
     */
    public void actionPerformed(ActionEvent e) {
        int rangActuel = rappel.getRang();
        int idRappelActuel = rappel.getID();
        int rangAutreRappel = 0;

        switch (this.direction) {
            case "plus":
                rangAutreRappel = rangActuel - 1;
                break;
            case "moins":
                rangAutreRappel = rangActuel + 1;
                break;
        }

        if (rangAutreRappel <= 0) {
            return;
        }

        ModeleRappelBD.echangerRangRappel(rangActuel, rangAutreRappel, idRappelActuel);
        VuePapillon.instancePrincipale.rafraichirRappels();
        vueAffichage.dispose();
    }
}
