package ch.mgubler.zhaw;

import ch.mgubler.zhaw.move.PaintableObject;
import ch.mgubler.zhaw.move.Position;
import ch.mgubler.zhaw.objects.Snake;
import ch.mgubler.zhaw.objects.SnakeElement;
import ch.mgubler.zhaw.score.GameScore;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class SnakeScreen {

    public static final int INFO_TEXT_ROW = 2;

    public static final int INFO_TEXT_START_COLUMN = 2;

    public static final char BLANK_FIELD = ' ';

    public static final char WALL_CHAR = 'X';

    private static final int SCORE_TEXT_ROW = 3;

    private static final int SCORE_TEXT_START_COLUMN = 2;

    private static final int HEADER_GAP = 5;

    private static final String SCORE_TITLE = "Score: ";

    private final int width;

    private final int height;

    private final Character[][] screenMatrix;

    private final Screen screen;

    private final List<PaintableObject> gameElements = new ArrayList<>();

    private final Snake mainObject;

    private final GameScore gameScore;

    private char[] infoText = {};

    public SnakeScreen(int height, int width, Screen screen, Snake mainObject, GameScore gameScore) {
        this.height = height;
        this.width = width;
        screenMatrix = new Character[height][width];
        this.screen = screen;
        this.mainObject = mainObject;
        this.gameScore = gameScore;
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
                screen.setCharacter(new TerminalPosition(columnIndex, rowIndex + HEADER_GAP), new TextCharacter(screenMatrix[rowIndex][columnIndex]));
            }
        }
        displayTexts();
        try {
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setElementsOnScreen() {
        //Paint Snake
        setSnakeOnScreen();

        //Set Elements on Screen
        for (PaintableObject gameElement : gameElements) {
            setElementOnScreen(gameElement);
        }

    }

    private void setSnakeOnScreen() {
        setElementOnScreen(mainObject);
        for (SnakeElement snakeElement : mainObject.getSnakeElements()) {
            setElementOnScreen(snakeElement);
        }
    }


    public Character[][] getScreenMatrix() {
        return screenMatrix;
    }

    public void setElementOnScreen(PaintableObject paintableObject) {
        System.out.println(MessageFormat.format("Painting element with Symbol ''{0}'' to Position [{1}][{2}]", paintableObject.getSymbol(), paintableObject.getPosition().getX(), paintableObject.getPosition().getY()));
        this.screenMatrix[paintableObject.getPosition().getY()][paintableObject.getPosition().getX()] = paintableObject.getSymbol();
    }

    public void setInfoTextOnScreen(String infoTextString) {
        this.infoText = infoTextString.toCharArray();
    }

    public void writeInfoTextOnScreen() {
        displayTexts();
        refreshScreen();
    }

    private void displayTexts() {
        displayInfoText();
        displayScore();
    }

    private void displayInfoText() {
        displayText(infoText, INFO_TEXT_START_COLUMN, INFO_TEXT_ROW);
    }

    private void displayScore() {
        displayText(SCORE_TITLE + gameScore.getGameScore(), SCORE_TEXT_START_COLUMN, SCORE_TEXT_ROW);
    }

    private void displayText(char[] text, int startPositionX, int row) {
        int x = startPositionX;
        for (char letter : text) {
            TerminalPosition currentPosition = new TerminalPosition(x, row);
            screen.setCharacter(currentPosition, new TextCharacter(letter));
            x++;
        }
    }

    private void displayText(String text, int startPositionX, int row) {
        this.displayText(text.toCharArray(), startPositionX, row);
    }

    private void refreshScreen() {
        try {
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addGameElement(PaintableObject gameElement) {
        this.gameElements.add(gameElement);
    }

    public void removeGameElement(PaintableObject gameElement) {
        this.gameElements.remove(gameElement);
    }

    public List<Position> getOccupiedPositions() {
        List<Position> occupiedPositions = new ArrayList<>();
        for (int rowIndex = 0; rowIndex < screenMatrix.length; rowIndex++) {
            for (int columnIndex = 0; columnIndex < screenMatrix[rowIndex].length; columnIndex++) {
                if (!Character.isWhitespace(screenMatrix[rowIndex][columnIndex])) {
                    occupiedPositions.add(new Position(columnIndex, rowIndex));
                }
            }
        }
        return occupiedPositions;
    }
}
