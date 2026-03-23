import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class AddConsultationController implements Initializable {

    @FXML
    private TextField age;

    @FXML
    private DatePicker date;

    @FXML
    private Button enregistrer;

    @FXML
    private TextField heure;

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private ChoiceBox<String> typeConsultation;

    private String[] types = {"En-ligne", "En-présentiel"};

    private Orthophoniste orthophoniste;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        typeConsultation.getItems().addAll(types);
        this.orthophoniste = App.getCurrentOrthophoniste();
    }

    @FXML
    void enreg(ActionEvent event) throws IOException {
        // Extract data from the fields
        String patientNom = nom.getText();
        String patientPrenom = prenom.getText();
        int patientAge = Integer.parseInt(age.getText());
        String consultationDate = date.getValue().toString();
        String consultationHeure = heure.getText();
        String nom = date.getValue().getDayOfWeek().toString();

        // Get the selected item from the choice box
        String consultationType = typeConsultation.getValue();

        // Create Consultation object
        Consultation consultation = new Consultation(consultationHeure, consultationDate, patientNom, patientPrenom, patientAge);

        // Find or create the corresponding Jour
        Jour jour = getOrCreateJour(nom,consultationDate);
        jour.addRDV(consultation);
        

        // Debugging information
        System.out.println("Consultation created: " + consultation.getRDV());
        System.out.println("Patient: " + Consultation.getPatient().getNom() + " " + Consultation.getPatient().getPrenom());
        System.out.println("Patient age: " + patientAge);
        System.out.println("Type of Consultation: " + consultationType);

        // Close the current stage
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    private Jour getOrCreateJour(String nom,String date) {
        for (Jour jour : orthophoniste.getAgenda().getJours()) {
            if (jour.getDate().equals(date)) {
                return jour;
            }
        }
        // If the Jour does not exist, create a new one
        orthophoniste.getAgenda().addJour(nom ,date);
        System.out.println("Jour created: " + nom + ", Date: " + date);
        return orthophoniste.getAgenda().getJourByDate(date);
    }
}
