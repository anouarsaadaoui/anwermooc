/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import dao.CategorieDAO;
import dao.CoursDAO;
import entities.Categories;
import entities.Cours;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import test.Test;

/**
 * FXML Controller class
 *
 * @author Anouar
 */
public class AfficherCoursController implements Initializable {

    @FXML
    private ListView<Cours> listview;
    @FXML
    private Label labtitre;
    @FXML
    private Label labdescriptions;
    @FXML
    private Label labdatedebut;
    @FXML
    private Label labdatefin;
    @FXML
    private Label labcatégorie;
    @FXML
    private Label labdifficulte;
    private Window primaryStage;
    private Stage primarystage;
    ObservableList<Cours> listecours = FXCollections.observableArrayList();

    List<Cours> cours = new ArrayList<Cours>();
    List<Categories> categories = new ArrayList<>();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        showListcours();
        // TODO
    }

    public void showListcours() {

        CoursDAO cd = new CoursDAO();
        cours = cd.findAll();
        for (Cours c : cours) {
            listecours.add(c);
            listview.setItems(listecours);

        }
        listview.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> AfficherCoursDetaille(newValue));

    }

    public void AfficherCoursDetaille(Cours cours) {

        if (cours != null) {
            labtitre.setText(cours.getTitre());
            labdescriptions.setText(cours.getDescription());
            labdatedebut.setText(cours.getDatedebut() + "");
            labdatefin.setText(cours.getDatefin() + "");
            labdifficulte.setText(cours.getDifficulte());
            labcatégorie.setText(cours.getCategorie().getCate());

        } else {

            labtitre.setText("");
            labdescriptions.setText("");
            labdatedebut.setText("");
            labdatefin.setText("");
            labdifficulte.setText("");
            labcatégorie.setText("");
        }

    }

    @FXML
    private void AjouterAction(ActionEvent event) {
        CoursDAO coursdao = new CoursDAO();
        Cours c = new Cours();

        boolean ajouterClicked = showProblemeAddDialog(c);
        if (ajouterClicked) {
            coursdao.ajouterCous(c);
            listecours.add(c);

            listview.setItems(listecours);
        }
    }

    public boolean showProblemeAddDialog(Cours cours) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Test.class.getResource("/GUI/Ajoutercours.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Ajouter Cours");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initStyle(StageStyle.UTILITY);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the cours into the controller.
            ajouterCoursController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setCour(cours);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @FXML
    private void Modifier(ActionEvent event) {
        Cours cour = new Cours();
        int selectedIndex = listview.getSelectionModel().getSelectedIndex();
        cour = listview.getSelectionModel().getSelectedItem();

        if (cour != null) {
            boolean ajouterClicked = showProblemeAddDialog(cour);
            if (ajouterClicked) {
                new CoursDAO().modifierCours(cour);
                listview.getItems().add(selectedIndex, cour);
                listview.getItems().remove(cour);
                AfficherCoursDetaille(cour);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(primarystage);
            alert.setTitle("No Selection");
            alert.setHeaderText("selectioné un cours ");
            alert.setContentText(" selectionné un cours dans la liste.");

            alert.showAndWait();
        }

    }

   

    @FXML
    private void btnsupprimer(ActionEvent event) {
        Cours cours = new Cours();
        cours = listview.getSelectionModel().getSelectedItem();

        if (cours != null) {
            listview.getItems().remove(cours);
            new CoursDAO().supprimerCoursById(cours.getId());
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(primarystage);
            alert.setTitle("Sélectionner Cours");
            alert.setHeaderText("Cours non sélectionner dans la liste");
            alert.setContentText("SVP Cliquer sur un cours :).");

            alert.showAndWait();
        }
    }

}
