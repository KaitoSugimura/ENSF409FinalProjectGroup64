/**
@author Danny Duong, Kaito Sugimura, Kevin Johnson, Joshua Walters
@version 2.1
@since 1.0
*/
import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class InventoryTest {
    
     /* INVENTORY TESTS */

    // test the getter and validateOrder function
    // validateOrder() should populate the FoodItem ArrayList
    // getFoodItems() should return the FoodItem ArrayList
    @Test
    public void testGetFoodItems(){
        ArrayList<Hamper> list = new ArrayList<>();
        Hamper hamper = new Hamper();
        Inventory inventory = new Inventory();

		hamper.addClient(ClientType.ADULT_MALE, 1);
        list.add(hamper);
        try{
            inventory.validateOrder(list);
        } catch(InsufficientInventoryException e){
            // This exception is tested in another test
        }
        ArrayList<FoodItem> check = null;
        check = inventory.getFoodItems();

        assertNotNull("getFoodItems returns null even after order is validated", check);
    }

    // validateOrder() should populate the FoodItem ArrayList
    // test that that an exception is thrown when insufficient items are present in the inventory
    @Test
    public void testValidateOrderThrowsInsufficientInventoryException(){
        ArrayList<Hamper> list = new ArrayList<>();
        Hamper hamper = new Hamper();
        Inventory inventory = new Inventory();
        boolean exceptionThrown = false;

		hamper.addClient(ClientType.ADULT_MALE, 100);
        list.add(hamper);
        try{
            inventory.validateOrder(list);
        } catch(InsufficientInventoryException e){
            exceptionThrown = true;
        }
        assertTrue("validateOrder did not throw InsufficientInventoryException when not enough fooditems in inventory"
            , exceptionThrown);
    }

}
