import java.util.*;

public class Hamper {
    private ArrayList<FoodItem> foodItems = new ArrayList<>();
    private ArrayList<Client> clients = new ArrayList<>();

    public void addItem(FoodItem item){
        this.foodItems.add(item);
    }

    public void addClient(ClientType bodyType, int quantity){
        for(int i = 0; i < quantity; i++){
            this.clients.add(new Client(bodyType));
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
    public void removeClient(ClientType bodyType, int N) throws IllegalStateException {
        // My code ain't good but we can improve it later
        // Count up to N occurences
        ArrayList<Client> toBeDeleted = new ArrayList<>();
        int n = 0;
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

}
