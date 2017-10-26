package ch.mgubler.zhaw.collision;

import ch.mgubler.zhaw.SnakeGame;
import ch.mgubler.zhaw.objects.Snake;

public class GameOverCollision extends CollisionBehaviour {

    public GameOverCollision(SnakeGame snakeGame) {
        super(snakeGame);
    }

    @Override
    public void collide(Snake collider) {
        snakeGame.gameOver();
    }
}
