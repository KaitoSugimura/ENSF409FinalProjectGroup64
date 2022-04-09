import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class OrderForm {
    public void printOrder(ArrayList<Hamper> hampers){
        try {
            FileWriter writer = new FileWriter("OrderForm.txt");
            writer.write("64 Food Bank\nHamper Order Form\n\nName:\nDate:\n\nOriginal Request\n");
            for(int i =0; i<hampers.size();i++){
                writer.write("Hamper "+(i+1)+" Items:\n");
                ArrayList<FoodItem> temp = hampers.get(i).getItems();
                for(int j = 0; j < temp.size();j++){
                    writer.write(temp.get(j).getName()+"\n");
                }
                writer.write("\n");
            }
            writer.close();
            
        } catch (IOException e) {
            System.out.println("Error writing to file!");
        }
        
    } 
    public static void main(String[] args) {
        ArrayList<Hamper> myhampers = new ArrayList<>();

        ArrayList<FoodItem> myFood = new ArrayList<>();
        FoodItem food1 = new FoodItem(1, "PeanutButter", 420, 69, 21, 11, 13);
        FoodItem food2 = new FoodItem(1, "Burger", 420, 69, 21, 11, 13);
        FoodItem food3 = new FoodItem(1, "Lettuce", 420, 69, 21, 11, 13);
        myFood.add(food1);
        myFood.add(food2);
        myFood.add(food3);

        Hamper hamper1 = new Hamper();
        hamper1.setItems(myFood);
        Hamper hamper2 = new Hamper();
        hamper2.setItems(myFood);

        myhampers.add(hamper1);
        myhampers.add(hamper2);
        
        OrderForm myOrder = new OrderForm();
        myOrder.printOrder(myhampers);
    }
}
