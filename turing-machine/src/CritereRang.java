public class CritereRang extends Critere {
    private int valeurReference;
    private boolean superieur;

    public CritereRang(String description, int valeurReference, boolean superieur) {
        super(description);
        this.valeurReference = valeurReference;
        this.superieur = superieur;
    }

    @Override
    public boolean verifier(CodeSalle solution, CodeSalle proposition) {
        int valeur = solution.getEtage(); // Par exemple, comparer l'étage
        return superieur ? (valeur > valeurReference) : (valeur < valeurReference);
    }
    private int etageMin; // Étage minimum
    private int numMin;   // Numéro de salle minimum

    public CritereRang(int etageMin, int numMin) {
        this.etageMin = etageMin;
        this.numMin = numMin;
    }

    public boolean verifEtage(CodeSalle codeSalle) {
        return codeSalle.getEtage() > etageMin; // Vérifie si l'étage est supérieur à etageMin
    }

    public boolean verifNum(CodeSalle codeSalle) {
        return codeSalle.getSalle() >= numMin; // Vérifie si le numéro de salle est supérieur ou égal à numMin
    }
}
