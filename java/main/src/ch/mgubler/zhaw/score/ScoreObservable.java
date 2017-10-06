package ch.mgubler.zhaw.score;

import java.util.ArrayList;
import java.util.List;

public abstract class ScoreObservable {

    private List<ScoreObserver> scoreObserverList = new ArrayList<>();

    public ScoreObservable(ScoreObserver scoreObserver) {
        registerObserver(scoreObserver);
    }

    public void registerObserver(ScoreObserver scoreObserver){
        scoreObserverList.add(scoreObserver);
    }

    public void deregisterObserver(ScoreObserver scoreObserver){
        scoreObserverList.remove(scoreObserver);
    }

    public void notifyObserversScoreChange(int scoreToAdd){
        scoreObserverList.forEach(scoreObserver -> scoreObserver.updateScore(scoreToAdd));
    }

}
