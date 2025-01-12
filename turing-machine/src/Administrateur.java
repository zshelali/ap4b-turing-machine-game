public class Administrateur {

    private int interactions;
    private CodeSalle proposition;
    private int identifiant; 

    public Administrateur(int identifiant) {
        this.identifiant = identifiant;
        this.interactions = 2; 
    }

    public int getIdentifiant() {
        return identifiant;
    }

    public int getInteractions() {
        return interactions;
    }

    public void resetInteractions() {
        this.interactions = 2;
    }

    public void decremInteractions() {
        if (interactions > 0) {
            this.interactions--;
        }
    }

    public boolean peutInteragir() {
        return interactions > 0;
    }

    public CodeSalle getProposition() {
        return proposition;
    }

    public void setProposition(CodeSalle proposition) {
        this.proposition = proposition;
    }
}