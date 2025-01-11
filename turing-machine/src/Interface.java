import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Interface extends Application {

    private TextArea outputArea;
    private Stage stage;
    private Challenge currChallenge;

    @Override
    public void start(Stage primaryStage) {
    stage = primaryStage;
    primaryStage.setTitle("U-Turing B-Machine game (U-T B-M)");
    
    List<Administrateur> administrateurs = creerAdministrateursAvecDialogue();

    afficherMenuPrincipal(administrateurs);

    primaryStage.show();
}

private List<Administrateur> creerAdministrateursAvecDialogue() {
    List<Administrateur> administrateurs = new ArrayList<>();
    int id = 1; // Commencez avec l'identifiant 1
    boolean continuer = true;

    while (continuer) {
        // Afficher une boîte de dialogue pour demander si on veut ajouter un administrateur
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Ajouter un administrateur");
        alert.setHeaderText("Voulez-vous ajouter un nouvel administrateur ?");
        alert.setContentText("Cliquez sur Oui pour ajouter, ou sur Non pour passer au menu principal.");

        ButtonType buttonOui = new ButtonType("Oui");
        ButtonType buttonNon = new ButtonType("Non", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonOui, buttonNon);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == buttonOui) {
            // Créer un nouvel administrateur avec l'ID actuel
            Administrateur admin = new Administrateur(id++);
            administrateurs.add(admin);
            afficherMessage("Administrateur avec ID " + (id - 1) + " ajouté !");
        } else {
            // L'utilisateur a choisi "Non", sortir de la boucle
            continuer = false;
        }
    }

    // Si aucun administrateur n'a été ajouté, en créer un par défaut
    if (administrateurs.isEmpty()) {
        Administrateur defaultAdmin = new Administrateur(id);
        administrateurs.add(defaultAdmin);
        afficherMessage("Administrateur par défaut avec ID " + id + " créé.");
    }

    afficherMessage("Nombre total d'administrateurs créés : " + administrateurs.size());
    return administrateurs;
}

