package ch.mgubler.zhaw.collision;

import ch.mgubler.zhaw.SnakeGame;
import ch.mgubler.zhaw.objects.Snake;

public abstract class CollisionBehaviour {

    protected final SnakeGame snakeGame;

    public CollisionBehaviour(SnakeGame snakeGame) {
        this.snakeGame = snakeGame;
    }

    public abstract void collide(Snake collider);

    public SnakeGame getSnakeGame() {
        return snakeGame;
    }
}
