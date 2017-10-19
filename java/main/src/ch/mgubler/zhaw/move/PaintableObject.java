package ch.mgubler.zhaw.move;

import ch.mgubler.zhaw.collision.CollisionBehaviour;
import ch.mgubler.zhaw.collision.NoActionCollisionBehaviour;

public abstract class PaintableObject {

    private CollisionBehaviour collisionBehaviour;

    private char symbol;

    private Position position;

    public PaintableObject(char symbol, Position position) {
        this.collisionBehaviour = new NoActionCollisionBehaviour(null, this);
        this.symbol = symbol;
        this.position = position;
    }

    public PaintableObject(CollisionBehaviour collisionBehaviour, char symbol, Position position) {
        this.collisionBehaviour = collisionBehaviour;
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

    public void setCollisionBehaviour(CollisionBehaviour collisionBehaviour) {
        this.collisionBehaviour = collisionBehaviour;
    }
}
