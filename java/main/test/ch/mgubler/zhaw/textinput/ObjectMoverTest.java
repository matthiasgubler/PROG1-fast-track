package ch.mgubler.zhaw.textinput;

import ch.mgubler.zhaw.objects.Direction;
import ch.mgubler.zhaw.objects.MoveableObject;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.Terminal;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ObjectMoverTest {

    private ObjectMover objectMover;

    @Mock
    private MoveableObject moveableObject;

    @Mock
    private Terminal terminal;

    @Before
    public void setUp() {
        objectMover = new ObjectMover(terminal, moveableObject);
    }

    @Test
    public void pollDirectionChange() throws Exception {
        when(terminal.pollInput()).thenReturn(new KeyStroke('a', false, false));
        objectMover.pollDirectionChange();
        verify(terminal, times(1)).pollInput();
        verify(moveableObject, times(1)).setCurrentDirection(Direction.LEFT);
    }

    @Test
    public void moveObject() throws Exception {
        objectMover.moveObject();
        verify(moveableObject, times(1)).move();
    }

}