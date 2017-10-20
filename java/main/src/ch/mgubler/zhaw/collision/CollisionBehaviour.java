package ch.mgubler.zhaw.collision;

import ch.mgubler.zhaw.SnakeGame;
import ch.mgubler.zhaw.move.PaintableObject;
import ch.mgubler.zhaw.objects.Snake;

public abstract class CollisionBehaviour {

    protected SnakeGame snakeGame;

    protected PaintableObject collidingObject;

    public CollisionBehaviour(SnakeGame snakeGame, PaintableObject collidingObject) {
        this.snakeGame = snakeGame;
        this.collidingObject = collidingObject;
    }

    public abstract void collide(Snake collider);

    public SnakeGame getSnakeGame() {
        return snakeGame;
    }
}
