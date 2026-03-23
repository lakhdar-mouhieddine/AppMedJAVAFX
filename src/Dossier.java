import java.io.Serializable;
import java.util.ArrayList;

public class Dossier implements Serializable {
    public static int numero = 1;
    private Patient patient;
    private ArrayList<RDV> rdvs;
    private ArrayList<CompteRendu> compteRendus;
    private ArrayList<BO> bos;
    private ArrayList<FicheSuivi> fiches;

    public Dossier() {
        this.rdvs = new ArrayList<>();
        this.bos = new ArrayList<>();
        this.fiches = new ArrayList<>();
        this.compteRendus = new ArrayList<>();
    }

    // Getters
    public int getNumero() {
        return numero;
    }

    public Patient getPatient() {
        return patient;
    }

    public ArrayList<RDV> getRdvs() {
        return rdvs;
    }

    public ArrayList<BO> getBos() {
        return bos;
    }

    public ArrayList<FicheSuivi> getFiches() {
        return fiches;
    }

    public ArrayList<CompteRendu> getCompteRendus() {
        return compteRendus;
    }

    public CompteRendu getCompteRendu(String nom) {
        for (CompteRendu compteRendu : compteRendus) {
            if (compteRendu instanceof CompteRenduQuestionnaire) {
                CompteRenduQuestionnaire crq = (CompteRenduQuestionnaire) compteRendu;
                if (crq.getTest().getNom().equals(nom)) {
                    return compteRendu;
                }
            } else if (compteRendu instanceof CompteRenduExercice) {
                CompteRenduExercice cre = (CompteRenduExercice) compteRendu;
                if (cre.getTest().getNom().equals(nom)) {
                    return compteRendu;
                }
            }
        }
        return null;
    }

    // Setters
    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setRdvs(ArrayList<RDV> rdvs) {
        this.rdvs = rdvs;
    }

    public void setBos(ArrayList<BO> bos) {
        this.bos = bos;
    }

    public void setFiches(ArrayList<FicheSuivi> fiches) {
        this.fiches = fiches;
    }

    // Méthodes d'ajout
    public void ajouterRDV(RDV rdv) {
        rdvs.add(rdv);
    }

    public void ajouterBO(BO bo) {
        bos.add(bo);
    }

    public void ajouterFicheSuivi(FicheSuivi fiche) {
        fiches.add(fiche);
    }

    public void ajouterCompteRendu(CompteRendu compteRendu) {
        compteRendus.add(compteRendu);
    }
}
