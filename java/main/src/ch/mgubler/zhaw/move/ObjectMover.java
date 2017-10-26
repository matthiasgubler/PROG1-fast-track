package ch.mgubler.zhaw.move;

import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.text.MessageFormat;

public class ObjectMover {

    private final Terminal terminal;

    private final MoveableObject moveableObject;

    public ObjectMover(Terminal terminal, MoveableObject moveableObject) {
        this.terminal = terminal;
        this.moveableObject = moveableObject;
    }

    public void pollDirectionChange() throws IOException {
        Direction direction = Direction.getDirectionByKeyStroke(terminal.pollInput());
        System.out.println(MessageFormat.format("Polled Direction {0}", direction.name()));

        moveableObject.setCurrentDirection(direction);
    }

    public void moveObject() {
        moveableObject.move();
    }

}
