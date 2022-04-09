import java.util.*;
import java.sql.SQLException;

public class Inventory {    
    private ArrayList<FoodItem> foodItems = new ArrayList<>();
    private Database database = new Database("jdbc:mysql://localhost/food_inventory", "student", "ensf");

    public boolean validateOrder(ArrayList<Hamper> hampers) {
        try {
            database.initializeConnection();
        } catch(SQLException e){
            e.printStackTrace();
        }

        for (Hamper hamper: hampers) {
            // Calculate required values
            int[] reqValues = new int[]{0,0,0,0};
            for (Client client: hamper.getClients()) {
                reqValues[0] += client.getWholeGrains();
                reqValues[1] += client.getFruitVeggies();
                reqValues[2] += client.getProtein();
                reqValues[3] += client.getOther();                
            }

            // Attempt to find a best combination if one exists
            ArrayList<FoodItem> bestComb = null;
            for (int i = 0; i < foodItems.size(); i++) {
                bestComb = combinations(new ArrayList<>(), bestComb, new int[]{0,0,0,0}, reqValues, i);
            }

            // If a best combination exists, add items to hamper and remove from inventory
            if (bestComb != null) {
                hamper.setItems(bestComb);
                foodItems.removeAll(bestComb);
            } else { // If no combination meets the requirements, put all items back in inventory and return false
                for (Hamper hamperUndo: hampers) {
                    foodItems.addAll(hamperUndo.getItems());
                    hamperUndo.resetItems();
                }
                System.out.println("Insufficient inventory. No items were removed.");
                return false;
            }
        }

        System.out.println("Order fulfilled. Items removed from inventory.");
        return true;
    }
    
    // A recursive method that calculates and returns the combination of foods that
    // 1. meets the caloric requirements of reqValues
    // 2. minimizes caloric waste
    // If no combination meets the requirements, returns null
    // 
    // currComb: each recursion adds an item to currComb
    // bestComb: the current combination with the least waste
    // currValues: the nutritional contents of currComb
    // reqValues: the required nutritional contents
    // pos: the index in foodItems that the recursion uses
    private ArrayList<FoodItem> combinations(ArrayList<FoodItem> currComb, ArrayList<FoodItem> bestComb, int[] currValues, int[] reqValues, int pos) {
        // update currValues
        currComb.add(foodItems.get(pos));
        currValues[0] += foodItems.get(pos).getWholeGrains();
        currValues[1] += foodItems.get(pos).getFruitVeggies();
        currValues[2] += foodItems.get(pos).getProtein();
        currValues[3] += foodItems.get(pos).getOther();

        // Calculate minWaste
        int minWaste = -reqValues[0] - reqValues[1] - reqValues[2] - reqValues[3];
        if (bestComb != null) {
            for (FoodItem item: bestComb) {
                minWaste += item.getCalories();
            }
        }
        
        // If currComb meets requirements
        if (currValues[0] >= reqValues[0] && currValues[1] >= reqValues[1] && currValues[2] >= reqValues[2] && currValues[3] >= reqValues[3]) {
            int currWaste = currValues[0] - reqValues[0] + currValues[1] - reqValues[1] + currValues[2] - reqValues[2] + currValues[3] - reqValues[3];
            // If currComb's waste is less than minWaste, replace bestComb
            if (bestComb == null || currWaste < minWaste) {
                // System.out.printf("New minWaste: %d\n", currWaste);
                bestComb = new ArrayList<>(currComb);
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
    
    public void convertDatabaseToFoodItemsList() {
        this.foodItems = database.getFoodValues();
        // FOR TESTING PURPOSES: keeps only a few items in foodItems
        foodItems = new ArrayList<FoodItem>(foodItems.subList(0, 30));
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
        
        ArrayList<Hamper> hampers = new ArrayList<>();
        Hamper hamper1 = new Hamper();
        hamper1.addClient(ClientType.ADULT_FEMALE, 1);
        hampers.add(hamper1);
        Hamper hamper2 = new Hamper();
        hamper2.addClient(ClientType.CHILD_OVER_8, 1);
        hamper2.addClient(ClientType.CHILD_UNDER_8, 1);
        hampers.add(hamper2);

        long startTime = System.nanoTime();
        System.out.println(inventory.validateOrder(hampers));
        double elapsedTime = (System.nanoTime() - startTime) / 1E9;
        System.out.printf("Elapsed time: %f seconds\n", elapsedTime);
    }
}
