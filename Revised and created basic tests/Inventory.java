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

            ArrayList<FoodItem> bestComb = null;
            int minWaste = 0;
            
            int n = foodItems.size(); // # of food items
            int N = (int) Math.pow(2d, Double.valueOf(n)); // # of combinations
            
            // Iterate through all combinations of food items
            // I adapted this code for finding all combinations:
            // https://stackoverflow.com/questions/37835286/generate-all-possible-combinations-java
            for (int i = 1; i < N; i++) {
                ArrayList<FoodItem> currComb = new ArrayList<>();
                int currWholeGrains = 0;
                int currFruitsVeggies = 0;
                int currProtein = 0;
                int currOther = 0;
                int currCalories = 0;
    
                String code = Integer.toBinaryString(N | i).substring(1);
                for (int j = 0; j < n; j++) {
                    if (code.charAt(j) == '1') {
                        FoodItem item = foodItems.get(j);
                        currComb.add(item);
                        currWholeGrains += item.getWholeGrains();
                        currFruitsVeggies += item.getFruitVeggies();
                        currProtein += item.getProtein();
                        currOther += item.getOther();
                        currCalories += item.getCalories();        
                    }
                }
                
                // If currComb meets requirements and has less waste than bestComb, replace bestComb
                if (currWholeGrains >= reqWholeGrains && currFruitsVeggies >= reqFruitsVeggies && currProtein >= reqProtein && currOther >= reqOther && currCalories >= reqCalories) {
                    int currWaste = currWholeGrains - reqWholeGrains + currFruitsVeggies - reqFruitsVeggies + currProtein - reqProtein + currOther - reqOther + currCalories - reqCalories;
                    if (bestComb == null || currWaste < minWaste) {
                        bestComb = currComb;
                        minWaste = currWaste;
                        System.out.printf("New minWaste: %d\n", minWaste);
                    }
                }
            }

            // If a best combination exists, add items to hamper and remove from inventory
            if (bestComb != null) {
                foodItems.removeAll(bestComb);
                hamper.setItems(bestComb);

            } else { // If no combination meets the requirements, put all items back in inventory and return false
                for (Hamper hamperUndo: hampers) {
                    foodItems.addAll(hamperUndo.getItems());
                    hamperUndo.resetItems();
                }
                return false;
            }
        }

        return true;
    }

    public void convertDatabaseToFoodItemsList() {
        this.foodItems = database.getFoodValues();
        // FOR TESTING PURPOSES: keeps only a few items in foodItems
        foodItems = new ArrayList<FoodItem>(foodItems.subList(0, 15));
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

        long startTime = System.nanoTime();
        inventory.validateOrder(hampers);
        double elapsedTime = (System.nanoTime() - startTime) / 1E9;
        System.out.printf("Elapsed time: %f seconds\n", elapsedTime);
    }
}
