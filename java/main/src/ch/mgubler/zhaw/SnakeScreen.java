package ch.mgubler.zhaw;

import ch.mgubler.zhaw.move.PaintableObject;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class SnakeScreen {

    public static final int HEADER_GAP = 5;

    public static final char BLANK_FIELD = ' ';

    public static final char WALL_CHAR = 'X';

    private int width;

    private int height;

    private Character[][] screenMatrix;

    private Screen screen;

    private List<PaintableObject> gameElements = new ArrayList<>();

    private PaintableObject mainObject;

    public SnakeScreen(int height, int width, Screen screen, PaintableObject mainObject) {
        this.height = height;
        this.width = width;
        screenMatrix = new Character[height][width];
        this.screen = screen;
        this.mainObject = mainObject;
    }

    public void init() {
        clearScreen();
        try {
            screen.startScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopScreen() {
        try {
            screen.stopScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clearScreen() {
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
    }

    private void fillLine(Character[] currentLine, int toCounter, char fillChar) {
        fillLine(currentLine, 0, toCounter, fillChar);
    }

    private void fillLine(Character[] currentLine, int fromCounter, int toCounter, char fillChar) {
        for (int columnCounter = fromCounter; columnCounter < toCounter; columnCounter++) {
            currentLine[columnCounter] = fillChar;
        }
    }

    public void paintScreen() {
        clearScreen();
        setElementsOnScreen();
        screen.clear();
        for (int rowIndex = 0; rowIndex < height; rowIndex++) {
            for (int columnIndex = 0; columnIndex < width; columnIndex++) {
                screen.setCharacter(new TerminalPosition(columnIndex, rowIndex+HEADER_GAP), new TextCharacter(screenMatrix[rowIndex][columnIndex]));
            }
        }
        try {
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setElementsOnScreen() {
        //Paint Snake
        setElementOnScreen(mainObject);

        //Set Elements on Screen
        for (PaintableObject gameElement : gameElements) {
            setElementOnScreen(gameElement);
        }

    }


    public Character[][] getScreenMatrix() {
        return screenMatrix;
    }

    public Character getElementOnScreen(int x, int y) {
        return this.screenMatrix[x][y];
    }

    public void setElementOnScreen(PaintableObject paintableObject) {
        System.out.println(MessageFormat.format("Painting element with Symbol ''{0}'' to Position [{1}][{2}]", paintableObject.getSymbol(), paintableObject.getPosition().getX(), paintableObject.getPosition().getY()));
        this.screenMatrix[paintableObject.getPosition().getY()][paintableObject.getPosition().getX()] = paintableObject.getSymbol();
    }

    public void writeOnScreen(String infoText){
        writeText(1,1,infoText);
        try {
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeText(int startX, int startY, String text){
        int x = startX;
        for (char letter : text.toCharArray()) {
            TerminalPosition currentPosition = new TerminalPosition(x, startY);
            screen.setCharacter(currentPosition, new TextCharacter(letter));
            x++;
        }
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

    public PaintableObject getMainObject() {
        return mainObject;
    }

    public void setMainObject(PaintableObject mainObject) {
        this.mainObject = mainObject;
    }

    public void addGameElement(PaintableObject gameElement) {
        this.gameElements.add(gameElement);
    }
}
