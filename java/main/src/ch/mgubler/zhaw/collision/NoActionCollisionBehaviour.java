package ch.mgubler.zhaw.collision;

import ch.mgubler.zhaw.SnakeGame;
import ch.mgubler.zhaw.objects.Snake;

public class NoActionCollisionBehaviour extends CollisionBehaviour {

    public NoActionCollisionBehaviour(SnakeGame snakeGame) {
        super(snakeGame);
    }

    @Override
    public void collide(Snake collider) {
        //Nothing to do here
    }
}
