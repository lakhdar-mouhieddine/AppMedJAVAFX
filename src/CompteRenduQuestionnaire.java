import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CompteRenduQuestionnaire extends CompteRendu {
    // Utilisation de Map pour stocker les scores associés à chaque question
    private Map<Question, Integer> scores;
    private TestQuestionnaire test;

    // Constructeur
    public CompteRenduQuestionnaire(TestQuestionnaire test) {
        this.test = test;
        this.scores = creerMapScores(test.getQuestions());
    }

    public static Map<Question, Integer> creerMapScores(Set<Question> questions) {
        Map<Question, Integer> scores = new HashMap<>();
        for (Question question : questions) {
            scores.put(question, 0); // Initialiser le score de chaque question à zéro
        }
        return scores;
    }

    // Méthode pour ajouter un score pour une question
    public void ajouterScore(Question question, int score) {
        scores.put(question, score);
    }

    public Map<Question, Integer> getScores() {
        return scores;
    }

    // Méthode pour obtenir le score associé à une question
    public int getScore(Question question) {
        return scores.getOrDefault(question, 0); // Si la question n'a pas de score, retourner 0
    }

    // Méthode pour calculer le score total
    public int calculerScoreTotal() {
        int scoreTotal = 0;
        for (int score : scores.values()) {
            scoreTotal += score;
        }
        return scoreTotal;
    }

    @Override
    public Test getTest() {
        return test;
    }
}
