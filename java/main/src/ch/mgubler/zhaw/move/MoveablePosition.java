package ch.mgubler.zhaw.move;

public class MoveablePosition extends Position {

    public MoveablePosition(int x, int y) {
        super(x, y);
    }

    public MoveablePosition moveLeft() {
        return new MoveablePosition(getX() - 1, getY());
    }

    public MoveablePosition moveRight() {
        return new MoveablePosition(getX() + 1, getY());
    }

    public MoveablePosition moveUp() {
        return new MoveablePosition(getX(), getY() - 1);
    }

    public MoveablePosition moveDown() {
        return new MoveablePosition(getX(), getY() + 1);
    }

}
