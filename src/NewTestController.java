import java.io.IOException;
import java.util.ArrayList;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NewTestController {

    @FXML
    private Button retour;

    @FXML
    private TextField nom;
    @FXML
    private TextField nom2;
    @FXML
    private TextField capacite;
    @FXML
    private TextField capacite2;

    @FXML
    private VBox content1;
    @FXML
    private VBox content2;

    @FXML
    private VBox contentVBox;

    @FXML
    private Button creer1;
    @FXML
    private Button creer2;

    @FXML
    private VBox list1;
    @FXML
    private VBox list2;

    @FXML
    private Label page;

    @FXML
    private ToggleButton toggleButton;

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
            list1.getChildren().add(checkBox);
        }
    }

    public void afficherExercices() {
        list2.getChildren().clear();
        HashMap<String, Exercice> exercices = orthophoniste.getExercices();
        // Parcours de chaque entrée dans la HashMap
        for (Map.Entry<String, Exercice> entry : exercices.entrySet()) {
            String key = entry.getKey(); // Clé de la question
            System.out.println(key);
            // Création d'un CheckBox avec le promptedText étant la clé de l'exercice'
            CheckBox checkBox = new CheckBox();
            checkBox.setText(key);
            VBox.setMargin(checkBox, new Insets(10));

            // Ajout du CheckBox au VBox dynamique
            list2.getChildren().add(checkBox);
        }
    }

    public Set<Question> getSelectedQuestions() {
        Set<Question> selectedQuestions = new HashSet<>();
        HashMap<String, Question> questions = orthophoniste.getQuestions();

        for (javafx.scene.Node node : list1.getChildren()) {
            if (node instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) node;
                if (checkBox.isSelected()) {
                    String key = checkBox.getText();
                    Question question = questions.get(key);
                    if (question != null) {
                        selectedQuestions.add(question);
                    }
                }
            }
        }

        return selectedQuestions;
    }

    @FXML
    void saveQuest(ActionEvent event) {
        // Vérifier si l'un des champs est vide
        if (nom.getText().isEmpty() || capacite.getText().isEmpty()) {
            Util.afficherErreur("Erreur", "Tous les champs doivent être remplis ");
            return;
        }

        // Obtenir les questions sélectionnées
        Set<Question> selectedQuestions = getSelectedQuestions();
        if (selectedQuestions.isEmpty()) {
            Util.afficherErreur("Erreur", "Au moins une question doit être sélectionnée");
            return;
        }

        // Créer un questionnaire
        TestQuestionnaire questionnaire = new TestQuestionnaire(nom.getText(), capacite.getText(), selectedQuestions);

        // Vérifier si le questionnaire existe déjà
        if (orthophoniste.ajouterQuestionnaire(nom.getText(), questionnaire)) {
            Util.afficherSucces("Questionnaire créé", "Questionnaire créé avec succès");
        } else {
            // Demander à l'utilisateur s'il souhaite remplacer le questionnaire existant
            promptReplaceQuestionnaire(nom.getText(), questionnaire);
        }

        try {
            // Charger le fichier FXML de la page d'inscription
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Agenda.fxml"));
            Parent root = loader.load();

            // Obtenir le contrôleur de la page d'inscription
            AgendaController controller = loader.getController();
            controller.setOrthophoniste(orthophoniste);

            // Créer une nouvelle scène avec la racine chargée depuis le fichier FXML
            Scene scene = new Scene(root);

            // Obtenir la référence de la scène actuelle à partir du bouton cliqué
            Stage stage = (Stage) page.getScene().getWindow();

            // Modifier la scène actuelle pour afficher la nouvelle scène
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Exercice> getSelectedExercices() {
        ArrayList<Exercice> selectedExercices = new ArrayList<>();
        HashMap<String, Exercice> exercices = orthophoniste.getExercices();

        for (javafx.scene.Node node : list2.getChildren()) {
            if (node instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) node;
                if (checkBox.isSelected()) {
                    String key = checkBox.getText();
                    Exercice exercice = exercices.get(key); // Correct the variable name here
                    if (exercice != null) { // Correct the null check here
                        selectedExercices.add(exercice);
                    }
                }
            }
        }

        return selectedExercices;
    }

    @FXML
    void saveEx(ActionEvent event) {
        // Vérifier si l'un des champs est vide
        if (nom2.getText().isEmpty() || capacite2.getText().isEmpty()) {
            Util.afficherErreur("Erreur", "Tous les champs doivent être remplis ");
            return;
        }

        // Obtenir les exercices sélectionnés
        ArrayList<Exercice> selectedExercices = getSelectedExercices();
        if (selectedExercices.isEmpty()) {
            Util.afficherErreur("Erreur", "Au moins un exercice doit être sélectionné");
            return;
        }

        // Créer un TestExercice
        TestExercice exercice = new TestExercice(nom2.getText(), capacite2.getText(), selectedExercices);

        // Vérifier si le TestExercice existe déjà
        if (orthophoniste.ajouterTestExercice(nom2.getText(), exercice)) {
            Util.afficherSucces("Test créé", "Test exercice créé avec succès");
        } else {
            // Demander à l'utilisateur s'il souhaite remplacer le TestExercice existant
            promptReplaceTestExercice(nom2.getText(), exercice);
        }
        try {
            // Charger le fichier FXML de la page d'inscription
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Agenda.fxml"));
            Parent root = loader.load();

            // Obtenir le contrôleur de la page d'inscription
            AgendaController controller = loader.getController();
            controller.setOrthophoniste(orthophoniste);

            // Créer une nouvelle scène avec la racine chargée depuis le fichier FXML
            Scene scene = new Scene(root);

            // Obtenir la référence de la scène actuelle à partir du bouton cliqué
            Stage stage = (Stage) page.getScene().getWindow();

            // Modifier la scène actuelle pour afficher la nouvelle scène
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        // Set initial visibility
        content1.setVisible(true);
        content1.setManaged(true);

        content2.setVisible(false);
        content2.setManaged(false);
    }

    @FXML
    private void handleToggleAction() {
        if (toggleButton.isSelected()) {
            toggleButton.setText("Passer à Test Questionnaire");
            page.setText("Créer un test exercice");
            content1.setVisible(false);
            content1.setManaged(false);
            content2.setVisible(true);
            content2.setManaged(true);
            afficherExercices();
        } else {
            toggleButton.setText("Passer à Test Exercice");
            page.setText("Créer un test questionnaire");
            content1.setVisible(true);
            content1.setManaged(true);
            content2.setVisible(false);
            content2.setManaged(false);
        }
    }

    private void promptReplaceQuestionnaire(String key, TestQuestionnaire questionnaire) {
        // Demander à l'utilisateur s'il souhaite remplacer le questionnaire existant
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de modification");
        alert.setHeaderText("Le questionnaire existe déjà.");
        alert.setContentText("Voulez-vous remplacer le questionnaire existant ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            orthophoniste.remplacerTest(key, questionnaire);
            Util.afficherSucces("Questionnaire remplacé", "Questionnaire remplacé avec succès");
        } else {
            System.out.println("Aucune modification effectuée.");
        }
    }

    private void promptReplaceTestExercice(String key, TestExercice testExercice) {
        // Demander à l'utilisateur s'il souhaite remplacer le TestExercice existant
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de modification");
        alert.setHeaderText("Le TestExercice existe déjà.");
        alert.setContentText("Voulez-vous remplacer le TestExercice existant ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            orthophoniste.remplacerTest(key, testExercice);
            Util.afficherSucces("TestExercice remplacé", "TestExercice remplacé avec succès");
        } else {
            System.out.println("Aucune modification effectuée.");
        }
    }

    @FXML
    public void retourPage(ActionEvent event) {
        try {
            // Charger le fichier FXML de la page d'inscription
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Agenda.fxml"));
            Parent root = loader.load();

            // Obtenir le contrôleur de la page d'inscription
            AgendaController controller = loader.getController();
            controller.setOrthophoniste(orthophoniste);

            // Créer une nouvelle scène avec la racine chargée depuis le fichier FXML
            Scene scene = new Scene(root);

            // Obtenir la référence de la scène actuelle à partir du bouton cliqué
            Stage stage = (Stage) page.getScene().getWindow();

            // Modifier la scène actuelle pour afficher la nouvelle scène
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
