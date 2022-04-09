import java.util.*;

public class Application {
    private ArrayList<Hamper> hampers = new ArrayList<>();

    public void calculateOrder(){
        var inventory = new Inventory();  //should not have to initialize on actual implementation
        inventory.validateOrder(this.hampers);
    }

    public void addHamper() {
        this.hampers.add(new Hamper());
    }

    // Removes the hamper at index i in the hampers ArrayList
    public void removeHamper(int i) throws IllegalStateException{
        if (this.hampers.isEmpty() || i < 0 | i >= hampers.size()) {
            throw new IllegalStateException();
        }
        this.hampers.remove(i);
    }

    public void addClient(int i, ClientType bodyType, int quantity){
        this.hampers.get(i).addClient(bodyType, quantity);
    }

    public void removeClient(int i, ClientType bodyType, int quantity) throws IllegalStateException {
        if (this.hampers.get(i).getClients().isEmpty()) {
            throw new IllegalStateException();
        }
        this.hampers.get(i).removeClient(bodyType, quantity);
    }

    public void resetApplication() {
        this.hampers = new ArrayList<>();
    }

    public void requestOrderForm() throws HamperHasNoClientsException {
        // Check to ensure all hampers have at least one client
        if (hampers.isEmpty()) {
            throw new HamperHasNoClientsException();
        }
        for (Hamper hamper: hampers) {
            if (hamper.getClients().isEmpty()) {
                throw new HamperHasNoClientsException();
            }
        }
    }

    public ArrayList<Hamper> getHampers(){
        return hampers;
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
                // System.out.println("    - Calories: " + client.getCalories());
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
