import java.io.Serializable;

public class Trouble implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nom;
    private TroubleCategorie categorie;

    public Trouble(String nom, TroubleCategorie categorie) {
        this.nom = nom;
        this.categorie = categorie;
    }

    // Getters et setters

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public TroubleCategorie getCategorie() {
        return categorie;
    }

    public void setCategorie(TroubleCategorie categorie) {
        this.categorie = categorie;
    }
}
