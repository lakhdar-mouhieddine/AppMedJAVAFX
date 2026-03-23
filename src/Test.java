import java.io.Serializable;
import java.util.Objects;

public abstract class Test implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nom;
    private String capacite;

    // Constructeur
    public Test(String nom, String capacite) {
        this.nom = nom;
        this.capacite = capacite;
    }

    // Getter pour le nom
    public String getNom() {
        return nom;
    }

    // Setter pour le nom
    public void setNom(String nom) {
        this.nom = nom;
    }

    // Getter pour la capacité
    public String getCapacite() {
        return capacite;
    }

    // Setter pour la capacité
    public void setCapacite(String capacite) {
        this.capacite = capacite;
    }

    // Redéfinition de equals
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true; // Vérifie si les références sont identiques
        if (o == null || getClass() != o.getClass())
            return false; // Vérifie le type de l'objet
        Test test = (Test) o; // Cast l'objet pour accéder à ses champs
        return Objects.equals(nom, test.nom); // Compare les champs nom

    }

    // Redéfinition de hashCode
    @Override
    public int hashCode() {
        return Objects.hash(nom); // Génère un code de hachage basé sur nom
    }
}
