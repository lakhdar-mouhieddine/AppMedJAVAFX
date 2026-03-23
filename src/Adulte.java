
public class Adulte extends Patient {
    private String diplome;
    private String profession;

    // Constructeur

    public Adulte(String nom, String prenom , int age) {
        super(nom, prenom , age);
    }
    
    public Adulte(String nom, String prenom, String dateDeNaissance, String lieuDeNaissance, String adresse,
             String numeroTel,String diplome, String profession) {
        super(nom, prenom, dateDeNaissance, lieuDeNaissance, adresse, numeroTel);
        this.diplome = diplome;
        this.profession = profession;
    }

    // Getters
    public String getDiplome() {
        return diplome;
    }

    public String getProfession() {
        return profession;
    }

    // Setters
    public void setDiplome(String diplome) {
        this.diplome = diplome;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}
