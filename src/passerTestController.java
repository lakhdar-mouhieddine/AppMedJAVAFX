import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;

public class passerTestController {

    final int MIN = 0;
    final int MAX = 10;

    @FXML
    private Button retour;

    @FXML
    private VBox afficher;

    @FXML
    private TextField conclusion;

    @FXML
    private Button enregistrer;

    Orthophoniste orthophoniste;

    public void setOrthophoniste(Orthophoniste orthophoniste) {
        this.orthophoniste = orthophoniste;
    }

    CompteRendu compteRendu;

    public void setCompteRendu(CompteRendu compteRendu) {
        this.compteRendu = compteRendu;
        afficher();
    }

    Dossier dossier;

    public void setDossier(Dossier dossier) {
        this.dossier = dossier;
    }

    EpreuveClinique EC;

    public void setEpreuveClinique(EpreuveClinique EpreuveClinique) {
        this.EC = EpreuveClinique;
    }

    BO bo;

    public void setBO(BO bo) {
        this.bo = bo;
    }

    HashMap<Slider, Object> scores;

    public void afficher() {
        Test test = compteRendu.getTest();
        scores = new HashMap<>();
        if (test instanceof TestQuestionnaire) {
            Set<Question> questions = ((TestQuestionnaire) test).getQuestions();
            for (Question question : questions) {
                // Enoncé de la question
                Label enonceLabel = new Label(question.getEnonce());
                enonceLabel.setStyle("-fx-font-weight: bold; -fx-padding: 10 0 5 0;");
                afficher.getChildren().add(enonceLabel);

                if (question instanceof QCM) {
                    QCM qcm = (QCM) question;
                    ArrayList<String> propositions = qcm.getPropositions();
                    ArrayList<String> reponses = qcm.getReponses();

                    // Titre des propositions
                    Label propositionTitle = new Label("- Propositions:");
                    propositionTitle.setStyle("-fx-padding: 5 0 0 20;");
                    afficher.getChildren().add(propositionTitle);

                    for (String proposition : propositions) {
                        Label propositionLabel = new Label(proposition);
                        if (reponses.contains(proposition)) {
                            propositionLabel.setText(proposition + " (correcte)");
                        }
                        propositionLabel.setStyle("-fx-padding: 2 0 2 40;");
                        afficher.getChildren().add(propositionLabel);
                    }
                } else if (question instanceof QCU) {
                    QCU qcu = (QCU) question;
                    ArrayList<String> propositions = qcu.getPropositions();
                    String reponse = qcu.getReponse();

                    // Titre des propositions
                    Label propositionTitle = new Label("- Propositions:");
                    propositionTitle.setStyle("-fx-padding: 5 0 0 20;");
                    afficher.getChildren().add(propositionTitle);
                    for (String proposition : propositions) {
                        Label propositionLabel = new Label(proposition);
                        if (proposition.equals(reponse)) {
                            propositionLabel.setText(proposition + " (correcte)");
                        }
                        propositionLabel.setStyle("-fx-padding: 2 0 2 40;");
                        afficher.getChildren().add(propositionLabel);
                    }
                }
                // Slider pour la note de la question
                HBox sliderBox = new HBox();
                sliderBox.setSpacing(10);
                sliderBox.setPadding(new Insets(0, 0, 10, 20));
                Label sliderLabel = new Label("Score: 5");
                Slider slider = new Slider(MIN, MAX, (MIN + MAX) / 2);
                // Add a listener to the slider to update the label
                slider.valueProperty().addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observable, Number oldValue,
                            Number newValue) {
                        sliderLabel.setText("Score: " + newValue.intValue());
                    }
                });
                sliderBox.getChildren().addAll(sliderLabel, slider);
                afficher.getChildren().add(sliderBox);
                scores.put(slider, question);

            }
        } else if (test instanceof TestExercice) {
            ArrayList<Exercice> exercices = ((TestExercice) test).getExercices();
            for (Exercice exercice : exercices) {
                // Enoncé de l'exercice
                Label enonceLabel = new Label(exercice.getNom());
                enonceLabel.setStyle("-fx-font-weight: bold; -fx-padding: 10 0 5 0;");
                afficher.getChildren().add(enonceLabel);

                // Affichage du matériel, s'il y en a
                String materiel = exercice.getNomMateriel();
                if (materiel != null && !materiel.isEmpty()) {
                    Label materielLabel = new Label("Materiel: " + materiel);
                    materielLabel.setStyle("-fx-padding: 2 0 2 20;");
                    afficher.getChildren().add(materielLabel);
                }

                // Slider pour la note de l'exercice
                HBox sliderBox = new HBox();
                sliderBox.setSpacing(10);
                sliderBox.setPadding(new Insets(0, 0, 10, 20));
                Label sliderLabel = new Label("Score: 5");
                Slider slider = new Slider(MIN, MAX, (MIN + MAX) / 2);
                // Add a listener to the slider to update the label
                slider.valueProperty().addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observable, Number oldValue,
                            Number newValue) {
                        sliderLabel.setText("Score: " + newValue.intValue());
                    }
                });
                sliderBox.getChildren().addAll(sliderLabel, slider);
                afficher.getChildren().add(sliderBox);
                scores.put(slider, exercice);
            }
        }
    }

    @FXML
    void creerCompteRendu(ActionEvent event) {
        if (conclusion.getText().isEmpty()) {
            Util.afficherErreur("Erreur", "Vous devez ajouter une conclusion ");
            return;
        }
        Test test = compteRendu.getTest();
        if (test instanceof TestQuestionnaire) {
            // compteRendu = new CompteRenduQuestionnaire((TestQuestionnaire) test);
            for (Map.Entry<Slider, Object> entry : scores.entrySet()) {
                int score = (int) entry.getKey().getValue();
                ((CompteRenduQuestionnaire) compteRendu).ajouterScore((Question) entry.getValue(), score);
            }
            compteRendu.setConclusion(conclusion.getText());
        } else {
            Map<Exercice, ArrayList<Integer>> notes = new HashMap<>();
            // compteRendu = new CompteRenduExercice((TestExercice) test);
            for (Map.Entry<Slider, Object> entry : scores.entrySet()) {
                int score = (int) entry.getKey().getValue();
                Exercice exercice = (Exercice) entry.getValue();
                if (!notes.containsKey(exercice)) {
                    notes.put(exercice, new ArrayList<>());
                }
                notes.get(exercice).add(score);
            }
            ((CompteRenduExercice) compteRendu).setScores(notes);
            compteRendu.setConclusion(conclusion.getText());
        }
        dossier.ajouterCompteRendu(compteRendu);
        Util.afficherSucces("Compte rendu enregistré ", "Compte rendu crée avec succès");
        try {
            // Charger le fichier FXML de la page d'inscription
            FXMLLoader loader = new FXMLLoader(getClass().getResource("passerEc.fxml"));
            Parent root = loader.load();

            // Obtenir le contrôleur de la page d'inscription
            passerEcController controller = loader.getController();
            controller.setOrthophoniste(orthophoniste);
            controller.setDossier(dossier);
            controller.setEpreuveClinique(EC);
            controller.setBO(bo);

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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("passerEc.fxml"));
            Parent root = loader.load();

            // Obtenir le contrôleur de la page d'inscription
            passerEcController controller = loader.getController();
            controller.setOrthophoniste(orthophoniste);
            controller.setDossier(dossier);
            controller.setEpreuveClinique(EC);
            controller.setBO(bo);

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
