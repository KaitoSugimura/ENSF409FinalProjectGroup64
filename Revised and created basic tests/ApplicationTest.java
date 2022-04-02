
//package edu.ucalgary.ensf409;

// javac -cp .;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar;lib/mysql-connector-java-8.0.23.jar *.java
// java -cp .;lib/mysql-connector-java-8.0.23.jar;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore ApplicationTest

import org.junit.*;
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.sql.SQLPermission;
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
    public void testAddHamperGetHamper() {
        app.addHamper();
        assertFalse("addHamper() did not add hamper", app.getHampers().isEmpty());
    }

    // removeHamper() is used to remove a hamper.
    // getHampers() returns an ArrayList where the removed hamper is absent.
    @Test
    public void testRemoveHamperRemovesHamper() {
        app.addHamper();
        app.removeHamper(0);
        assertTrue("removeHamper() did not remove hamper", app.getHampers().isEmpty());
    }

    // removeHamper() is called on an application with no hampers.
    // removeHamper() throws an exception.
    @Test
    public void testRemoveHamperWhenNoHampersGivesException() {
        boolean correctException = false;

        try {
            app.removeHamper(0);
        } catch (IllegalStateException e){
            correctException = true;
        }

        assertEquals("removeHamper() did not throw exception when no hampers existed"
            , true, correctException);
    }

    // addClient() is used to add a client to a hamper.
    // getClient() returns an ArrayList containing the added client.
    @Test
    public void testAddClientGetClient() {
        app.addHamper();
        app.addClient(0, ClientType.CHILD_OVER_8, 1);
        assertFalse("addClient() did not add client", app.getHampers().get(0).getClients().isEmpty());
    }

    // removeClient() is used to remove a client from a hamper.
    // getClient() returns an ArrayList where the removed client is absent.
    @Test
    public void testRemoveClientRemovesClient() {
        app.addHamper();

        app.addClient(0, ClientType.ADULT_MALE, 1);
        app.addClient(0, ClientType.ADULT_FEMALE, 10);
        app.addClient(0, ClientType.CHILD_OVER_8, 20);
        app.addClient(0, ClientType.CHILD_UNDER_8, 2);

        app.removeClient(0, ClientType.ADULT_MALE, 1);
        app.removeClient(0, ClientType.ADULT_FEMALE, 10);
        app.removeClient(0, ClientType.CHILD_OVER_8, 20);
        app.removeClient(0, ClientType.CHILD_UNDER_8, 2);

        assertTrue("removeClient() did not remove client", app.getHampers().get(0).getClients().isEmpty());
    }

    // removeClient() is used to remove a client from a hamper with no clients.
    // removeClient() throws an exception.
    @Test
    public void testRemoveClientWhenNoClientsGivesException() {
        boolean exceptionThrown = false;

        try{
            app.addHamper();
            app.removeClient(0, ClientType.ADULT_MALE, 1);
        } catch (IllegalStateException e){
            exceptionThrown = true;
        }

        assertTrue("removeClient() did not remove client when none existed"
            , exceptionThrown);
    } 

    // resetApplication() is used to remove all hampers.
    // getHamper() returns an empty ArrayList.
    @Test
    public void testResetApplicationRemovesAllHampers(){
        boolean isEmpty = false;

        app.addHamper();
        app.addClient(0, ClientType.ADULT_MALE, 2);
        app.addHamper();
        app.addClient(1, ClientType.ADULT_MALE, 1);
        app.addClient(1, ClientType.ADULT_FEMALE, 1);
        app.addClient(1, ClientType.CHILD_OVER_8, 1);
        app.addClient(1, ClientType.CHILD_UNDER_8, 1);
        app.resetApplication();
        isEmpty = app.getHampers().isEmpty();

        assertTrue("resetApplication() did not empty hamper", isEmpty);
    }

    // requestOrderForm() is called when no hampers were added.
    // requestOrderForm() throws a HamperHasNoClientsException.
    @Test
    public void testRequestOrderFormGivesException() {
        boolean exceptionThrown = false;

        try{
            app.requestOrderForm();
        } catch (HamperHasNoClientsException e){
            exceptionThrown = true;
        }

        assertTrue("requestOrderForm() did not remove client when none existed"
            , exceptionThrown);
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

    // Client(ClientType, boolean) is called
    // isHandicapped() returns the correct Boolean
    @Test
    public void testIsHandicapped() {
        Client client = new Client(ClientType.ADULT_MALE, true);
        Boolean actualBool = client.isHandicapped();
        assertTrue("isHandicapped() did not return the correct boolean", actualBool);
    }

    /* HAMPER TESTS */

	// addItem() is used to add a FoodItem to the hamper.
    // getItems() is used to return the hamper's FoodItems as an ArrayList.
	// The element present in this ArrayList should be same as the added FoodItem. 
	@Test
	public void testAddItemGetItems() {
		Hamper hamper = new Hamper();
		FoodItem food = new FoodItem(0,"default",0,0,0,0,0);
		hamper.addItem(food);
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
	// Checks if the Arraylist is empty after removing the only client present in it
	@Test
	public void testRemoveClient() {
		Hamper hamper = new Hamper();
		hamper.addClient(ClientType.ADULT_MALE, 10);
		hamper.removeClient(ClientType.ADULT_MALE, 10);
		ArrayList<Client> clients = hamper.getClients();
		boolean removed = false;
		if (clients.isEmpty()) {
			removed = true;
		}
		assertTrue("The client object was not removed from the clients ArrayList", removed);
	}

	// removeClient() is called when the hamper has no clients
	// removeClient() throws an IllegalStateException
	@Test
	public void testRemoveClientWhenNoClientsThrowsException() {
        Boolean exceptionThrown = false;
		Hamper hamper = new Hamper();
        try {
            hamper.removeClient(ClientType.ADULT_MALE, 10);
        } catch (IllegalStateException e) {
            exceptionThrown = true;
        }

		assertTrue("removeClient() did not throw an IllegalStateException when there are no clients to remove", exceptionThrown);
	}

    // Checks if hamper throws an IllegalArgumentException when a negative amount 
    // of clients are specified 
    @Test
    public void testHamperThrowsIllegalArgumentException(){
        boolean exceptionThrown = false;
        try{
            Hamper hamper = new Hamper();
            hamper.removeClient(ClientType.ADULT_FEMALE, -1);
        } catch(IllegalArgumentException e){
            exceptionThrown = true;
        }

        assertTrue("removeClient did not throw IllegalArgumentException when argument is negative amount of clients"
            , exceptionThrown);

    }

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

    
    /* DATABASE TESTS */

    // Database() is used to create a Database object
    // initializeConnection() should not throw an exception for a valid URL and credentials
    @Test
    public void testInitializeConnectionValidURLAndCredentials() {
        Database database = new Database("jdbc:mysql://localhost/available_food", "student", "ensf");
        Boolean exceptionThrown = false;
        try {
            database.initializeConnection();
        } catch (SQLException e) {
            exceptionThrown = true;
        }

        assertFalse("initializeConnect() threw an exception for a valid URL and credentials", exceptionThrown);
    }

    // Database() is used to create a Database object
    // initializeConnection() should throw an exception for an invalid URL and credentials
    @Test
    public void testInitializeConnectionInvalidURLAndCredentialsThrowsException() {
        Database database = new Database("bad_URL", "bad_username", "bad_password");
        Boolean exceptionThrown = false;
        try {
            database.initializeConnection();
        } catch (SQLException e) {
            exceptionThrown = true;
        }

        assertTrue("initializeConnect() did not throw an exception for an invalid URL and credentials", exceptionThrown);
    }

    // Database() is used to create a Database object
    // getUsername() should return the username given in the constructor
    @Test
    public void testGetUsername() {
        Database database = new Database("jdbc:mysql://localhost/available_food", "student", "ensf");
        String expected = "student";
        String actual = database.getUsername();
        assertEquals("getUsername() did not return the correct username", expected, actual);
    }

    // Database() is used to create a Database object
    // getPassword() should return the password given in the constructor
    @Test
    public void testGetPassword() {
        Database database = new Database("jdbc:mysql://localhost/available_food", "student", "ensf");
        String expected = "ensf";
        String actual = database.getPassword();
        assertEquals("getPassword() did not return the correct password", expected, actual);
    }

    // Database() is used to create a Database object
    // getClientValues() should return the password given in the constructor
    @Test
    public void testGetClientValues() {
        Database database = new Database("jdbc:mysql://localhost/available_food", "student", "ensf");
        String expected = "ensf";
        String actual = database.getPassword();
        assertEquals("getPassword() did not return the correct password", expected, actual);
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

    /* NUTRITION TESTS */

    // Creates food item and client and tests return whole grains
    @Test
    public void testAddGetWholeGrains(){
        int expected = 345;
        FoodItem myItem = new FoodItem(42069, "Food?", 345, 456, 678, 7, 5678);
        int actual = myItem.getWholeGrains();
        assertEquals("FoodItem fails to correctly add/or get Wholegrains", expected, actual);
        Client client= new Client(ClientType.ADULT_MALE, false);
        expected = 16; // Constant from database
        actual = client.getWholeGrains();
        assertEquals("Client fails to correctly add/or get Wholegrains", expected, actual);
    }

    //Creates food item  and client and tests return Fruits and veggies
    @Test
    public void testAddGetFruitsVeggies(){
        int expected = 456;
        FoodItem myItem = new FoodItem(42069, "Food?", 345, 456, 678, 7, 5678);
        int actual = myItem.getFruitVeggies();
        assertEquals("FoodItem fails to correctly add/or get FruitsVeggies", expected, actual);
        Client client= new Client(ClientType.ADULT_MALE, false);
        expected = 28; //Constant from database
        actual = client.getFruitVeggies();
        assertEquals("Client fails to correctly add/or get FruitsVeggies", expected, actual);
    }

    //Creates food item  and client and tests return protein
    @Test
    public void testAddGetProtein(){
        int expected = 678;
        FoodItem myItem = new FoodItem(42069, "Food?", 345, 456, 678, 7, 5678);
        int actual = myItem.getProtein();
        assertEquals("FoodItem fails to correctly add/or get Protein", expected, actual);
        Client client= new Client(ClientType.ADULT_MALE, false);
        expected = 26; //Constant from database
        actual = client.getProtein();
        assertEquals("Client fails to correctly add/or get Protein", expected, actual);
    }

    //Creates food item  and client and tests return other
    @Test
    public void testAddGetother(){
        int expected = 7;
        FoodItem myItem = new FoodItem(42069, "Food?", 345, 456, 678, 7, 5678);
        int actual = myItem.getOther();
        assertEquals("FoodItem fails to correctly add/or get other", expected, actual);
        Client client= new Client(ClientType.ADULT_MALE, false);
        expected = 30; //Constant from database
        actual = client.getOther();
        assertEquals("Client fails to correctly add/or get other", expected, actual);
    }

    //Creates food item  and client and tests return calories
    @Test
    public void testAddGetCalories(){
        int expected = 5678;
        FoodItem myItem = new FoodItem(42069, "Food?", 345, 456, 678, 7, 5678);
        int actual = myItem.getCalories();
        assertEquals("FoodItem fails to correctly add/or get Calories", expected, actual);
        Client client= new Client(ClientType.ADULT_MALE, false);
        expected = 2500; //Constant from database
        actual = client.getCalories();
        assertEquals("Client fails to correctly add/or get Calories", expected, actual);
    }
}
