
//package edu.ucalgary.ensf409;

// javac -cp .;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar;lib/mysql-connector-java-8.0.23.jar *.java
// java -cp .;lib/mysql-connector-java-8.0.23.jar;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore ApplicationTest

import org.junit.Test;
import org.junit.Assert;
import static org.junit.Assert.*;

import java.beans.Transient;


public class ApplicationTest {
    private Application app = new Application();

    // Tests for Application class

    // addHamper() is used to add a hamper.
    // getHampers() returns an ArrayList which contains the added hamper.
    @Test
    public void testAddHamperAddsHamper(){
        try{
            app.addHamper();
        } catch (HamperHasNoClientsException e){
            // This exception is tested in another test.
        }

        assertFalse("A new hamper is not added when called addHamper", app.getHamper().isEmpty());
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
        assertTrue("Hamper is not removed when called removeHamper: ", app.getHamper().isEmpty());
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

        assertEquals("Application did not throw an exception when removing hampers when there are none: "
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

        assertFalse("Clients are succesfully added: ", app.getHamper().get(0).getClients().isEmpty());
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

        assertTrue("Clients are not succesfully removed: ", app.getHamper().get(0).getClients().isEmpty());
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

        assertEquals("Application did not throw an exception when removing clients when there are none: "
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

        assertEquals("Application hamper is not empty upon reset: ", true, isEmpty);
        assertEquals("Application hamper is not empty upon reset: ", expectedIndex, actualIndex);
           
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

        assertEquals("Application did not throw a HamperHasNoClientsException when creating a new hamper while old is empty: "
            , true, correctException);
    }
}
