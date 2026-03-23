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
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class modifTestContentController {

    @FXML
    private VBox afficher;

    @FXML
    private VBox ajoutBox;

    @FXML
    private VBox afficherAjout;

    @FXML
    private Button ajoutSelect;

    @FXML
    private Button ajouter;

    @FXML
    private TextField capacite;

    @FXML
    private Button enregistrer;

    @FXML
    private TextField nom;

    @FXML
    private Button supprimer;

    @FXML
    private Button retour;

    Orthophoniste orthophoniste;

    public void setOrthophoniste(Orthophoniste orthophoniste) {
        this.orthophoniste = orthophoniste;
    }

    Test test;

    public void setTest(Test test) {
        this.test = test;
        nom.setText(test.getNom());
        capacite.setText(test.getCapacite());
        afficher();
    }

    public void afficher() {
        afficher.getChildren().clear();
        if (test instanceof TestQuestionnaire) {
            Set<Question> questions = ((TestQuestionnaire) test).getQuestions();
            // Parcours de chaque entrée dans la HashMap
            for (Question question : questions) {
                String enonce = question.getEnonce(); // Clé de la question

                // Création d'un CheckBox avec le promptedText étant la clé de la question
                CheckBox checkBox = new CheckBox();
                checkBox.setText(enonce);
                VBox.setMargin(checkBox, new Insets(10));

                // Ajout du CheckBox au VBox dynamique
                afficher.getChildren().add(checkBox);
            }
        } else if (test instanceof TestExercice) {
            ArrayList<Exercice> exercices = ((TestExercice) test).getExercices();
            // Parcours de chaque entrée dans la HashMap
            for (Exercice exercice : exercices) {
                String enonce = exercice.getNom(); // Clé de la Exercice

                // Création d'un CheckBox avec le promptedText étant la clé de la Exercice
                CheckBox checkBox = new CheckBox();
                checkBox.setText(enonce);
                VBox.setMargin(checkBox, new Insets(10));

                // Ajout du CheckBox au VBox dynamique
                afficher.getChildren().add(checkBox);
            }

        }
    }

    @FXML
    void ajouterQuest(ActionEvent event) {
        ajoutBox.setVisible(true);
        afficherAjout.getChildren().clear();
        if (test instanceof TestQuestionnaire) {
            HashMap<String, Question> questions = orthophoniste.getQuestions();
            Set<Question> testQuestions = ((TestQuestionnaire) test).getQuestions();
            for (Map.Entry<String, Question> entry : questions.entrySet()) {
                Question question = entry.getValue();
                if (!testQuestions.contains(question)) {
                    CheckBox checkBox = new CheckBox();
                    checkBox.setText(question.getEnonce());
                    VBox.setMargin(checkBox, new Insets(10));
                    afficherAjout.getChildren().add(checkBox);
                }
            }
        } else if (test instanceof TestExercice) {
            HashMap<String, Exercice> exercices = orthophoniste.getExercices();
            for (Map.Entry<String, Exercice> entry : exercices.entrySet()) {
                CheckBox checkBox = new CheckBox();
                checkBox.setText(entry.getKey());
                VBox.setMargin(checkBox, new Insets(10));
                afficherAjout.getChildren().add(checkBox);
            }
        }
    }

    @FXML
    void save(ActionEvent event) {
        // Vérifier si l'un des champs est vide
        if (nom.getText().isEmpty() || capacite.getText().isEmpty()) {
            Util.afficherErreur("Erreur", "Tous les champs doivent être remplis ");
            return;
        }
        // Afficher un pop-up de confirmation
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de modification");
        alert.setHeaderText("Enregistrement des modification");
        alert.setContentText("Voulez-vous vraiment appliquer les modifications sur ce test?");

        // Obtenir la réponse de l'utilisateur
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            String ancienNom = test.getNom();
            test.setNom(nom.getText());
            test.setCapacite(capacite.getText());
            orthophoniste.remplacerTest(ancienNom, test);
            try {
                // Charger le fichier FXML de la page de connexion
                FXMLLoader loader = new FXMLLoader(getClass().getResource("modifTest.fxml"));
                Parent root = loader.load();
                modifTestController controller = loader.getController();
                controller.setOrthophoniste(orthophoniste);

                // Créer une nouvelle scène avec la racine chargée depuis le fichier FXML
                Scene scene = new Scene(root);

                // Obtenir la référence de la scène actuelle à partir du bouton cliqué
                Stage stage = (Stage) enregistrer.getScene().getWindow();

                // Modifier la scène actuelle pour afficher la nouvelle scène
                stage.setScene(scene);
                // stage.setMaximized(true);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Set<String> getSelected(VBox vbox) {
        Set<String> selected = new HashSet<>();

        for (javafx.scene.Node node : vbox.getChildren()) {
            if (node instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) node;
                if (checkBox.isSelected()) {
                    String key = checkBox.getText();
                    selected.add(key);
                }
            }
        }

        return selected;
    }

    @FXML
    void ajouterSelection(ActionEvent event) {
        Set<String> selected = getSelected(afficherAjout);
        ajoutBox.setVisible(false);
        ajoutBox.setManaged(false);
        if (selected.isEmpty()) {
            Util.afficherErreur("Erreur", "Au moins une question doit être sélectionnée");
            return;
        }
        if (test instanceof TestQuestionnaire) {
            for (String key : selected) {
                Question question = orthophoniste.getQuestion(key);
                ((TestQuestionnaire) test).ajouterQuestion(question);
            }
        } else if (test instanceof TestExercice) {
            for (String key : selected) {
                Exercice exercice = orthophoniste.getExercice(key);
                ((TestExercice) test).ajouterExercice(exercice);
            }
        }
        afficher();
        afficherAjout.setVisible(false);
        afficherAjout.setManaged(false);
    }

    @FXML
    void supprimerQuests(ActionEvent event) {
        Set<String> selected = getSelected(afficher);

        if (!selected.isEmpty()) {
            // Afficher un pop-up de confirmation
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de suppression");
            alert.setHeaderText("Suppression des elements sélectionnées");
            alert.setContentText("Voulez-vous vraiment supprimer les elements sélectionnées de ce test?");

            // Obtenir la réponse de l'utilisateur
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Supprimer les elements sélectionnées
                if (test instanceof TestQuestionnaire) {
                    for (String enonce : selected) {
                        ((TestQuestionnaire) test).supprimerQuestion(enonce);
                    }
                } else if (test instanceof TestExercice) {
                    for (String nom : selected) {
                        ((TestExercice) test).supprimerExercice(nom);
                    }
                }
                System.out.println("elements supprimées.");
                // Mettre à jour l'interface utilisateur
                afficher.getChildren().removeIf(node -> {
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
    public void retourPage(ActionEvent event) {
        try {
            // Charger le fichier FXML de la page de connexion
            FXMLLoader loader = new FXMLLoader(getClass().getResource("modifTest.fxml"));
            Parent root = loader.load();
            modifTestController controller = loader.getController();
            controller.setOrthophoniste(orthophoniste);

            // Créer une nouvelle scène avec la racine chargée depuis le fichier FXML
            Scene scene = new Scene(root);

            // Obtenir la référence de la scène actuelle à partir du bouton cliqué
            Stage stage = (Stage) enregistrer.getScene().getWindow();

            // Modifier la scène actuelle pour afficher la nouvelle scène
            stage.setScene(scene);
            // stage.setMaximized(true);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
