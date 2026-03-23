import java.io.IOException;
import java.util.ArrayList;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;

public class newQuestionController {

    @FXML
    private Button ajouterProp;

    @FXML
    private TextField enonce;

    @FXML
    private Button enregistrer;

    @FXML
    private Button retour;

    @FXML
    private VBox propositions;

    @FXML
    private VBox reponsesCorrectes;

    private Orthophoniste orthophoniste;

    @SuppressWarnings("static-access")
    public void setOrthophoniste(Orthophoniste orthophoniste) {
        this.orthophoniste = orthophoniste;
    }

    @FXML
    void ajouterProposition(ActionEvent event) {
        ajouterNewPropositionTextField();
        reponsesCorrectes.setVisible(true);
    }

    @FXML
    void enregistrerQuestion(ActionEvent event) {
        String enonceText = enonce.getText().trim();

        if (enonceText.isEmpty()) {
            Util.afficherErreur("Erreur", "L'énoncé de la question ne peut pas être vide.");
            return;
        }

        ArrayList<String> propTexts = new ArrayList<>();
        ArrayList<String> correctAnswers = new ArrayList<>();

        for (int i = 1; i < propositions.getChildren().size(); i++) {
            TextField propField = (TextField) propositions.getChildren().get(i);
            String propText = propField.getText().trim();
            if (!propText.isEmpty()) {
                propTexts.add(propText);
                CheckBox correctCheckBox = (CheckBox) reponsesCorrectes.getChildren().get(i);
                if (correctCheckBox.isSelected()) {
                    correctAnswers.add(propText);
                }
            }
        }

        Question question;
        if (propTexts.isEmpty()) {
            question = new Question(enonceText);
        } else if (correctAnswers.size() == 1) {
            question = new QCU(enonceText, propTexts, correctAnswers.get(0));
        } else if (correctAnswers.size() > 1) {
            question = new QCM(enonceText, propTexts, correctAnswers);
        } else {
            Util.afficherErreur("Erreur", "Veuillez sélectionner au moins une réponse correcte.");
            return;
        }

        if (orthophoniste.ajouterQuestion(enonceText, question)) {
            Util.afficherSucces("Question ajoutée", "Question crée avec succès");
        } else {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de modification");
            alert.setHeaderText("La question existe déjà.");
            alert.setContentText("Voulez-vous remplacer la question existante ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                orthophoniste.remplacerQuestion(enonceText, question);
                Util.afficherSucces("Question remplacée", "Question modifiée avec succès");
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

    private void ajouterNewPropositionTextField() {
        TextField propositionTextField = new TextField();
        propositionTextField.setStyle("-fx-background-color: #FAFAFA;");
        propositionTextField.setPromptText("Proposition");

        CheckBox correctCheckBox = new CheckBox("Proposition " + (propositions.getChildren().size()));

        propositions.getChildren().add(propositionTextField);
        reponsesCorrectes.getChildren().add(correctCheckBox);

        VBox.setMargin(propositionTextField, new Insets(10, 20, 10, 20));
        VBox.setMargin(correctCheckBox, new Insets(10, 20, 10, 20));
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
