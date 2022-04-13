/**
@author Danny Duong, Kaito Sugimura, Kevin Johnson, Joshua Walters
@version 1.7
@since 1.0
*/
package edu.ucalgary.ensf409;

import org.junit.*;
import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.HashMap;

public class HamperTest {
	
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
	
	//resetItems() should replace the current FoodItem ArrayList with an empty one
	//Checks if the ArrayList is empty after resetting
	@Test
	public void testResetItems(){
		Hamper hamper=new Hamper();
		ArrayList<FoodItem> testItems = new ArrayList<>();
        	FoodItem food = new FoodItem(0,"default",0,0,0,0,0);
		testItems.add(food);
		hamper.setItems(testItems);
		hamper.resetItems();
		boolean empty=false;
		ArrayList<FoodItem> newItems=hamper.getItems();
		if(newItems.isEmpty()){
			empty=true;
		}
		assertTrue("resetItems() did not overwrite the FoodItem ArrayList with an empty one",empty);
	}
	
	//getClientCount() should return an array containing the correct number of a type of client to its corresponding index
	//Adult male count at index 0
	//Adult Female count at index 1
	//Child Over 8 count at index 2
	//Child under 8 count at index 3
	@Test
	public void testGetClientCountWithOnlyAdultMaleClient(){
		Hamper hamper = new Hamper();
		int expected=10;
		hamper.addClient(ClientType.ADULT_MALE, expected);
		int[] result=hamper.getClientCount();
		int maleCount=result[0];
		assertEquals("getClientCount() did not return the expected value for Adult Male clients",expected,maleCount);
		assertEquals("getClientCount() returned a value for Adult Female count when no Adult Females were passed to the hamper",0,result[1]);
		assertEquals("getClientCount() returned a value for Child Over 8 count when no Children over 8 were passed to the hamper",0,result[2]);
		assertEquals("getClientCount() returned a value for Child under 8 count when no Children under 8 were passed to the hamper",0,result[3]);
	}
	
	//Testing getClientCount() but with every type of client present in the hamper
	@Test
	public void testGetClientCountWithEveryClient(){
		Hamper hamper = new Hamper();
		int expectedM=10;
		hamper.addClient(ClientType.ADULT_MALE, expectedM);
		int expectedF=5;
		hamper.addClient(ClientType.ADULT_FEMALE, expectedF);
		int expectedO=7;
		hamper.addClient(ClientType.CHILD_OVER_8, expectedO);
		int expectedU=2;
		hamper.addClient(ClientType.CHILD_UNDER_8, expectedU);
		int[] result=hamper.getClientCount();
		int maleCount=result[0];
		int femaleCount=result[1];
		int childOverCount=result[2];
		int childUnderCount=result[3];
		assertEquals("getClientCount() did not return the expected value for Adult Male clients",expectedM,maleCount);
		assertEquals("getClientCount() did not return the expected value for Adult Female clients",expectedF,femaleCount);
		assertEquals("getClientCount() did not return the expected value for clients who are children over 8",expectedO,childOverCount);
		assertEquals("getClientCount() did not return the expected value for clients who are children under 8",expectedU,childUnderCount);
	}
	
	//getFoodItemCount() should return the number of times a specific FoodItem appears in a hamper.
	//In this test the FoodItem ArrayList in hamper only has one FoodItem in it.
	@Test
	public void testGetFoodItemCountOnlyOne(){
		Hamper hamper=new Hamper();
		ArrayList<FoodItem> testItems = new ArrayList<>();
        	FoodItem food = new FoodItem(0,"default",0,0,0,0,0);
		testItems.add(food);
		hamper.setItems(testItems);
		HashMap<String, Integer> foodCount=hamper.getFoodItemCount();
		int defaultValue=foodCount.get("default");
		assertEquals("getFoodItemCount() did not return 1 when hamper only has one FoodItem",1,defaultValue);
	}
	
	//Testing getFoodItemCount() but with multiple FoodItems of varying frequency in the FoodItem ArrayList in the hamper
	@Test
	public void testGetFoodItemCountMany(){
		Hamper hamper=new Hamper();
		ArrayList<FoodItem> testItems = new ArrayList<>();
        	FoodItem food = new FoodItem(0,"default",0,0,0,0,0);
		testItems.add(food);
		testItems.add(food);
		int defaultExpected=2;
		FoodItem food2 = new FoodItem(0,"random",0,0,0,0,0);
		testItems.add(food2);
		int randomExpected=1;
		hamper.setItems(testItems);
		HashMap<String, Integer> foodCount=hamper.getFoodItemCount();
		int defaultValue=foodCount.get("default");
		assertEquals("getFoodItemCount() did not return the expected value when hamper has multiple FoodItems",defaultExpected,defaultValue);
		int randomValue=foodCount.get("random");
		assertEquals("getFoodItemCount() did not return the expected value when hamper has multiple FoodItems",randomExpected,randomValue);
	}
}
