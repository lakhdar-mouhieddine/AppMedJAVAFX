import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class passerEcController {

    @FXML
    private Button ajouterObs;

    @FXML
    private Button enregistrer;

    @FXML
    private VBox observation;

    @FXML
    private Button retour;

    @FXML
    private VBox tests;

    Orthophoniste orthophoniste;

    public void setOrthophoniste(Orthophoniste orthophoniste) {
        this.orthophoniste = orthophoniste;
    }

    Dossier dossier;

    public void setDossier(Dossier dossier) {
        this.dossier = dossier;
    }

    EpreuveClinique EC;

    public void setEpreuveClinique(EpreuveClinique EpreuveClinique) {
        this.EC = EpreuveClinique;
        afficher();
    }

    BO bo;

    public void setBO(BO bo) {
        this.bo = bo;
    }

    public void afficher() {
        ArrayList<String> observations = EC.getObservationClinique();
        for (String obs : observations) {
            TextField textFieldObservation = new TextField(obs);
            textFieldObservation.setStyle("-fx-background-color: #FAFAFA;");
            observation.getChildren().add(textFieldObservation);
        }

        ArrayList<CompteRendu> compteRendus = EC.getCompterendu();
        for (CompteRendu compteRendu : compteRendus) {
            Test test = compteRendu.getTest();
            String nom = test.getNom();

            if (compteRendu.getConclusion() == null || compteRendu.getConclusion().isEmpty()) {
                // Création d'un bouton avec le nom du test comme libellé
                Button button = new Button(nom);
                button.setStyle("-fx-font-weight: bold; -fx-background-color : white ");
                button.setFont(new Font("Arial", 13));
                button.setCursor(Cursor.HAND);
                VBox.setMargin(button, new Insets(10, 0, 20, 40));

                // Ajout de l'événement d'écoute pour gérer les clics sur le bouton
                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            // Charger le fichier FXML de la page d'inscription
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("passerTest.fxml"));
                            Parent root = loader.load();

                            // Obtenir le contrôleur de la page d'inscription
                            passerTestController controller = loader.getController();
                            controller.setOrthophoniste(orthophoniste);
                            controller.setDossier(dossier);
                            controller.setEpreuveClinique(EC);
                            controller.setBO(bo);
                            controller.setCompteRendu(compteRendu);

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
                });

                // Ajout du bouton au VBox dynamique
                tests.getChildren().add(button);
            } else {
                Label label = new Label(nom);
                label.setFont(new Font("Arial", 13));
                VBox.setMargin(label, new Insets(10, 0, 20, 40));
                tests.getChildren().add(label);
            }

        }
    }

    @FXML
    void ajouterObservation(ActionEvent event) {
        TextField observationText = new TextField();
        observationText.setStyle("-fx-background-color: #FAFAFA;");
        observationText.setPromptText("Observation");
        VBox.setMargin(observationText, new Insets(10, 20, 10, 20));
        observation.getChildren().add(observationText);
    }

    @FXML
    void retourPage(ActionEvent event) {
        try {
            // Charger le fichier FXML de la page d'inscription
            FXMLLoader loader = new FXMLLoader(getClass().getResource("creerBo.fxml"));
            Parent root = loader.load();

            // Obtenir le contrôleur de la page d'inscription
            creerBoController controller = loader.getController();
            controller.setOrthophoniste(orthophoniste);
            controller.setDossier(dossier);
            controller.setBO(bo);

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
    void enregistrerEC(ActionEvent event) {
        ArrayList<String> observations = new ArrayList<>();
        for (int i = 1; i < observation.getChildren().size(); i++) {
            TextField propField = (TextField) observation.getChildren().get(i);
            String observationText = propField.getText().trim();
            if (!observationText.isEmpty()) {
                observations.add(observationText);
            }
        }
        EC.setObservationClinique(observations);
        try {
            // Charger le fichier FXML de la page d'inscription
            FXMLLoader loader = new FXMLLoader(getClass().getResource("creerBo.fxml"));
            Parent root = loader.load();

            // Obtenir le contrôleur de la page d'inscription
            creerBoController controller = loader.getController();
            controller.setOrthophoniste(orthophoniste);
            controller.setDossier(dossier);
            controller.setBO(bo);

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
