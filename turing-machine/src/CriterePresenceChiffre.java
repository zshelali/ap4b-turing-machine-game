public class CriterePresenceChiffre extends Critere {
    private char chiffre;

    public CriterePresenceChiffre(String description, char chiffre) {
        super(description);
        this.chiffre = chiffre;
    }

    @Override
    public boolean verifier(CodeSalle solution, CodeSalle proposition) {
        int countSolution = countDigitOccurrences(solution, chiffre);
        int countProposition = countDigitOccurrences(proposition, chiffre);
        
        return countSolution == countProposition;
    }

    private int countDigitOccurrences(CodeSalle codeSalle, char chiffre) {
        int count = 0;
        String codeStr = String.valueOf(codeSalle.getEtage()) + String.valueOf(codeSalle.getSalle());

        // Compter les occurrences du chiffre
        for (int i = 0; i < codeStr.length(); i++) {
            if (codeStr.charAt(i) == chiffre) {
                count++;
            }
        }
        return count;
    }
}
