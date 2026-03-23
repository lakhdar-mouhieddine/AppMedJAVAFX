import java.io.IOException;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class postBoController {

    @FXML
    private Button buttonDossier;

    @FXML
    private Button evaluer;

    Orthophoniste orthophoniste;

    public void setOrthophoniste(Orthophoniste orthophoniste) {
        this.orthophoniste = orthophoniste;
    }

    Dossier dossier;

    public void setDossier(Dossier dossier) {
        this.dossier = dossier;
    }

    @FXML
    void consulterDossier(ActionEvent event) {
        try {
            // Charger le fichier FXML de la page de connexion
            FXMLLoader loader = new FXMLLoader(getClass().getResource("profilPatient.fxml"));
            Parent root = loader.load();
            profilPatientController controller = loader.getController();
            controller.setOrthophoniste(orthophoniste);
            controller.setDossier(dossier);

            // Créer une nouvelle scène avec la racine chargée depuis le fichier FXML
            Scene scene = new Scene(root);

            // Obtenir la référence de la scène actuelle à partir du bouton cliqué
            Stage stage = (Stage) buttonDossier.getScene().getWindow();

            // Modifier la scène actuelle pour afficher la nouvelle scène
            stage.setScene(scene);
            // stage.setMaximized(true);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void evaluerPatient(ActionEvent event) {
        if (dossier.getFiches().size() == 0) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText(
                    "Vous ne travaillez sur aucune fiche de suivi avec ce patient, voulez-vous en créer une ?");

            // Gérer les boutons Oui et Annuler
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    // Charger le fichier FXML de la page de connexion
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("newFiche.fxml"));
                    Parent root = loader.load();
                    newFicheController controller = loader.getController();
                    controller.setOrthophoniste(orthophoniste);
                    controller.setDossier(dossier);

                    // Créer une nouvelle scène avec la racine chargée depuis le fichier FXML
                    Scene scene = new Scene(root);

                    // Obtenir la référence de la scène actuelle à partir du bouton cliqué
                    Stage stage = (Stage) buttonDossier.getScene().getWindow();

                    // Modifier la scène actuelle pour afficher la nouvelle scène
                    stage.setScene(scene);
                    // stage.setMaximized(true);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            try {
                // Charger le fichier FXML de la page de connexion
                FXMLLoader loader = new FXMLLoader(getClass().getResource("evaluerPatient.fxml"));
                Parent root = loader.load();
                evaluerPatientController controller = loader.getController();
                controller.setOrthophoniste(orthophoniste);
                controller.setDossier(dossier);
                controller.setFiche(dossier.getFiches().get(dossier.getFiches().size() - 1));

                // Créer une nouvelle scène avec la racine chargée depuis le fichier FXML
                Scene scene = new Scene(root);

                // Obtenir la référence de la scène actuelle à partir du bouton cliqué
                Stage stage = (Stage) buttonDossier.getScene().getWindow();

                // Modifier la scène actuelle pour afficher la nouvelle scène
                stage.setScene(scene);
                // stage.setMaximized(true);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}
