import org.junit.*;
import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
public class OrderFormTest {
    ArrayList<Hamper> myhampers = new ArrayList<>();

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
        
        
    }

    /* OrderForm TESTS */

    //Creates order form txt file
    @Test
    public void testOrderFormTextFileCreated(){
        //OrderForm.printOrder(myhampers);
        
        File f = new File("OrderForm.txt");
        boolean expected = true;
        boolean actual = f.exists() && !f.isDirectory();
        assertEquals("File was not created as expected",expected, actual);

    }
    
}
