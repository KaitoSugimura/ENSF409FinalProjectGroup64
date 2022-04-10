import java.util.*;

public class Application {
    private ArrayList<Hamper> hampers = new ArrayList<>();

    public void calculateOrder() throws HamperHasNoClientsException {
        if (hampers.isEmpty()) {
            throw new HamperHasNoClientsException();
        }
        for (Hamper hamper: hampers) {
            if (hamper.getClients().isEmpty()) {
                throw new HamperHasNoClientsException();
            }
        }

        var inventory = new Inventory();  //should not have to initialize on actual implementation
        inventory.validateOrder(this.hampers);
    }

    public void addHamper() {
        this.hampers.add(new Hamper());
    }

    // Removes the hamper at index i in the hampers ArrayList
    public void removeHamper(int i) throws IllegalStateException{
        if (this.hampers.isEmpty() || i < 0 || i >= hampers.size()) {
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

    public void removeAllClients() {
        for(Hamper hamper : hampers){
            hamper.removeAllClients();
        }
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

        OrderForm.printOrder(this.hampers);
    }

    public ArrayList<Hamper> getHampers(){
        return hampers;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        int i = 1;
        int j = 1;
        for (Hamper hamper : hampers) {
            builder.append("\nHamper" + i++ + ":");
            for (Client client : hamper.getClients()) {
                builder.append("\n\n    Client" + j++ + " (" + client.getType() + ") :");
                builder.append("\n\t    - WholeGrains: " + client.getWholeGrains());
                builder.append("\n\t    - FruitVeggies: " + client.getFruitVeggies());
                builder.append("\n\t    - Protien: " + client.getProtein());
                builder.append("\n\t    - Other: " + client.getOther());
                builder.append("\n\t    - Calories: " + client.getCalories() + "\n");
            }
            builder.append("\n------------------------------------------------");
        }
        return builder.toString();
    }


}
