
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.input.MouseEvent;


public class miniRDVController {

    @FXML
    private Label debut;

    @FXML
    private Button minirdv;

    private String date;
    private int type;

    @FXML
    void showRDV(ActionEvent event) throws IOException {
        if (type == 0){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Consultation.fxml"));
            Parent root = loader.load();

            ConsultationController consultationController = loader.getController();
            consultationController.setConsultationDetails(debut.getText(), date);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }else if (type ==1){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Suivi.fxml"));
            Parent root = loader.load();

            SuiviController suiviController = loader.getController();
            suiviController.setsuiviDetails(debut.getText(), date);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Atelier.fxml"));
            Parent root = loader.load();

            AtelierController atelierController = loader.getController();
            atelierController.setatelierDetails(debut.getText(), date);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    public void setRDVDetails(String startTime, String jourdate, int type) {
        debut.setText(startTime);
        this.date = jourdate;
        this.type = type ;
    }
}
