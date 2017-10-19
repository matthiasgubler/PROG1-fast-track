package ch.mgubler.zhaw.objects;

import ch.mgubler.zhaw.SnakeGame;
import ch.mgubler.zhaw.collision.PointsAddCollision;
import ch.mgubler.zhaw.move.PaintableObject;
import ch.mgubler.zhaw.move.Position;

public class Food extends PaintableObject {

    public static final char FOOD_SYMBOL = '*';

    public static final int FOOD_POINTS = 5;

    private int foodPoints = FOOD_POINTS;

    public Food(SnakeGame snakeGame, Position position) {
        this(snakeGame, FOOD_SYMBOL, position, FOOD_POINTS);
    }

    protected Food(SnakeGame snakeGame, char symbol, Position position, int foodPoints) {
        super(symbol, position);
        setCollisionBehaviour(new PointsAddCollision(snakeGame, this));
        this.foodPoints = foodPoints;
    }

    public int getFoodPoints() {
        return foodPoints;
    }
}
