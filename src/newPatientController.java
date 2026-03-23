import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class newPatientController {

    @FXML
    private TextField adresse;

    @FXML
    private TextField classeEtude;

    @FXML
    private VBox content1;

    @FXML
    private VBox content2;

    @FXML
    private TextField dateNaissance;

    @FXML
    private TextField diplome;

    @FXML
    private Button enregistrer;

    @FXML
    private TextField lieuNaissance;

    @FXML
    private TextField nom;

    @FXML
    private TextField numero;

    @FXML
    private TextField numeroParent;

    @FXML
    private TextField prenom;

    @FXML
    private TextField profession;

    @FXML
    private ToggleButton toggle;

    Orthophoniste orthophoniste;

    public void setOrthophoniste(Orthophoniste orthophoniste) {
        this.orthophoniste = orthophoniste;
    }

    Dossier dossier;

    public void setDossier(Dossier dossier) {
        this.dossier = dossier;
    }

    @FXML
    void creerPatient(ActionEvent event) {
        // Vérifier si l'un des champs est vide
        // if (nom.getText().isEmpty() || prenom.getText().isEmpty() ||
        // dateNaissance.getText().isEmpty()
        // || lieuNaissance.getText().isEmpty() ||
        // adresse.getText().isEmpty() || numero.getText().isEmpty() ||
        // (numeroParent.getText().isEmpty() && !toggle.isSelected()) ||
        // (diplome.getText().isEmpty() && toggle.isSelected()) ||
        // (classeEtude.getText().isEmpty() && !toggle.isSelected()) ||
        // (profession.getText().isEmpty() && toggle.isSelected())) {
        // Util.afficherErreur("Erreur", "Tous les champs doivent etre remplis ");
        // return;
        // }

        // if (!validerNumeroTelephone(numero.getText())
        // || (!validerNumeroTelephone(numeroParent.getText()) && !toggle.isSelected()))
        // {
        // Util.afficherErreur("Erreur", "Format numero telephone incorrect.");
        // return;
        // }

        // if (!validerDate(dateNaissance.getText())) {
        // Util.afficherErreur("Erreur", "Date de naissance incorrect.");
        // return;
        // }
        if (toggle.isSelected()) {
            Adulte patient = new Adulte(nom.getText(), prenom.getText(), dateNaissance.getText(),
                    lieuNaissance.getText(), adresse.getText(), numero.getText(), diplome.getText(),
                    profession.getText());
            dossier.setPatient(patient);
            //try {
                // Charger le fichier FXML de la page d'inscription
                //FXMLLoader loader = new FXMLLoader(getClass().getResource("essai.fxml"));
                //Parent root = loader.load();

                // Obtenir le contrôleur de la page d'inscription
                //essaiController controller = loader.getController();
                //controller.setOrthophoniste(orthophoniste);
               // controller.setDossier(dossier);

                // Créer une nouvelle scène avec la racine chargée depuis le fichier FXML
                //Scene scene = new Scene(root);

                // Obtenir la référence de la scène actuelle à partir du bouton cliqué
                //Stage stage = (Stage) enregistrer.getScene().getWindow();

                // Modifier la scène actuelle pour afficher la nouvelle scène
                //stage.setScene(scene);

               // stage.show();
            //} catch (IOException e) {
                //e.printStackTrace();
           // }
        } else {
            Enfant patient = new Enfant(nom.getText(), prenom.getText(), dateNaissance.getText(),
                    lieuNaissance.getText(), adresse.getText(), numero.getText(), diplome.getText(),
                    profession.getText());
            dossier.setPatient(patient);
            //try {
            //    // Charger le fichier FXML de la page d'inscription
            //    FXMLLoader loader = new FXMLLoader(getClass().getResource("essai.fxml"));
            //    Parent root = loader.load();
//
            //    // Obtenir le contrôleur de la page d'inscription
            //    essaiController controller = loader.getController();
            //    controller.setOrthophoniste(orthophoniste);
            //    controller.setDossier(dossier);
//
            //    // Créer une nouvelle scène avec la racine chargée depuis le fichier FXML
            //    Scene scene = new Scene(root);
//
            //    // Obtenir la référence de la scène actuelle à partir du bouton cliqué
            //    Stage stage = (Stage) enregistrer.getScene().getWindow();
//
            //    // Modifier la scène actuelle pour afficher la nouvelle scène
            //    stage.setScene(scene);
//
            //    stage.show();
            //} catch (IOException e) {
            //    e.printStackTrace();
            //}
        }
    }

    @FXML
    private void handleToggle() {
        if (toggle.isSelected()) {
            toggle.setText("Passer vers enfant");
            content1.setVisible(false);
            content1.setManaged(false);
            content2.setVisible(true);
            content2.setManaged(true);
        } else {
            toggle.setText("Passer vers adulte");
            content1.setVisible(true);
            content1.setManaged(true);
            content2.setVisible(false);
            content2.setManaged(false);
        }
    }

    private boolean validerNumeroTelephone(String numero) {
        // Expression régulière pour valider le numéro de téléphone
        String regex = "^(05|06|07)[0-9]{8}$";
        return numero.matches(regex);
    }

    public static boolean validerDate(String dateStr) {
        // Définir le format de la date. Par exemple : "dd/MM/yyyy"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate.parse(dateStr, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
