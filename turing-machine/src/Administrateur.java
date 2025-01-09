public class Administrateur {

    private int interactions;
    private CodeSalle proposition;

    public Administrateur() {
        this.interactions = 0;
    }

    public void faireProposition(CodeSalle proposition) {
        this.proposition = proposition;
        this.interactions++;
    }

    public CodeSalle getProposition() {
        return proposition;
    }

    public int getInteractions() {
        return interactions;
    }
}
