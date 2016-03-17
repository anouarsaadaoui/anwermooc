/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import entities.Categories;
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


public class CategorieDAO {
    private Connection cnn;
    private PreparedStatement pstm;

    public CategorieDAO() {
        cnn = new MyConnexion().connect();

    }

    public void ajouterCategorie(Categories c){
        try {
            pstm = cnn.prepareStatement("INSERT into categorie (cate) values (?)");
            pstm.setString(1, c.getCate());
            pstm.executeUpdate();
            System.out.println("categorie ajouté");

        } catch (SQLException ex) {
            Logger.getLogger(CategorieDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }    
 public void supprimerCategorieById(int id) {
        String requete = "delete from categorie where id=?";
        try {
             pstm = cnn.prepareStatement(requete);
            pstm.setInt(1, id);
            pstm.executeUpdate();
            System.out.println("Categorie supprimé");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }
    public void modifierCategorie(Categories c) {
        String requete = "update categorie set cate=? where id=?";
        try {
             pstm = cnn.prepareStatement(requete);
            pstm.setString(1, c.getCate());
            pstm.setInt(2, c.getId());
            pstm.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }
    public List<Categories> findAll() {
        List<Categories> listecategories = new ArrayList<>();
        String requete = "select * from categorie";
        try {
            Statement statement = cnn
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Categories c = new Categories();
                c.setId(resultat.getInt(1));
                c.setCate(resultat.getString(2));
                listecategories.add(c);
            }
            return listecategories;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des depots " + ex.getMessage());
            return null;
        }
    }
public Categories findCategoriesById(int id) {
        Categories categories = new Categories();
        String requete = "select * from categorie where id =?";
        try {
             pstm = cnn.prepareStatement(requete);
            pstm.setInt(1, id);
            ResultSet resultat = pstm.executeQuery();
            while (resultat.next()) {
                categories.setId(resultat.getInt(1));
                categories.setCate(resultat.getString(2));
            }
            return categories;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du categories " + ex.getMessage());
            return null;
        }
    }
    
}
