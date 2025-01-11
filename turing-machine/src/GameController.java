import java.util.List;

public class GameController {

    private List<Challenge> challenges;
    private Challenge currentChallenge;

    public GameController(List<Challenge> challenges) {
        this.challenges = challenges;
    }

    public Challenge getCurrentChallenge() {
        return currentChallenge;
    }


}
