import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CompteRenduExercice extends CompteRendu {
    // Utilisation de Map pour stocker les scores associés à chaque exercice
    private Map<Exercice, Integer> scores;
    private TestExercice test;

    // Constructeur
    public CompteRenduExercice(TestExercice test) {
        this.test = test;
        this.scores = creerMapScores(test.getExercices());

    }

    public static Map<Exercice, Integer> creerMapScores(ArrayList<Exercice> exercices) {
        Map<Exercice, Integer> scores = new HashMap<>();
        for (Exercice exercice : exercices) {
            scores.put(exercice, 0);
        }
        return scores;
    }

    public void setScores(Map<Exercice, ArrayList<Integer>> scoreMap) {
        for (Map.Entry<Exercice, ArrayList<Integer>> entry : scoreMap.entrySet()) {
            Exercice exercice = entry.getKey();
            ArrayList<Integer> notes = entry.getValue();

            if (notes != null && !notes.isEmpty()) {
                int sum = 0;
                for (int note : notes) {
                    sum += note;
                }
                int moyenne = sum / notes.size();
                scores.put(exercice, moyenne);
            } else {
                // Si la liste des notes est vide ou null, on peut définir une moyenne par
                // défaut, par exemple 0
                scores.put(exercice, 0);
            }
        }
    }

    public Map<Exercice, Integer> getScores() {
        return scores;
    }

    @Override
    public Test getTest() {
        return test;
    }
}
