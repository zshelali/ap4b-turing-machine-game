public class CritereRang extends Critere {
    private int valeurReference; // La valeur de référence pour la comparaison
    private boolean superieur;   // Si vrai, on vérifie si le numéro de salle est supérieur à la référence

    public CritereRang(String description, int valeurReference, boolean superieur) {
        super(description);
        this.valeurReference = valeurReference;
        this.superieur = superieur;
    }

    @Override
    public boolean verifier(CodeSalle solution, CodeSalle proposition) {
        int numeroSalleSolution = solution.getSalle();
        int numeroSalleProposition = proposition.getSalle();
        if (superieur) {
            return numeroSalleProposition > valeurReference;
        } else {
            return numeroSalleProposition < valeurReference;
        }
    }
}
