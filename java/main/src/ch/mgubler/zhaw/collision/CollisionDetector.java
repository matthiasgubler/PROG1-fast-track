package ch.mgubler.zhaw.collision;

import ch.mgubler.zhaw.SnakeGame;
import ch.mgubler.zhaw.SnakeScreen;
import ch.mgubler.zhaw.move.PaintableObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CollisionDetector {

    private final SnakeScreen snakeScreen;

    private final PaintableObject paintableObjectSnake;

    public List<PaintableObject> elementsOnScreen = new ArrayList<>();

    public CollisionDetector(SnakeScreen snakeScreen, PaintableObject paintableObjectSnake) {
        this.snakeScreen = snakeScreen;
        this.paintableObjectSnake = paintableObjectSnake;
    }

    public void addElementOnScreen(PaintableObject paintableObject) {
        this.elementsOnScreen.add(paintableObject);
    }

    public void detectCollision() {
        detectWallCollision();
        //TODO Detect Snake "self" collision
        //TODO Detect Object collision
        detectObjectCollision();
    }

    private void detectObjectCollision() {
        Iterator<PaintableObject> paintableObjectIterator = elementsOnScreen.iterator();

        while (paintableObjectIterator.hasNext()){
            PaintableObject currentPaintableObject = paintableObjectIterator.next();

            if(paintableObjectSnake.getPosition().equals(currentPaintableObject.getPosition()))
            {
                currentPaintableObject.getCollisionBehaviour().collide();
                paintableObjectIterator.remove();
            }
        }
    }

    private void detectWallCollision() {
        boolean collidedRight = isCollidedRightWall();
        boolean collidedLeft = isCollidedLeftWall();
        boolean collidedTop = isCollidedTopWall();
        boolean collidedBottom = isCollidedBottomWall();

        if (collidedRight || collidedLeft || collidedTop || collidedBottom) {
            paintableObjectSnake.getCollisionBehaviour().collide();
        }
    }

    private boolean isCollidedRightWall() {
        return (paintableObjectSnake.getPosition().getX() >= SnakeGame.SCREEN_WIDTH - 1);
    }

    private boolean isCollidedLeftWall() {
        return (paintableObjectSnake.getPosition().getX() <= 0);
    }

    private boolean isCollidedTopWall() {
        return (paintableObjectSnake.getPosition().getY() <= 0);
    }

    private boolean isCollidedBottomWall() {
        return (paintableObjectSnake.getPosition().getY() >= SnakeGame.SCREEN_HEIGHT - 1);
    }

}
