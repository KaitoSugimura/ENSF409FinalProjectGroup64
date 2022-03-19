
import java.util.*;

public class Inventory {

    /* THIS IS NOT IMPLEMENTED PROPERLY AT ALL!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     * temporary test to see if others are working
     */
    
    private ArrayList<FoodItem> fooditems = new ArrayList<>();

    public Inventory(){
        fooditems.add(new FoodItem(1, "Pasta", 60, 20, 10, 10, 400));
        fooditems.add(new FoodItem(2, "Salad", 0, 90, 0, 10, 100));
        fooditems.add(new FoodItem(3, "McDonalds", 0, 0, 0, 100, 2147483647));
    }

    private void addItem(){}

    private void removeItem(){

    }

    public boolean validateOrder(ArrayList<Hamper> hampers){
        for(int i = 0; i < hampers.size(); i++){
            hampers.get(i).addItem(fooditems.get(i));
            if(i == 0){
                hampers.get(i).addItem(fooditems.get(i+1));
            }
        }
        return true;
    }

    public void printShortages(){

    }

}
