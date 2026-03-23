public class Enfant extends Patient {
    private String classeEtude;
    private String numeroTelParent;

    // Constructeur

    public Enfant(String nom, String prenom , int age) {
        super(nom, prenom, age);
    }


    public Enfant(String nom, String prenom, String dateDeNaissance, String lieuDeNaissance, String adresse,
            String numeroTel, String classeEtude, String numeroTelParent) {
        super(nom, prenom, dateDeNaissance, lieuDeNaissance, adresse, numeroTel);
        this.classeEtude = classeEtude;
        this.numeroTelParent = numeroTelParent;
    }

    // Getters
    public String getClasseEtude() {
        return classeEtude;
    }

    public String getNumeroTelParent() {
        return numeroTelParent;
    }

    // Setters
    public void setClasseEtude(String classeEtude) {
        this.classeEtude = classeEtude;
    }

    public void setNumeroTelParent(String numeroTelParent) {
        this.numeroTelParent = numeroTelParent;
    }
}
