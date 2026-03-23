import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class SuiviController implements Initializable{

    @FXML
    private Button bo;

    @FXML
    private Label date;

    @FXML
    private Label heure;

    @FXML
    private Label nom;

    @FXML
    private Label numdossier;

    @FXML
    private Label prenom;


    Suivi suivi;

    public void setsuiviDetails(String startTime, String date) {
        this.heure.setText(startTime); // Assuming startTime is the date
        this.date.setText(date);
        this.nom.setText(Suivi.getPatient().getNom());
        this.prenom.setText(Suivi.getPatient().getPrenom());
        this.numdossier.setText(Integer.toString(Suivi.numDossier));
    }

    @Override
    public void initialize (URL arg0 , ResourceBundle arg1){
        
    }

    @FXML
    void addbo(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("creerBO.fxml"));
        Parent root = loader.load();
        creerBoController controller = loader.getController();
        controller.setOrthophoniste(App.getCurrentOrthophoniste());

        BO bO = new BO();
        controller.setBO(bO);

        Dossier dossier = findDossierByPatientName(nom.getText(), prenom.getText());

       
        controller.setDossier(dossier);
        // Créer une nouvelle scène avec la racine chargée depuis le fichier FXML
        Scene scene = new Scene(root);
        // Obtenir la référence de la scène actuelle à partir du bouton cliqué
        Stage stage = (Stage) bo.getScene().getWindow();
        // Modifier la scène actuelle pour afficher la nouvelle scène
        stage.setScene(scene);
        // stage.setMaximized(true);
          
    }

    private Dossier findDossierByPatientName(String nom, String prenom) {
        Orthophoniste orthophoniste = App.getCurrentOrthophoniste();
        if (orthophoniste != null) {
            TreeMap<Integer, Dossier> dossiers = orthophoniste.getDossiers();
            for (Dossier dossier : dossiers.values()) {
                Patient patient = dossier.getPatient();
                if (patient.getNom().equals(nom) && patient.getPrenom().equals(prenom)) {
                    return dossier;
                }
            }
        }
        return null;
    }

}
