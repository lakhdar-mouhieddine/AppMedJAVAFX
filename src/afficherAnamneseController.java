
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class afficherAnamneseController {

    @FXML
    private VBox afficher;

    @FXML
    private Button retour;

    private Orthophoniste orthophoniste;

    public void setOrthophoniste(Orthophoniste orthophoniste) {
        this.orthophoniste = orthophoniste;
    }

    private Anamnese anamnese;

    public void setAnamnese(Anamnese anamnese) {
        this.anamnese = anamnese;
        afficher();
    }

    private BO bo;

    public void setBO(BO bo) {
        this.bo = bo;
    }

    private Dossier dossier;

    public void setDossier(Dossier dossier) {
        this.dossier = dossier;
    }

    public void afficher() {
        if (anamnese instanceof AnamneseAdulte) {
            HashMap<QuestionAnamneseAdulte, String> questions = ((AnamneseAdulte) anamnese).getQuestions();
            for (Map.Entry<QuestionAnamneseAdulte, String> entry : questions.entrySet()) {
                QuestionAnamneseAdulte question = entry.getKey();
                String reponse = entry.getValue();

                Label enonceLabel = new Label("Énoncé: \" " + question.getEnonce() + " \"");
                enonceLabel.setFont(new Font("Arial", 13));
                enonceLabel.setPadding(new Insets(10, 0, 0, 10));

                Label reponseLabel = new Label("Réponse: \" " + reponse + " \"");
                reponseLabel.setFont(new Font("Arial", 13));
                reponseLabel.setPadding(new Insets(10, 0, 0, 10));

                Label categorieLabel = new Label("Catégorie:  \" " + question.getCategorie().getLabel() + " \"");
                categorieLabel.setFont(new Font("Arial", 13));
                categorieLabel.setPadding(new Insets(10, 0, 0, 10));

                VBox questionBox = new VBox(enonceLabel, reponseLabel, categorieLabel);
                questionBox.setPadding(new Insets(10));
                questionBox
                        .setStyle("-fx-border-color: #d3d3d3; -fx-border-width: 1px; -fx-background-color: #ffffff;");
                questionBox.setSpacing(5);

                afficher.getChildren().add(questionBox);
            }
        } else if (anamnese instanceof AnamneseEnfant) {
            HashMap<QuestionAnamneseEnfant, String> questions = ((AnamneseEnfant) anamnese).getQuestions();
            for (Map.Entry<QuestionAnamneseEnfant, String> entry : questions.entrySet()) {
                QuestionAnamneseEnfant question = entry.getKey();
                String reponse = entry.getValue();

                Label enonceLabel = new Label("Énoncé: \" " + question.getEnonce() + " \"");
                enonceLabel.setFont(new Font("Arial", 13));
                enonceLabel.setPadding(new Insets(10, 0, 0, 10));

                Label reponseLabel = new Label("Réponse: \" " + reponse + " \"");
                reponseLabel.setFont(new Font("Arial", 13));
                reponseLabel.setPadding(new Insets(10, 0, 0, 10));

                Label categorieLabel = new Label("Catégorie:  \" " + question.getCategorie().getLabel() + " \"");
                categorieLabel.setFont(new Font("Arial", 13));
                categorieLabel.setPadding(new Insets(10, 0, 0, 10));

                VBox questionBox = new VBox(enonceLabel, reponseLabel, categorieLabel);
                questionBox.setPadding(new Insets(10));
                questionBox
                        .setStyle("-fx-border-color: #d3d3d3; -fx-border-width: 1px; -fx-background-color: #ffffff;");
                questionBox.setSpacing(5);

                afficher.getChildren().add(questionBox);
            }
        }
    }

    @FXML
    void retourPage(ActionEvent event) {
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

    }

}
