import java.util.*;
import java.sql.SQLException;

public class Inventory {
    /* THIS IS NOT IMPLEMENTED PROPERLY AT ALL!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     * temporary test to see if others are working
     */
    
    private ArrayList<FoodItem> foodItems = new ArrayList<>();
    private Database database = new Database("jdbc:mysql://localhost/food_inventory", "student", "ensf");

    public boolean validateOrder(ArrayList<Hamper> hampers) {
        try {
            database.initializeConnection();
        } catch(SQLException e){
            e.printStackTrace();
        }

        for (Hamper hamper: hampers) {
            // Calculate hamper requirements
            int reqWholeGrains = 0;
            int reqFruitsVeggies = 0;
            int reqProtein = 0;
            int reqOther = 0;
            int reqCalories = 0;
            for (Client client: hamper.getClients()) {
                reqWholeGrains += client.getWholeGrains();
                reqFruitsVeggies += client.getFruitVeggies();
                reqProtein += client.getProtein();
                reqOther += client.getOther();
                reqCalories += client.getCalories();
            }

            System.out.printf("reqWholeGrains: %d\n", reqWholeGrains);
            System.out.printf("reqFruitsVeggies: %d\n", reqFruitsVeggies);
            System.out.printf("reqProtein: %d\n", reqProtein);
            System.out.printf("reqOther: %d\n", reqOther);
            System.out.printf("reqCalories: %d\n", reqCalories);

            int waste;
            ArrayList<FoodItem> bestCombination = new ArrayList<>();

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
        // FOR TESTING PURPOSES: keeps only the first few items in foodItems
        foodItems = new ArrayList<FoodItem>(foodItems.subList(0, 2));
    }

    public ArrayList<FoodItem> getFoodItems(){
        return foodItems;
    }

    public void printShortages() throws InsufficientInventoryException {
        throw new InsufficientInventoryException();
    }

    // Testing
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        try {
            inventory.database.initializeConnection();
        } catch (SQLException e) {
            System.out.println("Connection failed");
            System.exit(1);
        }
        inventory.convertDatabaseToFoodItemsList();

        // for (FoodItem foodItem: inventory.foodItems) {
        //     System.out.println(foodItem.getName());
        // }

        ArrayList<Hamper> hampers = new ArrayList<>();
        Hamper hamper = new Hamper();
        hamper.addClient(ClientType.ADULT_FEMALE, 2);
        hampers.add(hamper);

        inventory.validateOrder(hampers);
    }
}
