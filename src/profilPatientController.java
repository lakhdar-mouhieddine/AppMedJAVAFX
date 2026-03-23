import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class profilPatientController {

    @FXML
    private Button BO;

    @FXML
    private Button retour;

    @FXML
    private Label adresse;

    @FXML
    private Label attribut1;

    @FXML
    private Label attribut2;

    @FXML
    private Button compteRendu;

    @FXML
    private VBox content2;

    @FXML
    private Label dateNaissance;

    @FXML
    private TextField diplome;

    @FXML
    private Button ficheSuivi;

    @FXML
    private Label lieuNaissance;

    @FXML
    private Label nom;

    @FXML
    private Label numero;

    @FXML
    private Label prenom;

    @FXML
    private TextField profession;

    @FXML
    private Button rdv;

    @FXML
    private Label valAttribut1;

    @FXML
    private Label valAttribut2;

    private Orthophoniste orthophoniste;

    public void setOrthophoniste(Orthophoniste orthophoniste) {
        this.orthophoniste = orthophoniste;
    }

    private Dossier dossier;

    public void setDossier(Dossier dossier) {
        this.dossier = dossier;
        afficher();
    }

    public void afficher() {
        Patient patient = dossier.getPatient();
        nom.setText(patient.getNom());
        prenom.setText(patient.getPrenom());
        dateNaissance.setText(patient.getDateDeNaissance());
        lieuNaissance.setText(patient.getLieuDeNaissance());
        adresse.setText(patient.getAdresse());
        numero.setText(patient.getNumeroTel());
        if (patient instanceof Adulte) {
            attribut1.setText("Diplome : ");
            valAttribut1.setText(((Adulte) patient).getDiplome());
            attribut2.setText("Profession : ");
            valAttribut2.setText(((Adulte) patient).getProfession());
        } else if (patient instanceof Enfant) {
            attribut1.setText("Numero de téléphone 2eme parent : ");
            valAttribut1.setText(((Enfant) patient).getNumeroTelParent());
            attribut2.setText("Classe d'étude : ");
            valAttribut2.setText(((Enfant) patient).getClasseEtude());
        }

    }

    @FXML
    void accederBO(ActionEvent event) {
        try { // Charger le fichier FXML de la page d'inscription
            FXMLLoader loader = new FXMLLoader(getClass().getResource("listeBos.fxml"));
            Parent root = loader.load();

            // Obtenir le contrôleur de la page d'inscription
            listeBosController controller = loader.getController();
            controller.setOrthophoniste(orthophoniste);
            controller.setDossier(dossier);

            // Créer une nouvelle scène avec la racine chargée depuis le fichier FXML
            Scene scene = new Scene(root);

            // Obtenir la référence de la scène actuelle à partir du bouton cliqué
            Stage stage = (Stage) retour.getScene().getWindow();

            // Modifier la scène actuelle pour afficher la nouvelle scène
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void accederCompteRendu(ActionEvent event) {
        try { // Charger le fichier FXML de la page d'inscription
            FXMLLoader loader = new FXMLLoader(getClass().getResource("listeCompteRendus.fxml"));
            Parent root = loader.load();

            // Obtenir le contrôleur de la page d'inscription
            listeCompteRendusController controller = loader.getController();
            controller.setOrthophoniste(orthophoniste);
            controller.setDossier(dossier);

            // Créer une nouvelle scène avec la racine chargée depuis le fichier FXML
            Scene scene = new Scene(root);

            // Obtenir la référence de la scène actuelle à partir du bouton cliqué
            Stage stage = (Stage) retour.getScene().getWindow();

            // Modifier la scène actuelle pour afficher la nouvelle scène
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void accederFicheSuivi(ActionEvent event) {
        try { // Charger le fichier FXML de la page d'inscription
            FXMLLoader loader = new FXMLLoader(getClass().getResource("listeFicheSuivi.fxml"));
            Parent root = loader.load();

            // Obtenir le contrôleur de la page d'inscription
            listeFicheSuiviController controller = loader.getController();
            controller.setOrthophoniste(orthophoniste);
            controller.setDossier(dossier);

            // Créer une nouvelle scène avec la racine chargée depuis le fichier FXML
            Scene scene = new Scene(root);

            // Obtenir la référence de la scène actuelle à partir du bouton cliqué
            Stage stage = (Stage) retour.getScene().getWindow();

            // Modifier la scène actuelle pour afficher la nouvelle scène
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void accederRdv(ActionEvent event) {

    }

    @FXML
    void retourPage(ActionEvent event) {
        try { // Charger le fichier FXML de la page d'inscription
            FXMLLoader loader = new FXMLLoader(getClass().getResource("findDossier.fxml"));
            Parent root = loader.load();

            // Obtenir le contrôleur de la page d'inscription
            findDossierController controller = loader.getController();
            controller.setOrthophoniste(orthophoniste);

            // Créer une nouvelle scène avec la racine chargée depuis le fichier FXML
            Scene scene = new Scene(root);

            // Obtenir la référence de la scène actuelle à partir du bouton cliqué
            Stage stage = (Stage) retour.getScene().getWindow();

            // Modifier la scène actuelle pour afficher la nouvelle scène
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
