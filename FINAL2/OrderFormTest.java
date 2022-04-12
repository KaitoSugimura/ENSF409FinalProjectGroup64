import org.junit.*;
import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
public class OrderFormTest {
    ArrayList<Hamper> myhampers = new ArrayList<>();

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

    /* OrderForm TESTS */

    //Creates order form txt file
    @Test
    public void testOrderFormTextFileCreated(){
        //OrderForm.printOrder(myhampers);
        
        File f = new File("OrderForm.txt");
        boolean expected = true;
        boolean actual = f.exists() && !f.isDirectory();
        assertEquals("File was not created as expected",expected, actual);

    }
    
}
