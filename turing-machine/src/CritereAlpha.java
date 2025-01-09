public class CritereAlpha extends Critere {

    public CritereAlpha(String description) {
        super(description);
    }

    @Override
    public boolean verifier(CodeSalle solution, CodeSalle proposition) {
        // Vérifie si le bâtiment est avant/après le campus par ordre alphabétique
        return proposition.getBatiment() < solution.getCampus();
    }
}
