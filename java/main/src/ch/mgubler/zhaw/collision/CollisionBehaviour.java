package ch.mgubler.zhaw.collision;

import ch.mgubler.zhaw.SnakeGame;

public abstract class CollisionBehaviour {

    protected SnakeGame snakeGame;

    public CollisionBehaviour(SnakeGame snakeGame) {
        this.snakeGame = snakeGame;
    }

    public abstract void collide();

    public SnakeGame getSnakeGame() {
        return snakeGame;
    }
}
