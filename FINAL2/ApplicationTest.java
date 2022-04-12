//package edu.ucalgary.ensf409;

// javac -cp .;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar;lib/mysql-connector-java-8.0.23.jar *.java
// java -cp .;lib/mysql-connector-java-8.0.23.jar;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore ApplicationTest

import org.junit.*;
import static org.junit.Assert.*;

public class ApplicationTest {
    private Application app;

    @Before
    public void setUp() {
        app = new Application();
    }

    /* APPLICATION TESTS */

    

    // addHamper() is used to add a hamper.
    // getHampers() returns an ArrayList which contains the added hamper.
    // Add a hamper and should get a hamper
    @Test
    public void testAddHamperGetHamper() {
        app.addHamper();
        assertFalse("addHamper() did not add hamper", app.getHampers().isEmpty());
    }

    // removeHamper() is used to remove a hamper.
    // getHampers() returns an ArrayList where the removed hamper is absent.
    // remove hamper should remove a hamper
    @Test
    public void testRemoveHamperRemovesHamper() {
        app.addHamper();
        app.removeHamper(0);
        assertTrue("removeHamper() did not remove hamper", app.getHampers().isEmpty());
    }

    // removeHamper() is called on an application with no hampers.
    // removeHamper() throws an exception.
    // removing a hamper when there is none should throw an exception
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
    // Add a client and get that client
    @Test
    public void testAddClientGetClient() {
        app.addHamper();
        app.addClient(0, ClientType.CHILD_OVER_8, 1);
        assertFalse("addClient() did not add client", app.getHampers().get(0).getClients().isEmpty());
    }

    // removeAllClient() is used to remove all clients
    // getClient() returns an ArrayList where the removed client is absent.
    // should remove all clients
    @Test
    public void testRemoveAllClientRemovesAllClients() {
        app.addHamper();

        app.addClient(0, ClientType.ADULT_MALE, 1);
        app.addClient(0, ClientType.ADULT_FEMALE, 10);
        app.addClient(0, ClientType.CHILD_OVER_8, 20);
        app.addClient(0, ClientType.CHILD_UNDER_8, 2);

        app.removeAllClients();

        assertTrue("removeAllClients() did not remove all the clients", app.getHampers().get(0).getClients().isEmpty());
    }

    // removeAllClient() does not given an error on a empty hamper
    // removeAllClient on empty hamper should just do nothing
    @Test
    public void testRemoveAllClientWhenNoClientsGivesNoError() {
        boolean exceptionThrown = false;

        try{
            app.addHamper();
            app.removeAllClients();
        } catch (Exception e){
            exceptionThrown = true;
        }

        assertFalse("removeClient() threw an unknown exception when trying to remove from empty hamper"
            , exceptionThrown);
    } 

    // resetApplication() is used to remove all hampers.
    // getHamper() returns an empty ArrayList.
    // test the reset 
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

    //toString() used to return a string in the proper format to be written to an output file.
    //toString returns string in correct format.
    @Test
    public void testToString(){
        Application testApp = new Application();
        testApp.addHamper();
        testApp.addClient(0, ClientType.ADULT_FEMALE, 2);
        String actual = "";
        
        try {
            testApp.calculateOrder();
            actual = testApp.toString();
            
        } catch (Exception e) {
            System.out.println("An exception occured during the test setup!");
        }
        String expected = "64 Food Bank\nHamper Order Form\n\nName:\nDate:\n\nOriginal Request\n\nHamper1: 2 Adult Females, \n\nHamper 1 Items:\n1\tTuna, six large cans\n1\tChicken breast, pound\n2\tBroccoli, 3 bunches\n1\tGranola Bar, box\n1\tChicken broth, can";  
        expected = expected.trim();
        actual = actual.trim();
        assertEquals("The string was not returned in the proper format!",expected, actual);
    }
    

}
