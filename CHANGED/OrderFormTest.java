import org.junit.*;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
public class OrderFormTest {
    ArrayList<Hamper> myhampers = new ArrayList<>();

    //Set up hampers for testing
    @Before
    public void setUp() {
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
        hamper1.addClient(ClientType.ADULT_MALE, 2);
        hamper1.addClient(ClientType.CHILD_OVER_8, 2);
        Hamper hamper2 = new Hamper();
        hamper2.setItems(myFood);
        hamper1.addClient(ClientType.ADULT_FEMALE, 2);
        hamper1.addClient(ClientType.CHILD_UNDER_8, 2);

        myhampers.add(hamper1);
        myhampers.add(hamper2);
        OrderForm.printOrder(myhampers);
        
        
    }

    /* OrderForm TESTS */

    //Creates order form txt file
    @Test
    public void testOrderFormTextFileCreated(){
        
        
        File f = new File("OrderForm.txt");
        boolean expected = true;
        boolean actual = f.exists() && !f.isDirectory();
        assertEquals("File was not created as expected",expected, actual);

    }

    //Tests Output File format
    @Test
    public void testOutputFileFormat(){
        String expected = "64 Food Bank\nHamper Order Form\n\nName:\nDate:\n\nOriginal Request\n\nHamper1: 2 Adult Males, 2 Adult Females, 2 Children Over 8, 2 Children Under 8 \nHamper2:\n\nHamper 1 Items:\n1\tBurger\n2\tLettuce\n1\tPeanutButter\n\nHamper 2 Items:\n1\tBurger\n2\tLettuce\n1\tPeanutButter";
        String actual = "";
        try {
            File file = new File("OrderForm.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st="";
            while((st=br.readLine())!= null){
                actual += st+"\n";
            }
            
            assertEquals("Output file was not formated as expected",expected, actual);
            
        } catch (Exception e) {
            System.out.println("There was an error opening the file!");
        }
    }
}