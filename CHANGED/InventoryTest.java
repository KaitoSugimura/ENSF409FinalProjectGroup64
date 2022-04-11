import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
public class InventoryTest {


     /* INVENTORY TESTS */

    // test that printShortages throws a proper exception
    // InsufficientInventoryException() (Custom exception)
    @Test
    public void testPrintShortagesThrowsInsufficientInventoryException(){        
        boolean exceptionThrown = false;
        try{
            Inventory inventory = new Inventory();
            inventory.printShortages();
        } catch(InsufficientInventoryException e){
            exceptionThrown = true;
        }

        assertTrue("printShortages did not throw an InsufficientInventoryException when tested with insuffienct foods in ArrayList"
            , exceptionThrown);
    }

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
        inventory.validateOrder(list);
        ArrayList<FoodItem> check = null;
        check = inventory.getFoodItems();

        assertNotNull("getFoodItems returns null even after order is validated", check);
    }
    
}
