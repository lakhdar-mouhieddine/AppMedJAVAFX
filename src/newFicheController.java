import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class newFicheController {
    @FXML
    private Button ajouter;

    @FXML
    private Button enregistrer;

    @FXML
    void retourPage(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("listeFicheSuivi.fxml"));
            Parent root = loader.load();
            listeFicheSuiviController controller = loader.getController();
            controller.setOrthophoniste(orthophoniste);
            controller.setDossier(dossier);
            Scene scene = new Scene(root);
            Stage stage = (Stage) vboxContainer.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<VBoxLayout> vboxLayouts = new ArrayList<>();

    @FXML
    private Button retour;

    @FXML
    private VBox vboxContainer;

    private Orthophoniste orthophoniste;

    public void setOrthophoniste(Orthophoniste orthophoniste) {
        this.orthophoniste = orthophoniste;
    }

    private Dossier dossier;

    public void setDossier(Dossier dossier) {
        this.dossier = dossier;
    }

    @FXML
    private void initialize() {
        // Initialize with some VBox layouts
        VBoxLayout layout = createVBoxLayout();
        vboxLayouts.add(layout);
        vboxContainer.getChildren().add(layout.getVbox());
    }

    // Méthode pour gérer la sélection des RadioButtons
    private void handleRadioButtonAction(VBoxLayout layout, RadioButton selectedRadioButton) {
        RadioButton[] radioButtons = { layout.getRadioButton1(), layout.getRadioButton2(), layout.getRadioButton3() };
        // Désélectionner les autres RadioButtons dans le même VBoxLayout
        for (RadioButton radioButton : radioButtons) {
            if (!radioButton.equals(selectedRadioButton)) {
                radioButton.setSelected(false);
            }
        }
    }

    // Méthode appelée lorsque le bouton "Ajouter" est cliqué
    @FXML
    private void ajouterObjectif() {
        VBoxLayout layout = createVBoxLayout();
        vboxLayouts.add(layout);
        vboxContainer.getChildren().add(layout.getVbox());
    }

    // Méthode appelée lorsque le bouton "Enregistrer" est cliqué
    @FXML
    private void enregistrerFiche() {
        FicheSuivi fiche = new FicheSuivi();
        for (VBoxLayout layout : vboxLayouts) {
            TextField nom = layout.getTextField();
            RadioButton radioButton1 = layout.getRadioButton1();
            RadioButton radioButton2 = layout.getRadioButton2();
            RadioButton radioButton3 = layout.getRadioButton3();

            // Ajouter votre logique pour enregistrer les informations ici
            System.out.println("Nom: " + nom.getText());
            System.out.println("Court terme: " + radioButton1.isSelected());
            System.out.println("Moyen terme: " + radioButton2.isSelected());
            System.out.println("Long terme: " + radioButton3.isSelected());

            if (nom.getText().isEmpty()) {
                Util.afficherErreur("Erreur", "Veuillez donner un nom pour chaque objectif");
                return;
            }
            ObjectifCategorie categ;
            if (radioButton1.isSelected()) {
                categ = ObjectifCategorie.COURT_TERME;
            } else if (radioButton2.isSelected()) {
                categ = ObjectifCategorie.MOYEN_TERME;
            } else if (radioButton3.isSelected()) {
                categ = ObjectifCategorie.LONG_TERME;
            } else {
                Util.afficherErreur("Erreur", "Veuillez selectionnez une categorie pour chaque objectif");
                return;
            }
            Objectif objectif = new Objectif(nom.getText(), categ);
            fiche.ajouterObjectif(objectif);
        }
        dossier.ajouterFicheSuivi(fiche);
        Util.afficherSucces("Fiche de suivi enregistrée", "Fiche de suivi crée avec succès");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("listeFicheSuivi.fxml"));
            Parent root = loader.load();
            listeFicheSuiviController controller = loader.getController();
            controller.setOrthophoniste(orthophoniste);
            controller.setDossier(dossier);
            Scene scene = new Scene(root);
            Stage stage = (Stage) vboxContainer.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // Method to create a VBox layout
    private VBoxLayout createVBoxLayout() {
        VBox vbox = new VBox();
        vbox.setPrefHeight(62.0);
        vbox.setPrefWidth(300.0);
        vbox.setStyle("-fx-background-color: white;");
        vbox.setPadding(new Insets(10, 20, 10, 20));

        Label label1 = new Label("Nom de l'objectif : ");
        label1.setFont(new Font("Arial", 12.0));
        VBox.setMargin(label1, new Insets(0, 10, 0, 10));

        TextField textField = new TextField();
        textField.setPrefHeight(6.0);
        textField.setPrefWidth(280.0);
        textField.setPromptText("Nom");
        textField.setStyle("-fx-background-color: FAFAFA;");
        VBox.setMargin(textField, new Insets(10, 10, 10, 10));

        Label label2 = new Label("Catégorie");
        label2.setFont(new Font("Arial", 12.0));
        VBox.setMargin(label2, new Insets(10, 10, 0, 10));

        GridPane grid = new GridPane();
        grid.setStyle("-fx-background-color: white;");
        grid.setPadding(new Insets(10, 10, 10, 10));

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        col1.setMinWidth(10.0);
        col1.setPrefWidth(100.0);

        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        col2.setMinWidth(10.0);
        col2.setPrefWidth(100.0);

        ColumnConstraints col3 = new ColumnConstraints();
        col3.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        col3.setMinWidth(10.0);
        col3.setPrefWidth(100.0);

        grid.getColumnConstraints().addAll(col1, col2, col3);

        RowConstraints row1 = new RowConstraints();
        row1.setMinHeight(10.0);
        row1.setPrefHeight(30.0);
        row1.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        grid.getRowConstraints().add(row1);

        RadioButton radioButton1 = new RadioButton("Court terme");
        RadioButton radioButton2 = new RadioButton("Moyen terme");
        RadioButton radioButton3 = new RadioButton("Long terme");

        VBoxLayout vboxLayout = new VBoxLayout(vbox, textField, radioButton1, radioButton2, radioButton3);
        radioButton1.setOnAction(event -> handleRadioButtonAction(vboxLayout, radioButton1));
        radioButton2.setOnAction(event -> handleRadioButtonAction(vboxLayout, radioButton2));
        radioButton3.setOnAction(event -> handleRadioButtonAction(vboxLayout, radioButton3));

        GridPane.setColumnIndex(radioButton2, 1);
        GridPane.setColumnIndex(radioButton3, 2);

        grid.getChildren().addAll(radioButton1, radioButton2, radioButton3);

        vbox.getChildren().addAll(label1, textField, label2, grid);

        // Return the VBoxLayout object containing the VBox and its controls
        return vboxLayout;
    }

    private static class VBoxLayout {
        private VBox vbox;
        private TextField textField;
        private RadioButton radioButton1;
        private RadioButton radioButton2;
        private RadioButton radioButton3;

        public VBoxLayout(VBox vbox, TextField textField, RadioButton radioButton1, RadioButton radioButton2,
                RadioButton radioButton3) {
            this.vbox = vbox;
            this.textField = textField;
            this.radioButton1 = radioButton1;
            this.radioButton2 = radioButton2;
            this.radioButton3 = radioButton3;
        }

        public VBox getVbox() {
            return vbox;
        }

        public TextField getTextField() {
            return textField;
        }

        public RadioButton getRadioButton1() {
            return radioButton1;
        }

        public RadioButton getRadioButton2() {
            return radioButton2;
        }

        public RadioButton getRadioButton3() {
            return radioButton3;
        }
    }

}
