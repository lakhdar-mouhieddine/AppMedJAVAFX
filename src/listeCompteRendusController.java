import java.io.IOException;
import java.util.ArrayList;
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

public class listeCompteRendusController {

    @FXML
    private VBox afficherCompteRendus;

    @FXML
    private Button consult;

    @FXML
    private Button retour;

    private Orthophoniste orthophoniste;

    public void setOrthophoniste(Orthophoniste orthophoniste) {
        this.orthophoniste = orthophoniste;
    }

    private Dossier dossier;

    public void setDossier(Dossier dossier) {
        this.dossier = dossier;
        afficher();
    }

    public void afficher() {
        ArrayList<CompteRendu> compteRendus = dossier.getCompteRendus();
        // Parcours de chaque entrée dans la HashMap
        for (CompteRendu compteRendu : compteRendus) {
            String nom = compteRendu.getTest().getNom(); // nom de CompteRendu passé

            // Création d'un CheckBox avec le promptedText étant la clé de la CompteRendu
            CheckBox checkBox = new CheckBox();
            checkBox.setText(nom);
            VBox.setMargin(checkBox, new Insets(10));

            // Ajout du CheckBox au VBox dynamique
            afficherCompteRendus.getChildren().add(checkBox);
        }
    }

    public String getSelectedCompteRendu() throws Exception {
        String selectedCompteRendu = null;

        for (javafx.scene.Node node : afficherCompteRendus.getChildren()) {
            if (node instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) node;
                if (checkBox.isSelected()) {
                    if (selectedCompteRendu != null) {
                        throw new Exception(
                                "Plus d'un compte rendu sélectionné. Veuillez sélectionner un seul compte rendu.");
                    }
                    selectedCompteRendu = checkBox.getText();
                }
            }
        }

        if (selectedCompteRendu == null) {
            throw new Exception("Aucun compte rendu sélectionné. Veuillez sélectionner un compte rendu.");
        }

        return selectedCompteRendu;
    }

    @FXML
    void consulter(ActionEvent event) {
        try {
            String selectedCompteRendu = getSelectedCompteRendu();

            CompteRendu compteRendu = dossier.getCompteRendu(selectedCompteRendu);

            try {
                // Charger le fichier FXML de la page de connexion
                FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherCompteRendu.fxml"));
                Parent root = loader.load();
                afficherCompteRenduController controller = loader.getController();
                controller.setOrthophoniste(orthophoniste);
                controller.setDossier(dossier);
                controller.setCompteRendu(compteRendu);

                // Créer une nouvelle scène avec la racine chargée depuis le fichier FXML
                Scene scene = new Scene(root);

                // Obtenir la référence de la scène actuelle à partir du bouton cliqué
                Stage stage = (Stage) consult.getScene().getWindow();

                // Modifier la scène actuelle pour afficher la nouvelle scène
                stage.setScene(scene);
                // stage.setMaximized(true);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                Util.afficherErreur("Erreur",
                        "Une erreur s'est produite lors du chargement de la page.");
            }
        } catch (Exception e) {
            Util.afficherErreur("Erreur", e.getMessage());
        }

    }

    @FXML
    void retourPage(ActionEvent event) {
        try {
            // Charger le fichier FXML de la page de connexion
            FXMLLoader loader = new FXMLLoader(getClass().getResource("profilPatient.fxml"));
            Parent root = loader.load();
            profilPatientController controller = loader.getController();
            controller.setOrthophoniste(orthophoniste);
            controller.setDossier(dossier);

            // Créer une nouvelle scène avec la racine chargée depuis le fichier FXML
            Scene scene = new Scene(root);

            // Obtenir la référence de la scène actuelle à partir du bouton cliqué
            Stage stage = (Stage) consult.getScene().getWindow();

            // Modifier la scène actuelle pour afficher la nouvelle scène
            stage.setScene(scene);
            // stage.setMaximized(true);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            Util.afficherErreur("Erreur",
                    "Une erreur s'est produite lors du chargement de la page.");
        }
    }

}
