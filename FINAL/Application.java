/**
@author Danny Duong, Kaito Sugimura, Kevin Johnson, Joshua Walters
@version 2.1
@since 1.0
*/

import java.util.*;

/** 
 * Class with all program functionalities
 * Completely functional but with no GUI or Main function (Must be called)
 * Contains a hamper ArrayList and creates an instance of inventory once the user wishes to calculate order
 * Option to add remove Hampers/Clients
 * Overrides toString()
 */
public class Application {

    private ArrayList<Hamper> hampers = new ArrayList<>();
    private Inventory inventory = new Inventory();

    /** 
     * Constructor 
     * Calculates the order based off of the clients in the hamper. 
     * Throws HamperHasNoClientsException if there are no hampers or no clients in any of the hampers
     * @throws HamperHasNoClientsException
     */
    public void calculateOrder() throws HamperHasNoClientsException, InsufficientInventoryException {
        if (hampers.isEmpty()) {
            throw new HamperHasNoClientsException();
        }
        for (Hamper hamper: hampers) {
            if (hamper.getClients().isEmpty()) {
                throw new HamperHasNoClientsException();
            }
        }

        inventory.validateOrder(this.hampers);
    }

    /**
     *  Add a hamper to the ArrayList
     */
    public void addHamper() {
        this.hampers.add(new Hamper());
    }

    /** 
     * Removes the hamper at index i in the hampers ArrayList
     * Throws IllegalStateException if hampers is empty or i is negative or bigger than the ArrayList
     * @param i specified removing index of Hamper ArrayList
     * @throws IllegalStateException
     */
    public void removeHamper(int i) throws IllegalStateException{
        if (this.hampers.isEmpty() || i < 0 || i >= hampers.size()) {
            throw new IllegalStateException();
        }
        this.hampers.remove(i);
    }
    
    /** 
     * Adds a client to the Hamper present in private variable hampers at index i of hampers
     * @param i-index where the hamper to which we want to add client resides
     * @param bodyType- the body type of the client we want to add to the hamper
     * @param quantity- the number of clients of the specified body type we want to add
     */
    public void addClient(int i, ClientType bodyType, int quantity){
        this.hampers.get(i).addClient(bodyType, quantity);
    }
    
    /**
     * Removes all clients from all the hampers in member variable hampers
     */
    public void removeAllClients() {
        for(Hamper hamper : hampers){
            hamper.removeAllClients();
        }
    }

    /**
     * Resets the member variable hampers by replacing it with a new ArrayList
     */
    public void resetApplication() {
        this.hampers = new ArrayList<>();
    }

    /**
     * Writes out the order based on the hampers in member variable hampers to a text file
     * Throws HamperHasNoClientsException if member variable hampers is empty or if any hamper present in member variable hampers is empty
     * @throws HamperHasNoClientsException
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

        OrderForm.printOrder(this.toString());
    }

    /**
     * Returns member variable hampers
     * @return hampers ArrayList
     */
    public ArrayList<Hamper> getHampers(){
        return hampers;
    }

    /**
     * get the shortages from inventory
     * @return shortages String
     */
    public Inventory getInventory(){
        return inventory;
    }

    /**
     * Converts the Hampers present into a printable order list with food items
     * @return String list
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("64 Food Bank\nHamper Order Form\n\nName:\nDate:\n\nOriginal Request\n");
        for(int i = 0; i < hampers.size(); i++){
            builder.append("\nHamper"+(i+1)+": ");
            int[] count = hampers.get(i).getClientCount();
            if(count[0]!=0){builder.append(count[0]+" Adult Males, ");}
            if(count[1]!=0){builder.append(count[1]+" Adult Females, ");}
            if(count[2]!=0){builder.append(count[2]+" Children Over 8, ");}
            if(count[3]!=0){builder.append(count[3]+" Children Under 8 ");}                
        }
        builder.append("\n\n");
        for (int i =0; i<hampers.size();i++) {
            builder.append("Hamper "+(i+1)+" Items:\n");
            HashMap <String, Integer> temp = hampers.get(i).getFoodItemCount();
            for(Map.Entry<String, Integer> mapElement: temp.entrySet()){
                builder.append((Integer)mapElement.getValue()+"\t");
                builder.append((String)mapElement.getKey()+"\n");                    
            }
            builder.append("\n");
        }

        return builder.toString();
    }

    /**
     * Converts the Hampers present into a deatiled list of clients
     * @return String list
     */
    public String toStringList(){
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
