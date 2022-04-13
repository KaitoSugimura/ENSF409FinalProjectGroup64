package edu.ucalgary.ensf409;

import org.junit.*;
import static org.junit.Assert.*;

import java.io.File;
public class OrderFormTest {
    

    @Before
    public void setUp() {
        Application testApp = new Application();
        testApp.addHamper();
        testApp.addClient(0, ClientType.ADULT_FEMALE, 2);
        
        try {
            testApp.calculateOrder();
            testApp.requestOrderForm();
            
        } catch (Exception e) {
            System.out.println("An exception occured during the test setup!");
        }  

        
    }

    /* ORDERFORM TESTS */

    // printOrder is used to create and OrderForm txt file
    //A file OrderForm.txt should be created.
    @Test
    public void testOrderFormTextFileCreated(){
        
        File f = new File("OrderForm.txt");
        boolean expected = true;
        boolean actual = f.exists() && !f.isDirectory();
        assertEquals("File was not created as expected",expected, actual);

    }

}
