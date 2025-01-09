public class CodeSalle {

    private char campus;
    private char batiment;
    private int etage;
    private int numero;

    public CodeSalle(char campus, char batiment, int etage, int numero) {
        this.campus = campus;
        this.batiment = batiment;
        this.etage = etage;
        this.numero = numero;
    }

    public char getCampus() {
        return campus;
    }

    public char getBatiment() {
        return batiment;
    }

    public int getEtage() {
        return etage;
    }

    public int getNumero() {
        return numero;
    }

    public boolean comparer(CodeSalle c) {
        // Implémentez la comparaison ici si nécessaire
        return false;
    }
}
