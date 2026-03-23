import java.util.HashMap;

public class AnamneseEnfant extends Anamnese {
    private HashMap<QuestionAnamneseEnfant, String> questions;
    private QstAdulteCategorie qstEnfantCateg;

    public AnamneseEnfant(HashMap<QuestionAnamneseEnfant, String> questions) {
        this.questions = questions;
    }
    public HashMap<QuestionAnamneseEnfant, String> getQuestions() {
        return questions;
    }

    public void setQuestions(HashMap<QuestionAnamneseEnfant, String> questions) {
        this.questions = questions;
    }

    public AnamneseEnfant(QstAdulteCategorie qstEnfantCateg) {
        super();
        this.qstEnfantCateg = qstEnfantCateg;
    }
}
