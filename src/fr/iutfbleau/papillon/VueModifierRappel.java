package fr.iutfbleau.papillon;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Vue principale d'affichage du rappel.
 * Récupère les entrées de l'utilisateur.
 */
public class VueModifierRappel extends JFrame{

    /**
     * Menu déroulant pour les thèmes (couleur)
     */
    private JComboBox<String> menu; // concept pris d'internet

    /**
     * Bouton ajouter à la fin d'entrer tous les champs
     */
    private JButton save;

    /**
     * Panel principal
     */
    private JPanel ajoutRappel;

    /**
     * Boite où l'utilisateur entre le titre du rappel
     */
    private JTextField fieldTitre;

    /**
     * Boite où l'utilisateur entre la description du rappel
     */
    private JTextArea fieldDescription;

    /**
     * Modèle du rappel à modifier
     */
    private ModeleRappel rappel;

    /**
     * Vue parente qui a affiché ce rappel
     */
    private VueAfficherRappel parentAfficher;


    /**
     * Créer une fenêtre pour modifier un rappel.
     * Initialise les composants graphiques, configure les événements et affiche la fenêtre.
     * @param rappel le modèle du rappel à modifier
     * @param parent la vue parente affichant le rappel
     */
    public VueModifierRappel(ModeleRappel rappel, VueAfficherRappel parent) {

        this.rappel = rappel;
        this.parentAfficher = parent;

        setLocation(660,380);
        setSize(600,400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        ajoutRappel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // saisie du titre & descripiton

        JLabel labelTitre = new JLabel("Nom du rappel :");
        labelTitre.setFont(new Font("Segoe UI", Font.BOLD, 15));
        fieldTitre = new JTextField(rappel.getTitre()); 
        gbc.gridx=0;
        gbc.gridy=0;
        ajoutRappel.add(labelTitre,gbc);
        gbc.gridx=1;
        gbc.gridy=0;
        ajoutRappel.add(fieldTitre,gbc);


        JLabel labelDescription = new JLabel("Entrez une courte description :");
        labelDescription.setFont(new Font("Segoe UI", Font.BOLD, 15));
        fieldDescription = new JTextArea(rappel.getDescription(), 4, 20);
        gbc.gridx=0;
        gbc.gridy=1;
        ajoutRappel.add(labelDescription,gbc);

        gbc.gridx=1;
        gbc.gridy=1;
        ajoutRappel.add(fieldDescription,gbc);

        // sasie du theme

        JLabel labelMenu = new JLabel("Choisissez un thème :");
        labelMenu.setFont(new Font("Segoe UI", Font.BOLD, 15));
        gbc.gridx=0;
        gbc.gridy=2;
        ajoutRappel.add(labelMenu,gbc);

        menu = new JComboBox<>(new String[]{"Jaune","Orange","Bleu","Violet","Vert"});
        menu.addActionListener(new ControleurChangerCouleur(ajoutRappel, menu));
        menu.setSelectedItem(ModeleGestionIndexEtCouleur.getChaineFromIndexCouleur(rappel.getCouleurIndex()));
        gbc.gridx=1;
        gbc.gridy=2;
        ajoutRappel.add(menu,gbc);

        ajoutRappel.setBackground(ModeleGestionIndexEtCouleur.getColorFromIndex(rappel.getCouleurIndex()));

        save = new JButton("Sauvegarder les modifications");
        gbc.gridx=1;
        gbc.gridy=5;
        ajoutRappel.add(save,gbc);
        save.addActionListener(new ControleurModifierRappel(this));

        add(ajoutRappel);
        setVisible(true);
    }


    /**
     * Permet de récupérer le titre entré par l'utilisateur 
     * @return le titre
     */
    public String getTitre() { 
        return fieldTitre.getText(); 
    }


    /**
     * Permet de récupérer la description entrée par l'utilisteur
     * @return la description
     */
    public String getDescription() { 
        return fieldDescription.getText(); 
    }


    /**
     * Permet de récupérer l'index de la couleur choisie par l'utilisateur
     * @return l'index de la couleur
     */
    public int getCouleurIndex() { 
        return (menu.getSelectedIndex());
    }


    /**
     * Permet de retourner l'ID du rappel modifié
     * @return l'id
     */
    public int getId(){
        return rappel.getID();
    }

    
    /** 
     * Retourne la vue parente affichant le rappel 
     * @return la vue
     * */
    public VueAfficherRappel getParentAfficher() {
        return parentAfficher;
    }

}