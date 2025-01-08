/* comparaisons sur le rang des numeros */

public class CritereRang extends Critere {
    private int etageMin; // Étage minimum
    private int numMin;   // Numéro de salle minimum

    public CritereRang(int etageMin, int numMin) {
        this.etageMin = etageMin;
        this.numMin = numMin;
    }

    public boolean verifEtage(CodeSalle codeSalle) {
        return codeSalle.getEtage() > etageMin; // Vérifie si l'étage est supérieur à etageMin
    }

    public boolean verifNum(CodeSalle codeSalle) {
        return codeSalle.getSalle() >= numMin; // Vérifie si le numéro de salle est supérieur ou égal à numMin
    }
}
