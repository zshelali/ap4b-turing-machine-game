public class CritereParite extends Critere {
    private boolean pair; // Si true, vérifier si l'étage est pair, sinon vérifier si le numéro de salle est pair

    public CritereParite(String description, boolean pair) {
        super(description);
        this.pair = pair;
    }

    @Override
    public boolean verifier(CodeSalle solution, CodeSalle proposition) {
        // Si pair est true, on compare l'étage, sinon on compare le numéro de salle
        int valeurSolution = pair ? solution.getEtage() : solution.getSalle();
        int valeurProposition = pair ? proposition.getEtage() : proposition.getSalle();

        // Comparaison de la parité des valeurs
        if (pair) {
            // Vérifie si l'étage de la proposition a la même parité que l'étage de la solution
            return (valeurProposition % 2 == 0) == (valeurSolution % 2 == 0); // Compare si les deux ont la même parité
        } else {
            // Vérifie si le numéro de la salle de la proposition a la même parité que celui de la solution
            return (valeurProposition % 2 == 0) == (valeurSolution % 2 == 0); // Compare si les deux ont la même parité
        }
    }
}
