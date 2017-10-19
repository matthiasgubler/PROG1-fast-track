package ch.mgubler.zhaw.collision;

import ch.mgubler.zhaw.SnakeGame;
import ch.mgubler.zhaw.objects.Food;

public class PointsAddCollision extends CollisionBehaviour {

    private Food collidingObject;

    public PointsAddCollision(SnakeGame snakeGame, Food collidingObject) {
        super(snakeGame, collidingObject);
        this.collidingObject = collidingObject;
    }

    @Override
    public void collide() {
        collidingObject.getFoodPointsObservable().notifyObserversScoreChange(collidingObject.getFoodPoints());
        snakeGame.getGameSnakeScreen().removeGameElement(collidingObject);
        //Todo FoodGenerator generateFood
    }
}
