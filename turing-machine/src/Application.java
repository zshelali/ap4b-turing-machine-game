import java.util.List;

public class Application {
    public static void main(String[] args) {
        List<Challenge> challenges = ChallengeManager.creerChallenges();
        // Choisir un challenge (par exemple, le premier)
        Challenge challenge = challenges.get(0);

        // Exemple de proposition d'un joueur
        CodeSalle proposition = new CodeSalle('B', 'A', 2, 3);

        // Vérifier les critères du challenge choisi
        for (Critere critere : challenge.getListeCriteres()) {
            boolean resultat = challenge.verifierCritere(critere, proposition);
            System.out.println(critere.getDescription() + " : " + (resultat ? "Validé" : "Non validé"));
        }
    }
}
