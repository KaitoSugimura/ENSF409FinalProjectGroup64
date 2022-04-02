import java.util.*;
import java.sql.SQLException;

public class Inventory {
    /* THIS IS NOT IMPLEMENTED PROPERLY AT ALL!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     * temporary test to see if others are working
     */
    
    private ArrayList<FoodItem> foodItems = new ArrayList<>();
    private Database database = new Database("jdbc:mysql://localhost/food_inventory", "student", "ensf");

    //private Database database = new Database("jdbc:mysql://localhost/food_inventory", "student", "ensf");

    public boolean validateOrder(ArrayList<Hamper> hampers) {
        try {
            database.initializeConnection();
        } catch(SQLException e){
            e.printStackTrace();
        }

        /*
        for (Hamper hamper : hampers) {
            for (Client client : hamper.getClients()) {
                hamper.addItem(getNewFoodItem());
            }
        }*/
        
        return true;
    }

    public void convertDatabaseToFoodItemsList() {
        this.foodItems = database.getFoodValues();
    }

    public ArrayList<FoodItem> getFoodItems(){
        return foodItems;
    }

    public void printShortages() throws InsufficientInventoryException {
        throw new InsufficientInventoryException();
    }
}
