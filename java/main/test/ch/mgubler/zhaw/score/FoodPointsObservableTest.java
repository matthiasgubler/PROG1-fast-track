package ch.mgubler.zhaw.score;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class FoodPointsObservableTest {

    public static final int SCORE_TO_ADD = 20;

    private FoodPointsObservable foodPointsObservableTestee;

    @Mock
    private ScoreObserver scoreObserverMock;

    @Mock
    private ScoreObserver scoreObserverMock2;

    @Before
    public void setUp() throws Exception {
        foodPointsObservableTestee = new FoodPointsObservable(scoreObserverMock);
    }

    @Test
    public void notifyObserversScoreChange_one_observer() throws Exception {
        foodPointsObservableTestee.notifyObserversScoreChange(SCORE_TO_ADD);
        verify(scoreObserverMock).updateScore(SCORE_TO_ADD);
    }

    @Test
    public void notifyObserversScoreChange_multiple_observer() throws Exception {
        foodPointsObservableTestee.registerObserver(scoreObserverMock2);
        foodPointsObservableTestee.notifyObserversScoreChange(SCORE_TO_ADD);
        verify(scoreObserverMock).updateScore(SCORE_TO_ADD);
        verify(scoreObserverMock2).updateScore(SCORE_TO_ADD);
    }

}