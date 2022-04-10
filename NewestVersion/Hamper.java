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

    /**
     * getClientCount - retunrs client info in format for the order form
     * @return arraylist of client type count
     */
    public int[] getClientCount(){
        int[] clientCount = new int[4];
        for(int i =0;i<this.clients.size();i++){
            Client temp = this.clients.get(i);
            if(temp.getType().toString().compareTo("Adult Male") == 0){clientCount[0]++;}
            else if(temp.getType().toString().compareTo("Adult Female") == 0){clientCount[1]++;}
            else if(temp.getType().toString().compareTo("Child over 8") == 0){clientCount[2]++;}
            else if(temp.getType().toString().compareTo("Child under 8") == 0){clientCount[0]++;}
        }
        return clientCount;
    }

    /**
     * getFooditemCount - returns food items in proper format for oder form
     * @return hashmap of all food and quatity
     */
    public HashMap<String, Integer> getFoodItemCount(){
        HashMap<String, Integer> myFoodCount = new HashMap<>();
        ArrayList<FoodItem> temp = this.foodItems;
        for(int i =0;i<temp.size();i++){
            String tempFood = temp.get(i).getName();
            if(myFoodCount.get(tempFood)!= null){
                myFoodCount.put(tempFood, myFoodCount.get(tempFood)+1);
            }
            else{
                myFoodCount.put(tempFood, 1);
            }
        }
        return myFoodCount;
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
