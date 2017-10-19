package ch.mgubler.zhaw.collision;

import ch.mgubler.zhaw.SnakeGame;
import ch.mgubler.zhaw.move.PaintableObject;

public class GameOverCollision extends CollisionBehaviour {


    public GameOverCollision(SnakeGame snakeGame, PaintableObject paintableObject) {
        super(snakeGame, paintableObject);
    }

    @Override
    public void collide() {
        snakeGame.gameOver();
    }
}
