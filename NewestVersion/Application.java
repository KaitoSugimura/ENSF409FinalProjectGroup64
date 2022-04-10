/**
@author Danny Duong, Kaito Sugimura, Kevin Johnson, Joshua Walters
@version 2.1
@since 1.0
*/

import java.util.*;

public class Application {
    private ArrayList<Hamper> hampers = new ArrayList<>();

    //Calculates the order based off of the clients in the hamper. Throws HamperHasNoClientsException if any hamper in hampers has no clients in it
 
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
    //Adds a hamper to private variable hampers
    public void addHamper() {
        this.hampers.add(new Hamper());
    }

    /* Removes the hamper at index i in the hampers ArrayList
    @param i-index at which the hamper we want to remove resides in private variable hampers
    Throws IllegalStateException if private variable hampers is empty or i <0 or if index i is out of bounds of the ArrayList
    */
    public void removeHamper(int i) throws IllegalStateException{
        if (this.hampers.isEmpty() || i < 0 || i >= hampers.size()) {
            throw new IllegalStateException();
        }
        this.hampers.remove(i);
    }
    
    /* Adds a client to the Hamper present in private variable hampers at index i of hampers
    @param i-index where the hamper to which we want to add client resides
    @param bodyType- the body type of the client we want to add to the hamper
    @param quantity- the number of clients of the specified body type we want to add
    */
    public void addClient(int i, ClientType bodyType, int quantity){
        this.hampers.get(i).addClient(bodyType, quantity);
    }
    /* Removes the client of a specific type from the hamper present at index i of hampers
    @param i-index where the hamper from which we want to remove the client resides in private variable hampers
    @param bodyType- body type of the client we want to remove
    @param quantity- how many clients of the specified body type we want to remove from hamper in member variable hampers
    Throws IllegalStateException if the clients ArrayList we want to delete from is empty
    */
    public void removeClient(int i, ClientType bodyType, int quantity) throws IllegalStateException {
        if (this.hampers.get(i).getClients().isEmpty()) {
            throw new IllegalStateException();
        }
        this.hampers.get(i).removeClient(bodyType, quantity);
    }
    
    //Removes all clients from all the hampers in member variable hampers
    public void removeAllClients() {
        for(Hamper hamper : hampers){
            hamper.removeAllClients();
        }
    }

    //Resets the member variable hampers by replacing it with a new ArrayList
    public void resetApplication() {
        this.hampers = new ArrayList<>();
    }
    /* Writes out the order based on the hampers in member variable hampers to a text file
    Throws HamperHasNoClientsException if member variable hampers is empty or if any hamper present in member variable hampers is empty
    */
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
    /* Returns member variable hampers
    @return hampers
    */
    public ArrayList<Hamper> getHampers(){
        return hampers;
    }
    /* Makes a string detailing the body type and the needs of each client in the hampers present in member variable hampers
    @return the generated string
    */
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
