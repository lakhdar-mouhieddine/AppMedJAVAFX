/*
import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file
        Parent root = FXMLLoader.load(getClass().getResource("Bienvenue.fxml"));

        // Set up the stage
        primaryStage.setTitle("Bienvenue !");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }
}
*/

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file
        // BOpremierRDV bo = new BOpremierRDV();
        // Dossier dossier = new Dossier(1 , consultation.getPatient() , consultation , bo);
        // ortho.ajouterDossier(0,dossier);
        // ortho.getDossier(0);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Bienvenue.fxml"));
        Parent root = loader.load();
        
        // Access the controller
        //AgendaController agendaController = loader.getController();

        // Set up the stage
        primaryStage.setTitle("Agenda");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.setResizable(false);

        


        // Add some days and RDVs to the agenda for testing
        /*agendaController.getAgenda().addJour("Day 1", LocalDate.now().plusDays(1).toString());
        agendaController.getAgenda().addJour("Day 2", LocalDate.now().plusDays(2).toString());

        // Add some RDVs to the days
        RDV rdv1 = new RDV("10:00", "11:00") {};
        RDV rdv2 = new RDV("11:00", "12:00") {};
        RDV rdv3 = new RDV("09:00", "10:00") {};

        agendaController.getAgenda().getJours().get(0).addRDV(rdv1);
        agendaController.getAgenda().getJours().get(0).addRDV(rdv2);
        agendaController.getAgenda().getJours().get(1).addRDV(rdv3);
    }*/
    }

    public static void main(String[] args) {
        launch(args);
    }
}
