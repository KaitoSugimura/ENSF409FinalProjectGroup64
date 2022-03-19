
import java.util.*;

public class Application {
    
    private ArrayList<Hamper> hampers = new ArrayList<>();
    public int index = -1;

    public void calculateOrder(){
        var inventory = new Inventory();  //should not have to initialize on actual implementation
        inventory.validateOrder(hampers);
    }

    public void addHamper(){
        this.hampers.add(new Hamper());
        index++;
    }

    public void removeHamper(int amount){

    }

    public void addClient(String bodyType, int quantity){
        this.hampers.get(index).addClient(bodyType, quantity);
    }

    public void removeClient(String bodyType, int quantity){
        this.hampers.get(index).removeClient(bodyType, quantity);
    }

    public void resetApplication(){

    }

    public void requestOrderForm(){

    }

    @Override
    public String toString() {
        int i = 1;
        for (Hamper hamper : hampers) {
            System.out.println("Hamper" + i++ + ":");
            for (FoodItem item : hamper.getItems()) {
                System.out.println(item.getName());
            }
        }
        System.out.println("--------------------");
        return super.toString();
    }


}
