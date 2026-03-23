import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class afficherFicheSuiviController {

    @FXML
    private VBox afficher;

    @FXML
    private Button retour;

    private Orthophoniste orthophoniste;

    public void setOrthophoniste(Orthophoniste orthophoniste) {
        this.orthophoniste = orthophoniste;

    }

    private Dossier dossier;

    public void setDossier(Dossier dossier) {
        this.dossier = dossier;
    }

    FicheSuivi fiche;

    public void setFiche(FicheSuivi fiche) {
        this.fiche = fiche;
        afficher();
    }

    public void afficher() {
        ArrayList<Objectif> objectifs = fiche.getObjectifs();
        for (Objectif objectif : objectifs) {
            // Nom de l'objectif
            Label nom = new Label("Nom :" + objectif.getNom());
            nom.setStyle("-fx-font-weight: bold; -fx-padding: 10 0 5 0;");
            afficher.getChildren().add(nom);

            // Categorie de l'objectif
            Label categorie = new Label(" Categorie : " + objectif.getCategorie());
            categorie.setStyle("-fx-padding: 5 0 0 20;");
            afficher.getChildren().add(categorie);

            // Note de l'objectif
            Label note = new Label(" Note : " + objectif.getNotes());
            note.setStyle("-fx-padding: 5 0 0 20;");
            afficher.getChildren().add(note);

            Region spacer = new Region();
            spacer.setMinHeight(20);
            afficher.getChildren().add(spacer);

        }
    }

    @FXML
    void retourPage(ActionEvent event) {
        try {
            // Charger le fichier FXML de la page d'inscription
            FXMLLoader loader = new FXMLLoader(getClass().getResource("listeFicheSuivi.fxml"));
            Parent root = loader.load();

            // Obtenir le contrôleur de la page d'inscription
            listeFicheSuiviController controller = loader.getController();
            controller.setOrthophoniste(orthophoniste);
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
