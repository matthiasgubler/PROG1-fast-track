package ch.mgubler.zhaw.objects;

import ch.mgubler.zhaw.SnakeGame;
import ch.mgubler.zhaw.SnakeScreen;
import ch.mgubler.zhaw.collision.CollisionDetector;
import ch.mgubler.zhaw.move.Position;
import ch.mgubler.zhaw.score.ScoreObserver;

public class FoodGenerator {

    private int foodCounter = 1;

    private SnakeGame snakeGame;

    private SnakeScreen snakeScreen;

    private ScoreObserver scoreObserver;

    private CollisionDetector collisionDetector;

    public FoodGenerator(SnakeGame snakeGame, SnakeScreen snakeScreen, ScoreObserver scoreObserver, CollisionDetector collisionDetector) {
        this.snakeGame = snakeGame;
        this.snakeScreen = snakeScreen;
        this.scoreObserver = scoreObserver;
        this.collisionDetector = collisionDetector;
    }

    public void generateFood() {
        Position position = new Position(15, 10);
        Food newFood = FoodFactory.generateFood(foodCounter, snakeGame, position, scoreObserver);
        snakeScreen.addGameElement(newFood);
        collisionDetector.addElementOnScreen(newFood);
        foodCounter++;
    }

    public int getFoodCounter() {
        return foodCounter;
    }
}
