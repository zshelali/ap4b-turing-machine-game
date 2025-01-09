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
}
