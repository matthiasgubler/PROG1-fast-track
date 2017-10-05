package ch.mgubler.zhaw.objects;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static ch.mgubler.zhaw.objects.MoveableObjectTest.TEST_START_X;
import static ch.mgubler.zhaw.objects.MoveableObjectTest.TEST_START_Y;

public class MoveablePositionTest {

    private MoveablePosition testee;

    @Before
    public void setUp(){
        testee = new MoveablePosition(TEST_START_X, TEST_START_Y);
    }

    @Test
    public void moveLeft() throws Exception {
        Position newPosition = testee.moveLeft();
        Assert.assertEquals(TEST_START_X-1, newPosition.getX());
        Assert.assertEquals(TEST_START_Y, newPosition.getY());
    }

    @Test
    public void moveRight() throws Exception {
        Position newPosition = testee.moveRight();
        Assert.assertEquals(TEST_START_X+1, newPosition.getX());
        Assert.assertEquals(TEST_START_Y, newPosition.getY());
    }

    @Test
    public void moveUp() throws Exception {
        Position newPosition = testee.moveUp();
        Assert.assertEquals(TEST_START_X, newPosition.getX());
        Assert.assertEquals(TEST_START_Y-1, newPosition.getY());
    }

    @Test
    public void moveDown() throws Exception {
        Position newPosition = testee.moveDown();
        Assert.assertEquals(TEST_START_X, newPosition.getX());
        Assert.assertEquals(TEST_START_Y+1, newPosition.getY());
    }

}