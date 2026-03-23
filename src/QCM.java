import java.util.ArrayList;

public class QCM extends Question {
    private ArrayList<String> propositions;
    private ArrayList<String> reponses;

    // Constructeur
    public QCM(String enonce, ArrayList<String> propositions) {
        super(enonce);
        this.propositions = propositions;
    }

    // Constructeur
    public QCM(String enonce, ArrayList<String> propositions, ArrayList<String> reponses) {
        super(enonce);
        this.propositions = propositions;
        this.reponses = reponses;
    }

    // Getters et setters pour les propositions
    public ArrayList<String> getPropositions() {
        return propositions;
    }

    public void setPropositions(ArrayList<String> propositions) {
        this.propositions = propositions;
    }

    // Getters et setters pour les réponses
    public ArrayList<String> getReponses() {
        return reponses;
    }

    public void setReponses(ArrayList<String> reponses) {
        this.reponses = reponses;
    }
}