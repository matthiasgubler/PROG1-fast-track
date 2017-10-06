package ch.mgubler.zhaw;

import ch.mgubler.zhaw.move.Direction;
import ch.mgubler.zhaw.move.MoveablePosition;
import ch.mgubler.zhaw.objects.Snake;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.function.BinaryOperator;
import java.util.stream.IntStream;

import static ch.mgubler.zhaw.SnakeGame.SCREEN_HEIGHT;
import static ch.mgubler.zhaw.SnakeGame.SCREEN_WIDTH;
import static ch.mgubler.zhaw.SnakeScreen.BLANK_FIELD;
import static ch.mgubler.zhaw.SnakeScreen.WALL_CHAR;
import static ch.mgubler.zhaw.objects.MoveableObjectTest.TEST_START_X;
import static ch.mgubler.zhaw.objects.MoveableObjectTest.TEST_START_Y;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SnakeScreenTest {

    public static final int WALL_COUNT = 2;

    public static final BinaryOperator<String> STRING_CONCATENATOR = (s, s2) -> s + s2;

    private static final String WALL_TOP_BOTTOM = IntStream.range(0, SnakeGame.SCREEN_WIDTH).mapToObj(counter -> String.valueOf(WALL_CHAR)).reduce(STRING_CONCATENATOR).get();

    public static final int FIELDS_COUNT = SnakeGame.SCREEN_WIDTH - WALL_COUNT;

    public static final int ROWS_COUNT = SCREEN_HEIGHT - WALL_COUNT;

    private static final String LINE_CONTENT = IntStream.range(0, FIELDS_COUNT).mapToObj(counter -> String.valueOf(BLANK_FIELD)).reduce(STRING_CONCATENATOR).get();

    private static final String LINE_CONTENT_WITH_WALLS = WALL_CHAR + LINE_CONTENT + WALL_CHAR;

    private static final char[] WALL_TOP_BOTTOM_CHAR_ARRAY = WALL_TOP_BOTTOM.toCharArray();

    private static final char[] LINE_CONTENT_WITH_WALLS_CHAR_ARRAY = LINE_CONTENT_WITH_WALLS.toCharArray();

    private static final char[][] INIT_SCREEN_EXPECTED = new char[SCREEN_HEIGHT][SnakeGame.SCREEN_WIDTH];

    {
        //Init Expected Game Matrix with Walls on Top/Bottom and the regular Fieldcontent
        INIT_SCREEN_EXPECTED[0] = WALL_TOP_BOTTOM_CHAR_ARRAY;
        for (int arraysCounter = 1; arraysCounter <= ROWS_COUNT; arraysCounter++) {
            INIT_SCREEN_EXPECTED[arraysCounter] = LINE_CONTENT_WITH_WALLS_CHAR_ARRAY;
        }
        INIT_SCREEN_EXPECTED[SCREEN_HEIGHT - 1] = WALL_TOP_BOTTOM_CHAR_ARRAY;
    }

    private SnakeScreen snakeScreenTestee;

    @Mock
    private Screen screenMock;

    @Mock
    private SnakeGame snakeGameMock;

    private Snake snake;

    @Before
    public void setUp() {
        snake = new Snake(snakeGameMock, new MoveablePosition(TEST_START_X, TEST_START_Y));
        snakeScreenTestee = new SnakeScreen(SCREEN_HEIGHT, SCREEN_WIDTH, screenMock, snake);
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
        verify(screenMock, times(SCREEN_HEIGHT * SCREEN_WIDTH)).setCharacter(any(TerminalPosition.class), any(TextCharacter.class));
        Assert.assertEquals(new Character(snake.getSymbol()), snakeScreenTestee.getScreenMatrix()[TEST_START_Y][TEST_START_X]);
    }

    @Test
    public void paintScreen_move_snake_test() throws Exception {
        snake.setCurrentDirection(Direction.RIGHT);
        snakeScreenTestee.paintScreen();
        Assert.assertEquals(new Character(snake.getSymbol()), snakeScreenTestee.getScreenMatrix()[TEST_START_Y][TEST_START_X]);
        snake.move();
        snakeScreenTestee.paintScreen();
        Assert.assertEquals(new Character(snake.getSymbol()), snakeScreenTestee.getScreenMatrix()[TEST_START_Y][TEST_START_X + 1]);
        snake.move();
        snakeScreenTestee.paintScreen();
        Assert.assertEquals(new Character(snake.getSymbol()), snakeScreenTestee.getScreenMatrix()[TEST_START_Y][TEST_START_X + 2]);
        snake.setCurrentDirection(Direction.DOWN);
        snake.move();
        snakeScreenTestee.paintScreen();
        Assert.assertEquals(new Character(snake.getSymbol()), snakeScreenTestee.getScreenMatrix()[TEST_START_Y + 1][TEST_START_X + 2]);
    }

    @Test
    public void writeOnScreen() throws Exception {
        snakeScreenTestee.writeOnScreen("Hello");
        verify(screenMock).setCharacter(eq(new TerminalPosition(1,1)), eq(new TextCharacter('H')));
        verify(screenMock).setCharacter(eq(new TerminalPosition(2,1)), eq(new TextCharacter('e')));
        verify(screenMock).setCharacter(eq(new TerminalPosition(3,1)), eq(new TextCharacter('l')));
        verify(screenMock).setCharacter(eq(new TerminalPosition(4,1)), eq(new TextCharacter('l')));
        verify(screenMock).setCharacter(eq(new TerminalPosition(5,1)), eq(new TextCharacter('o')));
        verify(screenMock).refresh();
    }

}
