public class QuestionAnamneseEnfant extends QuestionAnamnese {
    private QstEnfantCategorie categorie;

    public QuestionAnamneseEnfant(String enonce, QstEnfantCategorie categorie) {
        super(enonce);
        this.categorie = categorie;
    }

    public QstEnfantCategorie getCategorie() {
        return categorie;
    }
}