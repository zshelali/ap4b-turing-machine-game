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

    private boolean verifcrit0 = false;
    private boolean verifcrit1 = false;
    private boolean verifcrit2 = false;
    private boolean verifcrit3 = false;
    private CodeSalle reponseAdmin;

    private int compteurTours;

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
    Button quitButton = new Button("Quitter le jeu");

    challengeButton1.setOnAction(e -> afficherPage(administrateurs, 1));
    challengeButton2.setOnAction(e -> afficherPage(administrateurs, 2));
    challengeButton3.setOnAction(e -> afficherPage(administrateurs, 3));
    quitButton.setOnAction(e -> Platform.exit()); 


    outputArea = new TextArea();
    outputArea.setEditable(false);
    outputArea.setPrefHeight(100);

    VBox layout = new VBox(10, new Label("Menu Principal"), challengeButton1, challengeButton2, challengeButton3, quitButton, outputArea);
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
    
        Critere critere0 = currChallenge.getListeCriteres().get(0);
        Critere critere1 = currChallenge.getListeCriteres().get(1);
        Critere critere2 = currChallenge.getListeCriteres().get(2);
        Critere critere3 = currChallenge.getListeCriteres().get(3);

        Label critere0Affichage = new Label("1");
        Label critere1Affichage = new Label("2");
        Label critere2Affichage = new Label("3");
        Label critere3Affichage = new Label("4");

        critere0Affichage.setText(critere0.getDescription());
        critere1Affichage.setText(critere1.getDescription());
        critere2Affichage.setText(critere2.getDescription());
        critere3Affichage.setText(critere3.getDescription());

        System.out.println("DEBOG : " + currChallenge.getSolution().getSalle());

        // Label pour afficher les interactions restantes
        Label interactionsLabel = new Label("Interactions restantes : " + admin.getInteractions());
        Label compteurToursLabel = new Label("Nombre de tours : " + compteurTours);
    
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
    
        Label salleLabel = new Label("Salle:");
        ComboBox<Integer> salleComboBox = new ComboBox<>();
        salleComboBox.getItems().addAll(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

        //Affichage des cartes critère 
    
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
        salleComboBox.setOnAction(e -> {
            resultLabel.setText("Numéro sélectionné : " + salleComboBox.getValue());
        });

        

        // Finaliser la validation lorsque tout est sélectionné
        Button nextTurnButton = new Button("Tour suivant");
        nextTurnButton.setOnAction(e -> {
            admin.resetInteractions();
            interactionsLabel.setText("Interactions restantes : " + admin.getInteractions());
            resultLabel.setText("");
            compteurTours++;
            compteurToursLabel.setText("Nombre de tours : " + compteurTours);

        });


        Button validateButton = new Button("Confirmer code");
        validateButton.setOnAction(e -> {
            /* COMPARER CODE ADMIN AVEC SOLUTION */
            if (campusComboBox.getValue() != null &&
            batimentComboBox.getValue() != null &&
            etageComboBox.getValue() != null &&
            salleComboBox.getValue() != null) {
                resultLabel.setText("Confirmer ?");
                    Alert surete = new Alert(Alert.AlertType.CONFIRMATION);
                    surete.setTitle("Confirmer ?");
                    surete.setHeaderText("Êtes-vous sûr de vouloir confirmer ?");
                    surete.setContentText("Si votre code est faux vous aurez perdu !");
                
                    ButtonType buttonSur = new ButtonType("OK");
                    ButtonType buttonPasSur = new ButtonType("Non", ButtonBar.ButtonData.CANCEL_CLOSE);

                    surete.getButtonTypes().setAll(buttonSur, buttonPasSur);
                
                    // Affichez l'alerte
                    reponseAdmin = new CodeSalle(campusComboBox.getValue(), batimentComboBox.getValue(), etageComboBox.getValue(), salleComboBox.getValue());

                    Optional<ButtonType> result2 = surete.showAndWait();
                    if (result2.isPresent() && result2.get() == buttonSur) {

                        // Réinitialiser les interactions et retourner au menu principal
                        admin.resetInteractions();
                        interactionsLabel.setText("Interactions restantes : " + admin.getInteractions());
                        resultLabel.setText("");
                        if (GameController.comparerCodes(reponseAdmin, currChallenge.getSolution())) {
                            // CHANGER URGENT MODIFIER
                            resultLabel.setText("VICTOIRE");
                            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
                            confirmation.setTitle("Victoire");
                            confirmation.setHeaderText("Bravo ! Vous avez gagné avec "+ compteurTours + " tours !");
                            confirmation.setContentText("Cliquez sur OK pour retourner au menu principal");
                        
                            ButtonType buttonOK = new ButtonType("OK");
                            confirmation.getButtonTypes().setAll(buttonOK);
                        
                            // Affichez l'alerte
                            Optional<ButtonType> result = confirmation.showAndWait();
                            if (result.isPresent() && result.get() == buttonOK) {
                                // Réinitialiser les interactions et retourner au menu principal
                                admin.resetInteractions();
                                interactionsLabel.setText("Interactions restantes : " + admin.getInteractions());
                                resultLabel.setText("");
                                compteurTours = 0;
                                compteurToursLabel.setText("Nombre de tours : " + compteurTours);
                                afficherMenuPrincipal(administrateurs);
                            }
                        }
                        else {
                            resultLabel.setText("Dommage, le code est faux");
                            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
                            confirmation.setTitle("Défaite");
                            confirmation.setHeaderText("Vous avez perdu");
                            confirmation.setContentText("Cliquez sur OK pour retourner au menu principal");
            
                            ButtonType buttonOK = new ButtonType("OK");
                            confirmation.getButtonTypes().setAll(buttonOK);
            
                            Optional<ButtonType> result = confirmation.showAndWait();
                            if (result.isPresent() && result.get() == buttonOK) {
                                // Réinitialiser les interactions de l'administrateur
                                admin.resetInteractions();
                                interactionsLabel.setText("Interactions restantes : " + admin.getInteractions());
                                resultLabel.setText("");
                                compteurTours = 0;
                                compteurToursLabel.setText("Nombre de tours : " + compteurTours);
                    
                                // Retourner au menu principal
                                afficherMenuPrincipal(administrateurs);
                            }
                        }
                        afficherMenuPrincipal(administrateurs);
                    }
                    else {
                        System.out.println("Canceled");
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
                resultLabel.setText("");
    
                // Retourner au menu principal
                afficherMenuPrincipal(administrateurs);
            }
        });

        Button testcritere0Button = new Button("Tester critère 1");
        testcritere0Button.setOnAction(e -> {
            if (admin.getInteractions() >= 1 
            && campusComboBox.getValue() != null &&
            batimentComboBox.getValue() != null &&
            etageComboBox.getValue() != null &&
            salleComboBox.getValue() != null) {
                verifcrit0 = currChallenge.verifierCritere(critere0, reponseAdmin);
                admin.decremInteractions();
                interactionsLabel.setText("Interactions restantes : " + admin.getInteractions());
                resultLabel.setText(verifcrit0 ? "Verifié !" : "Ah ! c'est faux");           
            }
            else {
                resultLabel.setText("Réponse vide \n ou plus d'intéractions possibles");
            }
        });
        Button testcritere1Button = new Button("Tester critère 2");
        testcritere1Button.setOnAction(e -> {
            if (admin.getInteractions() >= 1 
            && campusComboBox.getValue() != null &&
            batimentComboBox.getValue() != null &&
            etageComboBox.getValue() != null &&
            salleComboBox.getValue() != null) {
                verifcrit1 = currChallenge.verifierCritere(critere1, reponseAdmin);
                admin.decremInteractions();
                interactionsLabel.setText("Interactions restantes : " + admin.getInteractions());
                resultLabel.setText(verifcrit1 ? "Verifié !" : "Ah ! c'est faux");           
            }
            else {
                resultLabel.setText("Réponse vide \n ou plus d'intéractions possibles");
            }
        });
        Button testcritere2Button = new Button("Tester critère 3");
        testcritere2Button.setOnAction(e -> {
            if (admin.getInteractions() >= 1 
            && campusComboBox.getValue() != null &&
            batimentComboBox.getValue() != null &&
            etageComboBox.getValue() != null &&
            salleComboBox.getValue() != null) {
                System.out.println("DEBOG : " + currChallenge.getSolution().getSalle());

                verifcrit2 = currChallenge.verifierCritere(critere2, reponseAdmin);
                admin.decremInteractions();
                interactionsLabel.setText("Interactions restantes : " + admin.getInteractions());
                resultLabel.setText(verifcrit2 ? "Verifié !" : "Ah ! c'est faux");   
            }
            else {
                resultLabel.setText("Réponse vide \n ou plus d'intéractions possibles");
            }
        });
        Button testcritere3Button = new Button("Tester critère 4");
        testcritere3Button.setOnAction(e -> {
            if (admin.getInteractions() >= 1 
            && campusComboBox.getValue() != null &&
            batimentComboBox.getValue() != null &&
            etageComboBox.getValue() != null &&
            salleComboBox.getValue() != null) {
                System.out.println("DEBOG ETAGE SOLUTION 🤪: " + currChallenge.getSolution().getEtage());

                verifcrit3 = currChallenge.verifierCritere(critere3, reponseAdmin);
                admin.decremInteractions();
                interactionsLabel.setText("Interactions restantes : " + admin.getInteractions());
                resultLabel.setText(verifcrit3 ? "Verifié !" : "Ah ! c'est faux");   
            }
            else {
                resultLabel.setText("Réponse vide \n ou plus d'intéractions possibles");
            }
        });

        System.out.println("DEBOG : " + currChallenge.getSolution().getSalle());


        // Organiser les éléments dans un layout
        VBox choicesLayout = new VBox(10, campusLabel, campusComboBox,
                                       batimentLabel, batimentComboBox,
                                       etageLabel, etageComboBox,
                                       salleLabel, salleComboBox, resultLabel, nextTurnButton, validateButton, boutonRetour);

        VBox critereBox = new VBox(20,
        critere0Affichage,
        testcritere0Button,

        critere1Affichage,
        testcritere1Button,

        critere2Affichage,
        testcritere2Button,

        critere3Affichage,
        testcritere3Button
        );

        critereBox.setStyle("-fx-padding: 10px; -fx-border-color: black; -fx-border-width: 1px;");
        critereBox.setVisible(true);
    
        VBox layout = new VBox(15, new Label("Challenge "+challengeID), interactionsLabel, compteurToursLabel);
        layout.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        HBox mainLayout = new HBox(20, choicesLayout, critereBox, layout);
        mainLayout.setStyle("-fx-padding: 20px;");
        mainLayout.setPrefWidth(700);
    
        // Créer et appliquer la scène
        Scene scene = new Scene(mainLayout, 1000, 500);
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