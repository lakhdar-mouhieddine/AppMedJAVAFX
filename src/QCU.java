import java.util.ArrayList;

public class QCU extends Question {
    private ArrayList<String> propositions;
    private String reponse;

    // Constructeur
    public QCU(String enonce, ArrayList<String> propositions) {
        super(enonce);
        this.propositions = propositions;
    }

    // Constructeur
    public QCU(String enonce, ArrayList<String> propositions, String reponse) {
        super(enonce);
        this.propositions = propositions;
        this.reponse = reponse;
    }

    // Getters et setters pour les propositions
    public ArrayList<String> getPropositions() {
        return propositions;
    }

    public void setPropositions(ArrayList<String> propositions) {
        this.propositions = propositions;
    }

    // Getter et setter pour la réponse
    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }
}
