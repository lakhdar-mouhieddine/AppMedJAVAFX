import java.io.Serializable;
import java.util.ArrayList;

public class Diagnostic implements Serializable {
    private ArrayList<Trouble> troubles;

    public Diagnostic() {
        this.troubles = new ArrayList<>();
    }

    public void ajouterTrouble(Trouble trouble) {
        troubles.add(trouble);
    }

    // Autres getters et setters

    public ArrayList<Trouble> getTroubles() {
        return troubles;
    }

    public void setTroubles(ArrayList<Trouble> troubles) {
        this.troubles = troubles;
    }
}
