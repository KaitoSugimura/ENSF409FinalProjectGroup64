import java.sql.*;
import java.util.*;

public class Database{
    public final String DBURL;
    public final String USERNAME;
    public final String PASSWORD;    
    
    private Connection dbConnect;
    private ResultSet results;

    //"jdbc:mysql://localhost/food_inventory", "student", "ensf"

    //javac -cp .;lib/mysql-connector-java-8.0.23.jar *.java
    //java -cp .;lib/mysql-connector-java-8.0.23.jar Main

    public Database(String url, String user, String pw){
        // Database URL
        this.DBURL = url;

        //  Database credentials
        this.USERNAME = user;
        this.PASSWORD = pw;
    }

    /* creates a connection to the data base using the initialized finals from the constructor:
     * DBURL (path-url), USERNAME (username), and PASSWORD (password)
     * Throws an SQLException if connection cannot be made
     */
    public void initializeConnection() throws SQLException {
        dbConnect = DriverManager.getConnection(this.DBURL, this.USERNAME, this.PASSWORD);         
    }
    
    // getters
    public String getDburl() {
        return this.DBURL;
    }

    public String getUsername() {
        return this.USERNAME;
    }
    
    public String getPassword() {
        return this.PASSWORD;
    }

    public int[] getClientValues(String bodyType) {
        int[] nutValues = new int[5];

        try {                    
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM food_inventory.daily_client_needs");
            
            while (results.next()) {
                if (bodyType.equals(results.getString("Client"))) {
                    nutValues[0] = Integer.parseInt(results.getString("WholeGrains"));
                    nutValues[1] = Integer.parseInt(results.getString("FruitVeggies"));
                    nutValues[2] = Integer.parseInt(results.getString("Protein"));
                    nutValues[3] = Integer.parseInt(results.getString("Other"));
                    nutValues[4] = Integer.parseInt(results.getString("Calories"));
                    break;
                }
            }
            
            myStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return nutValues;
    }

    // PLEASE CHANGE THIS FUNCTION
    public ArrayList<FoodItem> getFoodValues(){
        ArrayList<FoodItem> foodItems = new ArrayList<>();

        try {                    
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM food_inventory.available_food");

            while(results.next()){
                int ID = Integer.parseInt(results.getString("ItemID")); 
                String name = results.getString("Name");
                int wholeGrains = Integer.parseInt(results.getString("GrainContent"));
                int fruitVeggies = Integer.parseInt(results.getString("FVContent"));
                int protein = Integer.parseInt(results.getString("ProContent"));
                int other = Integer.parseInt(results.getString("Other"));
                int calories = Integer.parseInt(results.getString("Calories"));
                FoodItem foodItem = new FoodItem(ID, name, wholeGrains, fruitVeggies, protein, other, calories);
                foodItems.add(foodItem);
            }
        
            myStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return foodItems;
    }
    
    /* Closes the Connection and ResultSet */
    public void close() {
        try {
            results.close();
            dbConnect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }              
    }
}

