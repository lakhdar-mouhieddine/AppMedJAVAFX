public class Suivi extends RDV {
    private final String duree = "1h";
    public static int numDossier;
    private String type;

    public Suivi(String heure, String date, int numDossier, String type) {
        super(heure, date);
        this.numDossier = numDossier;
        this.type = type;
    }

    public int getNumDossier() {
        return numDossier;
    }

    public static Patient getPatient() {
        return Orthophoniste.getPatient(numDossier);
    }

    public String getHeure() {
        return super.getHeure();
    }

    public String getDate() {
        return super.getDate();
    }
}
