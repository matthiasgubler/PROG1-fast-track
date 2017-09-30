package ch.mgubler.zhaw;

import java.util.Arrays;

public class Screen {

    public static final char BLANK_FIELD = ' ';

    public static final char WALL_CHAR = 'X';

    private int width;

    private int height;

    private char[][] screenMatrix;

    public Screen(int width, int height){
        this.width = width;
        this.height = height;
        screenMatrix = new char[width][height];
    }

    public void init() {
        fillLine(screenMatrix[0], width, WALL_CHAR);
        for (int rowCounter = 1; rowCounter < height-1; rowCounter++) {
            screenMatrix[rowCounter][0] = WALL_CHAR;
            int lastHorizontalIndex = width-1;
            for (int columnCounter = 1; columnCounter < lastHorizontalIndex; columnCounter++) {
                screenMatrix[rowCounter][columnCounter] = BLANK_FIELD;
            }
            screenMatrix[rowCounter][lastHorizontalIndex] = WALL_CHAR;
        }
        fillLine(screenMatrix[height-1], width, WALL_CHAR);
    }

    public void fillLine(char[] currentLine, int toCounter, char fillChar) {
        fillLine(currentLine, 0, toCounter, fillChar);
    }

    public void fillLine(char[] currentLine, int fromCounter, int toCounter, char fillChar) {
        for (int columnCounter = fromCounter; columnCounter < toCounter; columnCounter++) {
            currentLine[columnCounter] = fillChar;
        }
    }

    public void paintScreen(){
        Arrays.stream(screenMatrix).forEach(horizontalRow -> {
            Arrays.asList(horizontalRow).forEach(System.out::print);
            System.out.println();
        });
    }

    public char[][] getScreenMatrix() {
        return screenMatrix;
    }
}
