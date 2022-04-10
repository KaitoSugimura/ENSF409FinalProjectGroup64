/**
@author Danny Duong, Kaito Sugimura, Kevin Johnson, Joshua Walters
@version 1.9
@since 1.0
*/

/*
class Hamper - contains data for a given order's hampers.
*/
import java.util.*;

public class Hamper {
    //Fields
    private ArrayList<FoodItem> foodItems = new ArrayList<>();
    private ArrayList<Client> clients = new ArrayList<>();

    //Public Methods
    /**
     * resetItems() - resets the food items contained within the hamper.
     */
    public void resetItems() {
        this.foodItems = new ArrayList<>();
    }

    /**
     * setItems() - update the fooditems arraylist.
     * @param items
     */
    public void setItems(ArrayList<FoodItem> items){
        this.foodItems = items;
    }

    /**
     * addClient() - add a new client to the current hamper.
     * @param bodyType
     * @param quantity
     */
    public void addClient(ClientType bodyType, int quantity){
        for(int i = 0; i < quantity; i++){
            this.clients.add(new Client(bodyType));
        }
    }

    //getters
    public ArrayList<FoodItem> getItems(){
        return this.foodItems;
    }

    public ArrayList<Client> getClients(){
        return this.clients;
    }

    // Removes N clients with the matching bodyType.
    // If N is greater than or equal to the number of matches, all matches will be removed
    public void removeClient(ClientType bodyType, int N) throws IllegalStateException, IllegalArgumentException {
        // My code ain't good but we can improve it later
        // Count up to N occurences
        ArrayList<Client> toBeDeleted = new ArrayList<>();
        int n = 0;

        if (N < 0){
            throw new IllegalArgumentException();
        }

        for (Client client: clients) {
            if (client.getType() == bodyType) {
                toBeDeleted.add(client);
                if (++n == N) {
                    break;
                }
            }
        }

        // If # of occurrences < N, throw exception
        if (n < N) {
            throw new IllegalStateException();
        }

        // Delete
        for (Client client: toBeDeleted){
            clients.remove(client); 
        }
    }

    /**
     * removeALlClients() - removes all clients linked to hamper
     */
    public void removeAllClients(){
        this.clients = new ArrayList<>();
    }
}
