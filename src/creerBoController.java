import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
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

public class creerBoController {

    @FXML
    private VBox EC;

    @FXML
    private Button ajouterTrouble;

    @FXML
    private VBox anamnese;

    @FXML
    private Button boutonAnamnese;

    @FXML
    private Button boutonEC;

    @FXML
    private VBox diagnostic;

    @FXML
    private Button enregistrer;

    @FXML
    private TextField projet;

    private Orthophoniste orthophoniste;

    public void setOrthophoniste(Orthophoniste orthophoniste) {
        this.orthophoniste = orthophoniste;

    }

    private BO bo;

    public void setBO(BO bo) {
        this.bo = bo;
        afficher();
    }

    Dossier dossier;

    public void setDossier(Dossier dossier) {
        this.dossier = dossier;
    }

    public void afficher() {
        if (bo instanceof BOpremierRDV) {
            if (((BOpremierRDV) bo).getAnamnese() != null) {
                boutonAnamnese.setVisible(false);
                boutonAnamnese.setManaged(false);
                Label label = new Label("Ce bilan orthophonique contient déjà une anamnèse");
                label.setFont(new Font("Arial", 13));
                VBox.setMargin(label, new Insets(10, 0, 20, 40));
                anamnese.getChildren().add(label);
            }
        } else {
            anamnese.setVisible(false);
            anamnese.setManaged(false);
        }
        ArrayList<EpreuveClinique> ecs = bo.getEpreuvesCliniques();
        for (int i = 0; i < ecs.size(); i++) {
            EpreuveClinique ec = ecs.get(i);
            if (ec.getObservationClinique().isEmpty()) {
                Button bouton = new Button("Passer l'épreuve " + (i + 1));
                bouton.setOnAction(event -> {
                    try { // Charger le fichier FXML de la page d'inscription
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("passerEc.fxml"));
                        Parent root = loader.load();

                        // Obtenir le contrôleur de la page d'inscription
                        passerEcController controller = loader.getController();
                        controller.setOrthophoniste(orthophoniste);
                        controller.setBO(bo);
                        controller.setDossier(dossier);
                        controller.setEpreuveClinique(ec);

                        // Créer une nouvelle scène avec la racine chargée depuis le fichier FXML
                        Scene scene = new Scene(root);

                        // Obtenir la référence de la scène actuelle à partir du bouton cliqué
                        Stage stage = (Stage) anamnese.getScene().getWindow();

                        // Modifier la scène actuelle pour afficher la nouvelle scène
                        stage.setScene(scene);

                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                bouton.setStyle("-fx-font-weight: bold; -fx-background-color : white ");
                bouton.setFont(new Font("Arial", 13));
                bouton.setCursor(Cursor.HAND);
                VBox.setMargin(bouton, new Insets(10, 0, 20, 40));
                EC.getChildren().add(bouton);
            } else {
                Label label = new Label("Epreuve " + (i + 1));
                label.setFont(new Font("Arial", 13));
                VBox.setMargin(label, new Insets(10, 0, 20, 40));
                EC.getChildren().add(label);
            }
        }

    }

    @FXML
    void ajouterAnamnese(ActionEvent event) {
        if (dossier.getPatient() instanceof Enfant) {
            try { // Charger le fichier FXML de la page d'inscription
                FXMLLoader loader = new FXMLLoader(getClass().getResource("newAnamneseEnfant.fxml"));
                Parent root = loader.load();

                // Obtenir le contrôleur de la page d'inscription
                newAnamneseEnfantController controller = loader.getController();
                controller.setOrthophoniste(orthophoniste);
                controller.setBO(bo);
                controller.setDossier(dossier);

                // Créer une nouvelle scène avec la racine chargée depuis le fichier FXML
                Scene scene = new Scene(root);

                // Obtenir la référence de la scène actuelle à partir du bouton cliqué
                Stage stage = (Stage) anamnese.getScene().getWindow();

                // Modifier la scène actuelle pour afficher la nouvelle scène
                stage.setScene(scene);

                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try { // Charger le fichier FXML de la page d'inscription
                FXMLLoader loader = new FXMLLoader(getClass().getResource("newAnamneseAdulte.fxml"));
                Parent root = loader.load();

                // Obtenir le contrôleur de la page d'inscription
                newAnamneseAdulteController controller = loader.getController();
                controller.setOrthophoniste(orthophoniste);
                controller.setBO(bo);
                controller.setDossier(dossier);

                // Créer une nouvelle scène avec la racine chargée depuis le fichier FXML
                Scene scene = new Scene(root);

                // Obtenir la référence de la scène actuelle à partir du bouton cliqué
                Stage stage = (Stage) anamnese.getScene().getWindow();

                // Modifier la scène actuelle pour afficher la nouvelle scène
                stage.setScene(scene);

                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    void ajouterEC(ActionEvent event) {
        try { // Charger le fichier FXML de la page d'inscription
            FXMLLoader loader = new FXMLLoader(getClass().getResource("newEc.fxml"));
            Parent root = loader.load();

            // Obtenir le contrôleur de la page d'inscription
            newEcController controller = loader.getController();
            controller.setOrthophoniste(orthophoniste);
            controller.setBO(bo);
            controller.setDossier(dossier);

            // Créer une nouvelle scène avec la racine chargée depuis le fichier FXML
            Scene scene = new Scene(root);

            // Obtenir la référence de la scène actuelle à partir du bouton cliqué
            Stage stage = (Stage) anamnese.getScene().getWindow();

            // Modifier la scène actuelle pour afficher la nouvelle scène
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<VBoxLayout> vboxLayouts = new ArrayList<>();

    @FXML
    void ajouterObjectif(ActionEvent event) {
        VBoxLayout layout = createVBoxLayout();
        vboxLayouts.add(layout);
        diagnostic.getChildren().add(layout.getVbox());
    }

    @FXML
    void enregistrerBO(ActionEvent event) {
        if (!(projet.getText().isEmpty())) {
            bo.setProjetTherapeutique(projet.getText());
        }
        
        for (VBoxLayout layout : vboxLayouts) {
            TextField nom = layout.getNom();
            RadioButton radioButton1 = layout.getRadioButton1();
            RadioButton radioButton2 = layout.getRadioButton2();
            RadioButton radioButton3 = layout.getRadioButton3();

            if (nom.getText().isEmpty()) {
                Util.afficherErreur("Erreur", "Veuillez donner un enonce pour chaque question");
                return;
            }

            TroubleCategorie categ;
            if (radioButton1.isSelected()) {
                categ = TroubleCategorie.TROUBLES_CONGNITIFS;
            } else if (radioButton2.isSelected()) {
                categ = TroubleCategorie.TROUBLES_DE_LA_DEGLUTITION;
            } else if (radioButton3.isSelected()) {
                categ = TroubleCategorie.TROUBLES_NEURODEVELOPPEMENTAUX;
            } else {
                Util.afficherErreur("Erreur", "Veuillez selectionnez une categorie pour chaque question");
                return;
            }
            Trouble trouble = new Trouble(nom.getText(), categ);
            bo.getDiagnostic().ajouterTrouble(trouble);
        }
        dossier.ajouterBO(bo);
        //TODO 
        //dossier.ajouterRDV();

        if (!(bo instanceof BOpremierRDV)) {
            
            try {
                // Charger le fichier FXML de la page d'inscription
                FXMLLoader loader = new FXMLLoader(getClass().getResource("postBO.fxml"));
                Parent root = loader.load();

                // Obtenir le contrôleur de la page d'inscription
                postBoController controller = loader.getController();
                controller.setOrthophoniste(orthophoniste);
                controller.setDossier(dossier);

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
        }else{
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        }
    }
    

    private void handleRadioButtonAction(VBoxLayout layout, RadioButton selectedRadioButton) {
        RadioButton[] radioButtons = { layout.getRadioButton1(), layout.getRadioButton2(), layout.getRadioButton3() };
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

        Label label1 = new Label("Nom du trouble : ");
        label1.setFont(new Font("Arial", 12.0));
        VBox.setMargin(label1, new Insets(0, 10, 0, 10));

        TextField nom = new TextField();
        nom.setPrefHeight(6.0);
        nom.setPrefWidth(280.0);
        nom.setPromptText("nom");
        nom.setStyle("-fx-background-color: FAFAFA;");
        VBox.setMargin(nom, new Insets(10, 10, 10, 10));

        Label label3 = new Label("Catégorie");
        label3.setFont(new Font("Arial", 12.0));
        VBox.setMargin(label3, new Insets(10, 10, 0, 10));

        GridPane grid = new GridPane();
        grid.setStyle("-fx-background-color: white;");
        grid.setHgap(10);
        grid.setVgap(10);
        VBox.setMargin(grid, new Insets(10, 10, 0, 10));
        // Set size for the GridPane
        grid.setPrefWidth(150);
        grid.setMinWidth(150);

        RadioButton radioButton1 = new RadioButton(TroubleCategorie.TROUBLES_CONGNITIFS.getLabel());
        RadioButton radioButton2 = new RadioButton(TroubleCategorie.TROUBLES_DE_LA_DEGLUTITION.getLabel());
        RadioButton radioButton3 = new RadioButton(TroubleCategorie.TROUBLES_NEURODEVELOPPEMENTAUX.getLabel());

        GridPane.setConstraints(radioButton1, 0, 0);
        GridPane.setConstraints(radioButton2, 1, 0);
        GridPane.setConstraints(radioButton3, 2, 0);

        grid.getChildren().addAll(radioButton1, radioButton2, radioButton3);

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

        RowConstraints row2 = new RowConstraints();
        row2.setMinHeight(10.0);
        row2.setPrefHeight(30.0);
        row2.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        grid.getRowConstraints().addAll(row1, row2);

        VBoxLayout vboxLayout = new VBoxLayout(vbox, nom, radioButton1, radioButton2, radioButton3);
        radioButton1.setOnAction(event -> handleRadioButtonAction(vboxLayout, radioButton1));
        radioButton2.setOnAction(event -> handleRadioButtonAction(vboxLayout, radioButton2));
        radioButton3.setOnAction(event -> handleRadioButtonAction(vboxLayout, radioButton3));

        vbox.getChildren().addAll(label1, nom, label3, grid);

        // Return the VBoxLayout object containing the VBox and its controls
        return vboxLayout;
    }

    private static class VBoxLayout {
        private VBox vbox;
        private TextField nom;
        private RadioButton radioButton1;
        private RadioButton radioButton2;
        private RadioButton radioButton3;

        public VBoxLayout(VBox vbox, TextField nom, RadioButton radioButton1,
                RadioButton radioButton2, RadioButton radioButton3) {
            this.vbox = vbox;
            this.nom = nom;
            this.radioButton1 = radioButton1;
            this.radioButton2 = radioButton2;
            this.radioButton3 = radioButton3;
        }

        public VBox getVbox() {
            return vbox;
        }

        public TextField getNom() {
            return nom;
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
