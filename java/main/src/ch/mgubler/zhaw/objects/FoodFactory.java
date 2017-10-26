package ch.mgubler.zhaw.objects;

import ch.mgubler.zhaw.SnakeGame;
import ch.mgubler.zhaw.move.Position;
import ch.mgubler.zhaw.score.ScoreObserver;

public class FoodFactory {

    private FoodFactory(){
    }

    public static Food generateFood(int counter, SnakeGame snakeGame, Position position, ScoreObserver scoreObserver){
        if(counter % 5 == 0){
            //Every fifth Element is a Superfood
            return new SuperFood(snakeGame, position, scoreObserver);
        }
        return new Food(snakeGame, position, scoreObserver);
    }

}
