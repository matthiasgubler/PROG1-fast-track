package ch.mgubler.zhaw.move;

import com.googlecode.lanterna.input.KeyStroke;

public enum Direction {
    UP('w'),
    LEFT('a'),
    DOWN('s'),
    RIGHT('d'),
    UNDEF('x');

    private char directionKey;

    Direction(char directionKey) {
        this.directionKey = directionKey;
    }

    public char getDirectionKey() {
        return directionKey;
    }

    public static Direction getDirectionByKeyStroke(KeyStroke keyStroke) {
        if (keyStroke != null) {
            return getDirectionByKey(keyStroke.getCharacter());
        }

        return UNDEF;
    }

    public static Direction getDirectionByKey(Character directionKey) {
        if(directionKey == null){
            return UNDEF;
        }

        for (Direction direction : Direction.values()) {
            if (direction.getDirectionKey() == directionKey) {
                return direction;
            }
        }

        return UNDEF;
    }


}
