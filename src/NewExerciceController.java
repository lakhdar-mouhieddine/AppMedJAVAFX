import java.io.IOException;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class NewExerciceController {

    @FXML
    private Button enregistrer;

    @FXML
    private Button retour;

    @FXML
    private CheckBox necessiteMateriel;

    @FXML
    private TextField nomExercice;

    @FXML
    private TextField nomMateriel;

    private Orthophoniste orthophoniste;

    @SuppressWarnings("static-access")
    public void setOrthophoniste(Orthophoniste orthophoniste) {
        this.orthophoniste = orthophoniste;
    }

    @FXML
    public void initialize() {
        nomMateriel.setVisible(false);
        necessiteMateriel.setOnAction(event -> {
            if (necessiteMateriel.isSelected()) {
                nomMateriel.setVisible(true);
            } else {
                nomMateriel.setVisible(false);
            }
        });
    }

    @FXML
    void enregistrerExercice(ActionEvent event) {
        String nom = nomExercice.getText().trim();

        if (nom.isEmpty()) {
            Util.afficherErreur("Erreur", "Le nom de l'exercice ne peut pas être vide.");
            return;
        }

        Exercice exercice;
        if (necessiteMateriel.isSelected()) {
            String materiel = nomMateriel.getText().trim();
            if (materiel.isEmpty()) {
                Util.afficherErreur("Erreur", "Le nom du matériel ne peut pas être vide.");
                return;
            }
            exercice = new Exercice(nom, materiel);
        } else {
            exercice = new Exercice(nom);
        }

        if (orthophoniste.ajouterExercice(nom, exercice)) {
            Util.afficherSucces("Exercice ajouté", "Exercice créé avec succès");
        } else {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de modification");
            alert.setHeaderText("L'exercice existe déjà.");
            alert.setContentText("Voulez-vous remplacer l'exercice existant ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                orthophoniste.remplacerExercice(nom, exercice);
                Util.afficherSucces("Exercice remplacé", "Exercice modifié avec succès");
            } else {
                System.out.println("Aucune modification effectuée.");
            }
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Agenda.fxml"));
            Parent root = loader.load();
            AgendaController controller = loader.getController();
            controller.setOrthophoniste(orthophoniste);
            Scene scene = new Scene(root);
            Stage stage = (Stage) enregistrer.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void retourPage(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Agenda.fxml"));
            Parent root = loader.load();
            AgendaController controller = loader.getController();
            controller.setOrthophoniste(orthophoniste);
            Scene scene = new Scene(root);
            Stage stage = (Stage) enregistrer.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
