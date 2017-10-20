package ch.mgubler.zhaw.move;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PositionHelperTest {

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

}