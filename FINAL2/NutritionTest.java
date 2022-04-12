import org.junit.*;
import static org.junit.Assert.*;
import java.sql.SQLException;
public class NutritionTest {
   
    /* NUTRITION TESTS */

    // Creates food item and client and tests return whole grains
    // Since FoodItem and Client extends Nutrition and both calls the Nutrition Constructor this is testing both Nutrition Constructors 
    @Test
    public void testAddGetWholeGrains(){
        int expected = 300;
        FoodItem myItem = new FoodItem(42069, "Food?", 30, 70, 0, 0, 1000);
        int actual = myItem.getWholeGrains();
        assertEquals("FoodItem fails to correctly add/or get Wholegrains", expected, actual);
        Client client= new Client(ClientType.ADULT_MALE);
       //TO BE CHECKED
        expected = 16; // Constant from database
        Database database = new Database("jdbc:mysql://localhost/food_inventory", "student", "ensf");
        
        try {
            database.initializeConnection();
        } catch(SQLException e){
            e.printStackTrace();
        }
        
        int[] nutValues = database.getClientValues(bodyType.toString());
        //expected=nutValues[0];
        int calories=nutValues[4]*7;
        expected = (int)Math.ceil(nutValues[0] / 100.0 * calories);
        actual = client.getWholeGrains();
        assertEquals("Client fails to correctly add/or get Wholegrains", expected, actual);
    }

    //Creates food item  and client and tests return Fruits and veggies
    @Test
    public void testAddGetFruitsVeggies(){
        int expected = 700;
        FoodItem myItem = new FoodItem(42069, "Food?", 30, 70, 0, 0, 1000);
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
        int expected = 300;
        FoodItem myItem = new FoodItem(42069, "Food?", 0, 0, 30, 70, 1000);
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
        int expected = 700;
        FoodItem myItem = new FoodItem(42069, "Food?", 0, 0, 30, 70, 1000);
        int actual = myItem.getOther();
        assertEquals("Nutrition through FoodItem fails to correctly add/or get other", expected, actual);
        Client client= new Client(ClientType.ADULT_MALE);
        expected = 30; //Constant from database
        actual = client.getOther();
        assertEquals("Nutrition through Client fails to correctly add/or get other", expected, actual);
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
