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
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Anouar
 */
public class ajouterCoursController implements Initializable {

    @FXML
    private TextField titreTextField;
    @FXML
    private TextArea descriptioneditProbTextArea;
    @FXML
    private TextField datefintextefieald;
    @FXML
    private TextField datedebuttextfieald;
    @FXML
    private ComboBox<Categories> categoriecomboajout;
    @FXML
    private ComboBox<String> difficucomboajout;
    Cours cours;
    private boolean ajouterClicked = false;
    private Stage dialogStage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CategorieDAO categoriesdao = new CategorieDAO();

        categoriecomboajout.setItems(FXCollections.observableArrayList(categoriesdao.findAll()));
        difficucomboajout.getItems().addAll("Facile", "Moyenne", "Deficile");

    }

    public void setCour(Cours c) {
        this.cours = c;
        titreTextField.setText(c.getTitre());
        descriptioneditProbTextArea.setText(c.getDescription());
        if(c.getDatedebut()!=null)
        datedebuttextfieald.setText(c.getDatedebut()+"");
        if(c.getDatefin()!=null)
        datefintextefieald.setText(c.getDatefin()+"");
        difficucomboajout.setValue(c.getDifficulte());
        categoriecomboajout.setValue(c.getCategorie());
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isOkClicked() {
        return ajouterClicked;
    }

    @FXML
    private void AjouterAction(ActionEvent event) {
        cours.setTitre(titreTextField.getText());
        cours.setDescription(descriptioneditProbTextArea.getText());
        cours.setDatedebut(Date.valueOf(datedebuttextfieald.getText()));
        cours.setDatefin(Date.valueOf(datefintextefieald.getText()));
        cours.setDifficulte(difficucomboajout.getValue());
        cours.setCategorie(categoriecomboajout.getValue());
      

        ajouterClicked = true;

        dialogStage.close();
    }

    @FXML
    private void handleAnnuler(ActionEvent event) {
        dialogStage.close();
    }

}
