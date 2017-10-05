package ch.mgubler.zhaw;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.function.BinaryOperator;
import java.util.stream.IntStream;

import static ch.mgubler.zhaw.SnakeScreen.BLANK_FIELD;
import static ch.mgubler.zhaw.SnakeScreen.WALL_CHAR;

public class SnakeScreenTest {

    public static final int WALL_COUNT = 2;

    public static final BinaryOperator<String> STRING_CONCATENATOR = (s, s2) -> s + s2;

    private static final String WALL_TOP_BOTTOM = IntStream.range(0, SnakeGame.SCREEN_WIDTH).mapToObj(counter -> String.valueOf(WALL_CHAR)).reduce(STRING_CONCATENATOR).get();

    public static final int FIELDS_COUNT = SnakeGame.SCREEN_WIDTH - WALL_COUNT;

    public static final int ROWS_COUNT = SnakeGame.SCREEN_HEIGHT - WALL_COUNT;

    private static final String LINE_CONTENT = IntStream.range(0, FIELDS_COUNT).mapToObj(counter -> String.valueOf(BLANK_FIELD)).reduce(STRING_CONCATENATOR).get();

    private static final String LINE_CONTENT_WITH_WALLS = WALL_CHAR + LINE_CONTENT + WALL_CHAR;

    private static final String NEW_LINE = System.getProperty("line.separator");

    private String expectedSOUTStringInitialized = "";

    private static final char[] WALL_TOP_BOTTOM_CHAR_ARRAY = WALL_TOP_BOTTOM.toCharArray();

    private static final char[] LINE_CONTENT_WITH_WALLS_CHAR_ARRAY = LINE_CONTENT_WITH_WALLS.toCharArray();

    private static final char[][] INIT_SCREEN_EXPECTED = new char[SnakeGame.SCREEN_WIDTH][SnakeGame.SCREEN_HEIGHT];

    {
        //Init Expected Game Matrix with Walls on Top/Bottom and the regular Fieldcontent
        INIT_SCREEN_EXPECTED[0] = WALL_TOP_BOTTOM_CHAR_ARRAY;
        for (int arraysCounter = 1; arraysCounter <= ROWS_COUNT; arraysCounter++) {
            INIT_SCREEN_EXPECTED[arraysCounter] = LINE_CONTENT_WITH_WALLS_CHAR_ARRAY;
        }
        INIT_SCREEN_EXPECTED[SnakeGame.SCREEN_HEIGHT - 1] = WALL_TOP_BOTTOM_CHAR_ARRAY;
    }

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    private SnakeScreen testeeSnakeScreen;

    @BeforeEach
    public void setUp() {
        expectedSOUTStringInitialized = WALL_TOP_BOTTOM + NEW_LINE
                + IntStream.range(0, ROWS_COUNT).mapToObj(counter -> LINE_CONTENT_WITH_WALLS + NEW_LINE).reduce(STRING_CONCATENATOR).get()
                + WALL_TOP_BOTTOM + NEW_LINE;


        System.setOut(new PrintStream(outContent));
        testeeSnakeScreen = new SnakeScreen(SnakeGame.SCREEN_HEIGHT, SnakeGame.SCREEN_WIDTH, null);
        testeeSnakeScreen.init();
    }

    @Test
    public void init_test() {
        Assertions.assertArrayEquals(INIT_SCREEN_EXPECTED, testeeSnakeScreen.getScreenMatrix());
    }

    @Test
    public void paintScreen_after_init_test() {
        testeeSnakeScreen.paintScreen();
        Assertions.assertEquals(expectedSOUTStringInitialized, outContent.toString());
    }

    @AfterEach
    public void reset() {
        System.setOut(null);
    }

}
