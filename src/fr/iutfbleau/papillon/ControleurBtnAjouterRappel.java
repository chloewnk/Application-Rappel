package fr.iutfbleau.papillon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * Controleur qui gère le bouton pour ajouter un rappel.
 */
public class ControleurBtnAjouterRappel implements ActionListener{

    /**
     * Bouton associé qui permet de "déclencher le controleur"
     */
    private JButton plus;

    /**
    * Création d'un controleur d'ajout de rappel avec le bouton "+"
    * Le bouton est associé à un ActionListener
    */
    public ControleurBtnAjouterRappel(){
        plus = new JButton("+");
        plus.addActionListener(this);     
    }

    /**
     * Ecoute les actions sur le bouton "+"" qui renvoie un message de confirmation si l'utilisateur veut bien ajouter ou non un rappel
     * @param e evenement associé au bouton
     */
    public void actionPerformed(ActionEvent e){
        Object[] choix= {"Non", "Oui !"};
        JOptionPane question = new JOptionPane(
            "Voulez-vous ajouter un nouveau rappel ?",
            JOptionPane.QUESTION_MESSAGE,
            JOptionPane.DEFAULT_OPTION,
            null,
            choix,
            null
        );

        JDialog dialog = question.createDialog("Question");
        dialog.setLocation(500, 480);
        dialog.setVisible(true);

        Object reponse = question.getValue();
        if (reponse.equals(choix[1])) {
            new VueAjouterRappel();
        }
    }

    /**
     * retourne le bouton
     * @return le bouton qui déclenche l'ajout d'un rappel
     */
    public JButton getButton() {
        return plus;
    }
}