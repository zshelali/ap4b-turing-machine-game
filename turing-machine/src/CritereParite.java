public class CritereParite extends Critere {
    private boolean pair;

    public CritereParite(String description, boolean pair) {
        super(description);
        this.pair = pair;
    }

    @Override
    public boolean verifier(CodeSalle solution, CodeSalle proposition) {
        int valeur = pair ? proposition.getEtage() : proposition.getNumero();
        return pair ? (valeur % 2 == 0) : (valeur % 2 != 0);
    }
}
