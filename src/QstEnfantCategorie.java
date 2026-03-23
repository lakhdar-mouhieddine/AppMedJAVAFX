public enum QstEnfantCategorie {
    STRUCTURE_FAMILIALE("structure familiale"),
    DYNAMIQUE_FAMILIALE("dynamique familiale"),
    ANTECEDENTS_FAMILIAUX("antécédents familiaux"),
    CONDITIONS_NATALES("conditions natales"),
    DEVELOPPEMENT_PSYCHOMOTEUR("développement psychomoteur"),
    CARACTERE_ET_COMPORTEMENT("caractère et comportement");

    private final String label;

    QstEnfantCategorie(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}