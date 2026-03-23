import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class findDossierController {

    @FXML
    private Button retour;

    @FXML
    private TextField searchField;

    @FXML
    private TableView<DossierDisplay> patientTable;

    @FXML
    private TableColumn<DossierDisplay, Integer> columnNumero;

    @FXML
    private TableColumn<DossierDisplay, String> columnNom;

    @FXML
    private TableColumn<DossierDisplay, String> columnPrenom;

    private Orthophoniste orthophoniste;
    private ObservableList<DossierDisplay> dossierList;

    public void setOrthophoniste(Orthophoniste orthophoniste) {
        this.orthophoniste = orthophoniste;
        loadDossiers();
    }

    @FXML
    public void initialize() {
        // Configure les colonnes de la table
        columnNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        columnNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        columnPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));

        // Ajouter un écouteur à la barre de recherche
        searchField.textProperty().addListener((observable, oldValue, newValue) -> filterPatients(newValue));

        // Ajouter un écouteur d'événement de clic de souris à la table
        patientTable.setOnMouseClicked(this::handleRowClick);
    }

    private void loadDossiers() {
        if (orthophoniste != null && orthophoniste.getDossiers() != null) {
            dossierList = FXCollections.observableArrayList();
            for (Map.Entry<Integer, Dossier> entry : orthophoniste.getDossiers().entrySet()) {
                dossierList.add(new DossierDisplay(entry.getValue()));
            }
            patientTable.setItems(dossierList);
        }
    }

    private void filterPatients(String query) {
        if (query == null || query.isEmpty()) {
            patientTable.setItems(dossierList);
            return;
        }

        ObservableList<DossierDisplay> filteredList = FXCollections.observableArrayList();
        for (DossierDisplay dossier : dossierList) {
            if (String.valueOf(dossier.getNumero()).contains(query) ||
                    dossier.getNom().toLowerCase().contains(query.toLowerCase()) ||
                    dossier.getPrenom().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(dossier);
            }
        }
        patientTable.setItems(filteredList);
    }

    // Méthode pour gérer les clics sur les lignes de la table
    private void handleRowClick(MouseEvent event) {
        if (event.getClickCount() == 2) { // Vérifie s'il s'agit d'un double clic
            DossierDisplay selectedDossierDisplay = patientTable.getSelectionModel().getSelectedItem();
            if (selectedDossierDisplay != null) {
                Dossier dossier = selectedDossierDisplay.getDossier();
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
    }

    public static class DossierDisplay {
        private final Integer numero;
        private final String nom;
        private final String prenom;
        private final Dossier dossier; // Ajout de l'attribut dossier

        public DossierDisplay(Dossier dossier) {
            this.numero = dossier.getNumero()-1;
            this.nom = dossier.getPatient().getNom();
            this.prenom = dossier.getPatient().getPrenom();
            this.dossier = dossier; // Initialisation de l'attribut dossier
        }

        public Integer getNumero() {
            return numero;
        }

        public String getNom() {
            return nom;
        }

        public String getPrenom() {
            return prenom;
        }

        public Dossier getDossier() {
            return dossier;
        }
    }

    public void retourPage(ActionEvent event) {
        try { // Charger le fichier FXML de la page d'inscription
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Agenda.fxml"));
            Parent root = loader.load();

            // Obtenir le contrôleur de la page d'inscription
            AgendaController controller = loader.getController();
            controller.setOrthophoniste(orthophoniste);

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
