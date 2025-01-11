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

    public CodeSalle(){}

    public char getCampus() {
        return campus;
    }

    public char getBatiment() {
        return batiment;
    }

    public int getEtage() {
        return etage;
    }

    public int getSalle() {
        return numero;
    }

}
