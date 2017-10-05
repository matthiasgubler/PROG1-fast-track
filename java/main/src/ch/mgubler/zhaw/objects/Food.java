package ch.mgubler.zhaw.objects;

public class Food extends PaintableObject{

    public static final char FOOD_SYMBOL = '*';

    public static final int FOOD_POINTS = 5;

    private int foodPoints = FOOD_POINTS;

    public Food() {
        super(FOOD_SYMBOL);
    }

    protected Food(int foodPoints, char symbol){
        super(symbol);
        this.foodPoints = foodPoints;
    }

    public int getFoodPoints() {
        return foodPoints;
    }
}
