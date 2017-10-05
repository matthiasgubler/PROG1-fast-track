package ch.mgubler.zhaw.objects;

public abstract class PaintableObject {

    private char symbol;

    private Position position;

    protected PaintableObject(char symbol){
        this.symbol = symbol;
    }

    public PaintableObject(char symbol, Position position){
        this.symbol = symbol;
        this.position = position;
    }

    public char getSymbol(){
        return symbol;
    }

    public Position getPosition(){
        return position;
    }

}
