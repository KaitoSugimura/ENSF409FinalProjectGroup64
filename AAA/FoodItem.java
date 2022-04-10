public class FoodItem extends Nutrition{
    private final int ITEM_ID;
    private final String NAME;

    public FoodItem(int ID, String name, int wholeGrains, int fruitVeggies, int protein, int other, int calories){
        super(wholeGrains, fruitVeggies, protein, other, calories);
        this.ITEM_ID = ID;
        this.NAME = name;
    }

    public int getItemID(){
        return this.ITEM_ID;
    }

    public String getName(){
        return this.NAME;
    }
}
