import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class AddSuiviController implements Initializable {

    @FXML
    private DatePicker date;

    @FXML
    private Button enregistrer;

    @FXML
    private TextField heure;

    @FXML
    private TextField numdossier;

    @FXML
    private ChoiceBox<String> typeSuivi;

    private String[] types = {"En-ligne", "En-présentiel"};

    private Orthophoniste orthophoniste;

    @Override
    public void initialize(URL arg0 , ResourceBundle arg1) {
        typeSuivi.getItems().addAll(types);
        this.orthophoniste = App.getCurrentOrthophoniste();
    }

    @FXML
    void enreg(ActionEvent event) {
        // Extract data from the fields
        String suiviDate = date.getValue().toString();
        String suiviHeure = heure.getText();
        int suiviNumDossier = Integer.parseInt(numdossier.getText());
        String suiviType = typeSuivi.getValue();
        String nom = date.getValue().getDayOfWeek().toString(); 

        // Create Suivi object
        Suivi suivi = new Suivi(suiviHeure, suiviDate, suiviNumDossier, suiviType);
        App.getCurrentOrthophoniste().getDossier(suiviNumDossier).ajouterRDV(suivi);

        // Add the Suivi to the Jour
        Jour jour = getOrCreateJour(nom,suiviDate);
        jour.addRDV(suivi);

        System.out.println("Suivi created: " + suivi.getRDV());
        System.out.println("Patient: " + suivi.getPatient().getNom() + " " + suivi.getPatient().getPrenom());
        System.out.println("Type of Suivi: " + suiviType);
        System.out.println("Numéro de dossier: " + suiviNumDossier);
        
        // Close the window
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
