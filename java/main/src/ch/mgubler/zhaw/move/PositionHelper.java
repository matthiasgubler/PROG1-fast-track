package ch.mgubler.zhaw.move;

import ch.mgubler.zhaw.SnakeGame;

import java.util.List;
import java.util.Random;

public class PositionHelper {

    private Random random = new Random();

    public Position generateFreePosition(List<Position> occupiedPositions){
        int randomX = random.nextInt(SnakeGame.SCREEN_WIDTH - 1) + 1;
        int randomY = random.nextInt(SnakeGame.SCREEN_HEIGHT - 1) + 1;
        Position randomPosition = new Position(randomX, randomY);

        if(isPositionOccupied(occupiedPositions, randomPosition)){
            //Recursion!
            return generateFreePosition(occupiedPositions);
        }

        return randomPosition;
    }

    public static boolean isPositionOccupied(List<Position> occupiedPositions, Position positionToCheck) {
        return occupiedPositions.stream().filter(occupiedPosition -> occupiedPosition.equals(positionToCheck)).findAny().isPresent();
    }

}
