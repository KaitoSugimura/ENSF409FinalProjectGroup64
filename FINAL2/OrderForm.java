/**
@author Danny Duong, Kaito Sugimura, Kevin Johnson, Joshua Walters
@version 2.3
@since 1.0
*/

import java.io.*;

/**
 * Helper class
 * Writes a String to textfile
 */
public class OrderForm {
    /**
     * Writes order form to textfile
     * The name of the output will be OrderForm.txt
     * @param toWrite String to write to file
     */
    public static void printOrder(String toWrite) {
        try {
            FileWriter writer = new FileWriter("OrderForm.txt");
            writer.write(toWrite);
            writer.close();
            
        } catch (IOException e) {
            System.out.println("Error writing to file!");
        }  
    } 

}
