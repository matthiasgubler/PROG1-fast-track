package ch.mgubler.zhaw;

import ch.mgubler.zhaw.move.Direction;
import ch.mgubler.zhaw.move.MoveablePosition;
import ch.mgubler.zhaw.move.PaintableObject;
import ch.mgubler.zhaw.move.Position;
import ch.mgubler.zhaw.objects.Snake;
import ch.mgubler.zhaw.score.GameScore;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.IntStream;

import static ch.mgubler.zhaw.SnakeGame.SCREEN_HEIGHT;
import static ch.mgubler.zhaw.SnakeGame.SCREEN_WIDTH;
import static ch.mgubler.zhaw.SnakeScreen.*;
import static ch.mgubler.zhaw.move.MoveableObjectTest.TEST_START_X;
import static ch.mgubler.zhaw.move.MoveableObjectTest.TEST_START_Y;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SnakeScreenTest {

    private static final int WALL_COUNT = 2;

    private static final BinaryOperator<String> STRING_CONCATENATOR = (s, s2) -> s + s2;

    private static final String WALL_TOP_BOTTOM = IntStream.range(0, SnakeGame.SCREEN_WIDTH).mapToObj(counter -> String.valueOf(WALL_CHAR)).reduce(STRING_CONCATENATOR).get();

    private static final int FIELDS_COUNT = SnakeGame.SCREEN_WIDTH - WALL_COUNT;

    private static final int ROWS_COUNT = SCREEN_HEIGHT - WALL_COUNT;

    private static final String LINE_CONTENT = IntStream.range(0, FIELDS_COUNT).mapToObj(counter -> String.valueOf(BLANK_FIELD)).reduce(STRING_CONCATENATOR).get();

    private static final String LINE_CONTENT_WITH_WALLS = WALL_CHAR + LINE_CONTENT + WALL_CHAR;

    private static final char[] WALL_TOP_BOTTOM_CHAR_ARRAY = WALL_TOP_BOTTOM.toCharArray();

    private static final char[] LINE_CONTENT_WITH_WALLS_CHAR_ARRAY = LINE_CONTENT_WITH_WALLS.toCharArray();

    private static final char[][] INIT_SCREEN_EXPECTED = new char[SCREEN_HEIGHT][SnakeGame.SCREEN_WIDTH];

    private static final int OTHER_CHARACTERS_ON_SCREEN = 8;

    static {
        //Init Expected Game Matrix with Walls on Top/Bottom and the regular Fieldcontent
        INIT_SCREEN_EXPECTED[0] = WALL_TOP_BOTTOM_CHAR_ARRAY;
        for (int arraysCounter = 1; arraysCounter <= ROWS_COUNT; arraysCounter++) {
            INIT_SCREEN_EXPECTED[arraysCounter] = LINE_CONTENT_WITH_WALLS_CHAR_ARRAY;
        }
        INIT_SCREEN_EXPECTED[SCREEN_HEIGHT - 1] = WALL_TOP_BOTTOM_CHAR_ARRAY;
    }

    private final static int COUNT_WALL_ELEMENTS = (SnakeGame.SCREEN_WIDTH * SnakeGame.SCREEN_HEIGHT) - (SnakeGame.SCREEN_WIDTH - 2) * (SnakeGame.SCREEN_HEIGHT - 2);

    private SnakeScreen snakeScreenTestee;

    @Mock
    private Screen screenMock;

    @Mock
    private SnakeGame snakeGameMock;

    @Mock
    private GameScore gameScoreMock;

    private Snake snake;

    @Before
    public void setUp() {
        snake = new Snake(snakeGameMock, new MoveablePosition(TEST_START_X, TEST_START_Y));
        snakeScreenTestee = new SnakeScreen(SCREEN_HEIGHT, SCREEN_WIDTH, screenMock, snake, gameScoreMock);
        snakeScreenTestee.init();
    }

    @Test
    public void init_test() throws Exception {
        Assert.assertArrayEquals(INIT_SCREEN_EXPECTED, snakeScreenTestee.getScreenMatrix());
    }

    @Test
    public void paintScreen_after_init_test() throws Exception {
        snakeScreenTestee.paintScreen();
        verify(screenMock, times(1)).clear();
        verify(screenMock, times(1)).refresh();
        verify(screenMock, times((SCREEN_HEIGHT * SCREEN_WIDTH) + OTHER_CHARACTERS_ON_SCREEN)).setCharacter(any(TerminalPosition.class), any(TextCharacter.class));
        assertEquals(new Character(snake.getSymbol()), snakeScreenTestee.getScreenMatrix()[TEST_START_Y][TEST_START_X]);
    }

    @Test
    public void paintScreen_move_snake_test() throws Exception {
        snake.setCurrentDirection(Direction.RIGHT);
        snakeScreenTestee.paintScreen();
        assertEquals(new Character(snake.getSymbol()), snakeScreenTestee.getScreenMatrix()[TEST_START_Y][TEST_START_X]);
        snake.move();
        snakeScreenTestee.paintScreen();
        assertEquals(new Character(snake.getSymbol()), snakeScreenTestee.getScreenMatrix()[TEST_START_Y][TEST_START_X + 1]);
        snake.move();
        snakeScreenTestee.paintScreen();
        assertEquals(new Character(snake.getSymbol()), snakeScreenTestee.getScreenMatrix()[TEST_START_Y][TEST_START_X + 2]);
        snake.setCurrentDirection(Direction.DOWN);
        snake.move();
        snakeScreenTestee.paintScreen();
        assertEquals(new Character(snake.getSymbol()), snakeScreenTestee.getScreenMatrix()[TEST_START_Y + 1][TEST_START_X + 2]);
    }

    @Test
    public void setInfoTextOnScreen_paint() throws Exception {
        snakeScreenTestee.setInfoTextOnScreen("Hello");
        snakeScreenTestee.paintScreen();

        verify(screenMock).setCharacter(eq(new TerminalPosition(INFO_TEXT_START_COLUMN, INFO_TEXT_ROW)), eq(new TextCharacter('H')));
        verify(screenMock).setCharacter(eq(new TerminalPosition(INFO_TEXT_START_COLUMN + 1, INFO_TEXT_ROW)), eq(new TextCharacter('e')));
        verify(screenMock).setCharacter(eq(new TerminalPosition(INFO_TEXT_START_COLUMN + 2, INFO_TEXT_ROW)), eq(new TextCharacter('l')));
        verify(screenMock).setCharacter(eq(new TerminalPosition(INFO_TEXT_START_COLUMN + 3, INFO_TEXT_ROW)), eq(new TextCharacter('l')));
        verify(screenMock).setCharacter(eq(new TerminalPosition(INFO_TEXT_START_COLUMN + 4, INFO_TEXT_ROW)), eq(new TextCharacter('o')));
        verify(screenMock).refresh();
    }

    @Test
    public void writeInfoTextOnScreen() throws Exception {
        snakeScreenTestee.writeInfoTextOnScreen();
        verify(screenMock, times(8)).setCharacter(any(), any());
        verify(screenMock).refresh();
    }

    @Test
    public void getOccupiedPositions_only_walls() throws Exception {
        List<Position> occupiedPositions = snakeScreenTestee.getOccupiedPositions();
        assertEquals(COUNT_WALL_ELEMENTS, occupiedPositions.size());
    }

    @Test
    public void getOccupiedPositions_with_one_element() throws Exception {
        PaintableObject paintableObjectMock = mock(PaintableObject.class);
        when(paintableObjectMock.getPosition()).thenReturn(new Position(7, 7));
        snakeScreenTestee.setElementOnScreen(paintableObjectMock);

        List<Position> occupiedPositions = snakeScreenTestee.getOccupiedPositions();
        assertEquals(COUNT_WALL_ELEMENTS + 1, occupiedPositions.size());
    }

}
