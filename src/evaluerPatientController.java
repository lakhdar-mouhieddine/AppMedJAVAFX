import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;

public class evaluerPatientController {

    final int MIN = 0;
    final int MAX = 5;

    @FXML
    private VBox afficher;

    @FXML
    private Button enregistrer;

    @FXML
    private Button retour;

    Orthophoniste orthophoniste;

    public void setOrthophoniste(Orthophoniste orthophoniste) {
        this.orthophoniste = orthophoniste;
    }

    FicheSuivi fiche;

    public void setFiche(FicheSuivi fiche) {
        this.fiche = fiche;
        afficher();
    }

    private Dossier dossier;

    public void setDossier(Dossier dossier) {
        this.dossier = dossier;
    }

    HashMap<Slider, Objectif> notes;

    public void afficher() {
        notes = new HashMap<>();
        ArrayList<Objectif> objectifs = fiche.getObjectifs();
        for (Objectif objectif : objectifs) {
            // Nom de l'objectif
            Label nom = new Label("Nom :" + objectif.getNom());
            nom.setStyle("-fx-font-weight: bold; -fx-padding: 10 0 5 0;");
            afficher.getChildren().add(nom);

            // Categorie de l'objectif
            Label categorie = new Label(" Categorie : " + objectif.getCategorie());
            categorie.setStyle("-fx-padding: 5 0 0 20;");
            afficher.getChildren().add(categorie);

            // Slider pour la note
            HBox sliderBox = new HBox();
            sliderBox.setSpacing(10);
            sliderBox.setPadding(new Insets(0, 0, 10, 20));
            Label sliderLabel = new Label("Note: " + (MIN + MAX) / 2);
            Slider slider = new Slider(MIN, MAX, (MIN + MAX) / 2);
            // Add a listener to the slider to update the label
            slider.valueProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue,
                        Number newValue) {
                    sliderLabel.setText("Note: " + newValue.intValue());
                }
            });
            sliderBox.getChildren().addAll(sliderLabel, slider);
            afficher.getChildren().add(sliderBox);
            notes.put(slider, objectif);

        }
    }

    @FXML
    void enregistrer(ActionEvent event) {
        for (Map.Entry<Slider, Objectif> entry : notes.entrySet()) {
            int score = (int) entry.getKey().getValue();
            Objectif objectif = entry.getValue();
            objectif.addNote(score);
        }
        Util.afficherSucces("Notes ajoutées", "Les notes des objectifs ont été ajouté avec succès");
        try {
            // Charger le fichier FXML de la page d'inscription
            FXMLLoader loader = new FXMLLoader(getClass().getResource("postBO.fxml"));
            Parent root = loader.load();

            // Obtenir le contrôleur de la page d'inscription
            postBoController controller = loader.getController();
            controller.setOrthophoniste(orthophoniste);
            controller.setDossier(dossier);

            // Créer une nouvelle scène avec la racine chargée depuis le fichier FXML
            Scene scene = new Scene(root);

            // Obtenir la référence de la scène actuelle à partir du bouton cliqué
            Stage stage = (Stage) enregistrer.getScene().getWindow();

            // Modifier la scène actuelle pour afficher la nouvelle scène
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void retourPage(ActionEvent event) {
        try {
            // Charger le fichier FXML de la page d'inscription
            FXMLLoader loader = new FXMLLoader(getClass().getResource("postBO.fxml"));
            Parent root = loader.load();

            // Obtenir le contrôleur de la page d'inscription
            postBoController controller = loader.getController();
            controller.setOrthophoniste(orthophoniste);
            controller.setDossier(dossier);

            // Créer une nouvelle scène avec la racine chargée depuis le fichier FXML
            Scene scene = new Scene(root);

            // Obtenir la référence de la scène actuelle à partir du bouton cliqué
            Stage stage = (Stage) enregistrer.getScene().getWindow();

            // Modifier la scène actuelle pour afficher la nouvelle scène
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
