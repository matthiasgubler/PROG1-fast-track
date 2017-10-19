package ch.mgubler.zhaw.collision;

import ch.mgubler.zhaw.SnakeGame;
import ch.mgubler.zhaw.move.PaintableObject;

public abstract class CollisionBehaviour {

    protected SnakeGame snakeGame;

    protected PaintableObject collidingObject;

    public CollisionBehaviour(SnakeGame snakeGame, PaintableObject collidingObject) {
        this.snakeGame = snakeGame;
        this.collidingObject = collidingObject;
    }

    public abstract void collide();

    public SnakeGame getSnakeGame() {
        return snakeGame;
    }
}
