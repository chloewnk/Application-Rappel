package fr.iutfbleau.papillon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

/**
 * Classe qui permet la gestion de la BD (affichage, ajout, suppression et modification)
 */
public class ModeleRappelBD {

    private static final String URL = "jdbc:mariadb://dwarves.iut-fbleau.fr/wiencek";
    private static final String USER = " ";
    private static final String MDP = " ";

    /**
     * Constructeur par défaut
     */
    public ModeleRappelBD(){}
    /**
     * Cette méthode récupère tous les rappels enregistrés dans la base de données
     * @return une liste de rappels et leurs données
     */
    public static List<ModeleRappel> getRappels() {
        List<ModeleRappel> rappels = new ArrayList<>();

        try {
			Connection cnx = DriverManager.getConnection(URL,USER,MDP);            
            
                try {
                
                    PreparedStatement req = cnx.prepareStatement("SELECT id, titre, description, couleurIndex, rang FROM rappels ORDER BY rang");
                    ResultSet rs = req.executeQuery();

                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String titre = rs.getString("titre");
                        int couleurIndex = rs.getInt("couleurIndex");
                        String description = rs.getString("description");
                        int rang = rs.getInt("rang");
                        rappels.add(new ModeleRappel(id, titre,couleurIndex,description,rang));
                    }
                    
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "erreur"+e.getMessage());
                }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erreur"+e.getMessage());
        }

        return rappels;
    }


    /**
     * Cette méthode ajoute un rappel dans la base de données
     *
     * @param titre le titre du rappel à créer
     * @param description la description du rappel
     * @param couleurIndex le thème du rappel
     * @return 0 si l'ajout a réussi, 1 si une erreur s'est produite
     */
    public static int ajouterRappelBD(String titre, String description, int couleurIndex){

        int rangMaximum = getRangMaximum();

        try {
        Connection cnx = DriverManager.getConnection(URL,USER,MDP);          

            try {
                PreparedStatement req = cnx.prepareStatement("INSERT INTO rappels (titre, description, couleurIndex, rang) VALUES (?, ?, ?, ?)");
                req.setString(1, titre);
                req.setString(2, description);
                req.setInt(3, couleurIndex);
                req.setInt(4, rangMaximum + 1);
                req.executeUpdate();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erreur"+e.getMessage());
                return 1;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erreur"+e.getMessage());
            return 1;
        }

        return 0;

    }


    /**
     * Cette méthode supprime un rappel enregistré dans la base de données
     *
     * @param id le numéro d'identification du rappel à supprimer
     * @return 0 si la suppression a réussi, 1 si une erreur s'est produite
     */
    public static int suppRappel(int id){

        try {
			Connection cnx = DriverManager.getConnection(URL,USER,MDP);            
            
                try {
                    PreparedStatement req = cnx.prepareStatement("DELETE FROM rappels WHERE id = ?");
                    req.setInt(1, id);
                    req.executeUpdate();                    
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "erreur"+e.getMessage());
                    return 1;
                }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erreur"+e.getMessage());
            return 1;
        }
        return 0;
    }


    /**
     * Cette méthode modifie un rappel enregistré dans la base de données
     *
     * @param id le numéro d'identification du rappel à modifier
     * @param titre le nouveau titre du rappel
     * @param description la nouvelle description du rappel
     * @param couleurIndex le nouveau thème du rappel
     * @return 0 si la mise à jour a réussi, 1 si une erreur s'est produite
     */
    public static int modifRappel(int id, String titre, String description, int couleurIndex){

        try {
            Connection cnx = DriverManager.getConnection(URL,USER,MDP);

            try {
                PreparedStatement req = cnx.prepareStatement("UPDATE rappels SET titre = ?, description = ?, couleurIndex = ? WHERE id = ?");
                req.setString(1, titre);
                req.setString(2, description);
                req.setInt(3, couleurIndex);
                req.setInt(4, id);
                req.executeUpdate();
                cnx.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erreur"+e.getMessage());
                cnx.close();
                return 1;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erreur"+e.getMessage());
            return 1;
        }
        return 0;
    }


    /**
     * Cette méthode récupère le rang le plus élevé dans la base de données
     *
     * @return le rang maximal, ou 0 s'il n'y a aucun rappel dans la BD
     */
    public static int getRangMaximum() {

        try {
            Connection cnx = DriverManager.getConnection(URL,USER,MDP);

            try {
                PreparedStatement req = cnx.prepareStatement("SELECT MAX(rang) AS rangMaximum FROM rappels");
                ResultSet rs = req.executeQuery();

                if (rs.next()) {
                    int rang = rs.getInt("rangMaximum");
                    return rang;
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erreur"+e.getMessage());
                return 0;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erreur"+e.getMessage());
            return 0;
        }
        return 0;
    }


    /**
     * Cette méthode récupère l'id d'un rappel depuis le rang
     *
     * @param rang le rang du rappel
     * @return l'id du rappel, ou 0 si un problème est survenu
     */
    public static int getIdDepuisRang(int rang) {

        // System.out.println("Recherche de l'ID du rappel avec le rang : " + rang);

        try {
            Connection cnx = DriverManager.getConnection(URL,USER,MDP);

            try {
                PreparedStatement req = cnx.prepareStatement("SELECT id FROM rappels WHERE rang = ?");
                req.setInt(1, rang);
                ResultSet rs = req.executeQuery();

                if (rs.next()) {
                    int idRappel = rs.getInt("id");
                    // System.out.println("ID du rappel trouvé : " + idRappel);
                    return idRappel;
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erreur"+e.getMessage());
                return 0;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erreur"+e.getMessage());
            return 0;
        }
        return 0;
    }

    /**
    * Cette méthode permet d'echanger le rang de deux rappels
    * @param rang1 le rang du premier rappel
    * @param rang2 le rang du second rappel
    * @param id1 l'ID du premier rappel
    * @return 0 si l'échange a réussi, 1 si une erreur s'est produite
    */
    public static int echangerRangRappel(int rang1, int rang2, int id1){

        int id2 = getIdDepuisRang(rang2);

        // System.out.println("Échange des rangs : rang1 = " + rang1 + ", rang2 = " + rang2 + ", id1 = " + id1 + ", id2 = " + id2);

        try {
            Connection cnx = DriverManager.getConnection(URL,USER,MDP);

            try {
                PreparedStatement req = cnx.prepareStatement("UPDATE rappels SET rang = ? WHERE id = ?");
                req.setInt(1, rang2);
                req.setInt(2, id1);
                req.executeUpdate();
                req.setInt(1, rang1);
                req.setInt(2, id2);
                req.executeUpdate();
                cnx.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erreur"+e.getMessage());
                cnx.close();
                return 1;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erreur"+e.getMessage());
            return 1;
        }
        return 0;
    }

    
    /**
    * Cette méthode permet de combler les "trous" dans les rangs des rappels  à la suited'une suppression
    */
    public static void ajusterRangs() {
        try {
            Connection cnx = DriverManager.getConnection(URL, USER, MDP);

            try {
                PreparedStatement reqSelect = cnx.prepareStatement("SELECT id, rang FROM rappels ORDER BY rang");
                ResultSet rs = reqSelect.executeQuery();

                int dernierRang = 0;

                while (rs.next()) {
                    int id = rs.getInt("id");
                    int rang = rs.getInt("rang");

                    if (rang != dernierRang + 1) {
                        // System.out.println("trou à l'ID " + id);
                        PreparedStatement reqUpdate = cnx.prepareStatement("UPDATE rappels SET rang = ? WHERE id = ?");
                        reqUpdate.setInt(1, dernierRang + 1);
                        reqUpdate.setInt(2, id);
                        reqUpdate.executeUpdate();
                    }

                    dernierRang++;
                }
                cnx.close();
            } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erreur"+e.getMessage());
                return;
            }
        } catch (SQLException e) {
           JOptionPane.showMessageDialog(null, "erreur"+e.getMessage());
            return;
        }
    }
}