package ch.mgubler.zhaw.collision;

import ch.mgubler.zhaw.SnakeGame;
import ch.mgubler.zhaw.move.PaintableObject;

public class PointsAddCollision extends CollisionBehaviour {

    public PointsAddCollision(SnakeGame snakeGame, PaintableObject collidingObject) {
        super(snakeGame, collidingObject);
    }

    @Override
    public void collide() {
        //TODO Implement points to add and remove
        System.out.println("Collided");
        snakeGame.getGameSnakeScreen().removeGameElement(collidingObject);
    }
}
