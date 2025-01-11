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

    @Override
    public void start(Stage primaryStage) {
    stage = primaryStage;
    primaryStage.setTitle("UTuring BMachine game (UTBM)");
    
    List<Administrateur> administrateurs = creerAdministrateursAvecDialogue();

    afficherMenuPrincipal(administrateurs);

    //afficherMenuPrincipal();

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
        alert.setContentText("Cliquez sur Oui pour ajouter, Non pour continuer sans ajouter, ou Quitter pour fermer l'application.");

        ButtonType buttonOui = new ButtonType("Oui");
        ButtonType buttonNon = new ButtonType("Non", ButtonBar.ButtonData.CANCEL_CLOSE);
        ButtonType buttonQuitter = new ButtonType("Quitter", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonOui, buttonNon, buttonQuitter);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent()) {
            if (result.get() == buttonOui) {
                // Créer un nouvel administrateur avec l'ID actuel
                Administrateur admin = new Administrateur(id++);
                administrateurs.add(admin);
                afficherMessage("Administrateur avec ID " + (id - 1) + " ajouté !");
            } else if (result.get() == buttonNon) {
                // Si aucun administrateur n'a encore été créé, empêcher de continuer sans un minimum
                if (administrateurs.isEmpty()) {
                    afficherMessage("Vous devez ajouter au moins un administrateur !");
                } else {
                    continuer = false; // Sortir de la boucle
                }
            } else if (result.get() == buttonQuitter) {
                // Quitter l'application
                Platform.exit();
                return administrateurs; // En cas de fermeture, retourner la liste actuelle (même si vide)
            }
        }
    }

    afficherMessage("Nombre total d'administrateurs créés : " + administrateurs.size());
    return administrateurs;
}

