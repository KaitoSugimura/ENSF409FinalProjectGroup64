import org.junit.*;
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
public class FoodItemTest {
    private Application app;

    @Before
    public void setUp() {
        app = new Application();
    }

    /* FOODITEM TESTS */

    //FoodItem(int ID, String name, int wholeGrain, int fruitsVeggies, int protein, int other, int calories)
    //Checks if getItemID() returns correct value
    @Test
    public void testAddGetItemID(){
        FoodItem myItem = new FoodItem(42069, "Food?", 345, 456, 678, 7, 5678);
        int expected = 42069;
        int actual =  myItem.getItemID();
        assertEquals("FoodItem fails to correctly add/or get itemID", expected, actual);
    }

    //FoodItem(int ID, String name, int wholeGrain, int fruitsVeggies, int protein, int other, int calories)
    //Checks if getName() returns correct value
    @Test
     public void testAddGetName(){
        String expected = "Food?";
        FoodItem myItem = new FoodItem(42069, expected, 345, 456, 678, 7, 5678);
        String actual = myItem.getName();
        assertEquals("FoodItem fails to correctly add/or get name", expected, actual);     
    }
    
}
