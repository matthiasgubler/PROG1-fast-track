package ch.mgubler.zhaw.objects;

public abstract class MoveableObject extends PaintableObject {

    private MoveablePosition position;

    //Default start direction is Right
    private Direction currentDirection = Direction.RIGHT;

    public MoveableObject(char symbol, MoveablePosition position) {
        super(symbol, position);
        this.position = position;
    }

    @Override
    public MoveablePosition getPosition() {
        return position;
    }

    private Position setAndGetNewPosition(MoveablePosition newPosition){
        this.position = newPosition;
        return position;
    }

    public void setCurrentDirection(Direction currentDirection) {
        switch (currentDirection) {
            case UP:
            case LEFT:
            case DOWN:
            case RIGHT:
                this.currentDirection = currentDirection;
            case UNDEF:
            default:
                //We don't have to change any direction
        }
    }

    public Position move(){
        switch (currentDirection) {
            case UP:
                return moveUp();
            case LEFT:
                return moveLeft();
            case RIGHT:
                return moveRight();
            case DOWN:
                return moveDown();
            case UNDEF:
            default:
                return position;
        }
    }

    private Position moveLeft(){
        return setAndGetNewPosition(position.moveLeft());
    }

    private Position moveRight(){
        return setAndGetNewPosition(position.moveRight());
    }

    private Position moveUp(){
        return setAndGetNewPosition(position.moveUp());
    }

    private Position moveDown(){
        return setAndGetNewPosition(position.moveDown());
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }
}
