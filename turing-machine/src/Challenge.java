import java.util.List;
import java.util.ArrayList;

public class Challenge {

    private CodeSalle solution;
    private List<Critere> listeCriteres;

    public Challenge(){}
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

     public static Challenge creerChallenge1() {
        // Challenge 1
        CodeSalle solution1 = new CodeSalle('B', 'C', 2, 5);
        List<Critere> criteres1 = List.of(
            new CritereAlpha("Le bâtiment est avant le campus par ordre alphabétique."),
            new CritereParite("L'étage est pair.", true)
        );
        Challenge challenge1 = new Challenge(solution1, criteres1);

        return challenge1;
     }
        public static Challenge creerChallenge2() {
        // Challenge 2
        CodeSalle solution2 = new CodeSalle('M', 'A', 3, 7);
        List<Critere> criteres2 = List.of(
            new CritereAlpha("Le bâtiment est après le campus par ordre alphabétique."),
            new CritereRang("Le numéro de salle est supérieur à 5.", 5, true)
        );
        Challenge challenge2 = new Challenge(solution2, criteres2);
        return challenge2;
    }
        public static Challenge creerChallenge3(){
        // Challenge 3
        CodeSalle solution3 = new CodeSalle('S', 'D', 0, 1);
        List<Critere> criteres3 = List.of(
            new CritereParite("Le numéro de salle est impair.", false),
            new CritereRang("L'étage est inférieur à 3.", 3, false)
        );
        Challenge challenge3 = new Challenge(solution3, criteres3);
        return challenge3;
    }

        
}

