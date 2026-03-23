import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class newAnamneseAdulteController {

    @FXML
    private VBox afficher;

    @FXML
    private Button retour;

    @FXML
    private Button ajouter;

    private List<VBoxLayout> vboxLayouts = new ArrayList<>();

    private Orthophoniste orthophoniste;

    public void setOrthophoniste(Orthophoniste orthophoniste) {
        this.orthophoniste = orthophoniste;
    }

    private Dossier dossier;

    public void setDossier(Dossier dossier) {
        this.dossier = dossier;
    }

    private BO bo;

    public void setBO(BO bo) {
        this.bo = bo;
    }

    @FXML
    void retourPage(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("creerBO.fxml"));
            Parent root = loader.load();
            creerBoController controller = loader.getController();
            controller.setOrthophoniste(orthophoniste);
            controller.setBO(bo);
            controller.setDossier(dossier);
            Scene scene = new Scene(root);
            Stage stage = (Stage) retour.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void ajouterQuestion() {
        VBoxLayout layout = createVBoxLayout();
        vboxLayouts.add(layout);
        afficher.getChildren().add(layout.getVbox());
    }

    @FXML
    private void initialize() {
        // Initialize with some VBox layouts
        VBoxLayout layout = createVBoxLayout();
        vboxLayouts.add(layout);
        afficher.getChildren().add(layout.getVbox());
    }

    // Méthode appelée lorsque le bouton "Enregistrer" est cliqué
    @FXML
    private void enregistrerAnamnese() {
        HashMap<QuestionAnamneseAdulte, String> questions = new HashMap<>();
        for (VBoxLayout layout : vboxLayouts) {
            TextField enonce = layout.getEnonce();
            TextField reponse = layout.getReponse();
            RadioButton radioButton1 = layout.getRadioButton1();
            RadioButton radioButton2 = layout.getRadioButton2();

            // Ajouter votre logique pour enregistrer les informations ici
            System.out.println("enonce: " + enonce.getText());
            System.out.println(radioButton1.isSelected());
            System.out.println(radioButton2.isSelected());

            if (enonce.getText().isEmpty()) {
                Util.afficherErreur("Erreur", "Veuillez donner un enonce pour chaque question");
                return;
            }

            if (reponse.getText().isEmpty()) {
                Util.afficherErreur("Erreur", "Veuillez donner une reponse pour chaque question");
                return;
            }
            QstAdulteCategorie categ;
            if (radioButton1.isSelected()) {
                categ = QstAdulteCategorie.HISTOIRE_DE_MALADIE;
            } else if (radioButton2.isSelected()) {
                categ = QstAdulteCategorie.SUIVI_MEDICAL;
            } else {
                Util.afficherErreur("Erreur", "Veuillez selectionnez une categorie pour chaque question");
                return;
            }
            QuestionAnamneseAdulte quest = new QuestionAnamneseAdulte(enonce.getText(), categ);
            questions.put(quest, reponse.getText());
        }
        AnamneseAdulte anamnese = new AnamneseAdulte(questions);
        ((BOpremierRDV) bo).setAnamnese(anamnese);
        Util.afficherSucces("Anamnese enregistrée", "Anamnese crée avec succès");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("creerBO.fxml"));
            Parent root = loader.load();
            creerBoController controller = loader.getController();
            controller.setOrthophoniste(orthophoniste);
            controller.setBO(bo);
            controller.setDossier(dossier);
            Scene scene = new Scene(root);
            Stage stage = (Stage) retour.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // Méthode pour gérer la sélection des RadioButtons
    private void handleRadioButtonAction(VBoxLayout layout, RadioButton selectedRadioButton) {
        RadioButton[] radioButtons = { layout.getRadioButton1(), layout.getRadioButton2() };
        // Désélectionner les autres RadioButtons dans le même VBoxLayout
        for (RadioButton radioButton : radioButtons) {
            if (!radioButton.equals(selectedRadioButton)) {
                radioButton.setSelected(false);
            }
        }
    }

    private VBoxLayout createVBoxLayout() {
        VBox vbox = new VBox();
        vbox.setPrefHeight(62.0);
        vbox.setPrefWidth(300.0);
        vbox.setStyle("-fx-background-color: white;");
        vbox.setPadding(new Insets(10, 20, 10, 20));

        Label label1 = new Label("Enoncé de la question : ");
        label1.setFont(new Font("Arial", 12.0));
        VBox.setMargin(label1, new Insets(0, 10, 0, 10));

        TextField enonce = new TextField();
        enonce.setPrefHeight(6.0);
        enonce.setPrefWidth(280.0);
        enonce.setPromptText("Enoncé");
        enonce.setStyle("-fx-background-color: FAFAFA;");
        VBox.setMargin(enonce, new Insets(10, 10, 10, 10));

        Label label2 = new Label("Réponse à la question : ");
        label2.setFont(new Font("Arial", 12.0));
        VBox.setMargin(label2, new Insets(0, 10, 0, 10));

        TextField reponse = new TextField();
        reponse.setPrefHeight(6.0);
        reponse.setPrefWidth(280.0);
        reponse.setPromptText("Réponse");
        reponse.setStyle("-fx-background-color: FAFAFA;");
        VBox.setMargin(reponse, new Insets(10, 10, 10, 10));

        Label label3 = new Label("Catégorie");
        label3.setFont(new Font("Arial", 12.0));
        VBox.setMargin(label3, new Insets(10, 10, 0, 10));

        GridPane grid = new GridPane();
        grid.setStyle("-fx-background-color: white;");
        grid.setHgap(10);
        grid.setVgap(10);
        VBox.setMargin(grid, new Insets(10, 10, 0, 10));
        // Set size for the GridPane
        grid.setPrefWidth(600);
        grid.setMinWidth(600);

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        col1.setMinWidth(10.0);
        col1.setPrefWidth(100.0);

        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        col2.setMinWidth(10.0);
        col2.setPrefWidth(100.0);

        grid.getColumnConstraints().addAll(col1, col2);

        RowConstraints row1 = new RowConstraints();
        row1.setMinHeight(10.0);
        row1.setPrefHeight(30.0);
        row1.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        grid.getRowConstraints().add(row1);

        RadioButton radioButton1 = new RadioButton(QstAdulteCategorie.HISTOIRE_DE_MALADIE.getLabel());
        RadioButton radioButton2 = new RadioButton(QstAdulteCategorie.SUIVI_MEDICAL.getLabel());

        VBoxLayout vboxLayout = new VBoxLayout(vbox, enonce, reponse, radioButton1, radioButton2);
        radioButton1.setOnAction(event -> handleRadioButtonAction(vboxLayout, radioButton1));
        radioButton2.setOnAction(event -> handleRadioButtonAction(vboxLayout, radioButton2));

        GridPane.setColumnIndex(radioButton2, 1);

        grid.getChildren().addAll(radioButton1, radioButton2);

        vbox.getChildren().addAll(label1, enonce, label2, reponse, label3, grid);

        // Return the VBoxLayout object containing the VBox and its controls
        return vboxLayout;
    }

    private static class VBoxLayout {
        private VBox vbox;
        private TextField enonce;
        private TextField reponse;
        private RadioButton radioButton1;
        private RadioButton radioButton2;

        public VBoxLayout(VBox vbox, TextField enonce, TextField reponse, RadioButton radioButton1,
                RadioButton radioButton2) {
            this.vbox = vbox;
            this.enonce = enonce;
            this.reponse = reponse;
            this.radioButton1 = radioButton1;
            this.radioButton2 = radioButton2;
        }

        public VBox getVbox() {
            return vbox;
        }

        public TextField getEnonce() {
            return enonce;
        }

        public TextField getReponse() {
            return reponse;
        }

        public RadioButton getRadioButton1() {
            return radioButton1;
        }

        public RadioButton getRadioButton2() {
            return radioButton2;
        }

    }

}
