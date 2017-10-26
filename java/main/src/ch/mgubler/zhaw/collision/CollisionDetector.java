package ch.mgubler.zhaw.collision;

import ch.mgubler.zhaw.SnakeGame;
import ch.mgubler.zhaw.move.PaintableObject;
import ch.mgubler.zhaw.objects.Snake;
import ch.mgubler.zhaw.objects.SnakeElement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CollisionDetector {

    private final Snake paintableObjectSnake;

    private final List<PaintableObject> elementsOnScreen = new ArrayList<>();

    public CollisionDetector(Snake paintableObjectSnake) {
        this.paintableObjectSnake = paintableObjectSnake;
    }

    public void addElementOnScreen(PaintableObject paintableObject) {
        this.elementsOnScreen.add(paintableObject);
    }

    public void detectCollision() {
        detectWallCollision();
        detectSelfCollision();
        detectObjectCollision();
    }

    private void detectSelfCollision() {
        List<SnakeElement> snakeElementList = paintableObjectSnake.getSnakeElements();
        for (SnakeElement snakeElement : snakeElementList) {
            if(paintableObjectSnake.getPosition().equals(snakeElement.getPosition())){
                snakeElement.getCollisionBehaviour().collide(paintableObjectSnake);
            }
        }
    }

    private void detectObjectCollision() {
        Iterator<PaintableObject> paintableObjectIterator = elementsOnScreen.iterator();

        while (paintableObjectIterator.hasNext()) {
            PaintableObject currentPaintableObject = paintableObjectIterator.next();

            if (paintableObjectSnake.getPosition().equals(currentPaintableObject.getPosition())) {
                paintableObjectIterator.remove();
                currentPaintableObject.getCollisionBehaviour().collide(paintableObjectSnake);
            }
        }
    }

    private void detectWallCollision() {
        boolean collidedRight = isCollidedRightWall();
        boolean collidedLeft = isCollidedLeftWall();
        boolean collidedTop = isCollidedTopWall();
        boolean collidedBottom = isCollidedBottomWall();

        if (collidedRight || collidedLeft || collidedTop || collidedBottom) {
            paintableObjectSnake.getCollisionBehaviour().collide(paintableObjectSnake);
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
