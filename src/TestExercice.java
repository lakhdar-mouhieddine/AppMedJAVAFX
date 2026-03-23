import java.util.ArrayList;

public class TestExercice extends Test {
    private ArrayList<Exercice> exercices;

    public TestExercice(String nom, String capacite, ArrayList<Exercice> exercices) {
        super(nom, capacite);
        this.exercices = exercices;
    }

    public void afficherTest() {

    }

    public void ajouterExercice(Exercice exercice) {
        exercices.add(exercice);
    }

    public void supprimerExercice(String nom) {
        for (int i = 0; i < exercices.size(); i++) {
            if (exercices.get(i).getNom().equals(nom)) {
                exercices.remove(i);
                return;
            }
        }
    }

    public void modifierExercice(Exercice ancienExercice, Exercice nouvelExercice) {
        if (exercices.contains(ancienExercice)) {
            exercices.remove(ancienExercice);
            exercices.add(nouvelExercice);
        }
    }

    public ArrayList<Exercice> getExercices() {
        return exercices;
    }

    public void setExercices(ArrayList<Exercice> exercices) {
        this.exercices = exercices;
    }

}
