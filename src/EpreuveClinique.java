import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;

public class EpreuveClinique implements Serializable {
    private ArrayList<String> observationClinique;
    private ArrayList<CompteRendu> compteRendu;

    // Constructeur
    public EpreuveClinique(Set<Test> tests) {
        this.compteRendu = new ArrayList<>();
        for (Test test : tests) {
            if (test instanceof TestExercice) {
                compteRendu.add(new CompteRenduExercice((TestExercice) test));
            } else if (test instanceof TestQuestionnaire) {
                compteRendu.add(new CompteRenduQuestionnaire((TestQuestionnaire) test));
            }
        }
        this.observationClinique = new ArrayList<>();
    }

    // Setters
    public void setObservationClinique(ArrayList<String> observationClinique) {
        this.observationClinique = observationClinique;
    }

    public void addObservations(ArrayList<String> nouvellesObservations) {
        this.observationClinique.addAll(nouvellesObservations);
    }

    public void setCompteRendu(ArrayList<CompteRendu> compteRendu) {
        this.compteRendu = compteRendu;
    }

    // Getters
    public ArrayList<String> getObservationClinique() {
        return observationClinique;
    }

    public ArrayList<CompteRendu> getCompterendu() {
        return compteRendu;
    }
}
