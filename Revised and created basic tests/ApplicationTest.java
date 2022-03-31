
//package edu.ucalgary.ensf409;

// javac -cp .;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar;lib/mysql-connector-java-8.0.23.jar *.java
// java -cp .;lib/mysql-connector-java-8.0.23.jar;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore ApplicationTest

import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class ApplicationTest {
    private Application app;

    @Before
    public void setUp() {
        app = new Application();
    }

    /* APPLICATION TESTS */

    // addHamper() is used to add a hamper.
    // getHampers() returns an ArrayList which contains the added hamper.
    @Test
    public void testAddHamperAddsHamper(){
        try{
            app.addHamper();
        } catch (HamperHasNoClientsException e){
            // This exception is tested in another test.
        }

        assertFalse("addHamper() did not add hamper", app.getHamper().isEmpty());
    }

    // removeHamper() is used to remove a hamper.
    // getHampers() returns an ArrayList where the removed hamper is absent.
    @Test
    public void testRemoveHamperRemovesHamper(){
        try{
            app.addHamper();
            app.removeHamper();
        } catch (HamperHasNoClientsException e){
            // This exception is tested in another test.
        }
        assertTrue("removeHamper() did not remove hamper", app.getHamper().isEmpty());
    }

    // removeHamper() is called on an application with no hampers.
    // removeHamper() throws an exception.
    @Test
    public void testRemoveHamperWhenNoHampersGivesException(){
        boolean correctException = false;

        try{
            app.removeHamper();
        } catch (IllegalStateException e){
            correctException = true;
        }

        assertEquals("removeHamper() did not throw exception when no hampers existed"
            , true, correctException);
    }

    // addClient() is used to add a client to a hamper.
    // getClient() returns an ArrayList containing the added client.
    @Test
    public void testAddClientAddsClient(){
        try{
            app.addHamper();
            app.addClient(ClientType.ADULT_MALE, 1);
        } catch (HamperHasNoClientsException e){
            // This exception is tested in another test.
        }

        assertFalse("addClient() did not add client", app.getHamper().get(0).getClients().isEmpty());
    }

    // removeClient() is used to remove a client from a hamper.
    // getClient() returns an ArrayList where the removed client is absent.
    @Test
    public void testRemoveClientRemovesClient(){
        try{
            app.addHamper();

            app.addClient(ClientType.ADULT_MALE, 1);
            app.addClient(ClientType.ADULT_FEMALE, 10);
            app.addClient(ClientType.CHILD_OVER_8, 20);
            app.addClient(ClientType.CHILD_UNDER_8, 2);

            app.removeClient(ClientType.ADULT_MALE);
            for(int i = 0; i<10; i++)
                app.removeClient(ClientType.ADULT_FEMALE);
            for(int i = 0; i<20; i++)
                app.removeClient(ClientType.CHILD_OVER_8);
            app.removeClient(ClientType.CHILD_UNDER_8);
            app.removeClient(ClientType.CHILD_UNDER_8);

        } catch (HamperHasNoClientsException e){
            // This exception is tested in another test.
        }

        assertTrue("removeClient() did not remove client", app.getHamper().get(0).getClients().isEmpty());
    }

    // removeClient() is used to remove a client from a hamper with no clients.
    // removeClient() throws an exception.
    @Test
    public void testRemoveClientWhenNoClientsGivesException(){
        boolean correctException = false;

        try{

            app.addHamper();
            app.removeClient(ClientType.ADULT_MALE);

        } catch (IllegalStateException e){
            correctException = true;
        } catch (HamperHasNoClientsException e){
            // This exception is tested in another test.
        }

        assertEquals("removeClient() did not remove client when none existed"
            , true, correctException);
    } 

    // resetApplication() is used to remove all hampers.
    // getHamper() returns an empty ArrayList.
    @Test
    public void testResetApplicationRemovesAllHampers(){
        boolean isEmpty = false;
        int expectedIndex = -1;
        int actualIndex = 0;
        try{

            app.addHamper();
            app.addClient(ClientType.ADULT_MALE, 2);
            app.addHamper();
            app.addClient(ClientType.ADULT_MALE, 1);
            app.addClient(ClientType.ADULT_FEMALE, 1);
            app.addClient(ClientType.CHILD_OVER_8, 1);
            app.addClient(ClientType.CHILD_UNDER_8, 1);
            app.resetApplication();
            isEmpty = app.getHamper().isEmpty();
            actualIndex = app.getIndex();

        } catch (HamperHasNoClientsException e){
            // This exception is tested in another test.
        }

        assertEquals("resetApplication() did not empty hamper", true, isEmpty);
        assertEquals("Application hamper is not empty upon reset", expectedIndex, actualIndex); // TO-DO: change or remove
    }

    // addHamper() is used where an existing hamper has no clients.
    // addHamper() throws HamperHasNoClientsException.
    @Test
    public void testApplicationThrowsHamperHasNoClientsException(){
        boolean correctException = false;

        try{
            app.addHamper();
            app.addHamper();
        } catch (HamperHasNoClientsException e){
            correctException = true;
        }

        assertEquals("addHamper() did not throw HamperHasNoClientsException when an existing hamper is empty"
            , true, correctException);
    }

    /* CLIENT TESTS */

    // Client(ClientType) is called
    // getType() returns the correct ClientType
    @Test
    public void testGetType() {
        Client client = new Client(ClientType.ADULT_MALE);
        ClientType actualType = client.getType();
        assertEquals("getType() did not return the correct type",ClientType.ADULT_MALE, actualType);
    }

    // Client(ClientType) is called
    // isHandicapped() returns the correct Boolean
    @Test
    public void testisHandicapped() {
        Client client = new Client(ClientType.ADULT_MALE, true);
        Boolean actualBool = client.isHandicapped();
        assertEquals("isHandicapped() did not return the correct boolean", true, actualBool);
    }

    /* HAMPER TESTS */
	//FoodItem is called and FoodItem object is added to Hamper. 
	//The element present in ArrayList should be same as the created FoodItem. 
	@Test
	public void testAddGetFood(){
		Hamper hamper=new Hamper();
		FoodItem food=new FoodItem(0,"default",0,0,0,0,0);
		hamper.addItem(food);
		ArrayList<FoodItem> returnArray=hamper.getItems();
		FoodItem actualFood=returnArray.get(0);
		assertEquals("Hamper fails to correctly add and/or get FoodItem",food,actualFood);
	}
	
	//Client(ClientType, int id) is called and added to ArrayList clients in Hamper
	//The element present in clients should be the same as the created Client
	@Test
	public void testAddGetClient(){
		Hamper hamper=new Hamper();
		Client client=new Client(ClientType.ADULT_MALE, false);
		hamper.addClient(ClientType.ADULT_MALE, 10);
		ArrayList<Client> clients=hamper.getClients();
		Client actualClient=clients.get(0);
		assertEquals("Hamper fails to correctly add and/or get Client",client,actualClient);
	}
	
	// Client(ClientType, int) is called and added to Array
	// Checks if the Arraylist is empty after removing the only client present in it
	@Test
	public void testRemoveClient(){
		Hamper hamper=new Hamper();
		hamper.addClient(ClientType.ADULT_MALE, 10);
		hamper.removeClient(ClientType.ADULT_MALE, 10);
		ArrayList<Client> clients=hamper.getClients();
		boolean expected = false;
		if (clients.isEmpty()) {
			expected = true;
		}
		assertTrue("The client object was not removed from the clients ArrayList",expected);
	}

    /* INVENTORY TESTS */

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

    /* NUTRITION TESTS(?) */

    //Creates food item  and clientand tests return whole grains
    @Test
    public void testAddGetWholeGrains(){
        int expected = 345;
        FoodItem myItem = new FoodItem(42069, "Food?", 345, 456, 678, 7, 5678);
        int actual = myItem.getWholeGrains();
        assertEquals("FoodItem fails to correctly add/or get Wholegrains", expected, actual);
        Client client= new Client(ClientType.ADULT_MALE, false);
        expected = 0; //Constant from database
        actual = client.getWholeGrains();
        assertEquals("Client fails to correctly add/or get Wholegrains", expected, actual);
    }

    //Creates food item  and clientand tests return Fruits and veggies
    @Test
    public void testAddGetFruitsVeggies(){
        int expected = 456;
        FoodItem myItem = new FoodItem(42069, "Food?", 345, 456, 678, 7, 5678);
        int actual = myItem.getFruitVeggies();
        assertEquals("FoodItem fails to correctly add/or get Wholegrains", expected, actual);
        Client client= new Client(ClientType.ADULT_MALE, false);
        expected = 0; //Constant from database
        actual = client.getFruitVeggies();
        assertEquals("Client fails to correctly add/or get Wholegrains", expected, actual);
    }

    //Creates food item  and clientand tests return protein
    @Test
    public void testAddGetProtein(){
        int expected = 678;
        FoodItem myItem = new FoodItem(42069, "Food?", 345, 456, 678, 7, 5678);
        int actual = myItem.getProtein();
        assertEquals("FoodItem fails to correctly add/or get Wholegrains", expected, actual);
        Client client= new Client(ClientType.ADULT_MALE, false);
        expected = 0; //Constant from database
        actual = client.getProtein();
        assertEquals("Client fails to correctly add/or get Wholegrains", expected, actual);
    }

    //Creates food item  and clientand tests return other
    @Test
    public void testAddGetother(){
        int expected = 7;
        FoodItem myItem = new FoodItem(42069, "Food?", 345, 456, 678, 7, 5678);
        int actual = myItem.getOther();
        assertEquals("FoodItem fails to correctly add/or get Wholegrains", expected, actual);
        Client client= new Client(ClientType.ADULT_MALE, false);
        expected = 0; //Constant from database
        actual = client.getOther();
        assertEquals("Client fails to correctly add/or get Wholegrains", expected, actual);
    }

    //Creates food item  and clientand tests return calories
    @Test
    public void testAddGetCalories(){
        int expected = 5678;
        FoodItem myItem = new FoodItem(42069, "Food?", 345, 456, 678, 7, 5678);
        int actual = myItem.getCalories();
        assertEquals("FoodItem fails to correctly add/or get Wholegrains", expected, actual);
        Client client= new Client(ClientType.ADULT_MALE, false);
        expected = 0; //Constant from database
        actual = client.getCalories();
        assertEquals("Client fails to correctly add/or get Wholegrains", expected, actual);
    }
}
