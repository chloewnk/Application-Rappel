package fr.iutfbleau.papillon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

/**
 * Classe qui gère les entrées utilisateur concernant la modification. Implémentation d'un ActionListener.
 */
public class ControleurModifierRappel implements ActionListener {

    /**
     * Vue principale où cette classe va s'appliquer
     */
    private VueModifierRappel vueModifier;
    /**
     * Vue parente de la fenêtre de modification
     */
    private VueAfficherRappel parent;

    /**
     * Créer un contrôleur pour gérer la modification d'un rappel.
     * @param vueModifier la vue principale où la modification d'un rappel s'effectue
     */
    public ControleurModifierRappel(VueModifierRappel vueModifier) {
        this.vueModifier = vueModifier;
        this.parent = vueModifier.getParentAfficher();
    }

    /**
     * Méthode permettant de gérer les entrées de l'utilisateur, affiche un message d'erreur si nécessaire, modifie le rappel dans la base de données, ferme les fenêtres et rafraîchit la liste principale.
     * @param e Action décelenchée
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String titre = vueModifier.getTitre();
        String desc = vueModifier.getDescription();
        int couleur = vueModifier.getCouleurIndex();
        int id = vueModifier.getId();
        
            if (VueAjouterRappel.titreValid(titre) && VueAjouterRappel.descriptionValid(desc)){
                ModeleRappelBD.modifRappel(id,titre, desc, couleur);
                JOptionPane.showMessageDialog(
                    null, "Rappel " + titre + " Modifié !"
                );
                vueModifier.dispose();
                parent.dispose();      
                if (VuePapillon.instancePrincipale != null) {
                    VuePapillon.instancePrincipale.rafraichirRappels();
                }
            } else if(!(VueAjouterRappel.titreValid(titre))){
                JOptionPane.showMessageDialog(
                    null, "erreur, le titre ne doit pas être vide, contenir au moins une lettre ou un chiffre, ne pas dépasser 50 caractères et ne pas contenir de saut de ligne"
                );
            } else if(!(VueAjouterRappel.descriptionValid(desc))){
                JOptionPane.showMessageDialog(
                    null, "erreur, la description doit contenir au maximum 4 lignes de 50 caractères"
                );
            }
    }

}
