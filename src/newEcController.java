import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class newEcController {

    @FXML
    private VBox afficherTests;

    @FXML
    private Button enregistrer;

    @FXML
    private Button retour;

    Orthophoniste orthophoniste;

    public void setOrthophoniste(Orthophoniste orthophoniste) {
        this.orthophoniste = orthophoniste;
        afficherTests();
    }

    Dossier dossier;

    public void setDossier(Dossier dossier) {
        this.dossier = dossier;
    }

    BO bo;

    public void setBO(BO bo) {
        this.bo = bo;
    }

    public void afficherTests() {
        HashMap<String, Test> Tests = orthophoniste.getTests();
        // Parcours de chaque entrée dans la HashMap
        for (Map.Entry<String, Test> entry : Tests.entrySet()) {
            String key = entry.getKey(); // Clé de la Test

            // Création d'un CheckBox avec le promptedText étant la clé de la Test
            CheckBox checkBox = new CheckBox();
            checkBox.setText(key);
            VBox.setMargin(checkBox, new Insets(10));

            // Ajout du CheckBox au VBox dynamique
            afficherTests.getChildren().add(checkBox);
        }
    }

    public Set<String> getSelectedTests() {
        Set<String> selectedTests = new HashSet<>();

        for (javafx.scene.Node node : afficherTests.getChildren()) {
            if (node instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) node;
                if (checkBox.isSelected()) {
                    String key = checkBox.getText();
                    selectedTests.add(key);
                }
            }
        }

        return selectedTests;
    }

    @FXML
    void enregistrerEC(ActionEvent event) {
        Set<String> selectedTests = getSelectedTests();
        Set<Test> Tests = new HashSet<>();

        for (String nomTest : selectedTests) {
            Test test = orthophoniste.getTest(nomTest);
            if (test != null) {
                Tests.add(test);
            }
        }

        if (!selectedTests.isEmpty()) {
            EpreuveClinique ec = new EpreuveClinique(Tests);
            bo.addEpreuveClinique(ec);

            try {
                // Charger le fichier FXML de la page de connexion
                FXMLLoader loader = new FXMLLoader(getClass().getResource("creerBO.fxml"));
                Parent root = loader.load();
                creerBoController controller = loader.getController();
                controller.setOrthophoniste(orthophoniste);
                controller.setDossier(dossier);
                controller.setBO(bo);

                // Créer une nouvelle scène avec la racine chargée depuis le fichier FXML
                Scene scene = new Scene(root);

                // Obtenir la référence de la scène actuelle à partir du bouton cliqué
                Stage stage = (Stage) retour.getScene().getWindow();

                // Modifier la scène actuelle pour afficher la nouvelle scène
                stage.setScene(scene);
                // stage.setMaximized(true);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            // Aucune Test sélectionnée
            Util.afficherErreur("Aucune Test sélectionnée",
                    "Veuillez sélectionner au moins une Test à supprimer.");
        }
    }

    @FXML
    void retourPage(ActionEvent event) {
        try {
            // Charger le fichier FXML de la page de connexion
            FXMLLoader loader = new FXMLLoader(getClass().getResource("creerBO.fxml"));
            Parent root = loader.load();
            creerBoController controller = loader.getController();
            controller.setOrthophoniste(orthophoniste);
            controller.setDossier(dossier);
            controller.setBO(bo);

            // Créer une nouvelle scène avec la racine chargée depuis le fichier FXML
            Scene scene = new Scene(root);

            // Obtenir la référence de la scène actuelle à partir du bouton cliqué
            Stage stage = (Stage) retour.getScene().getWindow();

            // Modifier la scène actuelle pour afficher la nouvelle scène
            stage.setScene(scene);
            // stage.setMaximized(true);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
