package ch.mgubler.zhaw.objects;

import ch.mgubler.zhaw.SnakeGame;
import ch.mgubler.zhaw.move.Position;
import ch.mgubler.zhaw.score.ScoreObserver;

public class SuperFood extends Food {

    private static final char SUPER_FOOD_SYMBOL = '#';

    private static final int SUPER_FOOD_POINTS = 20;

    public SuperFood(SnakeGame snakeGame, Position position, ScoreObserver scoreObserver) {
        super(snakeGame, SUPER_FOOD_SYMBOL, position, SUPER_FOOD_POINTS, scoreObserver);
    }


}
