package ch.mgubler.zhaw.objects;

import ch.mgubler.zhaw.SnakeGame;
import ch.mgubler.zhaw.move.Direction;
import ch.mgubler.zhaw.move.MoveablePosition;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class SnakeTest {

    private static final int TEST_SNAKE_START_X = 10;

    private static final int TEST_SNAKE_START_Y = 10;

    private Snake snakeTestee;

    @Mock
    private SnakeGame snakeGameMock;

    @Before
    public void setUp() throws Exception {
        snakeTestee = new Snake(snakeGameMock, new MoveablePosition(TEST_SNAKE_START_X, TEST_SNAKE_START_Y));
    }

    @Test
    public void move_only_head() throws Exception {
        assertEquals(Direction.RIGHT, snakeTestee.getCurrentDirection());
        snakeTestee.move();

        assertEquals(TEST_SNAKE_START_X+1 ,snakeTestee.getPosition().getX());
        assertEquals(TEST_SNAKE_START_Y ,snakeTestee.getPosition().getY());
    }

    @Test
    public void move_snake_one_bodyelement() throws Exception {
        assertEquals(Direction.RIGHT, snakeTestee.getCurrentDirection());
        snakeTestee.grow();

        snakeTestee.move();
        SnakeElement newlyAddedSnakeElement = snakeTestee.getElementAtIndex(0);
        assertEquals(TEST_SNAKE_START_X+1 ,snakeTestee.getPosition().getX());
        assertEquals(TEST_SNAKE_START_Y ,snakeTestee.getPosition().getY());
        assertEquals(TEST_SNAKE_START_X ,newlyAddedSnakeElement.getPosition().getX());
        assertEquals(TEST_SNAKE_START_Y ,newlyAddedSnakeElement.getPosition().getY());
    }

    @Test
    public void move_snake_two_bodyelements() throws Exception {
        assertEquals(Direction.RIGHT, snakeTestee.getCurrentDirection());
        snakeTestee.grow();
        snakeTestee.grow();

        snakeTestee.move();
        snakeTestee.move();
        snakeTestee.move();
        SnakeElement firstSnakeElement = snakeTestee.getElementAtIndex(0);
        SnakeElement secondSnakeElement = snakeTestee.getElementAtIndex(1);
        assertEquals(TEST_SNAKE_START_X+3 ,snakeTestee.getPosition().getX());
        assertEquals(TEST_SNAKE_START_Y ,snakeTestee.getPosition().getY());
        assertEquals(TEST_SNAKE_START_X+2 ,firstSnakeElement.getPosition().getX());
        assertEquals(TEST_SNAKE_START_Y ,firstSnakeElement.getPosition().getY());
        assertEquals(TEST_SNAKE_START_X+1 ,secondSnakeElement.getPosition().getX());
        assertEquals(TEST_SNAKE_START_Y ,secondSnakeElement.getPosition().getY());
    }

    @Test
    public void grow() throws Exception {
        assertEquals(0, snakeTestee.getSnakeElements().size());
        snakeTestee.grow();
        assertEquals(1, snakeTestee.getSnakeElements().size());
        SnakeElement newlyAddedSnakeElement = snakeTestee.getElementAtIndex(0);
        assertTrue(newlyAddedSnakeElement.getSnakeElements().isEmpty());
        assertEquals(TEST_SNAKE_START_X ,newlyAddedSnakeElement.getPosition().getX());
        assertEquals(TEST_SNAKE_START_Y ,newlyAddedSnakeElement.getPosition().getY());
    }

    @Test
    public void grow_three() throws Exception {
        assertEquals(0, snakeTestee.getSnakeElements().size());
        snakeTestee.grow(3);
        assertEquals(3, snakeTestee.getSnakeElements().size());
    }

}