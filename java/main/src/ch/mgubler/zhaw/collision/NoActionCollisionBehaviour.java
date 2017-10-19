package ch.mgubler.zhaw.collision;

import ch.mgubler.zhaw.SnakeGame;
import ch.mgubler.zhaw.move.PaintableObject;

public class NoActionCollisionBehaviour extends CollisionBehaviour {

    public NoActionCollisionBehaviour(SnakeGame snakeGame, PaintableObject paintableObject) {
        super(snakeGame, paintableObject);
    }

    @Override
    public void collide() {
        //Nothing to do here
    }
}
