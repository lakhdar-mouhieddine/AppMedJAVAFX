import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class troubleController {

    @FXML
    private Label pourcentage3;

    @FXML
    private Label pourcentage1;

    @FXML
    private Label pourcentage2;

    @FXML
    private Button retour;

    @FXML
    private VBox trouble1;

    @FXML
    private VBox trouble2;

    @FXML
    private VBox trouble3;

    Orthophoniste orthophoniste;

    public void setOrthophoniste(Orthophoniste orthophoniste) {
        this.orthophoniste = orthophoniste;
        afficher();
    }

    public void afficher() {
        HashMap<TroubleCategorie, ArrayList<Patient>> patientsParTrouble = orthophoniste.patientsParTroubles();
        // Affichage des patients par trouble
        Label pourcentages[] = { pourcentage1, pourcentage2, pourcentage3 };
        VBox troubles[] = { trouble1, trouble2, trouble3 };
        int i = 0;
        for (TroubleCategorie categorie : TroubleCategorie.values()) {
            ArrayList<Patient> patients = patientsParTrouble.get(categorie);
            double pourcentage = (double) patients.size() / orthophoniste.getDossiers().size() * 100;
            pourcentages[i].setText(String.valueOf(pourcentage) + " %");
            for (Patient patient : patients) {
                Label patientLabel = new Label("Nom : " + patient.getNom() + " Prenom : " + patient.getPrenom());
                patientLabel.setStyle("-fx-font-weight: bold; -fx-padding: 10 0 5 0;");
                troubles[i].getChildren().add(patientLabel);
            }
            i++;
        }
    }

    @FXML
    void retourPage(ActionEvent event) {
        try {
            // Charger le fichier FXML de la page de connexion
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Agenda.fxml"));
            Parent root = loader.load();
            AgendaController controller = loader.getController();
            controller.setOrthophoniste(orthophoniste);

            // Créer une nouvelle scène avec la racine chargée depuis le fichier FXML
            Scene scene = new Scene(root);

            // Obtenir la référence de la scène actuelle à partir du bouton cliqué
            Stage stage = (Stage) retour.getScene().getWindow();

            // Modifier la scène actuelle pour afficher la nouvelle scène
            stage.setScene(scene);
            // stage.setMaximized(true);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
