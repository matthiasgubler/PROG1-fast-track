package ch.mgubler.zhaw.objects;

import ch.mgubler.zhaw.SnakeGame;
import ch.mgubler.zhaw.move.Position;
import ch.mgubler.zhaw.score.ScoreObserver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class FoodFactoryTest {

    @Mock
    private SnakeGame snakeGameMock;

    @Mock
    private ScoreObserver scoreObserverMock;

    @Test
    public void generateFood_normal_food() throws Exception {
        Food generatedFood = FoodFactory.generateFood(1, snakeGameMock, new Position(1,2), scoreObserverMock);
        assertEquals(1, generatedFood.getPosition().getX());
        assertEquals(2, generatedFood.getPosition().getY());
        assertEquals(Food.class, generatedFood.getClass());
    }

    @Test
    public void generateFood_Superfood() throws Exception {
        Food generatedFood = FoodFactory.generateFood(5, snakeGameMock, new Position(1,2), scoreObserverMock);
        assertEquals(1, generatedFood.getPosition().getX());
        assertEquals(2, generatedFood.getPosition().getY());
        assertEquals(SuperFood.class, generatedFood.getClass());
    }

}