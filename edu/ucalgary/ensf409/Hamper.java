/**
@author Danny Duong, Kaito Sugimura, Kevin Johnson, Joshua Walters
@version 1.9
@since 1.0
*/
package edu.ucalgary.ensf409;

import java.util.*;

/**
 * Hamper class
 * Contains data for a given order's hampers.
 * that is ArrayList of calculated foodItems and specified Clients
 */
public class Hamper {
    //Fields
    private ArrayList<FoodItem> foodItems = new ArrayList<>();
    private ArrayList<Client> clients = new ArrayList<>();
    
    /**
     * Resets the food items contained within the hamper.
     */
    public void resetItems() {
        this.foodItems = new ArrayList<>();
    }

    /**
     * Update the fooditems arraylist.
     * @param items
     */
    public void setItems(ArrayList<FoodItem> items){
        this.foodItems = items;
    }

    /**
     * Add a new client to the current hamper.
     * @param bodyType ClientType enum body type
     * @param quantity amount of add
     */
    public void addClient(ClientType bodyType, int quantity) throws IllegalArgumentException{
        if(quantity < 0){
            throw new IllegalArgumentException();
        }
        for(int i = 0; i < quantity; i++){
            // this.clients.addAll(Collections.nCopies(new Client(bodyType), quantity));
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
            else if(temp.getType().toString().compareTo("Child under 8") == 0){clientCount[3]++;}
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

    /**
     * Removes all clients linked to hamper
     */
    public void removeAllClients(){
        this.clients = new ArrayList<>();
    }

    // getters
    public ArrayList<FoodItem> getItems(){
        return this.foodItems;
    }

    public ArrayList<Client> getClients(){
        return this.clients;
    }
}
