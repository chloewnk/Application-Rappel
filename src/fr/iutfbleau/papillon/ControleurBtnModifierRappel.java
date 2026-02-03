package fr.iutfbleau.papillon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
* Controleur qui gère le bouton pour modifier un rappel dans la fenetre VueAfficherRappel
*/
public class ControleurBtnModifierRappel implements ActionListener {

    /** 
     * Modèle du rappel à modifier.
     */
    private ModeleRappel rappel;

    /**
     * La vue parente de ce bouton, utilisée pour positionner ou rafraîchir la modification.
     */
    private VueAfficherRappel parent;


    /**
     * Constructeur appelé lors de la modification d'un rappel
     * @param rappel le modèle du rappel à modifier
     */
    public ControleurBtnModifierRappel(ModeleRappel rappel) {
        this(rappel, null);
    }

    /**
     * Second constructeur lors de la modification d'un rappel avec gestion de la vue parente
     * @param rappel le rappel qui est modifié
     * @param parent la vue parente
     */
    public ControleurBtnModifierRappel(ModeleRappel rappel, VueAfficherRappel parent) {
        this.rappel = rappel;
        this.parent = parent;
    }

    /**
     * Méthode appelée lorsqu'une action est effectuée
     * Affiche la fenêtre de modification de rappel
     * @param e evenement
     */
    public void actionPerformed(ActionEvent e) {
        new VueModifierRappel(rappel, parent);
    }
}
