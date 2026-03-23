import java.io.Serializable;
import java.util.ArrayList;

public class Objectif implements Serializable {
    private String nom;
    private ObjectifCategorie categorie;
    private ArrayList<Integer> notes;
    private double pourcentage;

    public Objectif(String nom, ObjectifCategorie categorie) {
        this.nom = nom;
        this.categorie = categorie;
        this.notes = new ArrayList<>();
        this.pourcentage = 0.0;
    }

    // Getters et setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ObjectifCategorie getCategorie() {
        return categorie;
    }

    public void setCategorie(ObjectifCategorie categorie) {
        this.categorie = categorie;
    }

    public ArrayList<Integer> getNotes() {
        return notes;
    }

    public void addNote(int note) {
        notes.add(note);
    }

    public double getPourcentageAtteint() {
        return pourcentage;
    }

    public void setPourcentage(double pourcentage) {
        this.pourcentage = pourcentage;
    }
}
