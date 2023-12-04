package writ1co2system;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class accessPageController {
    @FXML
    private TextField txtUserid;

    @FXML
    private TextField txtPostCode;

    @FXML
    private TextField txtCo2Conc;

    @FXML
    private Label txtMessage;

    @FXML
    private Button btnDisconnect;

    @FXML 
    private Button btnViewData;

    private App app; // Add this field

    public void setApp(App app) {
        this.app = app;
    }
    
    @FXML
    private void writeData() throws IOException 
    {
        String userID = txtUserid.getText();
        String postcode = txtPostCode.getText();
        float co2Concentration = Float.parseFloat(txtCo2Conc.getText());

        clientHandler handler = new clientHandler(userID); // Create an instance

        // Call the storeDataCSV method
        boolean success = handler.storeDataCSV(postcode, co2Concentration);

        if (success) {
            txtMessage.setText("Data stored successfully!");
            txtMessage.setStyle("-fx-text-fill: red;");
        } else {
            txtMessage.setText("Failed to store data.");
            txtMessage.setStyle("-fx-text-fill: red;");
        }
    }
    @FXML
    private void disconnect() throws IOException {
        // Get the current stage from the button's scene
        Stage currentStage = (Stage) btnDisconnect.getScene().getWindow();

        // Close the current stage
         currentStage.close();

         try {
            App.openStartPageOnNewStage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void viewData() throws IOException {
        // Get the current stage from the button's scene
        Stage currentStage = (Stage) btnViewData.getScene().getWindow();

        // Close the current stage
        currentStage.close();

        // Open the csvPage on a new stage

        try {
            App.openCsvPageOnNewStage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
}
