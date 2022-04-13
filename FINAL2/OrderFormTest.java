/**
@author Danny Duong, Kaito Sugimura, Kevin Johnson, Joshua Walters
@version 2.1
@since 1.0
*/
import org.junit.*;
import static org.junit.Assert.*;

import java.io.File;
import java.util.Scanner;
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

    // printOrder is used to create and OrderForm txt file
    //A file OrderForm.txt should be created in the correct format.
    @Test
    public void testOrderFormFormat(){
        String expected = "64 Food Bank\nHamper Order Form\n\nName:\nDate:\n\nOriginal Request\n\nHamper1: 2 Adult Females, \n\nHamper 1 Items:\n1\tTuna, six large cans\n1\tChicken breast, pound\n2\tBroccoli, 3 bunches\n1\tGranola Bar, box\n1\tChicken broth, can"; 
        expected.trim();
        String actual="";
        String st = "";
        
        
        try {
            File file = new File("OrderForm.txt");
            Scanner myReader = new Scanner(file);
            while((st =myReader.nextLine())!= null){
                actual += st+"\n";
            }
            myReader.close();
            
                       
        } catch (Exception e) {
            System.out.println("File error while running the test!");
        }
        System.out.println();
        expected = expected.trim();
        actual =actual.trim();
        assertEquals("File was not printed in the expected format!",expected, actual);
    }
    
    
}
