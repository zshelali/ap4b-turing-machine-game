public abstract class Critere {

    protected String description;

    public Critere(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    // Méthode abstraite pour vérifier un critère
    public abstract boolean verifier(CodeSalle proposition, CodeSalle solution);
}
