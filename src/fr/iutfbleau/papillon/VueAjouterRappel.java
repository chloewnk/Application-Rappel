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
 * Fenêtre permettant à l'utilisateur d'ajouter un nouveau rappel
 */
public class VueAjouterRappel extends JFrame{

    /**
     * Menu déroulant pour les thèmes (couleur)
     */
    private JComboBox<String> menu; // concept pris d'internet

    /**
     * Bouton pour valider l'ajout du rappel
     */
    private JButton ajout;

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
     * Créer une fenêtre pour ajouter un rappel
     * Initialise tous les composant graphique et les évenements associés
     */
    public VueAjouterRappel(){
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
        fieldTitre = new JTextField(20);
        gbc.gridx=0;
        gbc.gridy=0;
        ajoutRappel.add(labelTitre,gbc);
        gbc.gridx=1;
        gbc.gridy=0;
        ajoutRappel.add(fieldTitre,gbc);


        JLabel labelDescription = new JLabel("Entrez une courte description :");
        labelDescription.setFont(new Font("Segoe UI", Font.BOLD, 15));
        fieldDescription = new JTextArea(4,20);
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
        gbc.gridx=1;
        gbc.gridy=2;
        ajoutRappel.add(menu,gbc);

        // bouton pour enregistrer
        ajout = new JButton("+");
        gbc.gridx=1;
        gbc.gridy=5;
        ajoutRappel.add(ajout,gbc);
        ajout.addActionListener(new ControleurAjouterRappel(this));

        ajoutRappel.setBackground(ModeleGestionIndexEtCouleur.getColorFromTheme("Jaune")); // Valeur par defaut : jaune (car la position du menu deroulant est sur jaune de base)
        add(ajoutRappel);
        setVisible(true);
    
    }

    /**
     * Vérifie si le titre saisi est valide
     * @param titre Le titre à vérifier
     * @return true si le titre est valide, false sinon
     */
    public static boolean titreValid(String titre){
        if (titre == null || titre.isEmpty()) return false; // titre vide
        if (titre.length() > 50) return false; // plus que 5O char
        if (titre.contains("\n")) return false; // pas de saut lignes
        if (!titre.matches(".*[A-Za-z0-9].*")) return false; // au moins une lettre ou un chiffre
        return true;
    }

    /**
     * Vérifie si la description est valide
     * @param description La description à vérifier
     * @return true si la description est valide, false sinon
     */
    public static boolean descriptionValid(String description) {
        if (description == null || description.isEmpty()) return true; // description vide autorisé
        String[] lignes = description.split("\n", -1); // découpe par ligne
        if (lignes.length > 4) return false; // max 4 lignes
        for (String ligne : lignes) {
            if (ligne.length() > 50) return false; // chaque ligne max 50 caractères
        }
        return true;
    }

    /**
     * Permet de récupérer le titre entré par l'utilisateur 
     * @return le titre
     */
    public String getTitre() { 
        return fieldTitre.getText(); 
    }

    /**
     * Permet de récupérer la description la description entré par l'utilisteur
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
        return menu.getSelectedIndex(); 
    }

    
    
}
