package ch.mgubler.zhaw.collision;

import ch.mgubler.zhaw.SnakeGame;

public class GameOverCollision extends CollisionBehaviour {


    public GameOverCollision(SnakeGame snakeGame) {
        super(snakeGame);
    }

    @Override
    public void collide() {
        snakeGame.gameOver();
    }
}
