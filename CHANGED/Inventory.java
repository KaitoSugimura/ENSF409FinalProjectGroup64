/**
@author Danny Duong, Kaito Sugimura, Kevin Johnson, Joshua Walters
@version 2.4
@since 1.0
*/

import java.util.*;
import java.sql.SQLException;

/** 
 * Contains and manages the inventory of food items
 */
public class Inventory {    
    private ArrayList<FoodItem> foodItems = new ArrayList<>();
    private Database database = new Database("jdbc:mysql://localhost/food_inventory", "student", "ensf");
    private int minWaste;

    /**
     * Fills the hampers with food satisfying their nutritional requirements, minimizing waste
     * If successful, moves items from inventory to hampers and returns true
     * If inventory is insufficient, nothing is moved and returns false
     * @param hampers - ArrayList containing all hampers which we need to make the order for
     * @return true if successful, false otherwise
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
    
    /** 
     * A recursive method that calculates and returns the combination of foods that
     * 1. meets the caloric requirements of reqValues
     * 2. minimizes caloric waste
     * If no combination meets the requirements, returns null
     * @param currComb - current combination of food items being considered
     * @param bestComb - the most efficient combination of food items thus far that satisfy the hamper's requirements
     * @param currValues - array containing the amount of nutrients in the hamper
     * @param reqValues - array containing the required amount of nutrients for the hamper
     * @param pos - index in the Arraylist of food items
     * @return the bestComb of the current recursion. The base call will return the bestComb of all recursions 
     */
    private ArrayList<FoodItem> combinations(ArrayList<FoodItem> currComb, ArrayList<FoodItem> bestComb, int[] currValues, int[] reqValues, int pos) {
        FoodItem item = foodItems.get(pos);
        int wholeGrains = item.getWholeGrains();
        int fruitsVeggies = item.getFruitVeggies();
        int protein = item.getProtein();
        int other = item.getOther();

        // Update currValues
        currValues[0] += wholeGrains;
        currValues[1] += fruitsVeggies;
        currValues[2] += protein;
        currValues[3] += other;
        
        // If currComb meets requirements
        if ((currValues[0] >= reqValues[0] && currValues[1] >= reqValues[1] && currValues[2] >= reqValues[2] && currValues[3] >= reqValues[3])) {
            // If bestComb doesn't exist or currComb's waste is less than minWaste, replace bestComb
            int currWaste = currValues[0] - reqValues[0] + currValues[1] - reqValues[1] + currValues[2] - reqValues[2] + currValues[3] - reqValues[3];
            
            if (bestComb == null || currWaste < minWaste) {
                System.out.printf("New minWaste: %d\n", currWaste);
                bestComb = new ArrayList<>(currComb);
                minWaste = currWaste;
            } else {
                currValues[0] -= wholeGrains;
                currValues[1] -= fruitsVeggies;
                currValues[2] -= protein;
                currValues[3] -= other;
                return bestComb;
            }
        }
        
        currComb.add(item);
        for (int i = pos + 1; i < foodItems.size(); i++) {
            bestComb = combinations(currComb, bestComb, currValues, reqValues, i);
        }

        currComb.remove(item);
        currValues[0] -= wholeGrains;
        currValues[1] -= fruitsVeggies;
        currValues[2] -= protein;
        currValues[3] -= other;
        return bestComb;
    }
    
    /**
     * Reads all food items in the database into the ArrayList foodItems
     */ 
    public void convertDatabaseToFoodItemsList() {
        this.foodItems = database.getFoodValues();
        // FOR TESTING PURPOSES: keeps only a few items in foodItems
        foodItems = new ArrayList<FoodItem>(foodItems.subList(0, 30));
    }

    /**
     * Returns the ArrayList of food items
     * @return the ArrayList of food items
     */
    public ArrayList<FoodItem> getFoodItems(){
        return foodItems;
    }
    
    public void printShortages() throws InsufficientInventoryException {
        throw new InsufficientInventoryException();
    }

    // REMOVE BEFORE SUBMITTING ----------------------------------------
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        
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
        inventory.validateOrder(hampers);
        double elapsedTime = (System.nanoTime() - startTime) / 1E9;
        System.out.printf("Elapsed time: %f seconds\n", elapsedTime);
    }

}