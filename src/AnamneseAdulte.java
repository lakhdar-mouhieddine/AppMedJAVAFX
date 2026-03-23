import java.util.HashMap;

public class AnamneseAdulte extends Anamnese {
    private HashMap<QuestionAnamneseAdulte, String> questions;
    private QstEnfatCategorie qstAdulteCateg;

    public AnamneseAdulte(HashMap<QuestionAnamneseAdulte, String> questions) {
        this.questions = questions;
    }

    public HashMap<QuestionAnamneseAdulte, String> getQuestions() {
        return questions;
    }

    public void setQuestions(HashMap<QuestionAnamneseAdulte, String> questions) {
        this.questions = questions;
    }
    
    public AnamneseAdulte(QstEnfatCategorie qstAdulteCateg) {
        super();
        this.qstAdulteCateg = qstAdulteCateg;
    }
}
