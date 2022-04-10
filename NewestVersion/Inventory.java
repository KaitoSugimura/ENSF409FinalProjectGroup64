/**
@author Danny Duong, Kaito Sugimura, Kevin Johnson, Joshua Walters
@version 2.4
@since 1.0
*/
import java.util.*;
import java.sql.SQLException;
import java.util.stream.*;

public class Inventory {    
    private ArrayList<FoodItem> foodItems = new ArrayList<>();
    private Database database = new Database("jdbc:mysql://localhost/food_inventory", "student", "ensf");
    private int minWaste;

    /*Fills the hampers with food satisfying their nutritional requirements, minimizing waste
     If successful, moves items from inventory to hampers and returns true
     If inventory is insufficient, nothing is moved and returns false
     @param hampers- ArrayList containing all the hampers which we need to make the order for
    */
    public boolean validateOrder(ArrayList<Hamper> hampers) {
        try {
            database.initializeConnection();
        } catch(SQLException e){
            e.printStackTrace();
        }

        convertDatabaseToFoodItemsList();

        for (int i = 0; i < hampers.size(); i++) {
            // Calculate required values
            int[] reqValues = new int[]{0,0,0,0};
            for (Client client: hampers.get(i).getClients()) {
                reqValues[0] += client.getWholeGrains();
                reqValues[1] += client.getFruitVeggies();
                reqValues[2] += client.getProtein();
                reqValues[3] += client.getOther();                
            }
    
            // Attempt to find a best combination if one exists
            ArrayList<FoodItem> bestComb = null;
            minWaste = 0;
            for (int j = 0; j < foodItems.size(); j++) {
                bestComb = combinations(new ArrayList<>(), bestComb, new int[]{0,0,0,0}, reqValues, j);
            }
    
            // If a best combination exists, add items to hamper and remove from inventory
            if (bestComb != null) {
                hampers.get(i).setItems(bestComb);
                foodItems.removeAll(bestComb);
            } else { // If no combination meets the requirements, put all items back in inventory and return false
                for (Hamper hamperUndo: hampers) {
                    foodItems.addAll(hamperUndo.getItems());
                    hamperUndo.resetItems();
                }
                System.out.printf("Insufficient inventory for hamper #%d. No items were removed.\n", i + 1);
                return false;
            }

        }

        System.out.println("Order fulfilled. Items removed from inventory.\n");
        return true;
    }
    
    /* A recursive method that calculates and returns the combination of foods that
     1. meets the caloric requirements of reqValues
     2. minimizes caloric waste
     If no combination meets the requirements, returns null
     @param currComb- current combination of FoodItems that satisfy the client requirements
     @param bestComb- the most efficient combination of FoodItems thus far that satisfy the client requirements
     @param currValues- array containing the amount of nutrients in the hamper
     @param reqValues- array containing the required amount of nutrients for the clients
     @param pos
    */
    private ArrayList<FoodItem> combinations(ArrayList<FoodItem> currComb, ArrayList<FoodItem> bestComb, int[] currValues, int[] reqValues, int pos) {
        // Update currValues
        currComb.add(foodItems.get(pos));
        currValues[0] += foodItems.get(pos).getWholeGrains();
        currValues[1] += foodItems.get(pos).getFruitVeggies();
        currValues[2] += foodItems.get(pos).getProtein();
        currValues[3] += foodItems.get(pos).getOther();

        
        // If currComb meets requirements
        if ((currValues[0] >= reqValues[0] && currValues[1] >= reqValues[1] && currValues[2] >= reqValues[2] && currValues[3] >= reqValues[3])) {
            // If currComb's waste is less than minWaste, replace bestComb
            int currWaste = currValues[0] - reqValues[0] + currValues[1] - reqValues[1] + currValues[2] - reqValues[2] + currValues[3] - reqValues[3];
            
            if (bestComb == null || currWaste < minWaste) {
                System.out.printf("New minWaste: %d\n", currWaste);
                bestComb = new ArrayList<>(currComb);
                minWaste = -reqValues[0] - reqValues[1] - reqValues[2] - reqValues[3];
                if (bestComb != null) {
                    for (FoodItem item: bestComb) {
                        minWaste += item.getCalories();
                    }
                }
            } else {
                currComb.remove(foodItems.get(pos));
                currValues[0] -= foodItems.get(pos).getWholeGrains();
                currValues[1] -= foodItems.get(pos).getFruitVeggies();
                currValues[2] -= foodItems.get(pos).getProtein();
                currValues[3] -= foodItems.get(pos).getOther();
                return bestComb;
            }
        }

        for (int i = pos + 1; i < foodItems.size(); i++) {
            bestComb = combinations(currComb, bestComb, currValues, reqValues, i);
        }

        currComb.remove(foodItems.get(pos));
        currValues[0] -= foodItems.get(pos).getWholeGrains();
        currValues[1] -= foodItems.get(pos).getFruitVeggies();
        currValues[2] -= foodItems.get(pos).getProtein();
        currValues[3] -= foodItems.get(pos).getOther();
        return bestComb;
    }
    
    // Stores the food items in the database in the member variable foodItems
    public void convertDatabaseToFoodItemsList() {
        this.foodItems = database.getFoodValues();
        // FOR TESTING PURPOSES: keeps only a few items in foodItems
        foodItems = new ArrayList<FoodItem>(foodItems.subList(0, 30));
    }
    /* Returns member variable foodItems
    @return foodItems
    */
    public ArrayList<FoodItem> getFoodItems(){
        return foodItems;
    }
    
    public void printShortages() throws InsufficientInventoryException {
        throw new InsufficientInventoryException();
    }

    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        try {
            inventory.database.initializeConnection();
        } catch (SQLException e) {
            System.out.println("Connection failed");
            System.exit(1);
        }
        inventory.convertDatabaseToFoodItemsList();
        
        ArrayList<Hamper> hampers = new ArrayList<>();
        Hamper hamper1 = new Hamper();
        // hamper1.addClient(ClientType.ADULT_MALE, 1);
        // hamper1.addClient(ClientType.ADULT_FEMALE, 1);
        hamper1.addClient(ClientType.CHILD_UNDER_8, 1);
        hamper1.addClient(ClientType.CHILD_OVER_8, 1);
        hampers.add(hamper1);
        // Hamper hamper2 = new Hamper();
        // hamper2.addClient(ClientType.CHILD_OVER_8, 1);
        // hamper2.addClient(ClientType.CHILD_UNDER_8, 1);
        // hampers.add(hamper2);

        long startTime = System.nanoTime();
        System.out.println(inventory.validateOrder(hampers));
        double elapsedTime = (System.nanoTime() - startTime) / 1E9;
        System.out.printf("Elapsed time: %f seconds\n", elapsedTime);
    }

}
