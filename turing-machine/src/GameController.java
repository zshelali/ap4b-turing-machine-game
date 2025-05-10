import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameController {

    private List<Challenge> challenges;
    private Challenge currentChallenge;
    private List<Administrateur> administrateurs;

    public GameController(){}
    
    public GameController(Challenge challenges) {
        this.currentChallenge = challenges;
        // this.administrateurs = creerAdministrateurs();
    }
    
    public static boolean comparerCodes(CodeSalle solution, CodeSalle proposition) {
    if (solution == null || proposition == null) {
        System.err.println("Erreur : solution ou proposition null !");
        return false;
    }
    return solution.getCampus() == proposition.getCampus() &&
           solution.getBatiment() == proposition.getBatiment() &&
           solution.getEtage() == proposition.getEtage() &&
           solution.getSalle() == proposition.getSalle();
}
    
    
    
}
