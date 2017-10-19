package ch.mgubler.zhaw.objects;

import ch.mgubler.zhaw.SnakeGame;
import ch.mgubler.zhaw.SnakeScreen;
import ch.mgubler.zhaw.collision.CollisionDetector;
import ch.mgubler.zhaw.score.ScoreObserver;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class FoodGeneratorTest {

    private FoodGenerator foodGenerator;

    @Mock
    private SnakeGame snakeGameMock;

    @Mock
    private SnakeScreen snakeScreenMock;

    @Mock
    private ScoreObserver scoreObserverMock;

    @Mock
    private CollisionDetector collisionDetectorMock;

    @Before
    public void setUp() throws Exception {
        foodGenerator = new FoodGenerator(snakeGameMock, snakeScreenMock, scoreObserverMock, collisionDetectorMock);
    }

    @Test
    public void generateFood(){
        assertEquals(1, foodGenerator.getFoodCounter());
        foodGenerator.generateFood();
        verify(snakeScreenMock).addGameElement(any(Food.class));
        verify(collisionDetectorMock).addElementOnScreen(any(Food.class));
        assertEquals(2, foodGenerator.getFoodCounter());
    }

}