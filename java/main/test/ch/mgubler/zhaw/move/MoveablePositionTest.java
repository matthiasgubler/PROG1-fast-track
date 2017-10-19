package ch.mgubler.zhaw.move;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static ch.mgubler.zhaw.move.MoveableObjectTest.TEST_START_X;
import static ch.mgubler.zhaw.move.MoveableObjectTest.TEST_START_Y;

public class MoveablePositionTest {

    private MoveablePosition moveablePositionTestee;

    @Before
    public void setUp(){
        moveablePositionTestee = new MoveablePosition(TEST_START_X, TEST_START_Y);
    }

    @Test
    public void moveLeft() throws Exception {
        Position newPosition = moveablePositionTestee.moveLeft();
        Assert.assertEquals(TEST_START_X-1, newPosition.getX());
        Assert.assertEquals(TEST_START_Y, newPosition.getY());
    }

    @Test
    public void moveRight() throws Exception {
        Position newPosition = moveablePositionTestee.moveRight();
        Assert.assertEquals(TEST_START_X+1, newPosition.getX());
        Assert.assertEquals(TEST_START_Y, newPosition.getY());
    }

    @Test
    public void moveUp() throws Exception {
        Position newPosition = moveablePositionTestee.moveUp();
        Assert.assertEquals(TEST_START_X, newPosition.getX());
        Assert.assertEquals(TEST_START_Y-1, newPosition.getY());
    }

    @Test
    public void moveDown() throws Exception {
        Position newPosition = moveablePositionTestee.moveDown();
        Assert.assertEquals(TEST_START_X, newPosition.getX());
        Assert.assertEquals(TEST_START_Y+1, newPosition.getY());
    }

}