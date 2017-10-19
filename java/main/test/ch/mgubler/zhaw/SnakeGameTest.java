package ch.mgubler.zhaw;

import ch.mgubler.zhaw.collision.CollisionDetector;
import ch.mgubler.zhaw.move.ObjectMover;
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
    private SnakeScreen gameSnakeScreen;

    @Mock
    private GameScore gameScore;

    @Mock
    private Terminal terminal;

    @Mock
    private Snake snake;

    @Mock
    private ObjectMover mover;

    @Mock
    private CollisionDetector collisionDetector;

    @Before
    public void setUp() throws Exception {
        snakeGameTestee = new SnakeGame(terminal, snake, mover, gameSnakeScreen, gameScore, collisionDetector);
    }

    @Test
    public void startGame() throws Exception {
        //To ensure, that main loop will only called once
        doThrow(new RuntimeException()).when(collisionDetector).detectCollision();

        assertTrue(snakeGameTestee.isRunning());

        snakeGameTestee.startGame();

        verify(gameSnakeScreen).init();
        verify(mover).pollDirectionChange();
        verify(mover).moveObject();
        verify(collisionDetector).detectCollision();
        verify(gameSnakeScreen).stopScreen();

        assertFalse(snakeGameTestee.isRunning());
    }

    @Test
    public void gameOver() throws Exception {
        assertTrue(snakeGameTestee.isRunning());
        snakeGameTestee.gameOver();
        assertFalse(snakeGameTestee.isRunning());
    }

}