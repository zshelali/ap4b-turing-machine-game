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

    // public static void main(String[] args) {
    //     // Création des challenges
    //     List<Challenge> challenges = new ArrayList<>();
    //     challenges.add(Challenge.creerChallenge1());
    //     challenges.add(Challenge.creerChallenge2());
    //     challenges.add(Challenge.creerChallenge3());

    //     // Lancement du jeu
    //     GameController game = new GameController(challenges);
    //     game.jouer();
    // }

    // public void jouer() {
    //     Scanner scanner = new Scanner(System.in);
    //     System.out.println("Bienvenue dans le jeu des Challenges !\n");

    //     while (true) {
    //         System.out.println("Menu Principal");
    //         System.out.println("1. Jouer au Challenge 1");
    //         System.out.println("2. Jouer au Challenge 2");
    //         System.out.println("3. Jouer au Challenge 3");
    //         System.out.println("4. Quitter");

    //         System.out.print("Votre choix : ");
    //         int choix = scanner.nextInt();

    //         switch (choix) {
    //             case 1 -> jouerChallenge(0);
    //             case 2 -> jouerChallenge(1);
    //             case 3 -> jouerChallenge(2);
    //             case 4 -> {
    //                 System.out.println("Merci d'avoir joué ! À bientôt.");
    //                 return;
    //             }
    //             default -> System.out.println("Choix invalide. Veuillez réessayer.\n");
    //         }
    //     }
    // }
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
    
    // private void jouerChallenge(int indexChallenge) {
    //     if (indexChallenge >= challenges.size()) {
    //         System.out.println("Challenge inexistant.");
    //         return;
    //     }
    
    //     currentChallenge = challenges.get(indexChallenge);
    //     Administrateur admin = administrateurs.get(0); // On utilise le premier administrateur par défaut
    //     System.out.println("\n--- Début du " + (indexChallenge + 1) + "e Challenge ---\n");
    
    //     // Affichage des critères
    //     System.out.println("Critères à respecter :");
    //     for (int i = 0; i < currentChallenge.getListeCriteres().size(); i++) {
    //         System.out.println((i + 1) + ". " + currentChallenge.getListeCriteres().get(i).getDescription());
    //     }
    
    //     // Interactions avec le joueur
    //     Scanner scanner = new Scanner(System.in);
    //     while (true) {  // Les tours sont illimités tant que le joueur n'a pas soumis son code
    //         System.out.println("\nProposez un CodeSalle (campus, bâtiment, étage, numéro) :");
    
    //         System.out.print("Campus (caractère) : ");
    //         char campus = scanner.next().charAt(0);
    //         System.out.print("Bâtiment (caractère) : ");
    //         char batiment = scanner.next().charAt(0);
    //         System.out.print("Étage (entier) : ");
    //         int etage = scanner.nextInt();
    //         System.out.print("Numéro de salle (entier) : ");
    //         int numero = scanner.nextInt();
    
    //         CodeSalle proposition = new CodeSalle(campus, batiment, etage, numero);
    //         admin.setProposition(proposition);
    
    //         // Demander à l'utilisateur de choisir les critères à vérifier (0 à 2 critères)
    //         System.out.println("\nChoisissez les critères à utiliser (au maximum 2 critères) :");
    //         System.out.println("Entrez le numéro des critères que vous souhaitez utiliser, séparés par des espaces (choisir deux 0 séparés par un espace si):");
    //         int choix1 = scanner.nextInt();
    //         int choix2 = -1;  // Par défaut, pas de second critère sélectionné
    //         if (scanner.hasNextInt()) {
    //             choix2 = scanner.nextInt();
    //         }
    
    //         // Vérification des critères choisis
    //         boolean tousVerifies = true;
    //         if (choix1 >= 1 && choix1 <= currentChallenge.getListeCriteres().size()) {
    //             Critere critere1 = currentChallenge.getListeCriteres().get(choix1 - 1);
    //             boolean resultat1 = currentChallenge.verifierCritere(critere1, proposition);
    //             System.out.println("Critère vérifié : " + critere1.getDescription() + " -> " + (resultat1 ? "Validé" : "Non validé"));
    //             if (!resultat1) tousVerifies = false;
    //         }
    
    //         if (choix2 >= 1 && choix2 <= currentChallenge.getListeCriteres().size()) {
    //             Critere critere2 = currentChallenge.getListeCriteres().get(choix2 - 1);
    //             boolean resultat2 = currentChallenge.verifierCritere(critere2, proposition);
    //             System.out.println("Critère vérifié : " + critere2.getDescription() + " -> " + (resultat2 ? "Validé" : "Non validé"));
    //             if (!resultat2) tousVerifies = false;
    //         }
    
    //         // Demander à l'utilisateur s'il souhaite déposer son code
    //         System.out.print("\nVoulez-vous déposer votre code pour vérifier la solution (O pour oui, F pour non) : ");
    //         char choix = scanner.next().charAt(0);
    //         if (choix == 'O' || choix == 'o') {
    //             // Comparaison complète des deux codes
    //             if (comparerCodes(currentChallenge.getSolution(), proposition)) {
    //                 System.out.println("\nFélicitations ! Votre proposition est correcte. Vous avez trouvé la solution.");
    //                 return; // Fin du challenge si la proposition est correcte
    //             } else {
    //                 System.out.println("\nDésolé, votre proposition n'est pas correcte.");
    //                 return; // Fin du challenge si la proposition est incorrecte
    //             }
    //         } else if (choix == 'F' || choix == 'f') {
    //             // Si l'utilisateur ne veut pas soumettre, il peut recommencer
    //             System.out.println("\nVous pouvez continuer à proposer un autre code.");
    //         } else {
    //             System.out.println("\nChoix non valide. Essayez de nouveau.");
    //         }
    //     }
    // }
    
    
    
    
    
    

    // private List<Administrateur> creerAdministrateurs() {
    //     List<Administrateur> administrateurs = new ArrayList<>();
    //     administrateurs.add(new Administrateur(1));
    //     System.out.println("Administrateur créé avec 3 interactions par défaut.");
    //     return administrateurs;
    // }
    
}
