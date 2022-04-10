/**
@author Danny Duong, Kaito Sugimura, Kevin Johnson, Joshua Walters
@version 1.9
@since 1.0
*/

/*
 * Fooditem represents the various fooditems contained within the inventory
 * and extends nutrition to hold the nutrition values of each food.
 */
public class FoodItem extends Nutrition{

    //Fields
    private final int ITEM_ID;
    private final String NAME;

    /**
     * Constructor
     * @param ID Food Item ID
     * @param name Food Item name
     * @param wholeGrains wholegrain nutrition value
     * @param fruitVeggies fruitVeggies  nutrition value
     * @param protein protein nutrition value
     * @param other other nutrition value
     * @param calories calories nutrition value
     */
    public FoodItem(int ID, String name, int wholeGrains, int fruitVeggies, int protein, int other, int calories){
        super(wholeGrains, fruitVeggies, protein, other, calories);
        this.ITEM_ID = ID;
        this.NAME = name;
    }

    //Getters
    public int getItemID(){
        return this.ITEM_ID;
    }

    public String getName(){
        return this.NAME;
    }
}
