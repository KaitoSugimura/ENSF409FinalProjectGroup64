package edu.ucalgary.ensf409;

import org.junit.*;
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseTest {
    
     /* DATABASE TESTS */

    // Database() is used to create a Database object
    // initializeConnection() should not throw an exception for a valid URL and credentials
    @Test
    public void testInitializeConnectionValidURLAndCredentials() {
        Database database = new Database("jdbc:mysql://localhost/food_inventory", "student", "ensf");
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
        Database database = new Database("jdbc:mysql://localhost/food_inventory", "student", "ensf");
        String expected = "student";
        String actual = database.getUsername();
        assertEquals("getUsername() did not return the correct username", expected, actual);
    }

    // Database() is used to create a Database object
    // getPassword() should return the password given in the constructor
    @Test
    public void testGetPassword() {
        Database database = new Database("jdbc:mysql://localhost/food_inventory", "student", "ensf");
        String expected = "ensf";
        String actual = database.getPassword();
        assertEquals("getPassword() did not return the correct password", expected, actual);
    }

    // Database() is used to create a Database object
    // getClientValues() should return the password given in the constructor
    @Test
    public void testGetClientValues() {
        Database database = new Database("jdbc:mysql://localhost/food_inventory", "student", "ensf");
        String expected = "ensf";
        String actual = database.getPassword();
        assertEquals("getPassword() did not return the correct password", expected, actual);
    }

    // Database() is used to create a Database object
    // getFoodValues() is used to get food items in database as ArrayList
    // Test getFoodValues not null
    @Test
    public void testGetItemNotNull(){
        Database database = new Database("jdbc:mysql://localhost/food_inventory", "student", "ensf");
        try{
            database.initializeConnection();
        } catch(SQLException e){
            // Tested elsewhere
        }
        ArrayList<FoodItem> foods = null; 
        foods = database.getFoodValues();
        assertNotNull("getFoodValues() did not return an arrayList ", foods);
    }

    // Database() is used to create a Database object
    // removeItem() is used to remove an item
    // Test removeItem throws an exception
    @Test
    public void testRemoveItemThrowsException(){
        Database database = new Database("jdbc:mysql://localhost/food_inventory", "student", "ensf");
        try{
            database.initializeConnection();
        } catch(SQLException e){
            // Tested elsewhere
        }
        int nonExistingID = 99999999;
        boolean exceptionThrown = false;
        try{
            database.removeItem(nonExistingID);
        } catch(SQLException e){
            exceptionThrown = true;
        }
        assertTrue("removeItem() did not throw an exception given an unknown ID"
            , exceptionThrown);
    }
    
}
