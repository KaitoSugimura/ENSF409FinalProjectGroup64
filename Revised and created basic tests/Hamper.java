import java.util.*;

public class Hamper {
    private ArrayList<FoodItem> foodItems = new ArrayList<>();
    private ArrayList<Client> clients = new ArrayList<>();;

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

    public void removeClient(ClientType bodyType){
        for(int i = 0; i < clients.size(); i ++){
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

}
