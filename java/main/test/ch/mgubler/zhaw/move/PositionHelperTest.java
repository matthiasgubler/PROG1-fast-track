package ch.mgubler.zhaw.move;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static ch.mgubler.zhaw.SnakeGame.SCREEN_HEIGHT;
import static ch.mgubler.zhaw.SnakeGame.SCREEN_WIDTH;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PositionHelperTest {

    private PositionHelper positionHelperTestee;

    @Mock
    private Random randomMock;

    @Before
    public void setUp() throws Exception{
        positionHelperTestee = new PositionHelper();
        positionHelperTestee.setRandom(randomMock);
    }

    @Test
    public void isPositionOccupied_is_occupied() throws Exception {
        List<Position> occupiedPositions = Arrays.asList(new Position(1,2), new Position(10,10));
        boolean result = PositionHelper.isPositionOccupied(occupiedPositions, new Position(1,2));
        assertTrue(result);
    }

    @Test
    public void isPositionOccupied_is_free() throws Exception {
        List<Position> occupiedPositions = Arrays.asList(new Position(1,2), new Position(10,10));
        boolean result = PositionHelper.isPositionOccupied(occupiedPositions, new Position(5,2));
        assertFalse(result);
    }

    @Test
    public void generateFreePosition_called_once() throws Exception {
        when(randomMock.nextInt(SCREEN_WIDTH - 1)).thenReturn(2);
        when(randomMock.nextInt(SCREEN_HEIGHT - 1)).thenReturn(3);

        List<Position> occupiedPositions = Arrays.asList(new Position(1,2), new Position(10,10));
        Position resultPosition = positionHelperTestee.generateFreePosition(occupiedPositions);

        assertEquals(new Position(3,4), resultPosition);
    }

    @Test
    public void generateFreePosition_test_recursion() throws Exception {
        when(randomMock.nextInt(SCREEN_WIDTH - 1)).thenReturn(0, 2);
        when(randomMock.nextInt(SCREEN_HEIGHT - 1)).thenReturn(1, 3);

        List<Position> occupiedPositions = Arrays.asList(new Position(1,2), new Position(10,10));
        Position resultPosition = positionHelperTestee.generateFreePosition(occupiedPositions);

        assertEquals(new Position(3,4), resultPosition);
    }

}