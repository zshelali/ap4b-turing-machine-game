import java.util.List;

public class GameController {

    private List<Challenge> challenges;
    private Challenge currentChallenge;

    public GameController(List<Challenge> challenges) {
        this.challenges = challenges;
    }

    public void choisirChallenge(int index) {
        if (index >= 0 && index < challenges.size()) {
            currentChallenge = challenges.get(index);
        }
    }

    public Challenge getCurrentChallenge() {
        return currentChallenge;
    }
}
