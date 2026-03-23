public class QuestionAnamneseAdulte extends QuestionAnamnese {
    private QstAdulteCategorie categorie;

    public QuestionAnamneseAdulte(String enonce, QstAdulteCategorie categorie) {
        super(enonce);
        this.categorie = categorie;
    }

    public QstAdulteCategorie getCategorie() {
        return categorie;
    }
}