/* comparaisons sur la parite */

public class CritereParite extends Critere {
    public boolean verifEtage(CodeSalle codeSalle) {
        return codeSalle.getEtage() % 2 == 0; // Vérifie si l'étage est pair
    }

    public boolean verifNum(CodeSalle codeSalle) {
        return codeSalle.getSalle() % 2 == 0; // Vérifie si le numéro de salle est pair
    }
}
