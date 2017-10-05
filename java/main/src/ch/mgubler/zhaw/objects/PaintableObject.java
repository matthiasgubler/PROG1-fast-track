package ch.mgubler.zhaw.objects;

public abstract class PaintableObject {

    private char symbol;

    public PaintableObject(char symbol){
        this.symbol = symbol;
    }

    public char getSymbol(){
        return symbol;
    }

}
