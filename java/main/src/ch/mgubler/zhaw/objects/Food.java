package ch.mgubler.zhaw.objects;

import ch.mgubler.zhaw.SnakeGame;
import ch.mgubler.zhaw.collision.PointsAddCollision;
import ch.mgubler.zhaw.move.PaintableObject;
import ch.mgubler.zhaw.move.Position;
import ch.mgubler.zhaw.score.FoodPointsObservable;
import ch.mgubler.zhaw.score.ScoreObserver;

public class Food extends PaintableObject {

    private static final char FOOD_SYMBOL = '*';

    private static final int FOOD_POINTS = 5;

    private int foodPoints = FOOD_POINTS;

    private final FoodPointsObservable foodPointsObservable;

    public Food(SnakeGame snakeGame, Position position, ScoreObserver scoreObserver) {
        this(snakeGame, FOOD_SYMBOL, position, FOOD_POINTS, scoreObserver);
    }

    protected Food(SnakeGame snakeGame, char symbol, Position position, int foodPoints, ScoreObserver scoreObserver) {
        super(symbol, position);
        setCollisionBehaviour(new PointsAddCollision(snakeGame, this));
        this.foodPoints = foodPoints;
        this.foodPointsObservable = new FoodPointsObservable(scoreObserver);
    }

    public int getFoodPoints() {
        return foodPoints;
    }

    public FoodPointsObservable getFoodPointsObservable() {
        return foodPointsObservable;
    }
}
