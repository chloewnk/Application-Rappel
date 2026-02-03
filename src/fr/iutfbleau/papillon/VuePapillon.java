package fr.iutfbleau.papillon;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * Fenetre principale de l'application : la vue principale
 */
public class VuePapillon extends JFrame {

    /**
     * Panel contenant tous les rappels affichés
     */
    private JPanel panelRappels;

    /**
     * Instance de la vue principale utilisée dans le cadre du rafraichissement de vue (lors de la modification ou l'ajout de rappels)
     */
    public static VuePapillon instancePrincipale;

    /**
     * Création de la fenêtre principale et initialise les composants
     * Configure le header, le panneau (titre + boutons), scrollbar
     */
    public VuePapillon(){
        setTitle("Papillon");
        setLocation(15,805);
        setSize(420,280);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        instancePrincipale = this;

        setLayout(new BorderLayout());

        // le 'header' avec le bouton + et le nom de l'app
        ControleurBtnAjouterRappel a = new ControleurBtnAjouterRappel();
        JLabel titre = new JLabel("Papillon");
        JPanel header = new JPanel(new BorderLayout());
        
        header.add(titre, BorderLayout.WEST);
        header.add(a.getButton(), BorderLayout.EAST);
        add(header, BorderLayout.NORTH);

        // rappels
        panelRappels = new JPanel();
        panelRappels.setLayout(new BoxLayout(panelRappels, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(panelRappels); // pour qu'il y ait la scrollbar
        add(scrollPane, BorderLayout.CENTER);       

        chargerRappels();

    }

    /**
     * Cette méthode permet de parcourir la base de données et d'afficher les rappels
     * Sans cette methode il n'y a pas d'affichage
     *  
     * */
    public void chargerRappels() {
        List<ModeleRappel> rappels = ModeleRappelBD.getRappels();
        for (ModeleRappel rappel : rappels) {
            panelRappels.add(new VueRappelPanel(rappel));
        }
        panelRappels.revalidate();
        panelRappels.repaint();

        }

        /**
         * Cette méthode permet de raffraichir l'affichage des rappels sur la page principale
         * Si cette methode n'était pas implémentée, lorsque l'utilisateur modifirait le thème d'un rappel, la couleur effective ne searait pas mise jour dans la fenêtre principale
         *
         * */
        public void rafraichirRappels() {
            panelRappels.removeAll();
            chargerRappels();
        }

}


