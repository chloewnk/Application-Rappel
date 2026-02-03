package fr.iutfbleau.papillon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;

/**
 * Gestion des couleurs (cela facilite l'affichage et la sauvegarde de la couleur choisie par l'utilisateur)
 * Facilite l'affichage et la sauvegarde du thème choisi par l'utilisateur.
 */
public class ControleurChangerCouleur implements ActionListener {

    /**
     * Panel dont la couleur de fond sera modifié
     */
    private JPanel panel;
    /**
     * Le combo box qui contient la selection de couleur
     */
    private JComboBox<String> combo;

    /**
     * Création d'un controleur pour changer la couleur d'un panel
     * @param panel sur lequel le changement de couleur s'applique
     * @param combo le choix de couleur
     */
    public ControleurChangerCouleur(JPanel panel, JComboBox<String> combo) {
        this.panel = panel;
        this.combo = combo;
    }

    /**
     * Méthode appelée lorsqu'une action est effectuée sur le JComboBox
     * Change la couleur de fond du JPanel en fontion de l'élément selectionné
     * @param e L'action déclenchée
     */
    public void actionPerformed(ActionEvent e) {
        String theme = (String) combo.getSelectedItem();
        panel.setBackground(ModeleGestionIndexEtCouleur.getColorFromTheme(theme));
        panel.repaint();
    }
}
