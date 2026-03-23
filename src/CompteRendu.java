import java.io.Serializable;

public abstract class CompteRendu implements Serializable {
    String conclusion;

    // Getter pour la conclusion
    public String getConclusion() {
        return conclusion;
    }

    // Setter pour la conclusion
    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public abstract Test getTest();
}