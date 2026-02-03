package fr.iutfbleau.papillon;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * JPanel représentant le panel d'un rappel
 */
public class VueRappelPanel extends JPanel {

    /**
     * Bouton qui permet de supprimer un rappel
     */
    private JButton supp;

    /**
     * Bouton qui permet d'afficher un rappel
     */
    private JButton afficher;

    /**
     * Création d'un panel pour un rappel spécifique
     * Initialisation du titre, boutons, couleur de fond
     * @param rappel Le modèle du rappel à représenter
     */
    public VueRappelPanel(ModeleRappel rappel) {
        setLayout(new BorderLayout());

        JPanel panelTexte = new JPanel();
        panelTexte.setLayout(new BoxLayout(panelTexte, BoxLayout.Y_AXIS));

        JLabel titreLabel = new JLabel(rappel.getTitre());
        titreLabel.setFont(new Font("SansSerif", Font.BOLD, 16));

        panelTexte.add(titreLabel);

        add(panelTexte, BorderLayout.CENTER);

        JPanel panelBoutons = new JPanel(new FlowLayout(FlowLayout.RIGHT,3,3)); // pour que les bouttons se mettent à droite


        // gestion des boutons Afficher et Supprimer

        supp = new JButton("Supprimer");
        afficher = new JButton("Afficher");

        supp.addActionListener(new ControleurSupprimerRappel(rappel, this));
        afficher.addActionListener(new ControleurAfficheRappel(rappel));

        panelBoutons.add(afficher);
        panelBoutons.add(supp);

        panelTexte.setBackground(ModeleGestionIndexEtCouleur.getColorFromIndex(rappel.getCouleurIndex()));
        panelBoutons.setBackground(ModeleGestionIndexEtCouleur.getColorFromIndex(rappel.getCouleurIndex()));

        add(panelBoutons, BorderLayout.SOUTH);
    }
}
