package fr.iutfbleau.papillon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
* Controleur pour afficher le rappel demande :
* - implémentation d'action listener
* - ouvre la fenetre du rappel
*/
public class ControleurAfficheRappel implements ActionListener {
/** 
 * Implémentation de la variable ModeleRappel qui sera celle affichée dans la vue 
 */
    private ModeleRappel rappel;

    /**
     * créer un controleur pour afficher un rappel spécifique
     * @param rappel le modèle du rappel à afficher
     */
    public ControleurAfficheRappel(ModeleRappel rappel) {
        this.rappel = rappel;
    }

    /**
     * Créer et affiche la fenêtre associé au rappel
     * @param e evenement déclenché par le bouton "afficher"
     */
    public void actionPerformed(ActionEvent e) {
        new VueAfficherRappel(rappel);

    }
}
