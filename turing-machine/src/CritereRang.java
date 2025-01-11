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
        // Vérifie si le numéro de salle de la proposition respecte la condition par rapport à la solution
        int numeroSalleSolution = solution.getSalle();
        int numeroSalleProposition = proposition.getSalle();

        // Si le critère est "supérieur", on vérifie si le numéro de salle de la proposition est supérieur à la référence
        // Si le critère est "inférieur", on vérifie si le numéro de salle de la proposition est inférieur à la référence
        if (superieur) {
            return numeroSalleProposition > valeurReference;
        } else {
            return numeroSalleProposition < valeurReference;
        }
    }
}
