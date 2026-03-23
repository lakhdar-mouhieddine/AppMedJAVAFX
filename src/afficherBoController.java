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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class afficherBoController {

    @FXML
    private VBox EC;

    @FXML
    private VBox anamnese;

    @FXML
    private Button boutonAnamnese;

    @FXML
    private VBox diagnostic;

    @FXML
    private Label projet;

    @FXML
    private Button retour;

    private Orthophoniste orthophoniste;

    public void setOrthophoniste(Orthophoniste orthophoniste) {
        this.orthophoniste = orthophoniste;

    }

    private BO bo;

    public void setBO(BO bo) {
        this.bo = bo;
        afficher();
    }

    private Dossier dossier;

    public void setDossier(Dossier dossier) {
        this.dossier = dossier;
    }

    public void afficher() {
        if (!(bo instanceof BOpremierRDV)) {
            anamnese.setVisible(false);
            anamnese.setManaged(false);
        }
        ArrayList<EpreuveClinique> ecs = bo.getEpreuvesCliniques();
        for (int i = 0; i < ecs.size(); i++) {
            EpreuveClinique ec = ecs.get(i);
            Button bouton = new Button("Consulter l'épreuve " + (i + 1));
            bouton.setStyle("-fx-background-color: white;");
            bouton.setTextFill(javafx.scene.paint.Color.web("#00ced0"));
            bouton.setFont(new Font("Arial Bold", 12.0));
            bouton.setCursor(Cursor.HAND);
            VBox.setMargin(bouton, new Insets(10.0, 0.0, 20.0, 40.0));

            // Set the action handler for the button
            bouton.setOnAction(event -> {
                try { // Charger le fichier FXML de la page d'inscription
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherEc.fxml"));
                    Parent root = loader.load();

                    // Obtenir le contrôleur de la page d'inscription
                    afficherEcController controller = loader.getController();
                    controller.setOrthophoniste(orthophoniste);
                    controller.setBO(bo);
                    controller.setDossier(dossier);

                    controller.setEpreuveClinique(ec);

                    // Créer une nouvelle scène avec la racine chargée depuis le fichier FXML
                    Scene scene = new Scene(root);

                    // Obtenir la référence de la scène actuelle à partir du bouton cliqué
                    Stage stage = (Stage) anamnese.getScene().getWindow();

                    // Modifier la scène actuelle pour afficher la nouvelle scène
                    stage.setScene(scene);

                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            EC.getChildren().add(bouton);
        }
        ArrayList<Trouble> troubles = bo.getDiagnostic().getTroubles();
        for (Trouble trouble : troubles) {
            GridPane gridPane = new GridPane();
            gridPane.setPadding(new Insets(10));
            gridPane.setHgap(20);
            gridPane.setVgap(10);
            gridPane.setStyle("-fx-background-color: #ffffff; -fx-border-color: #d3d3d3; -fx-border-width: 1px;");

            VBox vbox1 = new VBox();
            vbox1.setPadding(new Insets(10));
            Label label1 = new Label("Nom du trouble:");
            label1.setFont(Font.font("Arial", FontWeight.BOLD, 13));
            Label value1 = new Label(trouble.getNom());
            value1.setFont(new Font("Arial", 12));
            VBox.setMargin(value1, new Insets(0, 0, 0, 10));
            vbox1.getChildren().addAll(label1, value1);

            VBox vbox2 = new VBox();
            vbox2.setPadding(new Insets(10));
            Label label2 = new Label("Catégorie:");
            label2.setFont(Font.font("Arial", FontWeight.BOLD, 13));
            Label value2 = new Label(trouble.getCategorie().getLabel());
            value2.setFont(new Font("Arial", 12));
            VBox.setMargin(value2, new Insets(0, 0, 0, 10));
            vbox2.getChildren().addAll(label2, value2);

            gridPane.add(vbox1, 0, 0);
            gridPane.add(vbox2, 1, 0);

            diagnostic.getChildren().add(gridPane);
        }
        projet.setText(bo.getProjetTherapeutique());
    }

    @FXML
    void afficherAnamnese(ActionEvent event) {
        try { // Charger le fichier FXML de la page d'inscription
            FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherAnamnese.fxml"));
            Parent root = loader.load();

            // Obtenir le contrôleur de la page d'inscription
            afficherAnamneseController controller = loader.getController();
            controller.setOrthophoniste(orthophoniste);
            controller.setDossier(dossier);
            controller.setBO(bo);
            controller.setAnamnese(((BOpremierRDV) bo).getAnamnese());

            // Créer une nouvelle scène avec la racine chargée depuis le fichier FXML
            Scene scene = new Scene(root);

            // Obtenir la référence de la scène actuelle à partir du bouton cliqué
            Stage stage = (Stage) anamnese.getScene().getWindow();

            // Modifier la scène actuelle pour afficher la nouvelle scène
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void retourPage(ActionEvent event) {
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

}
