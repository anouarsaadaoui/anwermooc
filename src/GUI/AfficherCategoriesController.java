/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import dao.CategorieDAO;
import entities.Categories;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Anouar
 */
public class AfficherCategoriesController implements Initializable {

    @FXML
    private Label idcategorie;
    @FXML
    private Label titrecategorie;
    @FXML
    private ListView<Categories> viewcategories;
    ObservableList<Categories> listec = FXCollections.observableArrayList();
    private Stage primaryStage;

    public AfficherCategoriesController() {
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherlist();

    }
    //afficher liste

    public void afficherlist() {

        CategorieDAO daocategories = new CategorieDAO();
        List<Categories> listcategories = new ArrayList<Categories>();
        listcategories = daocategories.findAll();
        for (Categories c : listcategories) {
            listec.add(c);
            viewcategories.setItems(listec);

        }
        //nétoiyer les détaille
        AfficherCategoriesDétaillé(null);
        //ecouter echange 
        viewcategories.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> AfficherCategoriesDétaillé(newValue));

    }
//Afficher Categories

    private void AfficherCategoriesDétaillé(Categories categories) {
        if (categories != null) {
            idcategorie.setText(categories.getId() + "");
            titrecategorie.setText(categories.getCate());

        } else {
            idcategorie.setText("");
            titrecategorie.setText("");
        }

    }

    @FXML
    private void Ajoutercategories(ActionEvent event) {
        Categories categories = new Categories();
        boolean okClicked = showPersonEditDialog(categories);
        if (okClicked) {
            CategorieDAO categorieDAO = new CategorieDAO();
            categorieDAO.ajouterCategorie(categories);
            listec.add(categories);

            viewcategories.setItems(listec);
        }
    }

    @FXML
    private void Modifiercategories(ActionEvent event) {
        Categories categories = new Categories();
        int selectedIndex = viewcategories.getSelectionModel().getSelectedIndex();

        categories = viewcategories.getSelectionModel().getSelectedItem();
        if (categories != null) {
            boolean okClicked = showPersonEditDialog(categories);
            if (okClicked) {
                new CategorieDAO().modifierCategorie(categories);

                viewcategories.getItems().add(selectedIndex, categories);
                viewcategories.getItems().remove(categories);

                AfficherCategoriesDétaillé(categories);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(primaryStage);
            alert.setTitle("Sélectionner Categorie");
            alert.setHeaderText("Catégories non sélectionner dans la liste");
            alert.setContentText("SVP Cliquer sur une categorie :).");

            alert.showAndWait();
        }
    }

    @FXML
    private void Supprimercaetegories(ActionEvent event) {
        Categories categories = new Categories();
        categories = viewcategories.getSelectionModel().getSelectedItem();

        if (categories != null) {
            viewcategories.getItems().remove(categories);
            new CategorieDAO().supprimerCategorieById(categories.getId());
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(primaryStage);
            alert.setTitle("Sélectionner Categorie");
            alert.setHeaderText("Catégories non sélectionner dans la liste");
            alert.setContentText("SVP Cliquer sur une categorie :).");

            alert.showAndWait();
        }
    }

    /**
     * ouvrir dialog pour modifier une categories. If the user clicks OK,
     * modifier est sauvgarder pour une categories et true est retouner.
     *
     * @param Categories la categories a modifier
     * @returne true si l'utilisateur clicked OK, false autre chose.
     */
    public boolean showPersonEditDialog(Categories categories) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AfficherCategoriesController.class.getResource("CategorieModfierDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // crier   dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            CategorieModfierDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(categories);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
