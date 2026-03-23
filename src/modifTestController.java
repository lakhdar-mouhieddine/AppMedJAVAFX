import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
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

public class modifTestController {

    @FXML
    private VBox afficherTests;

    @FXML
    private Button enregModif;

    @FXML
    private Button modTest;

    @FXML
    private Button suppTest;

    @FXML
    private Button retour;

    Orthophoniste orthophoniste;

    public void setOrthophoniste(Orthophoniste orthophoniste) {
        this.orthophoniste = orthophoniste;
        afficherTests();
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
            Stage stage = (Stage) suppTest.getScene().getWindow();

            // Modifier la scène actuelle pour afficher la nouvelle scène
            stage.setScene(scene);
            // stage.setMaximized(true);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void modifierTest(ActionEvent event) {
        Set<String> selectedTests = getSelectedTests();

        if (selectedTests.size() == 1) {
            Iterator<String> it = selectedTests.iterator();
            if (it.hasNext()) {
                String cle = it.next();
                Test test = orthophoniste.getTest(cle);
                try {
                    // Charger le fichier FXML de la page de connexion
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("modifTestContent.fxml"));
                    Parent root = loader.load();
                    modifTestContentController controller = loader.getController();
                    controller.setOrthophoniste(orthophoniste);
                    controller.setTest(test);

                    // Créer une nouvelle scène avec la racine chargée depuis le fichier FXML
                    Scene scene = new Scene(root);

                    // Obtenir la référence de la scène actuelle à partir du bouton cliqué
                    Stage stage = (Stage) suppTest.getScene().getWindow();

                    // Modifier la scène actuelle pour afficher la nouvelle scène
                    stage.setScene(scene);
                    // stage.setMaximized(true);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } else {
            Util.afficherErreur("Erreur",
                    "Veuillez sélectionner une seule Test à modifier.");
        }
    }

    @FXML
    void supprimerTest(ActionEvent event) {
        Set<String> selectedTests = getSelectedTests();

        if (!selectedTests.isEmpty()) {
            // Afficher un pop-up de confirmation
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de suppression");
            alert.setHeaderText("Suppression des Tests sélectionnées");
            alert.setContentText("Voulez-vous vraiment supprimer les Tests sélectionnées ?");

            // Obtenir la réponse de l'utilisateur
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Supprimer les Tests sélectionnées
                for (String Test : selectedTests) {
                    orthophoniste.supprimerTest(Test);
                }
                System.out.println("Tests supprimées.");
                // Mettre à jour l'interface utilisateur
                afficherTests.getChildren().removeIf(node -> {
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
            // Aucune Test sélectionnée
            Util.afficherErreur("Aucune Test sélectionnée",
                    "Veuillez sélectionner au moins une Test à supprimer.");
        }
    }

    @FXML
    public void retourPage(ActionEvent event) {
        try {
            // Charger le fichier FXML de la page de connexion
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Agenda.fxml"));
            Parent root = loader.load();
            AgendaController controller = loader.getController();
            controller.setOrthophoniste(orthophoniste);

            // Créer une nouvelle scène avec la racine chargée depuis le fichier FXML
            Scene scene = new Scene(root);

            // Obtenir la référence de la scène actuelle à partir du bouton cliqué
            Stage stage = (Stage) suppTest.getScene().getWindow();

            // Modifier la scène actuelle pour afficher la nouvelle scène
            stage.setScene(scene);
            // stage.setMaximized(true);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
