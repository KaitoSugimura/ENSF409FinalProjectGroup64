/**
@author Danny Duong, Kaito Sugimura, Kevin Johnson, Joshua Walters
@version 1.9
@since 1.0
*/

/*
class FoodItem - Fooditem represents the various fooditems contained within the inventory
and extends nutrition to hold the nutrition values of each food.
*/

public class FoodItem extends Nutrition{

    //Fields
    private final int ITEM_ID;
    private final String NAME;

    //Constructors
    
    public FoodItem(int ID, String name, int wholeGrains, int fruitVeggies, int protein, int other, int calories){
        super(wholeGrains, fruitVeggies, protein, other, calories);
        this.ITEM_ID = ID;
        this.NAME = name;
    }

    //Public Methods

    /**
     * getItemID() - returns the ItemID of a given food item.
     * @return ITEM_ID
     */
    public int getItemID(){
        return this.ITEM_ID;
    }

    /**
     * getName() - returns the name of a given food item.
     * @return NAME
     */
    public String getName(){
        return this.NAME;
    }
}
