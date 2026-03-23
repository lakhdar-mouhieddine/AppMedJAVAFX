import java.io.Serializable;
import java.util.Objects;

public class Exercice implements Serializable {
    private String nom;
    private String nomMateriel;

    // Constructeur
    public Exercice(String nom) {
        this.nom = nom;
        this.nomMateriel = null;
    }

    // Constructeur
    public Exercice(String nom, String nomMateriel) {
        this.nom = nom;
        this.nomMateriel = nomMateriel;
    }

    // Getters
    public String getNom() {
        return nom;
    }

    public String getNomMateriel() {
        return nomMateriel;
    }

    // Setters
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNomMateriel(String nomMateriel) {
        this.nomMateriel = nomMateriel;
    }

    // Redéfinition de equals
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Exercice exercice = (Exercice) o;
        return Objects.equals(nom, exercice.nom);
    }

    // Redéfinition de hashCode
    @Override
    public int hashCode() {
        return Objects.hash(nom);
    }
}
