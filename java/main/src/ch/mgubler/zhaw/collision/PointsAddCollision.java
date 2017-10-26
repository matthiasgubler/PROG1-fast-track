package ch.mgubler.zhaw.collision;

import ch.mgubler.zhaw.SnakeGame;
import ch.mgubler.zhaw.objects.Food;
import ch.mgubler.zhaw.objects.Snake;

public class PointsAddCollision extends CollisionBehaviour {

    private final Food collidingObject;

    public PointsAddCollision(SnakeGame snakeGame, Food collidingObject) {
        super(snakeGame);
        this.collidingObject = collidingObject;
    }

    @Override
    public void collide(Snake collider) {
        collidingObject.getFoodPointsObservable().notifyObserversScoreChange(collidingObject.getFoodPoints());
        snakeGame.getGameSnakeScreen().removeGameElement(collidingObject);
        snakeGame.generateFood();
        collider.grow();
    }
}
