public class CritereSommePaire extends Critere {
    private boolean pair; // true pour paire, false pour impaire

    public CritereSommePaire(String description, boolean pair) {
        super(description);
        this.pair = pair;
    }

    @Override
    public boolean verifier(CodeSalle solution, CodeSalle proposition) {
        int sommeSolution = calculerSommeChiffres(solution);
        int sommeProposition = calculerSommeChiffres(proposition);

        // Comparer la parité des deux sommes
        return (sommeSolution % 2 == 0) == (sommeProposition % 2 == 0);
    }

    /**
     * Calcule la somme des chiffres des champs pertinents de CodeSalle.
     *
     * @param codeSalle Le code de la salle.
     * @return La somme des chiffres.
     */
    private int calculerSommeChiffres(CodeSalle codeSalle) {
        int somme = 0;

        // Ajouter les chiffres d'étage et de salle
        somme += sommeDesChiffres(codeSalle.getEtage());
        somme += sommeDesChiffres(codeSalle.getSalle());

        return somme;
    }

    /**
     * Calcule la somme des chiffres d'un entier.
     *
     * @param valeur Un entier (exemple : 25).
     * @return La somme de ses chiffres (exemple : 2 + 5 = 7).
     */
    private int sommeDesChiffres(int valeur) {
        int somme = 0;
        while (valeur != 0) {
            somme += valeur % 10; // Récupérer le dernier chiffre
            valeur /= 10;         // Supprimer le dernier chiffre
        }
        return somme;
    }
}
