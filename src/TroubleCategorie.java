public enum TroubleCategorie {
    TROUBLES_DE_LA_DEGLUTITION("troubles de la déglutition"),
    TROUBLES_NEURODEVELOPPEMENTAUX("troubles neurodéveloppementaux"),
    TROUBLES_CONGNITIFS("troubles cognitifs");

    private final String label;

    TroubleCategorie(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
