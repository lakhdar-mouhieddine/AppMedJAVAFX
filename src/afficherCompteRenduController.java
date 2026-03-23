import java.io.IOException;
import java.util.ArrayList;
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
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class afficherCompteRenduController {
    @FXML
    private VBox afficher;

    @FXML
    private Button retour;

    @FXML
    void retourPage(ActionEvent event) {
        try {
            if (EC != null) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherEc.fxml"));
                Parent root = loader.load();

                // Obtenir le contrôleur de la page d'inscription
                afficherEcController controller = loader.getController();
                controller.setOrthophoniste(orthophoniste);
                controller.setDossier(dossier);
                controller.setBO(bo);
                controller.setEpreuveClinique(EC);

                // Créer une nouvelle scène avec la racine chargée depuis le fichier FXML
                Scene scene = new Scene(root);

                // Obtenir la référence de la scène actuelle à partir du bouton cliqué
                Stage stage = (Stage) retour.getScene().getWindow();

                // Modifier la scène actuelle pour afficher la nouvelle scène
                stage.setScene(scene);

                stage.show();
            } else {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("listeCompteRendus.fxml"));
                Parent root = loader.load();

                // Obtenir le contrôleur de la page d'inscription
                listeCompteRendusController controller = loader.getController();
                controller.setOrthophoniste(orthophoniste);
                controller.setDossier(dossier);

                // Créer une nouvelle scène avec la racine chargée depuis le fichier FXML
                Scene scene = new Scene(root);

                // Obtenir la référence de la scène actuelle à partir du bouton cliqué
                Stage stage = (Stage) retour.getScene().getWindow();

                // Modifier la scène actuelle pour afficher la nouvelle scène
                stage.setScene(scene);

                stage.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private Orthophoniste orthophoniste;

    public void setOrthophoniste(Orthophoniste orthophoniste) {
        this.orthophoniste = orthophoniste;
    }

    private CompteRendu compteRendu;

    public void setCompteRendu(CompteRendu compteRendu) {
        this.compteRendu = compteRendu;
        afficher();
    }

    private EpreuveClinique EC;

    public void setEpreuveClinique(EpreuveClinique EpreuveClinique) {
        this.EC = EpreuveClinique;
        afficher();
    }

    private Dossier dossier;

    public void setDossier(Dossier dossier) {
        this.dossier = dossier;
    }

    private BO bo;

    public void setBO(BO bo) {
        this.bo = bo;
        afficher();
    }

    public void afficher() {
        Text boldText = new Text("Nom du test : ");
        boldText.setFont(Font.font("System", FontWeight.BOLD, 12));
        Text regularText = new Text(compteRendu.getTest().getNom());
        TextFlow textFlow = new TextFlow(boldText, regularText);
        Label label = new Label();
        label.setGraphic(textFlow);
        VBox.setMargin(label, new Insets(10));
        afficher.getChildren().add(label);

        boldText = new Text("Capacité du test : ");
        boldText.setFont(Font.font("System", FontWeight.BOLD, 12));
        regularText = new Text(compteRendu.getTest().getCapacite());
        textFlow = new TextFlow(boldText, regularText);
        label = new Label();
        label.setGraphic(textFlow);
        VBox.setMargin(label, new Insets(10));
        afficher.getChildren().add(label);

        String conclusion = compteRendu.getConclusion();
        if (compteRendu instanceof CompteRenduQuestionnaire) {
            Map<Question, Integer> scores = ((CompteRenduQuestionnaire) compteRendu).getScores();
            for (Map.Entry<Question, Integer> entry : scores.entrySet()) {
                Question question = entry.getKey();
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

                label = new Label();
                label.setText("Score : " + entry.getValue());
                VBox.setMargin(label, new Insets(10));

                afficher.getChildren().add(label);
            }
        } else {
            Map<Exercice, Integer> scores = ((CompteRenduExercice) compteRendu).getScores();
            for (Map.Entry<Exercice, Integer> entry : scores.entrySet()) {
                Exercice exercice = entry.getKey();
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

                label = new Label();
                label.setText("Score : " + entry.getValue());
                VBox.setMargin(label, new Insets(10));

                afficher.getChildren().add(label);
            }
        }
        boldText = new Text("Conclusion : ");
        boldText.setFont(Font.font("System", FontWeight.BOLD, 12));
        regularText = new Text(conclusion);
        textFlow = new TextFlow(boldText, regularText);
        label = new Label();
        label.setGraphic(textFlow);
        VBox.setMargin(label, new Insets(10));
        afficher.getChildren().add(label);

    }
}
