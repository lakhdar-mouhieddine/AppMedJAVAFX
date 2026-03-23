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

public class SinscrireController {

    @FXML
    private TextField adresse;

    @FXML
    private TextField email;

    @FXML
    private PasswordField motdepasse1;

    @FXML
    private PasswordField motdepasse2;

    @FXML
    private TextField nom;

    @FXML
    private TextField numero;

    @FXML
    private TextField prenom;

    @FXML
    private Button sinscrire;

    @FXML
    private Button seconnecter;

    @FXML
    void inscription(ActionEvent event) {
        // Vérifier si l'un des champs est vide
         if (adresse.getText().isEmpty() || email.getText().isEmpty() || motdepasse1.getText().isEmpty() || motdepasse2.getText().isEmpty() || nom.getText().isEmpty() || numero.getText().isEmpty() || prenom.getText().isEmpty()) {
            Util.afficherErreur("Erreur d'inscription", "Tous les champs doivent etre remplis ");
         return;
         }
         // Vérifier si les mots de passe sont identiques
         if (!motdepasse1.getText().equals(motdepasse2.getText())) {
             Util.afficherErreur("Erreur d'inscription", "Les mots de passes ne correspondent pas");
         return;
         }
         if (!validerEmail(email.getText())) {
             Util.afficherErreur("Erreur lors de l'inscription", "Format d'email incorrect.");
         return;
         }
         if (!validerNumeroTelephone(numero.getText())) {
            Util.afficherErreur("Erreur lors de l'inscription", "Format numero telephone incorrect.");
         return;
         }
         if (motdepasse1.getText().length() < 8) {
            Util.afficherErreur("Erreur lors de l'inscription", "Le mot de passe doit  contenir au moins 8 caractères ");
         return;
         }
        
        
        // Si toutes les conditions sont remplies, appeler la méthode Inscription de la
        // classe App
        Orthophoniste orthophoniste = new Orthophoniste(nom.getText(), prenom.getText(), adresse.getText(),
                numero.getText(), email.getText(),
                motdepasse1.getText());
        if (App.Inscription(orthophoniste)) {
            Util.afficherSucces("Inscription confirmée", "Vous vous etes inscris avec succès");
            try {
                // Charger le fichier FXML de la page de connexion
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Agenda.fxml"));
                Parent root = loader.load();
                AgendaController controller = loader.getController();
                controller.setOrthophoniste(orthophoniste);

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
        } else {
            Util.afficherErreur("Erreur d'inscription", "Cet email est déjà utilisé");
            return;
        }

    }

    @FXML
    void ouvrirConnexion(ActionEvent event) {
        try {
            // Charger le fichier FXML de la page d'inscription
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Connexion.fxml"));
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

    private boolean validerEmail(String email) {
        // Expression régulière pour valider l'email avec des lettres minuscules après
        // le @
        String regex = "^[A-Za-z0-9+_.-]+@[a-z]+\\.[a-z]{2,}$";
        return email.matches(regex);
    }

    private boolean validerNumeroTelephone(String numero) {
        // Expression régulière pour valider le numéro de téléphone
        String regex = "^(05|06|07)[0-9]{8}$";
        return numero.matches(regex);
    }

}
