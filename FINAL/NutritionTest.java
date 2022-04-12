import org.junit.*;
import static org.junit.Assert.*;
public class NutritionTest {
    private Application app;

    @Before
    public void setUp() {
        app = new Application();
    }
   

    /* NUTRITION TESTS */

    // Creates food item and client and tests return whole grains
    @Test
    public void testAddGetWholeGrains(){
        int expected = 345;
        FoodItem myItem = new FoodItem(42069, "Food?", 345, 456, 678, 7, 5678);
        int actual = myItem.getWholeGrains();
        assertEquals("FoodItem fails to correctly add/or get Wholegrains", expected, actual);
        Client client= new Client(ClientType.ADULT_MALE);
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
        Client client= new Client(ClientType.ADULT_MALE);
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
        Client client= new Client(ClientType.ADULT_MALE);
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
        Client client= new Client(ClientType.ADULT_MALE);
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
        Client client= new Client(ClientType.ADULT_MALE);
        expected = 2500; //Constant from database
        actual = client.getCalories();
        assertEquals("Client fails to correctly add/or get Calories", expected, actual);
    }
    
}
