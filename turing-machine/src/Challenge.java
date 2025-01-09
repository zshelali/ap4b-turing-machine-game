import java.util.List;

public class Challenge {

    private CodeSalle solution;
    private List<Critere> listeCriteres;

    public Challenge(CodeSalle solution, List<Critere> listeCriteres) {
        this.solution = solution;
        this.listeCriteres = listeCriteres;
    }

    public CodeSalle getSolution() {
        return solution;
    }

    public List<Critere> getListeCriteres() {
        return listeCriteres;
    }

    // Vérifie un critère spécifique pour une proposition donnée
    public boolean verifierCritere(Critere critere, CodeSalle proposition) {
        return critere.verifier(proposition, solution);
    }
}
