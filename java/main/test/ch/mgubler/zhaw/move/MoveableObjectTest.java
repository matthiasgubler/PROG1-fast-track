package ch.mgubler.zhaw.move;

import ch.mgubler.zhaw.SnakeGame;
import ch.mgubler.zhaw.objects.Snake;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MoveableObjectTest {

    public static final int TEST_START_X = 5;

    public static final int TEST_START_Y = 7;

    private MoveableObject moveableObjectTestee;

    @Mock
    private SnakeGame snakeGameMock;

    @Before
    public void setUp() {
        moveableObjectTestee = new Snake(snakeGameMock, new MoveablePosition(TEST_START_X, TEST_START_Y));
    }

    @Test
    public void getPosition() throws Exception {
        Position position = moveableObjectTestee.getPosition();
        Assert.assertEquals(TEST_START_X, position.getX());
        Assert.assertEquals(TEST_START_Y, position.getY());
    }

    @Test
    public void move_default_test() throws Exception {
        Assert.assertEquals(Direction.RIGHT, moveableObjectTestee.getCurrentDirection());
        moveableObjectTestee.move();
        Position position = moveableObjectTestee.getPosition();
        Assert.assertEquals(TEST_START_X + 1, position.getX());
        Assert.assertEquals(TEST_START_Y, position.getY());
    }

    @Test
    public void move_right_test() throws Exception {
        moveableObjectTestee.setCurrentDirection(Direction.RIGHT);
        moveableObjectTestee.move();
        Position position = moveableObjectTestee.getPosition();
        Assert.assertEquals(TEST_START_X + 1, position.getX());
        Assert.assertEquals(TEST_START_Y, position.getY());
    }

    @Test
    public void move_left_test() throws Exception {
        moveableObjectTestee.setCurrentDirection(Direction.LEFT);
        moveableObjectTestee.move();
        Position position = moveableObjectTestee.getPosition();
        Assert.assertEquals(TEST_START_X - 1, position.getX());
        Assert.assertEquals(TEST_START_Y, position.getY());
    }

    @Test
    public void move_up_test() throws Exception {
        moveableObjectTestee.setCurrentDirection(Direction.UP);
        moveableObjectTestee.move();
        Position position = moveableObjectTestee.getPosition();
        Assert.assertEquals(TEST_START_X, position.getX());
        Assert.assertEquals(TEST_START_Y - 1, position.getY());
    }

    @Test
    public void move_down_test() throws Exception {
        moveableObjectTestee.setCurrentDirection(Direction.DOWN);
        moveableObjectTestee.move();
        Position position = moveableObjectTestee.getPosition();
        Assert.assertEquals(TEST_START_X, position.getX());
        Assert.assertEquals(TEST_START_Y + 1, position.getY());
    }

    @Test
    public void move_undef_test() throws Exception {
        moveableObjectTestee.setCurrentDirection(Direction.UNDEF);
        Assert.assertEquals(Direction.RIGHT, moveableObjectTestee.getCurrentDirection());
    }

}