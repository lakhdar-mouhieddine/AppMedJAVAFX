import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class afficherEcController {

    @FXML
    private VBox observation;

    @FXML
    private Button retour;

    @FXML
    private VBox tests;

    private Orthophoniste orthophoniste;

    public void setOrthophoniste(Orthophoniste orthophoniste) {
        this.orthophoniste = orthophoniste;
    }

    private EpreuveClinique EC;

    public void setEpreuveClinique(EpreuveClinique EpreuveClinique) {
        this.EC = EpreuveClinique;
        afficher();
    }

    private Dossier dossier;

    public void setDossier(Dossier dossier) {
        this.dossier = dossier;
    }

    private BO bo;

    public void setBO(BO bo) {
        this.bo = bo;
    }

    public void afficher() {
        ArrayList<String> observations = EC.getObservationClinique();
        for (String obs : observations) {
            Label label = new Label("\"" + obs + "\"");
            label.setFont(new Font("Arial", 13));
            VBox.setMargin(label, new Insets(10));
            observation.getChildren().add(label);
        }

        ArrayList<CompteRendu> compteRendus = EC.getCompterendu();
        for (CompteRendu compteRendu : compteRendus) {
            Button bouton = new Button(compteRendu.getTest().getNom());
            bouton.setStyle("-fx-background-color: white;");
            bouton.setTextFill(javafx.scene.paint.Color.web("#00ced0"));
            bouton.setFont(new Font("Arial Bold", 12.0));
            bouton.setCursor(Cursor.HAND);
            VBox.setMargin(bouton, new Insets(10.0, 0.0, 20.0, 40.0));

            // Set the action handler for the button
            bouton.setOnAction(event -> {
                try { // Charger le fichier FXML de la page d'inscription
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherCompteRendu.fxml"));
                    Parent root = loader.load();

                    // Obtenir le contrôleur de la page d'inscription
                    afficherCompteRenduController controller = loader.getController();
                    controller.setOrthophoniste(orthophoniste);
                    controller.setCompteRendu(compteRendu);
                    controller.setDossier(dossier);
                    controller.setBO(bo);
                    controller.setEpreuveClinique(EC);

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
            });
            tests.getChildren().add(bouton);
        }

    }

    @FXML
    void retourPage(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherBo.fxml"));
            Parent root = loader.load();

            // Obtenir le contrôleur de la page d'inscription
            afficherBoController controller = loader.getController();
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
