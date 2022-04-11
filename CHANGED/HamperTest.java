import org.junit.*;
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class HamperTest {

    private Application app;

    @Before
    public void setUp() {
        app = new Application();
    }

     /* HAMPER TESTS */

	// addItem() is used to add a FoodItem to the hamper.
    // getItems() is used to return the hamper's FoodItems as an ArrayList.
	// The element present in this ArrayList should be same as the added FoodItem. 
	@Test
	public void testAddItemGetItems() {
		Hamper hamper = new Hamper();
		ArrayList<FoodItem> testItems = new ArrayList<>();
        FoodItem food = new FoodItem(0,"default",0,0,0,0,0);
		testItems.add(food);
		hamper.setItems(testItems);
		ArrayList<FoodItem> returnArray = hamper.getItems();
		FoodItem actualFood = returnArray.get(0);
		assertEquals("Hamper failed to correctly add and/or get FoodItem",food,actualFood);
	}
	
	//Client(ClientType, int id) is called and added to ArrayList clients in Hamper
	//The element present in clients should be the same as the added Client
	@Test
	public void testAddClient(){
		Hamper hamper = new Hamper();
		ClientType expectedClientType = ClientType.CHILD_UNDER_8;
		hamper.addClient(ClientType.CHILD_UNDER_8, 10);
		ArrayList<Client> clients=hamper.getClients();
		ClientType actualClientType=clients.get(0).getType();
		assertEquals("Hamper failed to correctly add and/or get Client",expectedClientType,actualClientType);
	}
	
	// Client(ClientType, int) is called and added to Array
	// Checks if the Arraylist is empty after removing all clients
	@Test
	public void testRemoveAllClient() {
		Hamper hamper = new Hamper();
		hamper.addClient(ClientType.ADULT_MALE, 10);
		hamper.removeAllClients();
		ArrayList<Client> clients = hamper.getClients();
		boolean removed = false;
		if (clients.isEmpty()) {
			removed = true;
		}
		assertTrue("The client object was not removed from the clients ArrayList", removed);
	}

	// removeClient() should not throw an error even when called on empty hamper
	@Test
	public void testRemoveClientWhenNoClientsThrowsException() {
        Boolean exceptionThrown = false;
		Hamper hamper = new Hamper();
        try {
            hamper.removeAllClients();
        } catch (Exception e) {
            exceptionThrown = true;
        }

		assertFalse("removeClient() threw an unknown error when called on empty hamper", exceptionThrown);
	}
    
}
