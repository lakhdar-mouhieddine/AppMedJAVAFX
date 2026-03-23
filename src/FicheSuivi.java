import java.io.Serializable;
import java.util.ArrayList;

public class FicheSuivi implements Serializable {
    private ArrayList<Objectif> objectifs;

    public FicheSuivi() {
        this.objectifs = new ArrayList<>();
    }

    public void ajouterObjectif(Objectif objectif) {
        objectifs.add(objectif);
    }

    public ArrayList<Objectif> getObjectifs() {
        return objectifs;
    }

    public void setObjectifs(ArrayList<Objectif> objectifs) {
        this.objectifs = objectifs;
    }

}
