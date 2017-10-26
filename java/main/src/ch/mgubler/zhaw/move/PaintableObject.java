package ch.mgubler.zhaw.move;

import ch.mgubler.zhaw.collision.CollisionBehaviour;
import ch.mgubler.zhaw.collision.NoActionCollisionBehaviour;

public abstract class PaintableObject {

    private CollisionBehaviour collisionBehaviour;

    private final char symbol;

    private final Position position;

    public PaintableObject(char symbol, Position position) {
        this.collisionBehaviour = new NoActionCollisionBehaviour(null);
        this.symbol = symbol;
        this.position = position;
    }

    public char getSymbol() {
        return symbol;
    }

    public Position getPosition() {
        return position;
    }

    public CollisionBehaviour getCollisionBehaviour() {
        return collisionBehaviour;
    }

    protected void setCollisionBehaviour(CollisionBehaviour collisionBehaviour) {
        this.collisionBehaviour = collisionBehaviour;
    }
}