private void afficherMenuPrincipal(List<Administrateur> administrateurs) {
    Button challengeButton1 = new Button("Challenge 1");
    Button challengeButton2 = new Button("Challenge 2");
    Button challengeButton3 = new Button("Challenge 3");

    challengeButton1.setOnAction(e -> afficherPage(administrateurs, 1));
    challengeButton2.setOnAction(e -> afficherPage(administrateurs, 2));
    challengeButton3.setOnAction(e -> afficherPage(administrateurs, 3));

    outputArea = new TextArea();
    outputArea.setEditable(false);
    outputArea.setPrefHeight(100);

    VBox layout = new VBox(10, new Label("Menu Principal"), challengeButton1, challengeButton2, challengeButton3, outputArea);
    Scene scene = new Scene(layout, 400, 300);

    stage.setScene(scene); // Changer la scène
}

    /*
     * chaque page est un challenge.
     */
    private void afficherPage(List<Administrateur> administrateurs, int challengeID) {
        // Vérifier qu'il y a au moins un administrateur
        if (administrateurs.isEmpty()) {
            afficherMessage("Aucun administrateur disponible !");
            return;
        }
        Administrateur admin = administrateurs.get(0);
        

        

        VBox criteresLayout = new VBox(10);
        criteresLayout.setVisible(false);

        switch (challengeID){
            case 1:
                currChallenge = Challenge.creerChallenge1();
                break;
            case 2:
                currChallenge = Challenge.creerChallenge2();
                break;
            case 3:
                currChallenge = Challenge.creerChallenge3();
                break;
            default :
                System.out.println("HELP");
        }

        GameController gameController = new GameController();
        CodeSalle solution = new CodeSalle();
        
    
        // Label pour afficher les interactions restantes
        Label interactionsLabel = new Label("Interactions restantes : " + admin.getInteractions());
    
        // Création des menus déroulants pour le choix de CodeSalle
        Label campusLabel = new Label("Campus:");
        ComboBox<Character> campusComboBox = new ComboBox<>();
        campusComboBox.getItems().addAll('B', 'S', 'M');
    
        Label batimentLabel = new Label("Bâtiment:");
        ComboBox<Character> batimentComboBox = new ComboBox<>();
        batimentComboBox.getItems().addAll('A', 'B', 'C', 'D','E');
    
        Label etageLabel = new Label("Étage:");
        ComboBox<Integer> etageComboBox = new ComboBox<>();
        etageComboBox.getItems().addAll(0, 1, 2, 3, 4);
    
        Label numeroLabel = new Label("Numéro:");
        ComboBox<Integer> numeroComboBox = new ComboBox<>();
        numeroComboBox.getItems().addAll(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
    
        // Bouton pour valider le choix
        Label resultLabel = new Label(); // Label pour afficher le résultat
    
        // Ajouter un listener pour détecter les sélections dans les menus
        campusComboBox.setOnAction(e -> {
            resultLabel.setText("Campus sélectionné : " + campusComboBox.getValue());
        });
        batimentComboBox.setOnAction(e -> {
            resultLabel.setText("Bâtiment sélectionné : " + batimentComboBox.getValue());
        });
        etageComboBox.setOnAction(e -> {
            resultLabel.setText("Étage sélectionné : " + etageComboBox.getValue());
        });
        numeroComboBox.setOnAction(e -> {
            resultLabel.setText("Numéro sélectionné : " + numeroComboBox.getValue());
        });
    
        // Finaliser la validation lorsque tout est sélectionné
        Button validateButton = new Button("Valider");
        validateButton.setOnAction(e -> {
            /* VERIFIER CRITERE */
            if (campusComboBox.getValue() != null &&
            batimentComboBox.getValue() != null &&
            etageComboBox.getValue() != null &&
            numeroComboBox.getValue() != null) {

                CodeSalle reponseAdmin = new CodeSalle(campusComboBox.getValue(), batimentComboBox.getValue(), etageComboBox.getValue(), numeroComboBox.getValue());

                boolean critereValide = false;
                if(GameController.comparerCodes(reponseAdmin, currChallenge.getSolution()) == true){
                    //CHANGER URGENT MODIFIER
                     resultLabel.setText("VICTOIRE");
                }
                else {
                    resultLabel.setText("Dommage, le code est faux");;
                }
            
            }

        });
    
        // Bouton pour revenir au menu principal avec confirmation
        Button boutonRetour = new Button("Retour au Menu Principal");
        boutonRetour.setOnAction(e -> {
            // Afficher une boîte de dialogue de confirmation
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
            confirmation.setTitle("Confirmation");
            confirmation.setHeaderText("Êtes-vous sûr de vouloir quitter la partie ?");
            confirmation.setContentText("Cliquez sur Oui pour quitter ou sur Non pour rester dans la partie.");
    
            ButtonType buttonOui = new ButtonType("Oui");
            ButtonType buttonNon = new ButtonType("Non", ButtonBar.ButtonData.CANCEL_CLOSE);
            confirmation.getButtonTypes().setAll(buttonOui, buttonNon);
    
            Optional<ButtonType> result = confirmation.showAndWait();
            if (result.isPresent() && result.get() == buttonOui) {
                // Réinitialiser les interactions de l'administrateur
                admin.resetInteractions();
                interactionsLabel.setText("Interactions restantes : " + admin.getInteractions());
    
                // Retourner au menu principal
                afficherMenuPrincipal(administrateurs);
            }
        });
    
        // Organiser les éléments dans un layout
        VBox choicesLayout = new VBox(10, campusLabel, campusComboBox,
                                       batimentLabel, batimentComboBox,
                                       etageLabel, etageComboBox,
                                       numeroLabel, numeroComboBox, validateButton, resultLabel);
    
        VBox layout = new VBox(15, new Label("Challenge "+challengeID), interactionsLabel, choicesLayout, boutonRetour);
        layout.setStyle("-fx-padding: 20px; -fx-alignment: center;");
    
        // Créer et appliquer la scène
        Scene scene = new Scene(layout, 1024, 728);
        stage.setScene(scene);
    }
    
    ///////////////////////////////////////////////////////////////////////
    
    

    // Méthode pour afficher un message
    public void afficherMessage(String message) {
        Platform.runLater(() -> outputArea.appendText(message + "\n"));
    }

    // Méthode pour afficher les critères
    public void afficherCriteres(List<Critere> criteres) {
        afficherMessage("Chargement des critères...");
        try {
            Thread.sleep(2000); // Simule un délai (par exemple, chargement depuis une base de données)
        } catch (InterruptedException e) {
            afficherMessage("Erreur lors du chargement des critères.");
        }
        afficherMessage("Critères :");
        for (Critere critere : criteres) {
            afficherMessage(critere.getDescription());
        }
    }

    // Méthode pour afficher le résultat
    public void afficherResultat(boolean resultat) {
        try {
            Thread.sleep(1000); // Simule une vérification longue
        } catch (InterruptedException e) {
            afficherMessage("Erreur lors de la vérification.");
        }
        if (resultat) {
            afficherMessage("Critère vérifié !");
        } else {
            afficherMessage("Critère non vérifié !");
        }
    }

    // Méthode pour lancer une tâche dans un thread séparé
    public void lancerTache(Runnable tache) {
        Thread thread = new Thread(tache);
        thread.setDaemon(true); // Le thread se termine automatiquement quand l'application se ferme
        thread.start();
    }

    public static void main(String[] args) {
        launch(args);
        
    }
}