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
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class listeBosController {

    @FXML
    private VBox afficher;

    @FXML
    private Button retour;

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
        ArrayList<BO> bos = dossier.getBos();
        System.out.println("listeee " + bos.size());
        for (int i = 0; i < bos.size(); i++) {
            BO bo = bos.get(i);
            Button bouton = new Button(
                    "Consulter le bilan orthophonique " + (i == 0 ? "du premier rendez-vous" : i + 1));
            bouton.setStyle("-fx-background-color: white;");
            bouton.setTextFill(javafx.scene.paint.Color.web("#00ced0"));
            bouton.setFont(new Font("Arial Bold", 12.0));
            bouton.setCursor(Cursor.HAND);
            VBox.setMargin(bouton, new Insets(10.0, 0.0, 20.0, 40.0));

            // Set the action handler for the button
            bouton.setOnAction(event -> {
                try { // Charger le fichier FXML de la page d'inscription
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherBo.fxml"));
                    Parent root = loader.load();

                    // Obtenir le contrôleur de la page d'inscription
                    afficherBoController controller = loader.getController();
                    controller.setOrthophoniste(orthophoniste);
                    controller.setBO(bo);
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
            });
            afficher.getChildren().add(bouton);
        }
    }

    @FXML
    void retourPage(ActionEvent event) {
        try { // Charger le fichier FXML de la page d'inscription
            FXMLLoader loader = new FXMLLoader(getClass().getResource("profilPatient.fxml"));
            Parent root = loader.load();

            // Obtenir le contrôleur de la page d'inscription
            profilPatientController controller = loader.getController();
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

}
