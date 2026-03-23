import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddRDVController {

    @FXML
    private Button atelier;

    @FXML
    private Button consultation;

    @FXML
    private Button suivi;

    @FXML
    private Text user;

    @FXML
    void addAtelier(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddAtelier.fxml"));
            Parent root = loader.load();
            
            // Access the controller of the loaded FXML file if needed
            // AddRDVController addRDVController = loader.getController();
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void addConsultation(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddConsultation.fxml"));
            Parent root = loader.load();
            
            // Access the controller of the loaded FXML file if needed
            // AddRDVController addRDVController = loader.getController();
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            currentStage.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void addSuivi(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddSuivi.fxml"));
            Parent root = loader.load();
            
            // Access the controller of the loaded FXML file if needed
            // AddRDVController addRDVController = loader.getController();
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
