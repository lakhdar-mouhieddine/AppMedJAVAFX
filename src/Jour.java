import java.util.*;

public class Jour {
    private String nom;
    private String date;
    private List<RDV> rdvs;

    public Jour(String nom, String date) {
        this.nom = nom;
        this.date = date;
        this.rdvs = new ArrayList<>();
    }

    public void addRDV(RDV rdv) {
        rdvs.add(rdv);
    }

    public void deleteRDV(RDV rdv) {
        rdvs.remove(rdv);
    }

    public List<RDV> getRDVs() {
        return rdvs;
    }

    public boolean hasRDVs() {
        return !rdvs.isEmpty();
    }

    public String getNom() {
        return nom;
    }

    public String getDate() {
        return date;
    }
}
