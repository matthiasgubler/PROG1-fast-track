package ch.mgubler.zhaw.objects;

import java.util.ArrayList;
import java.util.List;

public class Snake extends PaintableObject{

    public static final char SNAKE_SYMBOL = '@';

    private List<SnakeElement> snakeElements = new ArrayList<>();

    public Snake() {
        super(SNAKE_SYMBOL);
    }

    public void addElement(SnakeElement snakeElement){
        snakeElements.add(snakeElement);
    }

    public void getElementAtIndex(int i){
        snakeElements.get(i);
    }

}
