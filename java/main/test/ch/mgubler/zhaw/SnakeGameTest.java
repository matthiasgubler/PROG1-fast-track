package ch.mgubler.zhaw;

import ch.mgubler.zhaw.collision.CollisionDetector;
import ch.mgubler.zhaw.move.ObjectMover;
import ch.mgubler.zhaw.objects.FoodGenerator;
import ch.mgubler.zhaw.objects.Snake;
import ch.mgubler.zhaw.score.GameScore;
import com.googlecode.lanterna.terminal.Terminal;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SnakeGameTest {

    private SnakeGame snakeGameTestee;

    @Mock
    private SnakeScreen gameSnakeScreenMock;

    @Mock
    private GameScore gameScoreMock;

    @Mock
    private Terminal terminalMock;

    @Mock
    private Snake snakeMock;

    @Mock
    private ObjectMover moverMock;

    @Mock
    private CollisionDetector collisionDetectorMock;

    @Mock
    private FoodGenerator foodGeneratorMock;

    @Before
    public void setUp() throws Exception {
        snakeGameTestee = new SnakeGame(terminalMock, snakeMock, moverMock, gameSnakeScreenMock, gameScoreMock, collisionDetectorMock, foodGeneratorMock);
    }

    @Test
    public void startGame() throws Exception {
        //To ensure, that main loop will only called once
        doThrow(new RuntimeException()).when(collisionDetectorMock).detectCollision();

        assertTrue(snakeGameTestee.isRunning());

        snakeGameTestee.startGame();

        verify(gameSnakeScreenMock).init();
        verify(foodGeneratorMock).generateFood();
        verify(moverMock).pollDirectionChange();
        verify(moverMock).moveObject();
        verify(collisionDetectorMock).detectCollision();
        verify(gameSnakeScreenMock).stopScreen();

        assertFalse(snakeGameTestee.isRunning());
    }

    @Test
    public void gameOver() throws Exception {
        assertTrue(snakeGameTestee.isRunning());
        snakeGameTestee.gameOver();
        assertFalse(snakeGameTestee.isRunning());
    }

}