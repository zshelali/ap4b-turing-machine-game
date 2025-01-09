import java.util.List;

public class Interface {

    public void afficherMessage(String message) {
        System.out.println(message);
    }

    public void afficherCriteres(List<Critere> criteres) {
        for (Critere critere : criteres) {
            System.out.println(critere.getDescription());
        }
    }

    public void afficherResultat(boolean resultat) {
        if (resultat) {
            System.out.println("Critère vérifié !");
        } else {
            System.out.println("Critère non vérifié !");
        }
    }
}
