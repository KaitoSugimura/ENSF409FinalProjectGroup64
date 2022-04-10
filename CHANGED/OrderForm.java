/**
@author Danny Duong, Kaito Sugimura, Kevin Johnson, Joshua Walters
@version 2.3
@since 1.0
*/

import java.io.*;
import java.util.*;

/*
 * Helper class
 * Writes an orderform to text file
 */
public class OrderForm {
    /**
     * Writes order form to textfile
     * The name of the output will be OrderForm.txt
     * @param hampers ArrayList of hampers for a given order
     */
    public static void printOrder(ArrayList<Hamper> hampers) {
        try {
            FileWriter writer = new FileWriter("OrderForm.txt");
            writer.write("64 Food Bank\nHamper Order Form\n\nName:\nDate:\n\nOriginal Request\n");
            for(int i = 0; i < hampers.size(); i++){
                writer.write("\nHamper"+(i+1)+": ");
                int[] count = hampers.get(i).getClientCount();
                if(count[0]!=0){writer.write(count[0]+" Adult Males, ");}
                if(count[1]!=0){writer.write(count[1]+" Adult Females, ");}
                if(count[2]!=0){writer.write(count[2]+" Children Over 8, ");}
                if(count[3]!=0){writer.write(count[3]+" Children Under 8 ");}                
            }
            writer.write("\n\n");
            for (int i =0; i<hampers.size();i++) {
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
}
