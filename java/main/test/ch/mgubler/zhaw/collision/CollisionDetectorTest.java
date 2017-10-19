package ch.mgubler.zhaw.collision;

import ch.mgubler.zhaw.SnakeGame;
import ch.mgubler.zhaw.SnakeScreen;
import ch.mgubler.zhaw.move.PaintableObject;
import ch.mgubler.zhaw.move.Position;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.verification.VerificationMode;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CollisionDetectorTest {

    private CollisionDetector collisionDetectorTestee;

    @Mock
    private SnakeScreen snakeScreenMock;

    @Mock
    private PaintableObject snakeObjectMock;

    @Mock
    private CollisionBehaviour collisionBehaviourMock;

    @Mock
    private PaintableObject collideElement;

    @Before
    public void setUp(){
        collisionDetectorTestee = new CollisionDetector(snakeScreenMock, snakeObjectMock);
    }

    @Test
    public void detectCollision_no_collision() throws Exception {
        verifyCollision(10, 10, never());

    }

    @Test
    public void detectCollision_wall_left_collision() throws Exception {
        verifyCollision(0, 10, times(1));

    }

    @Test
    public void detectCollision_wall_right_collision() throws Exception {
        verifyCollision(SnakeGame.SCREEN_WIDTH, 10, times(1));

    }

    @Test
    public void detectCollision_wall_top_collision() throws Exception {
        verifyCollision(10, 0, times(1));
    }

    @Test
    public void detectCollision_wall_bottom_collision() throws Exception {
        verifyCollision(10, SnakeGame.SCREEN_HEIGHT, times(1));
    }

    @Test
    public void detectCollision_object_on_screen_collision() throws Exception {
        collisionDetectorTestee.addElementOnScreen(collideElement);
        when(collideElement.getCollisionBehaviour()).thenReturn(collisionBehaviourMock);
        when(collideElement.getPosition()).thenReturn(new Position(10, 10));

        verifyCollision(10, 10, times(1));
    }

    @Test
    public void detectCollision_object_on_screen_no_collision() throws Exception {
        collisionDetectorTestee.addElementOnScreen(collideElement);
        when(collideElement.getPosition()).thenReturn(new Position(11, 10));

        verifyCollision(10, 10, never());
    }

    private void verifyCollision(int x, int y, VerificationMode verificationMode) {
        prepareSnakePosition(x, y);
        collisionDetectorTestee.detectCollision();
        verify(collisionBehaviourMock, verificationMode).collide();
    }

    private void prepareSnakePosition(int x, int y) {
        when(snakeObjectMock.getPosition()).thenReturn(new Position(x, y));
        when(snakeObjectMock.getCollisionBehaviour()).thenReturn(collisionBehaviourMock);
    }

}