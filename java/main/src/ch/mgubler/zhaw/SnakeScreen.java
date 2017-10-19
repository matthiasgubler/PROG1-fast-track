package ch.mgubler.zhaw;

import ch.mgubler.zhaw.move.PaintableObject;
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

    public static final int SCORE_TEXT_ROW = 3;

    public static final int SCORE_TEXT_START_COLUMN = 2;

    public static final int HEADER_GAP = 5;

    public static final char BLANK_FIELD = ' ';

    public static final char WALL_CHAR = 'X';

    public static final String SCORE_TITLE = "Score: ";

    private int width;

    private int height;

    private Character[][] screenMatrix;

    private Screen screen;

    private List<PaintableObject> gameElements = new ArrayList<>();

    private PaintableObject mainObject;

    private GameScore gameScore;

    private char[] infoText = {};

    public SnakeScreen(int height, int width, Screen screen, PaintableObject mainObject, GameScore gameScore) {
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
                screen.setCharacter(new TerminalPosition(columnIndex, rowIndex+HEADER_GAP), new TextCharacter(screenMatrix[rowIndex][columnIndex]));
            }
        }
        displayTextes();
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

    public void setInfoTextOnScreen(String infoTextString){
        this.infoText = infoTextString.toCharArray();
    }

    public void writeInfoTextOnScreen(){
        displayTextes();
        refreshScreen();
    }

    public void displayTextes(){
        displayInfoText();
        displayScore();
    }

    private void displayInfoText() {
        displayText(infoText, INFO_TEXT_START_COLUMN, INFO_TEXT_ROW);
    }

    private void displayScore() {
        displayText(SCORE_TITLE +gameScore.getGameScore(), SCORE_TEXT_START_COLUMN, SCORE_TEXT_ROW);
    }

    private void displayText(char[] text, int startPositionX, int row){
        int x = startPositionX;
        for (char letter : text) {
            TerminalPosition currentPosition = new TerminalPosition(x, row);
            screen.setCharacter(currentPosition, new TextCharacter(letter));
            x++;
        }
    }

    private void displayText(String text, int startPositionX, int row){
        this.displayText(text.toCharArray(), startPositionX, row);
    }

    private void refreshScreen(){
        try {
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
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

    public void removeGameElement(PaintableObject gameElement) {
        this.gameElements.remove(gameElement);
    }
}
