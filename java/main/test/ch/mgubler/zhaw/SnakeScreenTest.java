package ch.mgubler.zhaw;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.function.BinaryOperator;
import java.util.stream.IntStream;

import static ch.mgubler.zhaw.SnakeGame.SCREEN_HEIGHT;
import static ch.mgubler.zhaw.SnakeGame.SCREEN_WIDTH;
import static ch.mgubler.zhaw.SnakeScreen.BLANK_FIELD;
import static ch.mgubler.zhaw.SnakeScreen.WALL_CHAR;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;

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

    private SnakeScreen testeeSnakeScreen;

    @Mock
    private Screen screen;

    @Before
    public void setUp() {
        testeeSnakeScreen = new SnakeScreen(SCREEN_HEIGHT, SCREEN_WIDTH, screen, null);
        testeeSnakeScreen.init();
    }

    @Test
    public void init_test() throws Exception {
        Assert.assertArrayEquals(INIT_SCREEN_EXPECTED, testeeSnakeScreen.getScreenMatrix());
    }

    @Test
    public void paintScreen_after_init_test() throws Exception {
        testeeSnakeScreen.paintScreen();
        Mockito.verify(screen, times(1)).clear();
        Mockito.verify(screen, times(1)).refresh();
        Mockito.verify(screen, times(SCREEN_HEIGHT * SCREEN_WIDTH)).setCharacter(any(TerminalPosition.class), any(TextCharacter.class));
    }

}
