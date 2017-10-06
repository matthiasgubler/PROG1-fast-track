package ch.mgubler.zhaw.score;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class GameScore extends ScoreObserver {

    private int gameScore = 0;

    private void updateScoreOnScreen() {
        //TODO implement
        throw new NotImplementedException();
    }

    @Override
    public void updateScore(int scoreToAdd) {
        this.gameScore += scoreToAdd;
        updateScoreOnScreen();
    }

    public int getGameScore() {
        return gameScore;
    }
}
