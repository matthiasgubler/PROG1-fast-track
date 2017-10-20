package ch.mgubler.zhaw.collision;

import ch.mgubler.zhaw.SnakeGame;
import ch.mgubler.zhaw.SnakeScreen;
import ch.mgubler.zhaw.objects.Food;
import ch.mgubler.zhaw.objects.Snake;
import ch.mgubler.zhaw.score.FoodPointsObservable;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PointsAddCollisionTest {

    private static final int TEST_FOODPOINTS = 22;

    private PointsAddCollision pointsAddCollisionTestee;

    @Mock
    private SnakeGame snakeGameMock;

    @Mock
    private Food collidingObjectMock;

    @Mock
    private SnakeScreen snakeScreenMock;

    @Mock
    private FoodPointsObservable foodPointsObservableMock;

    @Mock
    private Snake collidingSnake;

    @Before
    public void setUp(){
        pointsAddCollisionTestee = new PointsAddCollision(snakeGameMock, collidingObjectMock);
    }

    @Test
    public void collide() throws Exception {
        when(snakeGameMock.getGameSnakeScreen()).thenReturn(snakeScreenMock);
        when(collidingObjectMock.getFoodPointsObservable()).thenReturn(foodPointsObservableMock);
        when(collidingObjectMock.getFoodPoints()).thenReturn(TEST_FOODPOINTS);
        pointsAddCollisionTestee.collide(collidingSnake);
        verify(snakeScreenMock).removeGameElement(collidingObjectMock);
        verify(foodPointsObservableMock).notifyObserversScoreChange(TEST_FOODPOINTS);
        verify(snakeGameMock).generateFood();
        verify(collidingSnake).grow();
    }

}