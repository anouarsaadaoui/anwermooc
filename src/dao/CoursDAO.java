package dao;

import entities.Categories;
import entities.Cours;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.MyConnexion;

public class CoursDAO {

    private Connection cnn;
    private PreparedStatement pstm;

    public CoursDAO() {
        cnn = new MyConnexion().connect();
    }
public void ajouterCous(Cours c){
        try {
            pstm = cnn.prepareStatement("INSERT into cours (titre,description,datedebut,datefin,difficulte,etat,categorie) values (?,?,?,?,?,?,?)");
            pstm.setString(1, c.getTitre());
            pstm.setString(2, c.getDescription());
            pstm.setString(3, c.getDatedebut()+"");
            pstm.setString(4, c.getDatefin()+"");
            pstm.setString(5, c.getDifficulte());
            pstm.setString(6,"Nonvalider");
            pstm.setInt(7, 1);

            pstm.executeUpdate();
            System.out.println("Cours ajouté");

        } catch (SQLException ex) {
            Logger.getLogger(CategorieDAO.class.getName()).log(Level.SEVERE, null, ex);
        }}
    public void modifierCours(Cours c) {
        String requete = "update cours set titre=?,description=?,datedebut=?,datefin=?,difficulte=?,categorie=? where id=?";
        try {
             pstm = cnn.prepareStatement(requete);
           pstm.setString(1, c.getTitre());
            pstm.setString(2, c.getDescription());
            pstm.setString(3, c.getDatedebut()+"");
            pstm.setString(4, c.getDatefin()+"");
            pstm.setString(5, c.getDifficulte());
            pstm.setInt(6, c.getCategorie().getId());
            pstm.setInt(7, c.getId());
            pstm.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }
 public List<Cours> findAll() {
        List<Cours> listecours = new ArrayList<>();
        String requete = "select * from cours";
        try {
            Statement statement = cnn.createStatement();
            ResultSet resultat = statement.executeQuery(requete);
            CategorieDAO categorieDAO=new CategorieDAO();

            while (resultat.next()) {
                Cours c = new Cours();
                c.setId(resultat.getInt(1));
                c.setTitre(resultat.getString(2));
                c.setDescription(resultat.getString(3));
                c.setDatedebut(resultat.getDate(4));
                c.setDatefin(resultat.getDate(5));
                c.setDifficulte(resultat.getString(6));
                c.setCategorie(categorieDAO.findCategoriesById(resultat.getInt(8)));

                listecours.add(c);
            }
            return listecours;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des depots " + ex.getMessage());
            return null;
        }}
 
  public void supprimerCoursById(int id) {
        String requete = "delete from cours where id=?";
        try {
             pstm = cnn.prepareStatement(requete);
            pstm.setInt(1, id);
            pstm.executeUpdate();
            System.out.println("Cours supprimé");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }
    
    


}
