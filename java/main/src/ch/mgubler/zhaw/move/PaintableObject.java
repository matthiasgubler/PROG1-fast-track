package ch.mgubler.zhaw.move;

import ch.mgubler.zhaw.collision.CollisionBehaviour;

public abstract class PaintableObject {

    private CollisionBehaviour collisionBehaviour;

    private char symbol;

    private Position position;

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
}
