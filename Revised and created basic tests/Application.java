import java.util.*;

public class Application {
    private ArrayList<Hamper> hampers = new ArrayList<>();
    private int index = -1;

    public void calculateOrder(){
        var inventory = new Inventory();  //should not have to initialize on actual implementation
        inventory.validateOrder(this.hampers);
    }

    public void addHamper() throws HamperHasNoClientsException {
        // Check to ensure the user does not add another hamper if the last one has no clients
        if (index > -1 && this.hampers.get(index).getClients().isEmpty()){
            throw new HamperHasNoClientsException();
        }
        this.hampers.add(new Hamper());
        index++;
    }

    public void removeHamper() throws IllegalStateException{
        if (index < 0) {
            throw new IllegalStateException();
        }
        this.hampers.remove(index--);
    }

    public void addClient(ClientType bodyType, int quantity){
        this.hampers.get(index).addClient(bodyType, quantity);
    }

    public void removeClient(ClientType bodyType) throws IllegalStateException {
        if(this.hampers.get(index).getClients().isEmpty()){
            throw new IllegalStateException();
        }
        this.hampers.get(index).removeClient(bodyType);
    }

    public void resetApplication(){
        this.hampers = new ArrayList<>();
        this.index = -1;
    }

    public void requestOrderForm(){

    }

    public ArrayList<Hamper> getHamper(){
        return hampers;
    }

    public int getIndex(){
        return index;
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
