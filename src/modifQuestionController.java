import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class modifQuestionController {
    @FXML
    private Button retour;

    @FXML
    private VBox afficherQuestions;

    @FXML
    private Button enregModif;

    @FXML
    private VBox modifierQuestion;

    @FXML
    private Button suppQuest;
    Orthophoniste orthophoniste;

    public void setOrthophoniste(Orthophoniste orthophoniste) {
        this.orthophoniste = orthophoniste;
        afficherQuestions();
    }

    public void afficherQuestions() {
        HashMap<String, Question> questions = orthophoniste.getQuestions();
        // Parcours de chaque entrée dans la HashMap
        for (Map.Entry<String, Question> entry : questions.entrySet()) {
            String key = entry.getKey(); // Clé de la question

            // Création d'un CheckBox avec le promptedText étant la clé de la question
            CheckBox checkBox = new CheckBox();
            checkBox.setText(key);
            VBox.setMargin(checkBox, new Insets(10));

            // Ajout du CheckBox au VBox dynamique
            afficherQuestions.getChildren().add(checkBox);
        }
    }

    public Set<String> getSelectedQuestions() {
        Set<String> selectedQuestions = new HashSet<>();

        for (javafx.scene.Node node : afficherQuestions.getChildren()) {
            if (node instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) node;
                if (checkBox.isSelected()) {
                    String key = checkBox.getText();
                    selectedQuestions.add(key);
                }
            }
        }

        return selectedQuestions;
    }

    @FXML
    void enregistrerModifications(ActionEvent event) {
        try {
            // Charger le fichier FXML de la page de connexion
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Agenda.fxml"));
            Parent root = loader.load();
            AgendaController controller = loader.getController();
            controller.setOrthophoniste(orthophoniste);

            // Créer une nouvelle scène avec la racine chargée depuis le fichier FXML
            Scene scene = new Scene(root);

            // Obtenir la référence de la scène actuelle à partir du bouton cliqué
            Stage stage = (Stage) suppQuest.getScene().getWindow();

            // Modifier la scène actuelle pour afficher la nouvelle scène
            stage.setScene(scene);
            // stage.setMaximized(true);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void supprimerQuestion(ActionEvent event) {
        Set<String> selectedQuestions = getSelectedQuestions();

        if (!selectedQuestions.isEmpty()) {
            // Afficher un pop-up de confirmation
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de suppression");
            alert.setHeaderText("Suppression des questions sélectionnées");
            alert.setContentText("Voulez-vous vraiment supprimer les questions sélectionnées ?");

            // Obtenir la réponse de l'utilisateur
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Supprimer les questions sélectionnées
                for (String question : selectedQuestions) {
                    orthophoniste.supprimerQuestion(question);
                }
                System.out.println("Questions supprimées.");
                // Mettre à jour l'interface utilisateur
                afficherQuestions.getChildren().removeIf(node -> {
                    if (node instanceof CheckBox) {
                        CheckBox checkBox = (CheckBox) node;
                        return checkBox.isSelected();
                    }
                    return false;
                });
            } else {
                System.out.println("Suppression annulée.");
            }
        } else {
            // Aucune question sélectionnée
            Util.afficherErreur("Aucune question sélectionnée",
                    "Veuillez sélectionner au moins une question à supprimer.");
        }
    }

    @FXML
    void retourPage(ActionEvent event) {
        try {
            // Charger le fichier FXML de la page de connexion
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Agenda.fxml"));
            Parent root = loader.load();
            AgendaController controller = loader.getController();
            controller.setOrthophoniste(orthophoniste);

            // Créer une nouvelle scène avec la racine chargée depuis le fichier FXML
            Scene scene = new Scene(root);

            // Obtenir la référence de la scène actuelle à partir du bouton cliqué
            Stage stage = (Stage) suppQuest.getScene().getWindow();

            // Modifier la scène actuelle pour afficher la nouvelle scène
            stage.setScene(scene);
            // stage.setMaximized(true);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
