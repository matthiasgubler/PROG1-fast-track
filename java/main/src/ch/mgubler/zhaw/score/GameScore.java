package ch.mgubler.zhaw.score;

public class GameScore extends ScoreObserver {

    private int gameScore = 0;

    @Override
    public void updateScore(int scoreToAdd) {
        this.gameScore += scoreToAdd;
    }

    public int getGameScore() {
        return gameScore;
    }
}
