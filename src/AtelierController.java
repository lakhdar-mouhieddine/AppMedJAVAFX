import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class AtelierController implements Initializable {

    @FXML
    private Button bo;

    @FXML
    private GridPane p1;

    @FXML
    private VBox container;

    private Atelier atelier;

    private String date;
    private String heure;

    private List<RDV> rdvs = new ArrayList<>();
    ArrayList<Integer> patients1 = new ArrayList<>();
    List<String> patients2;

    public void setatelierDetails(String startTime, String date) {
        container.getChildren().remove(p1);
        this.heure = startTime;
        this.date = date;
        this.atelier = (Atelier) getAtelier();
        if (atelier != null) {
            // Load the patients from the atelier
            patients1 = atelier.getNumDossierList();
            patients2 = new ArrayList<>();

            for (Integer numDossier : patients1) {
                Patient patient = App.getCurrentOrthophoniste().getPatient(numDossier);
                if (patient != null) {
                    patients2.add(numDossier.toString());
                    System.out.println("inside of patients2"+patient.getNom());
                }
            }

            // Add GridPane for each patient
            addPatientsToGrid(patients2);
        }
    }

    public RDV getAtelier() {
        rdvs = App.getCurrentOrthophoniste().getAgenda().getRDVsForJour(date);
        for (RDV rdv : rdvs) {
            if (rdv instanceof Atelier) {
                System.out.println("Atelier found");
                return rdv;
            }
        }
        System.out.println("Atelier not found");
        return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    private void addPatientsToGrid(List<String> patients) {
        // Iterate over the patients list and create a GridPane for each
        for (int i = 0; i < patients.size(); i++) {
            String dossierNumber = patients.get(i);
            Patient patient = App.getCurrentOrthophoniste().getDossier(Integer.parseInt(dossierNumber)).getPatient();
            String patientName = patient.getNom();
            String patientPrenom = patient.getPrenom();

            // Create and configure a GridPane
            GridPane newGrid = createPatientGridPane(patientName, patientPrenom, i + 1);

            // Add the new GridPane to the container
            container.getChildren().add(newGrid);
        }
    }

    private GridPane createPatientGridPane(String patientName, String patientPrenom, int index) {
        GridPane newGrid = new GridPane();
        newGrid.setHgap(10);
        newGrid.setVgap(10);
        newGrid.setPadding(new Insets(10.0));
        newGrid.setStyle("-fx-background-color: #ffffff;");

        // Define column constraints
        ColumnConstraints cc = new ColumnConstraints();
        cc.setHgrow(Priority.ALWAYS);
        newGrid.getColumnConstraints().add(cc);

        // Define row constraints
        RowConstraints rc = new RowConstraints();
        rc.setVgrow(Priority.ALWAYS);
        newGrid.getRowConstraints().add(rc);

        // Create and configure Label for patient name
        Label nameValue = new Label(patientName + " " + patientPrenom);
        nameValue.setId("nomprenom" + index);
        GridPane.setConstraints(nameValue, 0, 0);

        // Create and configure Button for action
        Button addButton = new Button("Créer Bilan Orthophonique");
        addButton.setId("bo" + index);
        addButton.setStyle("-fx-background-color: #00CED0; -fx-background-radius: 5;");
        addButton.setTextFill(javafx.scene.paint.Color.WHITE);
        addButton.setOnAction(event -> {
            try {
                addbo(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        GridPane.setConstraints(addButton, 1, 0);

        // Add components to GridPane
        newGrid.getChildren().addAll(nameValue, addButton);

        return newGrid;
    }

    @FXML
    void addbo(ActionEvent event) throws IOException {
        Button source = (Button) event.getSource();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("creerBO.fxml"));
        Parent root = loader.load();
        creerBoController controller = loader.getController();
        controller.setOrthophoniste(App.getCurrentOrthophoniste());

        BO bO = new BO();
        controller.setBO(bO);

        // Get the patient name and prenom from the button ID
        
        String buttonId = source.getId();
        int index = Integer.parseInt(buttonId.replace("bo", ""));
        String[] nameParts = ((Label) container.lookup("#nomprenom" + index)).getText().split(" ");
        String patientName = nameParts[0];
        String patientPrenom = nameParts[1];

        Dossier dossier = findDossierByPatientName(patientName, patientPrenom);
        controller.setDossier(dossier);

        // Create a new scene with the root loaded from the FXML file
        Scene scene = new Scene(root);
        // Get the reference of the current stage from the clicked button
        Stage stage = (Stage) source.getScene().getWindow();
        // Set the current scene to the new scene
        stage.setScene(scene);
    }

    private Dossier findDossierByPatientName(String nom, String prenom) {
        Orthophoniste orthophoniste = App.getCurrentOrthophoniste();
        if (orthophoniste != null) {
            TreeMap<Integer, Dossier> dossiers = orthophoniste.getDossiers();
            for (Dossier dossier : dossiers.values()) {
                Patient patient = dossier.getPatient();
                if (patient.getNom().equals(nom) && patient.getPrenom().equals(prenom)) {
                    return dossier;
                }
            }
        }
        return null;
    }

    public void setAtelier(Atelier atelier) {
        this.atelier = atelier;
    }
}
