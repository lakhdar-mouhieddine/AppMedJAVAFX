import java.util.ArrayList;

public class Atelier extends RDV {
    private final String duree = "1h";
    private ArrayList<Integer> numDossier;
    private String thematique;

    public Atelier(String heure, String date, ArrayList<Integer> numDossier, String thematique) {
        super(heure, date);
        this.numDossier = numDossier;
        this.thematique = thematique;
    }

    public String getThematique() {
        return thematique;
    }

    public ArrayList<Integer> getNumDossierList() {
        return numDossier;
    }

    public String getHeure() {
        return super.getHeure();
    }

    public String getDate() {
        return super.getDate();
    }
}
