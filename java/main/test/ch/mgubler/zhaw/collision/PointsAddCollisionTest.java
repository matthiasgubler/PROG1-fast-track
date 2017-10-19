package ch.mgubler.zhaw.collision;

import ch.mgubler.zhaw.SnakeGame;
import ch.mgubler.zhaw.SnakeScreen;
import ch.mgubler.zhaw.objects.Food;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PointsAddCollisionTest {

    private PointsAddCollision pointsAddCollisionTestee;

    @Mock
    private SnakeGame snakeGameMock;

    @Mock
    private Food collidingObjectMock;

    @Mock
    private SnakeScreen snakeScreenMock;

    @Before
    public void setUp(){
        pointsAddCollisionTestee = new PointsAddCollision(snakeGameMock, collidingObjectMock);
    }

    @Test
    public void collide() throws Exception {
        when(snakeGameMock.getGameSnakeScreen()).thenReturn(snakeScreenMock);
        pointsAddCollisionTestee.collide();
        verify(snakeScreenMock).removeGameElement(collidingObjectMock);

    }

}