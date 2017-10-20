package ch.mgubler.zhaw.objects;

import ch.mgubler.zhaw.SnakeGame;
import ch.mgubler.zhaw.collision.GameOverCollision;
import ch.mgubler.zhaw.move.MoveableObject;
import ch.mgubler.zhaw.move.MoveablePosition;
import ch.mgubler.zhaw.move.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Snake extends MoveableObject {

    public static final char SNAKE_SYMBOL = '@';

    private List<SnakeElement> snakeElements = new ArrayList<>();

    private SnakeGame snakeGame;

    public Snake(SnakeGame snakeGame, MoveablePosition position) {
        super(SNAKE_SYMBOL, position);
        setCollisionBehaviour(new GameOverCollision(snakeGame, this));
        this.snakeGame = snakeGame;
    }

    @Override
    public Position move() {
        MoveablePosition currentHeadPosition = getPosition();
        MoveablePosition lastElementPosition = currentHeadPosition;

        for (SnakeElement snakeElement : snakeElements) {
            MoveablePosition currentPosition = snakeElement.getPosition();
            snakeElement.setAndGetNewPosition(lastElementPosition);
            lastElementPosition = currentPosition;
        }
        return super.move();
    }

    public void addElement(SnakeElement snakeElement) {
        snakeElements.add(snakeElement);
    }

    public SnakeElement getElementAtIndex(int i) {
        return snakeElements.get(i);
    }

    private Snake getLastElement() {
        if (snakeElements.isEmpty()) {
            return this;
        }
        return snakeElements.get(snakeElements.size() - 1);
    }

    public List<SnakeElement> getSnakeElements() {
        return snakeElements;
    }

    public void grow(int growTimes) {
        IntStream.range(0, growTimes).forEach(i -> {
            this.grow();
        });
    }

    public void grow() {
        SnakeElement snakeElement = new SnakeElement(snakeGame, this.getLastElement().getPosition());
        this.addElement(snakeElement);
    }
}
