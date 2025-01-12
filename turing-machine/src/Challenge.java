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
        return critere.verifier(proposition, this.getSolution());
    }

    public static Challenge creerChallenge1() {
        // Solution du challenge 1
        CodeSalle solution1 = new CodeSalle('B', 'C', 2, 5);
        
        // Définition des critères
        List<Critere> criteres1 = List.of(
            new CritereAlpha("Le bâtiment est avant | après le campus par ordre alphabétique"),
            new CritereParite("L'étage est pair | impair", true),
            new CritereSommePaire("La somme des chiffres est paire | impaire", true),
            new CriterePresenceChiffre("Le nombre d'occurrences du chiffre 4 est identique", '4') 
        );
        
        // Création du challenge
        Challenge challenge1 = new Challenge(solution1, criteres1);
        return challenge1;
    }
    
    public static Challenge creerChallenge2() {
        // Challenge 2
        CodeSalle solution2 = new CodeSalle('M', 'A', 3, 7);
        List<Critere> criteres2 = List.of(
            new CritereAlpha("Le bâtiment est avant | après le campus par ordre alphabétique"),
            new CritereRang("Le numéro de salle est supérieur | inférieur à 5", 5, true),
            new CritereParite("L'étage est pair | impair", true), 
            new CriterePresenceChiffre("La présence du chiffre 7 dans l'étage ou le numéro de salle", '7')  
        );
        Challenge challenge2 = new Challenge(solution2, criteres2);
        return challenge2;
    }
    
    public static Challenge creerChallenge3() {
        // Challenge 3
        CodeSalle solution3 = new CodeSalle('S', 'D', 0, 2);
        List<Critere> criteres3 = List.of(
            new CritereParite("Le numéro de salle est pair | impair", false),
            new CritereRang("L'étage est inférieur | supérieur à 3.", 3, false),
            new CritereAlpha("Le bâtiment est avant | après le campus par ordre alphabétique"),  
            new CriterePresenceChiffre("La présence du chiffre 2 dans l'étage ou le numéro de salle", '2')  
        );
        Challenge challenge3 = new Challenge(solution3, criteres3);
        return challenge3;
    }
    
    
        
}

