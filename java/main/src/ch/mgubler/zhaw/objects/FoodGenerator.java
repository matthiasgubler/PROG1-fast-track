package ch.mgubler.zhaw.objects;

import ch.mgubler.zhaw.SnakeGame;
import ch.mgubler.zhaw.SnakeScreen;
import ch.mgubler.zhaw.collision.CollisionDetector;
import ch.mgubler.zhaw.move.Position;
import ch.mgubler.zhaw.move.PositionHelper;
import ch.mgubler.zhaw.score.ScoreObserver;

public class FoodGenerator {

    private int foodCounter = 1;

    private final SnakeGame snakeGame;

    private final SnakeScreen snakeScreen;

    private final ScoreObserver scoreObserver;

    private final CollisionDetector collisionDetector;

    private final PositionHelper positionHelper = new PositionHelper();

    public FoodGenerator(SnakeGame snakeGame, SnakeScreen snakeScreen, ScoreObserver scoreObserver, CollisionDetector collisionDetector) {
        this.snakeGame = snakeGame;
        this.snakeScreen = snakeScreen;
        this.scoreObserver = scoreObserver;
        this.collisionDetector = collisionDetector;
    }

    public void generateFood() {
        Position position = positionHelper.generateFreePosition(snakeScreen.getOccupiedPositions());

        Food newFood = FoodFactory.generateFood(foodCounter, snakeGame, position, scoreObserver);
        snakeScreen.addGameElement(newFood);
        collisionDetector.addElementOnScreen(newFood);
        foodCounter++;
    }

    public int getFoodCounter() {
        return foodCounter;
    }
}
