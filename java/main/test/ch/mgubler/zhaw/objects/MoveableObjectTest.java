package ch.mgubler.zhaw.objects;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MoveableObjectTest {

    public static final int TEST_START_X = 5;

    public static final int TEST_START_Y = 7;

    private MoveableObject moveableObject;

    @Before
    public void setUp(){
        moveableObject = new Snake(new MoveablePosition(TEST_START_X, TEST_START_Y));
    }

    @Test
    public void getPosition() throws Exception {
        Position position = moveableObject.getPosition();
        Assert.assertEquals(TEST_START_X, position.getX());
        Assert.assertEquals(TEST_START_Y, position.getY());
    }

    @Test
    public void move_default_test() throws Exception {
        Assert.assertEquals(Direction.RIGHT, moveableObject.getCurrentDirection());
        moveableObject.move();
        Position position = moveableObject.getPosition();
        Assert.assertEquals(TEST_START_X+1, position.getX());
        Assert.assertEquals(TEST_START_Y, position.getY());
    }

    @Test
    public void move_right_test() throws Exception {
        moveableObject.setCurrentDirection(Direction.RIGHT);
        moveableObject.move();
        Position position = moveableObject.getPosition();
        Assert.assertEquals(TEST_START_X+1, position.getX());
        Assert.assertEquals(TEST_START_Y, position.getY());
    }

    @Test
    public void move_left_test() throws Exception {
        moveableObject.setCurrentDirection(Direction.LEFT);
        moveableObject.move();
        Position position = moveableObject.getPosition();
        Assert.assertEquals(TEST_START_X-1, position.getX());
        Assert.assertEquals(TEST_START_Y, position.getY());
    }

    @Test
    public void move_up_test() throws Exception {
        moveableObject.setCurrentDirection(Direction.UP);
        moveableObject.move();
        Position position = moveableObject.getPosition();
        Assert.assertEquals(TEST_START_X, position.getX());
        Assert.assertEquals(TEST_START_Y-1, position.getY());
    }

    @Test
    public void move_down_test() throws Exception {
        moveableObject.setCurrentDirection(Direction.DOWN);
        moveableObject.move();
        Position position = moveableObject.getPosition();
        Assert.assertEquals(TEST_START_X, position.getX());
        Assert.assertEquals(TEST_START_Y+1, position.getY());
    }

    @Test
    public void move_undef_test() throws Exception {
        moveableObject.setCurrentDirection(Direction.UNDEF);
        Assert.assertEquals(Direction.RIGHT, moveableObject.getCurrentDirection());
    }

}