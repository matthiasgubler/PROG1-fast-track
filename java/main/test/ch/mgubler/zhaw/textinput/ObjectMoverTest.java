package ch.mgubler.zhaw.textinput;

import ch.mgubler.zhaw.move.Direction;
import ch.mgubler.zhaw.move.MoveableObject;
import ch.mgubler.zhaw.move.ObjectMover;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.Terminal;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ObjectMoverTest {

    private ObjectMover objectMoverTestee;

    @Mock
    private MoveableObject moveableObjectMock;

    @Mock
    private Terminal terminalMock;

    @Before
    public void setUp() {
        objectMoverTestee = new ObjectMover(terminalMock, moveableObjectMock);
    }

    @Test
    public void pollDirectionChange() throws Exception {
        when(terminalMock.pollInput()).thenReturn(new KeyStroke('a', false, false));
        objectMoverTestee.pollDirectionChange();
        verify(terminalMock).pollInput();
        verify(moveableObjectMock).setCurrentDirection(Direction.LEFT);
    }

    @Test
    public void moveObject() throws Exception {
        objectMoverTestee.moveObject();
        verify(moveableObjectMock).move();
    }

}