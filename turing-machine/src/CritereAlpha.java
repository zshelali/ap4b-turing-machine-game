public class CritereAlpha extends Critere {

    public CritereAlpha(String description) {
        super(description);
    }

    @Override
    public boolean verifier(CodeSalle solution, CodeSalle proposition) {
        // Comparaison de l'ordre alphabétique entre le bâtiment et le campus
        boolean isBatiAvantCampusSolution = solution.getBatiment() < solution.getCampus();
        boolean isBatiAvantCampusProposition = proposition.getBatiment() < proposition.getCampus();
    
        // Vérifie si l'ordre de la proposition correspond à l'ordre de la solution
        return isBatiAvantCampusSolution == isBatiAvantCampusProposition;
    }
    
}
