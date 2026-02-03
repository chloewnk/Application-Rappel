package fr.iutfbleau.papillon;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


/**
 * Fenêtre permettant d'affiche les détails d'un rappel spécifique
 */
public class VueAfficherRappel extends JFrame {

    /**
     * Le rappel à afficher
     */
    private ModeleRappel rappel;

    /**
     * Bouton pour descendre le rang d'un rappel
     */
    private JButton btnDescendre;

    /**
     * Bouton pour monter le rang d'un rappel
     */
    private JButton btnMonter;

    /**
     * Créer une fenêtre affichant les détails d'un rappel
     * @param rappel Le modèle du rappel à afficher
     */
    public VueAfficherRappel(ModeleRappel rappel) {
        this.rappel = rappel;

        setTitle("Détails du rappel");
        setLocation(780,390);
        setSize(420,350);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelTexte = new JPanel();
        panelTexte.setLayout(new BoxLayout(panelTexte, BoxLayout.Y_AXIS));
        panelTexte.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titre = new JLabel("Titre : " + rappel.getTitre());
        titre.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titre.setAlignmentX(CENTER_ALIGNMENT);
        titre.setHorizontalAlignment(JLabel.CENTER);
        JLabel labelDescription = new JLabel("Description :");
        labelDescription.setFont(new Font("Segoe UI", Font.BOLD, 20));
        labelDescription.setAlignmentX(CENTER_ALIGNMENT);
        labelDescription.setHorizontalAlignment(JLabel.CENTER);
        //JLabel description = new JLabel("Description :" + rappel.getDescription());

        /*
        Concept pris d'internet, cela permet d'afficher la description sur plusieurs lignes
        */
        JTextArea description = new JTextArea(rappel.getDescription());
        description.setFont(new Font("Segoe UI", Font.BOLD, 14));
         description.setLineWrap(true);      // retour à la ligne
         description.setWrapStyleWord(true); // retour à la ligne
         description.setOpaque(false);       // ne pas afficher le fond blanc de la zone de texte
         description.setEditable(false);     // ne pas permettre la modification de la description
         description.setFocusable(false);    // ne pas afficher le curseur
         description.setAlignmentX(CENTER_ALIGNMENT);
        description.setMaximumSize(new Dimension(360, Integer.MAX_VALUE));

        panelTexte.add(titre);
        panelTexte.add(Box.createVerticalStrut(10));
        panelTexte.add(labelDescription);
        panelTexte.add(Box.createVerticalStrut(8));
        panelTexte.add(description);

        add(panelTexte, BorderLayout.CENTER);

        int rangMaximum = ModeleRappelBD.getRangMaximum();
        
        JPanel panelBoutons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        if (rappel.getRang() > 1) {
            btnMonter = new JButton("↑");
            btnMonter.addActionListener(new ControleurModifierRang(rappel, "plus", this));
            panelBoutons.add(btnMonter);
        }
        
        JButton modifier = new JButton("Modifier le rappel");
        modifier.addActionListener(new ControleurBtnModifierRappel(rappel, this));
        panelBoutons.add(modifier);
        
        if (rappel.getRang() < rangMaximum) {
            btnDescendre = new JButton("↓");
            btnDescendre.addActionListener(new ControleurModifierRang(rappel, "moins", this));
            panelBoutons.add(btnDescendre);
        }

        panelBoutons.setBackground(ModeleGestionIndexEtCouleur.getColorFromIndex(rappel.getCouleurIndex()));
        panelTexte.setBackground(ModeleGestionIndexEtCouleur.getColorFromIndex(rappel.getCouleurIndex()));

        add(panelBoutons, BorderLayout.SOUTH);

        setVisible(true);
    }

    /**
     * Raffraichit l'affichage après modification du rappel
     * Ferme la fenêtre actuelle et ouvre une nouvelle avec les informations à jour
     */
    public void rafraichir() {
        dispose();
        new VueAfficherRappel(rappel);
    }

}

