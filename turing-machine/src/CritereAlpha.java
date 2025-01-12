public class CritereAlpha extends Critere {
    private char campusRef; // Référence de campus pour la comparaison
    private char batimentRef; // Référence de bâtiment pour la comparaison

    public CritereAlpha(String description) {
        super(description);
    }

    @Override
    public boolean verifier(CodeSalle solution, CodeSalle proposition) {
        // Vérifie si le bâtiment est avant/après le campus par ordre alphabétique
        return proposition.getBatiment() < solution.getCampus();
    }
    public CritereAlpha(char campusRef, char batimentRef) {
        this.campusRef = campusRef;
        this.batimentRef = batimentRef;
    }
    

    public boolean verifCamp(CodeSalle codeSalle) {
        return codeSalle.getCampus() > campusRef ; // Vérifie si le campus est après campusRef dans l'ordre alphabétique
    }

    public boolean verifBat(CodeSalle codeSalle) {
        return codeSalle.getBatiment() > batimentRef; // Vérifie si le bâtiment est après batimentRef dans l'ordre alphabétique
    }
}
