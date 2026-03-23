import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class AgendaController implements Initializable{

    @FXML
    private Button RDV;

    @FXML
    private ScrollPane c0, c1, c2, c3, c4, c5, c6;

    @FXML
    private VBox c10, c11, c12, c13, c14, c15, c16;

    @FXML
    private Label d10, d11, d12, d13, d14, d15, d16;

    @FXML
    private Text date;

    @FXML
    private BorderPane date_container;

    @FXML
    private Button next, previous;

    @FXML
    private Button troubles;

    @FXML
    private Button dossier1;

    @FXML
    private Button exercice;

    @FXML
    private Button modifTest;

    @FXML
    private Button modifierEx;

    @FXML
    private Button modifierQuest;

    @FXML
    private Button question;

    @FXML
    private Button test;

    private LocalDate startOfWeek;
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    

    private Agenda agenda; // Assuming this is the main agenda object

    public AgendaController() {
        startOfWeek = LocalDate.now().with(DayOfWeek.MONDAY).minusDays(1);
        agenda = new Agenda(); // Initialize the agenda, this might come from somewhere else
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateWeek();
        setupAutoRefresh();
    }

    @FXML
    void addRDV(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddRDV.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void nextWeek(ActionEvent event) {
        startOfWeek = startOfWeek.plusWeeks(1);
        date.setText(startOfWeek.format(dateFormatter) + " - " + startOfWeek.plusDays(6).format(dateFormatter));
        updateWeek();
    }

    @FXML
    void previousWeek(ActionEvent event) {
        startOfWeek = startOfWeek.minusWeeks(1);
        date.setText(startOfWeek.format(dateFormatter) + " - " + startOfWeek.plusDays(6).format(dateFormatter));
        updateWeek();        
    }

    @FXML
    void afficherDossier(ActionEvent event) {
        try {
            // Charger le fichier FXML de la page de connexion
            FXMLLoader loader = new FXMLLoader(getClass().getResource("findDossier.fxml"));
            Parent root = loader.load();
            findDossierController controller = loader.getController();
            controller.setOrthophoniste(orthophoniste);
            // Créer une nouvelle scène avec la racine chargée depuis le fichier FXML
            Scene scene = new Scene(root);

            // Obtenir la référence de la scène actuelle à partir du bouton cliqué
            Stage stage = (Stage) exercice.getScene().getWindow();

            // Modifier la scène actuelle pour afficher la nouvelle scène
            stage.setScene(scene);
            // stage.setMaximized(true);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private Orthophoniste orthophoniste;

    @SuppressWarnings("static-access")
    public void setOrthophoniste(Orthophoniste orthophoniste) {
        this.orthophoniste = orthophoniste;
    }

    @FXML
    void creerExercice(ActionEvent event) {
        try {
            // Charger le fichier FXML de la page de connexion
            FXMLLoader loader = new FXMLLoader(getClass().getResource("newExercice.fxml"));
            Parent root = loader.load();
            NewExerciceController controller = loader.getController();
            controller.setOrthophoniste(orthophoniste);

            // Créer une nouvelle scène avec la racine chargée depuis le fichier FXML
            Scene scene = new Scene(root);

            // Obtenir la référence de la scène actuelle à partir du bouton cliqué
            Stage stage = (Stage) exercice.getScene().getWindow();

            // Modifier la scène actuelle pour afficher la nouvelle scène
            stage.setScene(scene);
            // stage.setMaximized(true);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void creerQuestion(ActionEvent event) {
        try {
            // Charger le fichier FXML de la page de connexion
            FXMLLoader loader = new FXMLLoader(getClass().getResource("newQuestion.fxml"));
            Parent root = loader.load();
            newQuestionController controller = loader.getController();
            controller.setOrthophoniste(orthophoniste);

            // Créer une nouvelle scène avec la racine chargée depuis le fichier FXML
            Scene scene = new Scene(root);

            // Obtenir la référence de la scène actuelle à partir du bouton cliqué
            Stage stage = (Stage) question.getScene().getWindow();

            // Modifier la scène actuelle pour afficher la nouvelle scène
            stage.setScene(scene);
            // stage.setMaximized(true);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void creerTest(ActionEvent event) {
        try {
            // Charger le fichier FXML de la page de connexion
            FXMLLoader loader = new FXMLLoader(getClass().getResource("newTest.fxml"));
            Parent root = loader.load();
            NewTestController controller = loader.getController();
            controller.setOrthophoniste(orthophoniste);

            // Créer une nouvelle scène avec la racine chargée depuis le fichier FXML
            Scene scene = new Scene(root);
            // Obtenir la référence de la scène actuelle à partir du bouton cliqué
            Stage stage = (Stage) question.getScene().getWindow();

            // Modifier la scène actuelle pour afficher la nouvelle scène
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void modifierExercice(ActionEvent event) {
        try {
            // Charger le fichier FXML de la page de connexion
            FXMLLoader loader = new FXMLLoader(getClass().getResource("modifExercice.fxml"));
            Parent root = loader.load();
            modifExerciceController controller = loader.getController();
            controller.setOrthophoniste(orthophoniste);

            // Créer une nouvelle scène avec la racine chargée depuis le fichier FXML
            Scene scene = new Scene(root);

            // Obtenir la référence de la scène actuelle à partir du bouton cliqué
            Stage stage = (Stage) exercice.getScene().getWindow();

            // Modifier la scène actuelle pour afficher la nouvelle scène
            stage.setScene(scene);
            // stage.setMaximized(true);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void modifierQuestion(ActionEvent event) {
        try {
            // Charger le fichier FXML de la page de connexion
            FXMLLoader loader = new FXMLLoader(getClass().getResource("modifQuestion.fxml"));
            Parent root = loader.load();
            modifQuestionController controller = loader.getController();
            controller.setOrthophoniste(orthophoniste);

            // Créer une nouvelle scène avec la racine chargée depuis le fichier FXML
            Scene scene = new Scene(root);

            // Obtenir la référence de la scène actuelle à partir du bouton cliqué
            Stage stage = (Stage) exercice.getScene().getWindow();

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
        try {
            // Charger le fichier FXML de la page de connexion
            FXMLLoader loader = new FXMLLoader(getClass().getResource("modifTest.fxml"));
            Parent root = loader.load();
            modifTestController controller = loader.getController();
            controller.setOrthophoniste(orthophoniste);

            // Créer une nouvelle scène avec la racine chargée depuis le fichier FXML
            Scene scene = new Scene(root);

            // Obtenir la référence de la scène actuelle à partir du bouton cliqué
            Stage stage = (Stage) exercice.getScene().getWindow();

            // Modifier la scène actuelle pour afficher la nouvelle scène
            stage.setScene(scene);
            // stage.setMaximized(true);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void afficherTrouble(ActionEvent event) {
        try {
            // Charger le fichier FXML de la page de connexion
            FXMLLoader loader = new FXMLLoader(getClass().getResource("trouble.fxml"));
            Parent root = loader.load();
            troubleController controller = loader.getController();
            controller.setOrthophoniste(orthophoniste);

            // Créer une nouvelle scène avec la racine chargée depuis le fichier FXML
            Scene scene = new Scene(root);

            // Obtenir la référence de la scène actuelle à partir du bouton cliqué
            Stage stage = (Stage) exercice.getScene().getWindow();

            // Modifier la scène actuelle pour afficher la nouvelle scène
            stage.setScene(scene);
            // stage.setMaximized(true);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
    @FXML
    void creerDossier(ActionEvent event) {
        try {
            // Charger le fichier FXML de la page de connexion
            FXMLLoader loader = new FXMLLoader(getClass().getResource("essai.fxml"));
            Parent root = loader.load();
            essaiController controller = loader.getController();
            controller.setOrthophoniste(orthophoniste);
            controller.setDossier(new Dossier());
            // Créer une nouvelle scène avec la racine chargée depuis le fichier FXML
            Scene scene = new Scene(root);

            // Obtenir la référence de la scène actuelle à partir du bouton cliqué
            Stage stage = (Stage) exercice.getScene().getWindow();

            // Modifier la scène actuelle pour afficher la nouvelle scène
            stage.setScene(scene);
            // stage.setMaximized(true);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void afficherRDV(ActionEvent event) {
        try {
            // Charger le fichier FXML de la page de connexion
            FXMLLoader loader = new FXMLLoader(getClass().getResource("essai2.fxml"));
            Parent root = loader.load();
            essai2Controller controller = loader.getController();
            controller.setOrthophoniste(orthophoniste);
            System.out.println("provis " + orthophoniste.getDossier(0).getBos().size());
            controller.setDossier(orthophoniste.getDossier(0));
            // Créer une nouvelle scène avec la racine chargée depuis le fichier FXML
            Scene scene = new Scene(root);

            // Obtenir la référence de la scène actuelle à partir du bouton cliqué
            Stage stage = (Stage) exercice.getScene().getWindow();

            // Modifier la scène actuelle pour afficher la nouvelle scène
            stage.setScene(scene);
            // stage.setMaximized(true);
            stage.show();
        } catch (IOException e) {
            Util.afficherErreur("Erreur!", "pas de RDV pour ce patient");
        }

    }
    */


    private void updateWeek() {
        ArrayList<Label> dates = new ArrayList<>();
        ArrayList<VBox> containers = new ArrayList<>();
        dates.add(d10); dates.add(d11); dates.add(d12); dates.add(d13); dates.add(d14); dates.add(d15); dates.add(d16);
        containers.add(c10); containers.add(c11); containers.add(c12); containers.add(c13); containers.add(c14); containers.add(c15); containers.add(c16);
        
        date.setText(startOfWeek.format(dateFormatter) + " - " + startOfWeek.plusDays(6).format(dateFormatter));
        LocalDate currentDate = startOfWeek;
    
        for (int i = 0; i < 7; i++) {
            String month = currentDate.getMonth().getDisplayName(TextStyle.FULL, Locale.FRANCE).toLowerCase();
            month = month.substring(0, 1).toUpperCase() + month.substring(1);
            String dayOfMonth = currentDate.format(DateTimeFormatter.ofPattern("dd"));
            dates.get(i).setText(dayOfMonth + ". " + month);
            containers.get(i).getChildren().clear();
    
            Jour jour = getJourByDate(currentDate.toString());
            if (jour != null && jour.hasRDVs()) {
                containers.get(i).setVisible(true);
                for (RDV rdv : jour.getRDVs()) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("miniRDV.fxml"));
                        Parent miniRDV = loader.load();
                        miniRDVController controller = loader.getController();
                        if (rdv instanceof Consultation){
                            controller.setRDVDetails(rdv.getHeure(), jour.getDate().toString(),0);
                        }else if (rdv instanceof Suivi){
                            controller.setRDVDetails(rdv.getHeure(), jour.getDate().toString(),1);
                        }else{
                            controller.setRDVDetails(rdv.getHeure(), jour.getDate().toString(),2);
                        }
                        containers.get(i).getChildren().add(miniRDV);
                        VBox.setMargin(miniRDV, new Insets(6, 0, 6, 4));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                containers.get(i).setVisible(false);
            }
            currentDate = currentDate.plusDays(1);
        }
    }

    private Jour getJourByDate(String date) {
        Orthophoniste currentOrthophoniste = App.getCurrentOrthophoniste();
        if (currentOrthophoniste == null) {
            return null;
        }
        
        for (Jour jour : currentOrthophoniste.getAgenda().getJours()) {
            if (jour.getDate().equals(date)) {
                return jour;
            }
        }
        return null;
    }
    
    private void setupAutoRefresh() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), event -> updateWeek()));
        timeline.setCycleCount(Timeline.INDEFINITE); // Run indefinitely
        timeline.play();
    }

    public Agenda getAgenda() {
        return agenda;
    }
}

