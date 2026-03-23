import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SeconnecterController {

    @FXML
    private TextField email;

    @FXML
    private PasswordField motdepasse;

    @FXML
    private Button seconnecter;

    @FXML
    private Button sinscrire;

    @FXML
    void connexion(ActionEvent event) {
        // Vérifier si l'un des champs est vide
        if (email.getText().isEmpty() || motdepasse.getText().isEmpty()) {
            Util.afficherErreur("Erreur de connexion", "Tous les champs doivent etre remplis ");
            return;
        }

        Orthophoniste orthophoniste = App.Connexion(email.getText(), motdepasse.getText());
        if (orthophoniste != null) {
            try {
                // Charger le fichier FXML de la page de connexion
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Agenda.fxml"));

                Parent root = loader.load();
                AgendaController controller = loader.getController();
                controller.setOrthophoniste(orthophoniste);

                // Créer une nouvelle scène avec la racine chargée depuis le fichier FXML
                Scene scene = new Scene(root);

                // Obtenir la référence de la scène actuelle à partir du bouton cliqué
                Stage stage = (Stage) seconnecter.getScene().getWindow();

                // Modifier la scène actuelle pour afficher la nouvelle scène
                stage.setScene(scene);
                // stage.setMaximized(true);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Util.afficherErreur("Erreur de connexion", "Email ou mot de passe incorrect");
            return;
        }
    }

    @FXML
    void ouvrirInscription(ActionEvent event) {
        try {
            // Charger le fichier FXML de la page d'inscription
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Inscription.fxml"));
            Parent root = loader.load();

            // Créer une nouvelle scène avec la racine chargée depuis le fichier FXML
            Scene scene = new Scene(root);

            // Obtenir la référence de la scène actuelle à partir du bouton cliqué
            Stage stage = (Stage) sinscrire.getScene().getWindow();

            // Modifier la scène actuelle pour afficher la nouvelle scène
            stage.setScene(scene);
            // stage.setMaximized(true);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