private void afficherMenuPrincipal(List<Administrateur> administrateurs) {
    Button challengeButton1 = new Button("Challenge 1");
    Button challengeButton2 = new Button("Challenge 2");
    Button challengeButton3 = new Button("Challenge 3");

    challengeButton1.setOnAction(e -> afficherPage1(administrateurs));
    challengeButton2.setOnAction(e -> afficherPage2(administrateurs));
    challengeButton3.setOnAction(e -> afficherPage3(administrateurs));

    outputArea = new TextArea();
    outputArea.setEditable(false);
    outputArea.setPrefHeight(100);

    VBox layout = new VBox(10, new Label("Menu Principal"), challengeButton1, challengeButton2, challengeButton3, outputArea);
    Scene scene = new Scene(layout, 400, 300);

    stage.setScene(scene); // Changer la scène
}

    /*
     * Pour chaque page, c'est un challenge. On prend
     */
    private void afficherPage1(List<Administrateur> administrateurs) {
        // Select the first admin as an example
        if (administrateurs.isEmpty()) {
            afficherMessage("Aucun administrateur disponible !");
            return;
        }
        Administrateur admin = administrateurs.get(0);
    
        // Layout for displaying criteria
        VBox criteresLayout = new VBox(10);
        criteresLayout.setVisible(false); // Hide criteria by default
    
        // Display criteria descriptions
        Challenge challenge1 = Challenge.creerChallenge1();
        for (Critere critere : challenge1.getListeCriteres()) {
            Label critereLabel = new Label(critere.getDescription());
            criteresLayout.getChildren().add(critereLabel);
        }
    
        // Button to toggle criteria visibility
        Button toggleCriteresButton = new Button("Afficher les critères");
        toggleCriteresButton.setOnAction(e -> {
            if (admin.peutInteragir()) {
                admin.decremInteractions();
                criteresLayout.setVisible(!criteresLayout.isVisible());
                toggleCriteresButton.setText(criteresLayout.isVisible() ? "Masquer les critères" : "Afficher les critères");
            } else {
                afficherMessage("Aucune interaction restante pour cet administrateur !");
            }
        });
    
        // Button to return to the main menu
        Button boutonRetour = new Button("Retour au Menu Principal");
        boutonRetour.setOnAction(e -> afficherMenuPrincipal(administrateurs));
    
        // Main layout
        VBox layout = new VBox(10, new Label("Challenge 1"), toggleCriteresButton, criteresLayout, boutonRetour);
        layout.setPadding(new javafx.geometry.Insets(10));
    
        // Create and set the scene
        Scene scene = new Scene(layout, 400, 300);
        stage.setScene(scene);
    }
    
    private void afficherPage2(List<Administrateur> administrateurs) {
        // Select the first admin as an example
        if (administrateurs.isEmpty()) {
            afficherMessage("Aucun administrateur disponible !");
            return;
        }
        Administrateur admin = administrateurs.get(0);
    
        // Layout for displaying criteria
        VBox criteresLayout = new VBox(10);
        criteresLayout.setVisible(false); // Hide criteria by default
    
        // Display criteria descriptions
        Challenge challenge2 = Challenge.creerChallenge2();
        for (Critere critere : challenge2.getListeCriteres()) {
            Label critereLabel = new Label(critere.getDescription());
            criteresLayout.getChildren().add(critereLabel);
        }
    
        // Button to toggle criteria visibility
        Button toggleCriteresButton = new Button("Afficher les critères");
        toggleCriteresButton.setOnAction(e -> {
            if (admin.peutInteragir()) {
                admin.decremInteractions();
                criteresLayout.setVisible(!criteresLayout.isVisible());
                toggleCriteresButton.setText(criteresLayout.isVisible() ? "Masquer les critères" : "Afficher les critères");
            } else {
                afficherMessage("Aucune interaction restante pour cet administrateur !");
            }
        });
    
        // Button to return to the main menu
        Button boutonRetour = new Button("Retour au Menu Principal");
        boutonRetour.setOnAction(e -> afficherMenuPrincipal(administrateurs));
    
        // Main layout
        VBox layout = new VBox(10, new Label("Challenge 2"), toggleCriteresButton, criteresLayout, boutonRetour);
        layout.setPadding(new javafx.geometry.Insets(10));
    
        // Create and set the scene
        Scene scene = new Scene(layout, 400, 300);
        stage.setScene(scene);
    }

      
    private void afficherPage3(List<Administrateur> administrateurs) {
        // Select the first admin as an example
        if (administrateurs.isEmpty()) {
            afficherMessage("Aucun administrateur disponible !");
            return;
        }
        Administrateur admin = administrateurs.get(0);
    
        // Layout for displaying criteria
        VBox criteresLayout = new VBox(10);
        criteresLayout.setVisible(false); // Hide criteria by default
    
        // Display criteria descriptions
        Challenge challenge3 = Challenge.creerChallenge3();
        for (Critere critere : challenge3.getListeCriteres()) {
            Label critereLabel = new Label(critere.getDescription());
            criteresLayout.getChildren().add(critereLabel);
        }
    
        // Button to toggle criteria visibility
        Button toggleCriteresButton = new Button("Afficher les critères");
        toggleCriteresButton.setOnAction(e -> {
            if (admin.peutInteragir()) {
                admin.decremInteractions();
                criteresLayout.setVisible(!criteresLayout.isVisible());
                toggleCriteresButton.setText(criteresLayout.isVisible() ? "Masquer les critères" : "Afficher les critères");
            } else {
                afficherMessage("Aucune interaction restante pour cet administrateur !");
            }
        });
    
        // Button to return to the main menu
        Button boutonRetour = new Button("Retour au Menu Principal");
        boutonRetour.setOnAction(e -> afficherMenuPrincipal(administrateurs));
    
        // Main layout
        VBox layout = new VBox(10, new Label("Challenge 3"), toggleCriteresButton, criteresLayout, boutonRetour);
        layout.setPadding(new javafx.geometry.Insets(10));
    
        // Create and set the scene
        Scene scene = new Scene(layout, 400, 300);
        stage.setScene(scene);
    }
    

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