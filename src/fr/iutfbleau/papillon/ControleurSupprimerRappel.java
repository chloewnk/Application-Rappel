package fr.iutfbleau.papillon;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;


/**
 * Classe implémentant un ActionListener qui permet de supprimer un rappel spécifique de la BD
 * Retire également le panel associé au rappel
 */
public class ControleurSupprimerRappel implements ActionListener {
    /**
     * Le rappel qui est supprimé
     */
    private ModeleRappel rappel;
    /**
     * Panel associé au rappel
     */
    private JPanel panel;

    /**
     * Création du controleur pour supprimer le rappel
     * @param rappel le rappel à supprimer
     * @param panel le panel associé au rappel
     */
    public ControleurSupprimerRappel (ModeleRappel rappel, JPanel panel){
        this.rappel = rappel;
        this.panel = panel;
    }

    /**
     * Méthode appelée lorsqu'une action est effectuée 
     * Affiche un message de confirmation avec JOptionPane
     * Supprime le rappel de la BD
     * @param e déclenché lorque le message de confirmation est validée
     */
    public void actionPerformed (ActionEvent e){
        UIManager.put("OptionPane.yesButtonText", "Oui");   // Concept pris d'internet pour changer la langue des boutons (https://stackoverflow.com/questions/27706197/change-joptionpane-button-text-globally)
        UIManager.put("OptionPane.noButtonText", "Non");   // Concept pris d'internet pour changer la langue des boutons (https://stackoverflow.com/questions/27706197/change-joptionpane-button-text-globally)

        int fenetreConfirm = JOptionPane.showConfirmDialog(null, "Voulez vous supprimer ce rappel ?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (fenetreConfirm==JOptionPane.YES_OPTION){
            ModeleRappelBD.suppRappel(rappel.getID());
            ModeleRappelBD.ajusterRangs();
            Container parent = panel.getParent();
            parent.remove(panel);
            parent.revalidate();
            parent.repaint();
        }
    }
}
