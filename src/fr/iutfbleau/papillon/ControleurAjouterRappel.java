package fr.iutfbleau.papillon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
/**
 * Controleur qui permet de gérer l'ajout de rappel (si le titre et la description sont valides)
 */
public class ControleurAjouterRappel implements ActionListener{

    /**
     * Variable de la fenêtre d'ajout de rappel
     */
    private VueAjouterRappel vue;

    /**
     * Crée un controleur pour ajouter un rappel spécifique.
     * 
     * @param vue la vue contenant les informations saisies par l'utilisateur
     */
    public ControleurAjouterRappel(VueAjouterRappel vue) {
        this.vue = vue;
    }

 
    /**
     * Methode appelée pour gérer les entrées de l'utilisateur lors de l'ajout d'un rappel.
     * Vérifie les entrées de l'utilisateur, affiche un message d'erreur si nécessaire ou ajoute le rappel dans la base de données si les informations sont valides.
     * @param e l'action déclenchée
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String titre = vue.getTitre();
        String desc = vue.getDescription();
        int couleur = vue.getCouleurIndex();
        
            if (VueAjouterRappel.titreValid(titre) && VueAjouterRappel.descriptionValid(desc)){
                ModeleRappelBD.ajouterRappelBD(titre, desc, couleur);
                JOptionPane.showMessageDialog(
                    null, "Rappel " + titre + " ajouté !"
                );
                if (VuePapillon.instancePrincipale != null) {
                    VuePapillon.instancePrincipale.rafraichirRappels();
                }
                vue.dispose();
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