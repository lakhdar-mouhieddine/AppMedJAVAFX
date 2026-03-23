public class ReponseLibre extends Question {
    private String reponse;

    // Constructeur
    public ReponseLibre(String enonce, String reponse) {
        super(enonce);
        this.reponse = reponse;
    }

    // Getter et setter pour la réponse
    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }
}