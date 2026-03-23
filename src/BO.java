import java.io.Serializable;
import java.util.ArrayList;

public class BO implements Serializable {
    private ArrayList<EpreuveClinique> epreuvesCliniques;
    private Diagnostic diagnostic;
    private String projetTherapeutique;

    public BO() {
        epreuvesCliniques = new ArrayList<>();
        diagnostic = new Diagnostic();
    }

    public ArrayList<EpreuveClinique> getEpreuvesCliniques() {
        return epreuvesCliniques;
    }

    public void setEpreuvesCliniques(ArrayList<EpreuveClinique> epreuvesCliniques) {
        this.epreuvesCliniques = epreuvesCliniques;
    }

    public void addEpreuveClinique(EpreuveClinique epreuveClinique) {
        epreuvesCliniques.add(epreuveClinique);
    }

    public Diagnostic getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(Diagnostic diagnostic) {
        this.diagnostic = diagnostic;
    }

    public String getProjetTherapeutique() {
        return projetTherapeutique;
    }

    public void setProjetTherapeutique(String projetTherapeutique) {
        this.projetTherapeutique = projetTherapeutique;
    }
}
