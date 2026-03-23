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
import java.util.List;
import java.util.ResourceBundle;

import java.util.TreeMap;


public class ConsultationController implements Initializable{

    @FXML
    private Label age;

    @FXML
    private Button bo;

    @FXML
    private Label date;

    @FXML
    private Button dm;

    @FXML
    private Label heure;

    @FXML
    private Label nom;

    @FXML
    private Label prenom;

    int numDossier;


    @Override
    public void initialize (URL arg0 , ResourceBundle arg1){
        
    }

    public void setConsultationDetails(String startTime, String date) {
        this.heure.setText(startTime); // Assuming startTime is the date
        this.date.setText(date);
        this.nom.setText(Consultation.getPatient().getNom());
        this.prenom.setText(Consultation.getPatient().getPrenom());
        this.age.setText(Integer.toString(Consultation.getPatient().getAge()));
    }

    @FXML
    void addbo(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("creerBO.fxml"));
        Parent root = loader.load();
        creerBoController controller = loader.getController();
        controller.setOrthophoniste(App.getCurrentOrthophoniste());

        BOpremierRDV bopremierRDV = new BOpremierRDV();
        controller.setBO(bopremierRDV);

        Dossier dossier = findDossierByPatientName(nom.getText(), prenom.getText());

        if (dossier != null) {
            controller.setDossier(dossier);
            // Créer une nouvelle scène avec la racine chargée depuis le fichier FXML
            Scene scene = new Scene(root);
            // Obtenir la référence de la scène actuelle à partir du bouton cliqué
            Stage stage = (Stage) bo.getScene().getWindow();
            // Modifier la scène actuelle pour afficher la nouvelle scène
            stage.setScene(scene);
            // stage.setMaximized(true);
            stage.show();

        }else{
            Util.afficherSucces("Dossier introuvable", "veuillez creer un dossier avant de proceder a la creation d'un bilan orthophonique");
        }
        
    }

    @FXML
    void creerdossiermedical(ActionEvent event) throws IOException {
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("AddPatient.fxml"));


        //Parent root = loader.load();
        //Stage stage = new Stage();
        //stage.setScene(new Scene(root));
        //stage.show();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddPatient.fxml"));
        Parent root = loader.load();
        AddPatientController controller = loader.getController();
        controller.setheure(heure.getText());
        // Créer une nouvelle scène avec la racine chargée depuis le fichier FXML
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
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
