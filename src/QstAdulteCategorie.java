public enum QstAdulteCategorie {
    HISTOIRE_DE_MALADIE("histoire de maladie"),
    SUIVI_MEDICAL("suivi médical");

    private final String label;

    QstAdulteCategorie(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}