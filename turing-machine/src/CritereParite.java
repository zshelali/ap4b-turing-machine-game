public class CritereParite extends Critere {
    private boolean pair;

    public CritereParite(String description, boolean pair) {
        super(description);
        this.pair = pair;
    }

    @Override
    public boolean verifier(CodeSalle solution, CodeSalle proposition) {
        int valeur = pair ? proposition.getEtage() : proposition.getSalle();
        return pair ? (valeur % 2 == 0) : (valeur % 2 != 0);
    }

    
    public boolean verifEtage(CodeSalle codeSalle) {
        return codeSalle.getEtage() % 2 == 0; // Vérifie si l'étage est pair
    }

    public boolean verifSalle(CodeSalle codeSalle) {
        return codeSalle.getSalle() % 2 == 0; // Vérifie si le numéro de salle est pair
    }
}
