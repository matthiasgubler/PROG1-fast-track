package ch.mgubler.zhaw.objects;

import ch.mgubler.zhaw.SnakeGame;
import ch.mgubler.zhaw.collision.GameOverCollision;
import ch.mgubler.zhaw.move.MoveableObject;
import ch.mgubler.zhaw.move.MoveablePosition;

import java.util.ArrayList;
import java.util.List;

public class Snake extends MoveableObject {

    public static final char SNAKE_SYMBOL = '@';

    private List<SnakeElement> snakeElements = new ArrayList<>();

    public Snake(SnakeGame snakeGame, MoveablePosition position) {
        super(new GameOverCollision(snakeGame), SNAKE_SYMBOL, position);
    }

    public void addElement(SnakeElement snakeElement) {
        snakeElements.add(snakeElement);
    }

    public void getElementAtIndex(int i) {
        snakeElements.get(i);
    }
}
