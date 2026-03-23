import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.stage.Stage;

public class AddPatientController implements Initializable{

    @FXML
    private GridPane Gadulte;

    @FXML
    private GridPane Genfant;

    @FXML
    private TextField adress;

    @FXML
    private TextField classetude;

    @FXML
    private DatePicker date;

    @FXML
    private TextField diplome;

    @FXML
    private Button enreg;

    @FXML
    private TextField lieu;

    @FXML
    private TextField numparent;

    @FXML
    private TextField numtel;

    @FXML
    private TextField profession;

    @FXML
    private VBox container;

    private String heure;

    public void setheure(String heure) {
        this.heure = heure;
    }

    public void initialize (URL arg0 , ResourceBundle arg1) {
        container.getChildren().remove(Gadulte);
        container.getChildren().remove(Genfant);
    }

    @FXML
    void addsection(ActionEvent event) {
        LocalDate selectedDate = date.getValue();
        if (selectedDate != null) {
            LocalDate currentDate = LocalDate.now();
            int age = Period.between(selectedDate, currentDate).getYears();

            // Remove existing child nodes
            container.getChildren().remove(Gadulte);
            container.getChildren().remove(Genfant);

            if (age < 18) {
                // Add Genfant to the container above the enregistrer button
                int enregIndex = container.getChildren().indexOf(enreg);
                container.getChildren().add(enregIndex, Genfant);
                Genfant.setVisible(true);
                Gadulte.setVisible(false);
            } else {
                // Add Gadulte to the container above the enregistrer button
                int enregIndex = container.getChildren().indexOf(enreg);
                container.getChildren().add(enregIndex, Gadulte);
                Gadulte.setVisible(true);
                Genfant.setVisible(false);
            }
        }
    }

    @FXML
    void enregistrer(ActionEvent event) {


        LocalDate selectedDate = date.getValue();
        LocalDate currentDate = LocalDate.now();
        int age = Period.between(selectedDate, currentDate).getYears();
        


        if (age < 18) {
            
            Consultation.getPatient().setDateDeNaissance(date.getValue().toString());
            Consultation.getPatient().setLieuDeNaissance(lieu.getText());
            Consultation.getPatient().setAdresse(adress.getText());
            Consultation.getPatient().setNumeroTel(numtel.getText());
            ((Enfant) Consultation.getPatient()).setClasseEtude(classetude.getText());
            ((Enfant) Consultation.getPatient()).setNumeroTelParent(numparent.getText());

            

        } else {
            Consultation.getPatient().setDateDeNaissance(date.getValue().toString());
            Consultation.getPatient().setLieuDeNaissance(lieu.getText());
            Consultation.getPatient().setAdresse(adress.getText());
            Consultation.getPatient().setNumeroTel(numtel.getText());
            ((Adulte) Consultation.getPatient()).setDiplome(diplome.getText());
            ((Adulte) Consultation.getPatient()).setProfession(profession.getText());
            
        
        }

        Dossier dossier = new Dossier();
        dossier.setPatient(Consultation.getPatient());
        int num = Dossier.numero;
        App.getCurrentOrthophoniste().ajouterDossier(num, dossier);
        Consultation consultation = new Consultation(heure, date.getValue().toString(), Consultation.getPatient().getNom(), Consultation.getPatient().getPrenom(), age);
        App.getCurrentOrthophoniste().getDossier(num).ajouterRDV(consultation);
        


        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    

}
