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

public class modifExerciceController {
    @FXML
    private Button retour;

    @FXML
    private VBox afficherExercices;

    @FXML
    private Button enregModif;

    @FXML
    private Button modEx;

    @FXML
    private VBox modifierExercice;

    @FXML
    private Button suppEx;
    Orthophoniste orthophoniste;

    public void setOrthophoniste(Orthophoniste orthophoniste) {
        this.orthophoniste = orthophoniste;
        afficherExercices();
    }

    public void afficherExercices() {
        HashMap<String, Exercice> exercices = orthophoniste.getExercices();
        // Parcours de chaque entrée dans la HashMap
        for (Map.Entry<String, Exercice> entry : exercices.entrySet()) {
            String key = entry.getKey(); // Clé de la Exercice

            // Création d'un CheckBox avec le promptedText étant la clé de la Exercice
            CheckBox checkBox = new CheckBox();
            checkBox.setText(key);
            VBox.setMargin(checkBox, new Insets(10));

            // Ajout du CheckBox au VBox dynamique
            afficherExercices.getChildren().add(checkBox);
        }
    }

    public Set<String> getSelectedExercices() {
        Set<String> selectedExercices = new HashSet<>();

        for (javafx.scene.Node node : afficherExercices.getChildren()) {
            if (node instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) node;
                if (checkBox.isSelected()) {
                    String key = checkBox.getText();
                    selectedExercices.add(key);
                }
            }
        }

        return selectedExercices;
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
            Stage stage = (Stage) suppEx.getScene().getWindow();

            // Modifier la scène actuelle pour afficher la nouvelle scène
            stage.setScene(scene);
            // stage.setMaximized(true);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void modifierExercice(ActionEvent event) {
        Set<String> selectedExercices = getSelectedExercices();

        if (selectedExercices.size() == 1) {
            Iterator<String> it = selectedExercices.iterator();
            if (it.hasNext()) {
                String cle = it.next();
                Exercice exercice = orthophoniste.getExercice(cle);

            }

        } else {
            Util.afficherErreur("Erreur",
                    "Veuillez sélectionner une seule Exercice à modifier.");
        }
    }

    @FXML
    void supprimerExercice(ActionEvent event) {
        Set<String> selectedExercices = getSelectedExercices();

        if (!selectedExercices.isEmpty()) {
            // Afficher un pop-up de confirmation
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de suppression");
            alert.setHeaderText("Suppression des Exercices sélectionnées");
            alert.setContentText("Voulez-vous vraiment supprimer les Exercices sélectionnées ?");

            // Obtenir la réponse de l'utilisateur
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Supprimer les Exercices sélectionnées
                for (String Exercice : selectedExercices) {
                    orthophoniste.supprimerExercice(Exercice);
                }
                System.out.println("Exercices supprimées.");
                // Mettre à jour l'interface utilisateur
                afficherExercices.getChildren().removeIf(node -> {
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
            // Aucune Exercice sélectionnée
            Util.afficherErreur("Aucune Exercice sélectionnée",
                    "Veuillez sélectionner au moins une Exercice à supprimer.");
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
