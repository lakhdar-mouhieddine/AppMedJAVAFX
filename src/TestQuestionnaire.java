import java.util.Set;

public class TestQuestionnaire extends Test {
    private Set<Question> questions;

    public TestQuestionnaire(String nom, String capacite, Set<Question> questions) {
        super(nom, capacite);
        this.questions = questions;
    }

    public void afficherTest() {

    }

    public void ajouterQuestion(Question question) {
        questions.add(question);
    }

    public void supprimerQuestion(String enonce) {
        boolean removed = questions.removeIf(question -> question.getEnonce().equals(enonce));
        if (removed) {
            System.out.println("Question \"" + enonce + "\" supprimée.");
        } else {
            System.out.println("Aucune question avec l'énoncé \"" + enonce + "\" trouvée.");
        }
    }

    public void modifierQuestion(Question ancienneQuestion, Question nouvelleQuestion) {
        if (questions.contains(ancienneQuestion)) {
            questions.remove(ancienneQuestion);
            questions.add(nouvelleQuestion);
        }
    }

    // Getter pour les questions
    public Set<Question> getQuestions() {
        return questions;
    }

    // Setter pour les questions
    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

}
