package ch.mgubler.zhaw;

import ch.mgubler.zhaw.objects.PaintableObject;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class SnakeScreen {

    public static final char BLANK_FIELD = ' ';

    public static final char WALL_CHAR = 'X';

    private int width;

    private int height;

    private Character[][] screenMatrix;

    private Screen screen;

    public SnakeScreen(int height, int width, Screen screen) {
        this.height = height;
        this.width = width;
        screenMatrix = new Character[height][width];
        this.screen = screen;
    }

    public void init() {
        fillLine(screenMatrix[0], width, WALL_CHAR);
        for (int rowCounter = 1; rowCounter < height - 1; rowCounter++) {
            screenMatrix[rowCounter][0] = WALL_CHAR;
            int lastHorizontalIndex = width - 1;
            for (int columnCounter = 1; columnCounter < lastHorizontalIndex; columnCounter++) {
                screenMatrix[rowCounter][columnCounter] = BLANK_FIELD;
            }
            screenMatrix[rowCounter][lastHorizontalIndex] = WALL_CHAR;
        }
        fillLine(screenMatrix[height - 1], width, WALL_CHAR);
        try {
            screen.startScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fillLine(Character[] currentLine, int toCounter, char fillChar) {
        fillLine(currentLine, 0, toCounter, fillChar);
    }

    public void fillLine(Character[] currentLine, int fromCounter, int toCounter, char fillChar) {
        for (int columnCounter = fromCounter; columnCounter < toCounter; columnCounter++) {
            currentLine[columnCounter] = fillChar;
        }
    }

    public void paintScreen() {
        screen.clear();
        for (int rowIndex = 0; rowIndex < height; rowIndex++) {
            for (int columnIndex = 0; columnIndex < width; columnIndex++) {
                screen.setCharacter(new TerminalPosition(columnIndex, rowIndex), new TextCharacter(screenMatrix[rowIndex][columnIndex]));
            }
        }
        try {
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Character[][] getScreenMatrix() {
        return screenMatrix;
    }

    public Character getElementOnScreen(int x, int y) {
        return this.screenMatrix[x][y];
    }

    public void setElementOnScreen(int x, int y, PaintableObject paintableObject) {
        this.screenMatrix[x][y] = paintableObject.getSymbol();
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setScreenMatrix(Character[][] screenMatrix) {
        this.screenMatrix = screenMatrix;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }
}
