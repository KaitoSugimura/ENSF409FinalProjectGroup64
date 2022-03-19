import java.util.*;
import java.sql.SQLException;

public class Inventory {
    /* THIS IS NOT IMPLEMENTED PROPERLY AT ALL!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     * temporary test to see if others are working
     */
    
    private ArrayList<FoodItem> foodItems = new ArrayList<>();
    private Database database = new Database("jdbc:mysql://localhost/food_inventory", "student", "ensf");

    //private Database database = new Database("jdbc:mysql://localhost/food_inventory", "student", "ensf");
    private int PLEASE_DELETE_THIS_INT_ITS_TEMP_FOR_NOW = 1;


    private void addItem() {

    }

    private void removeItem() {

    }

    /*
    public boolean calculateOrder(ArrayList<Hamper> hampers){
        if(!connectToDatabase()){
            return false;
        }
        return true;
        
    }

    public boolean connectToDatabase(){
        try {
            database.initializeConnection();
        } catch(SQLException e){
            return false;
        }
        return true;
    } */

    public boolean validateOrder(ArrayList<Hamper> hampers) {
        try {
            database.initializeConnection();
        } catch(SQLException e){
            e.printStackTrace();
        }

        for (Hamper hamper : hampers) {
            for (Client client : hamper.getClients()) {
                hamper.addItem(getNewFoodItem());
            }
        }
        
        return true;
    }

    public FoodItem getNewFoodItem() {
        String[] foodValues = database.getNextFoodValues(PLEASE_DELETE_THIS_INT_ITS_TEMP_FOR_NOW);

        PLEASE_DELETE_THIS_INT_ITS_TEMP_FOR_NOW += 3;

        int id = Integer.parseInt(foodValues[0]);
        int wholeGrains = Integer.parseInt(foodValues[2]);
        int fruitVeggies = Integer.parseInt(foodValues[3]);
        int protein = Integer.parseInt(foodValues[4]);
        int other = Integer.parseInt(foodValues[5]);
        int calories = Integer.parseInt(foodValues[6]);

        return new FoodItem(id, foodValues[1], wholeGrains, fruitVeggies, protein, other, calories);
    }

    public void printShortages(){
        
    }
}
