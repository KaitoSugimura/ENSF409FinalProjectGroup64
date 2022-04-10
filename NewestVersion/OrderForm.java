/**
@author Danny Duong, Kaito Sugimura, Kevin Johnson, Joshua Walters
@version 2.3
@since 1.0
*/

/*
class OrderForm - writes an orderform text file
*/
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OrderForm {
    /**
     * printOrder - writes order form textfile
     * @param hampers - arraylist of hampers for a given order
     */
    public static void printOrder(ArrayList<Hamper> hampers){
        try {
            FileWriter writer = new FileWriter("OrderForm.txt");
            writer.write("64 Food Bank\nHamper Order Form\n\nName:\nDate:\n\nOriginal Request\n");
            for(int i =0; i<hampers.size();i++){
                writer.write("\nHamper"+(i+1)+": ");
                int[] count = hampers.get(i).getClientCount();
                if(count[0]!=0){writer.write(count[0]+"Adult Males, ");}
                if(count[1]!=0){writer.write(count[1]+"Adult Females, ");}
                if(count[2]!=0){writer.write(count[2]+"Children Over 8, ");}
                if(count[3]!=0){writer.write(count[3]+"Children Under 8 ");}                
            }
            writer.write("\n\n");
            for(int i =0; i<hampers.size();i++){
                writer.write("Hamper "+(i+1)+" Items:\n");
                HashMap <String, Integer> temp = hampers.get(i).getFoodItemCount();
                for(Map.Entry<String, Integer> mapElement: temp.entrySet()){
                    writer.write((Integer)mapElement.getValue()+"\t");
                    writer.write((String)mapElement.getKey()+"\n");                    
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
        myFood.add(food3);


        Hamper hamper1 = new Hamper();
        hamper1.setItems(myFood);
       //hamper1.addClient(ClientType.ADULT_MALE, 2);
        //hamper1.addClient(ClientType.CHILD_OVER_8, 2);
        Hamper hamper2 = new Hamper();
        hamper2.setItems(myFood);
        //hamper1.addClient(ClientType.ADULT_FEMALE, 2);
        //hamper1.addClient(ClientType.CHILD_UNDER_8, 2);

        myhampers.add(hamper1);
        myhampers.add(hamper2);
        
        OrderForm.printOrder(myhampers);
    }
}
