import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Agenda {
    private ArrayList<Jour> jours;

    public Agenda() {
        this.jours = new ArrayList<Jour>();
    }

    public void addJour(String name, String date) {
        Jour jour = new Jour(name, date);
        jours.add(jour);
    }

    public Jour getJourByDate(String date) {
        for (Jour jour : jours) {
            if (jour != null && jour.getDate().equals(date)) {
                return jour;
            }
        }
        return null;
    }

    public void addRDVToJour(String date, RDV rdv) {
        Jour jour = getJourByDate(date);
        if (jour != null) {
            jour.addRDV(rdv);
        } else {
            System.out.println("No jour found for the date: " + date);
        }
    }

    public List<RDV> getRDVsForJour(String date) {
        Jour jour = getJourByDate(date);
        if (jour != null) {
            return jour.getRDVs();
        } else {
            System.out.println("No jour found for the date: " + date);
            return new ArrayList<>();
        }
    }

    public void printAllJours() {
        for (Jour jour : jours) {
            System.out.println("Jour: " + jour.getNom() + ", Date: " + jour.getDate());
            for (RDV rdv : jour.getRDVs()) {
                System.out.println("  " + rdv.getRDV());
            }
        }
    }

    public void getjour() {
        LocalDate currentDate = LocalDate.now();
        for (Jour jour : jours) {
            if (jour != null && jour.getDate().equals(currentDate.toString())) {
                System.out.println("Today's date is: " + currentDate);
                System.out.println("Information about today: " + jour.getNom() + " " + jour.getDate());
                return;
            }
        }
        System.out.println("No information found for today.");
    }

    // Method to return an array of Jour objects
    public ArrayList<Jour> getJours() {
        return jours;
    }
}
