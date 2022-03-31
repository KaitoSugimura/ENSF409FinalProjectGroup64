import java.util.*;

public class Hamper {
    private ArrayList<FoodItem> foodItems = new ArrayList<>();
    private ArrayList<Client> clients = new ArrayList<>();

    public Hamper(){

    }

    public void addItem(FoodItem item){
        this.foodItems.add(item);
    }

    public void addClient(ClientType bodyType, int quantity){
        for(int i = 0; i < quantity; i++){
            this.clients.add(new Client(bodyType));
        }
    }

    // Removes the first occurrence of a client with the given bodyType
    public void removeClient(ClientType bodyType){
        for(int i = 0; i < clients.size(); i++){
            if(bodyType == clients.get(i).getType()){
                clients.remove(i);
                break;
            }
        }
    }

    public ArrayList<FoodItem> getItems(){
        return this.foodItems;
    }

    public ArrayList<Client> getClients(){
        return this.clients;
    }

    // Removes N clients with the matching bodyType.
    // If N is greater than or equal to the number of matches, all matches will be removed
    public void removeClient(ClientType bodyType, int N) {
        for(int i = 0, n = 0; i < clients.size(); i++){
            if (bodyType == clients.get(i).getType()){
                clients.remove(i--);
                if (n == N) {
                    break;
                }
            }
        }
    }

}
