import java.io.Serializable;
import java.util.Objects;

public class Question implements Serializable {
    private static final long serialVersionUID = 1L;
    private String enonce;

    // Constructeur
    public Question(String enonce) {
        this.enonce = enonce;
    }

    // Getter pour l'énoncé
    public String getEnonce() {
        return enonce;
    }

    // Setter pour l'énoncé
    public void setEnonce(String enonce) {
        this.enonce = enonce;
    }

    // Redéfinition de equals
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true; // Vérifie si les références sont identiques
        if (o == null || getClass() != o.getClass())
            return false; // Vérifie le type de l'objet
        Question question = (Question) o; // Cast l'objet pour accéder à son enonce
        return Objects.equals(enonce, question.enonce); // Compare les enonces
    }

    // Redéfinition de hashCode
    @Override
    public int hashCode() {
        return Objects.hash(enonce); // Génère un code de hachage basé sur enonce
    }
}
