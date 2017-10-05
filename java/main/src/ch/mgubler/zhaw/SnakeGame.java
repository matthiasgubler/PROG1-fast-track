package ch.mgubler.zhaw;

import ch.mgubler.zhaw.objects.Snake;
import ch.mgubler.zhaw.textinput.ObjectMover;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class SnakeGame {

    public static final int SCREEN_WIDTH = 50;

    public static final int SCREEN_HEIGHT = 20;
    public static final int GAME_SPEED = 100;

    private SnakeScreen gameSnakeScreen;

    private Terminal terminal;

    private Screen screen;

    private ObjectMover mover;

    private Snake snake;

    public static void main(String[] args) {
        SnakeGame snakeGame = new SnakeGame();
        snakeGame.startGame();
    }

    public SnakeGame() {
        try {
            terminal = new DefaultTerminalFactory().createTerminal();
            screen = new TerminalScreen(terminal);
        } catch (IOException e) {
            e.printStackTrace();
        }

        snake = new Snake();
        mover = new ObjectMover(terminal, snake);
    }

    public void startGame() {
        gameSnakeScreen = new SnakeScreen(SCREEN_HEIGHT, SCREEN_WIDTH, screen);
        gameSnakeScreen.init();

        while (true) {
            try {
                gameSnakeScreen.paintScreen();
                mover.pollInput();
                Thread.sleep(GAME_SPEED);
            } catch (InterruptedException e) {
                //TODO wäää
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
