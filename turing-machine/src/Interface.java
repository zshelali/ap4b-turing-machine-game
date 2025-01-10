import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.List;

public class Interface extends Application {

    private TextArea outputArea;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Jeu AP4B - Interface Graphique avec Threads");

        // Layout principal
        VBox root = new VBox(10);
        root.setPadding(new javafx.geometry.Insets(10));

        // Zone d'affichage des messages
        outputArea = new TextArea();
        outputArea.setEditable(false);
        outputArea.setPrefHeight(200);

        // Boutons
        Button afficherCriteresBtn = new Button("Afficher Critères");
        Button verifierBtn = new Button("Vérifier");

        // Champ de saisie
        TextField propositionField = new TextField();
        propositionField.setPromptText("Entrez une proposition...");

        // Gestion des actions des boutons
        afficherCriteresBtn.setOnAction(e -> lancerTache(() -> afficherCriteres(List.of(new CritereAlpha("Exemple critère")))));
        verifierBtn.setOnAction(e -> {
            String proposition = propositionField.getText();
            if (proposition.isEmpty()) {
                afficherMessage("Veuillez entrer une proposition !");
            } else {
                lancerTache(() -> afficherResultat(proposition.length() % 2 == 0)); // Exemple de vérification
            }
        });

        // Ajouter les éléments au layout
        root.getChildren().addAll(new Label("Proposition :"), propositionField, afficherCriteresBtn, verifierBtn, outputArea);

        // Créer la scène
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
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