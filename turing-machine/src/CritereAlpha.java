public class CritereAlpha extends Critere {
    private char campusRef; 
    private char batimentRef; 

    public CritereAlpha(String description) {
        super(description);
    }

    @Override
    public boolean verifier(CodeSalle solution, CodeSalle proposition) {
        return proposition.getBatiment() < solution.getCampus();
    }
    public CritereAlpha(char campusRef, char batimentRef) {
        this.campusRef = campusRef;
        this.batimentRef = batimentRef;
    }
    

    public boolean verifCamp(CodeSalle codeSalle) {
        return codeSalle.getCampus() > campusRef ;
    }

    public boolean verifBat(CodeSalle codeSalle) {
        return codeSalle.getBatiment() > batimentRef; 
    }
}
