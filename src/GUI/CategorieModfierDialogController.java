/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Categories;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Anouar
 */
public class CategorieModfierDialogController implements Initializable {

    @FXML
    private TextField titrefield;
    private Stage dialogStage;
    private boolean okClicked = false;
    private Categories categories;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets categories dans dialog.
     *
     * @param Categories
     */
    public void setPerson(Categories categories) {
        this.categories = categories;

        titrefield.setText(categories.getCate());
        
    }
    /**
     * Returns true si l' utilisateur cliquer sur  OK, false autrechose.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void btnok(ActionEvent event) {
 if (isInputValid()) {
            categories.setCate(titrefield.getText());

            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void btnannuler(ActionEvent event) {
        dialogStage.close();

    }

    private boolean isInputValid() {
        String errorMessage = "";
 if (titrefield.getText() == null || titrefield.getText().length() == 0) {
            errorMessage += "Taper titre de categories!\n";
        }
 if (errorMessage.length() == 0) {
            return true;
        } else {
            // afficher  error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("SVP Valider");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

}
