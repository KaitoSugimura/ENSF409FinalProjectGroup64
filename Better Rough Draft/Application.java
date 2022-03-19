
import java.util.*;

public class Application {
    
    private ArrayList<Hamper> hampers = new ArrayList<>();
    public int index = -1;

    public void calculateOrder(){
        var inventory = new Inventory();  //should not have to initialize on actual implementation
        inventory.validateOrder(hampers);
    }

    public void addHamper(){
        this.hampers.add(new Hamper());
        index++;
    }

    public void removeHamper(int amount){

    }

    public void addClient(ClientType bodyType, int quantity){
        this.hampers.get(index).addClient(bodyType, quantity);
    }

    public void removeClient(String bodyType, int quantity){
        this.hampers.get(index).removeClient(bodyType, quantity);
    }

    public void resetApplication(){

    }

    public void requestOrderForm(){

    }

    @Override
    public String toString() {
        int i = 1;
        int j = 1;
        for (Hamper hamper : hampers) {
            System.out.println("Hamper" + i++ + ":");
            for (Client client : hamper.getClients()) {
                System.out.println("    Client" + j++ + " (" + client.getType() + ") :");
                System.out.println("    - WholeGrains: " + client.getWholeGrains());
                System.out.println("    - FruitVeggies: " + client.getFruitVeggies());
                System.out.println("    - Protien: " + client.getProtein());
                System.out.println("    - Other: " + client.getOther());
                System.out.println("    - Calories: " + client.getCalories());
            }
            System.out.println("     _______________________________");
            System.out.println("    |     FoodItems:");
            for (FoodItem item : hamper.getItems()) {
                System.out.println("    |     - " + item.getName());
                System.out.println("    |        > ItemID: " + item.getItemID());
                System.out.println("    |        > Calories: " + item.getCalories());
            }
            System.out.println("    |_______________________________");
        }
        System.out.println("------------------------------------------------");
        return super.toString();
    }


}
