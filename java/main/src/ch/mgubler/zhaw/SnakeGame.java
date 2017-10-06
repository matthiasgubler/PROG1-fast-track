package ch.mgubler.zhaw;

import ch.mgubler.zhaw.move.MoveablePosition;
import ch.mgubler.zhaw.move.ObjectMover;
import ch.mgubler.zhaw.objects.Snake;
import ch.mgubler.zhaw.score.GameScore;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;

import java.awt.*;
import java.io.IOException;

public class SnakeGame {

    public static final int SCREEN_WIDTH = 50;

    public static final int SCREEN_HEIGHT = 20;
    public static final int GAME_SPEED = 100;
    public static final int SNAKE_START_Y = 5;
    public static final int SNAKE_START_X = 5;
    public static final int TERMINAL_SIZE_ADD = 10;
    public static final String FONT_NAME = "Courier New";

    private SnakeScreen gameSnakeScreen;

    private GameScore gameScore;

    private Terminal terminal;

    private Screen screen;

    private ObjectMover mover;

    private Snake snake;

    private boolean running = true;

    public static void main(String[] args) {
        SnakeGame snakeGame = new SnakeGame();
        snakeGame.startGame();
    }

    public SnakeGame() {
        try {
            terminal = new DefaultTerminalFactory()
                    .setInitialTerminalSize(new TerminalSize(SCREEN_WIDTH + TERMINAL_SIZE_ADD, SCREEN_HEIGHT + TERMINAL_SIZE_ADD))
                    .setTerminalEmulatorFontConfiguration(AWTTerminalFontConfiguration.newInstance(new Font(FONT_NAME, Font.PLAIN, 12)))
                    .createTerminal();
            screen = new TerminalScreen(terminal);
        } catch (IOException e) {
            e.printStackTrace();
        }

        snake = new Snake(this, new MoveablePosition(SNAKE_START_X, SNAKE_START_Y));
        mover = new ObjectMover(terminal, snake);
        gameScore = new GameScore();
    }

    public void startGame() {
        gameSnakeScreen = new SnakeScreen(SCREEN_HEIGHT, SCREEN_WIDTH, screen, snake);
        gameSnakeScreen.init();

        while (running) {
            try {
                gameSnakeScreen.paintScreen();
                mover.pollDirectionChange();
                mover.moveObject();
                gameSnakeScreen.writeOnScreen("Hello World");
                Thread.sleep(GAME_SPEED);
            } catch (Exception e) {
                e.printStackTrace();
                running = false;
            }
        }

        gameSnakeScreen.stopScreen();
    }

    public void gameOver() {
        //TODO gameSnakeScreen.writeText...
        running = false;
    }

    public GameScore getGameScore() {
        return gameScore;
    }
}
