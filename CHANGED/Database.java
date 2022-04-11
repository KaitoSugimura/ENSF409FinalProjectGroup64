/**
@author Danny Duong, Kaito Sugimura, Kevin Johnson, Joshua Walters
@version 2.5
@since 1.0
*/

import java.sql.*;
import java.util.*;

/*
 * Class that deals with connections between the database and program.
*/
public class Database{
    //Fields
    public final String DBURL;
    public final String USERNAME;
    public final String PASSWORD;    
    
    private Connection dbConnect;
    private ResultSet results;

    //"jdbc:mysql://localhost/food_inventory", "student", "ensf"

    //javac -cp .;lib/mysql-connector-java-8.0.23.jar *.java
    //java -cp .;lib/mysql-connector-java-8.0.23.jar Main

    //Constructors
    public Database(String url, String user, String pw){
        // Database URL
        this.DBURL = url;

        //  Database credentials
        this.USERNAME = user;
        this.PASSWORD = pw;
    }

    /**
     * creates a connection to the data base using the initialized finals from the constructor:
     * DBURL (path-url), USERNAME (username), and PASSWORD (password)
     * Throws an SQLException if connection cannot be made
     * @throws SQLException
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

    /**
     * Return the specified bodyTypes nutritional values as an array
     * @param bodyType specified body type
     * @return array with nutritional values
     */
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

    /**
     * Obtain food items as an ArrayList in the MySQL database
     * @return ArrayList of FoodItem
     */
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

    /** 
     * Removes the food items with the matching ID from the databse
     * @param ID - the ID of the item to be deleted
     */
    public void removeItem(int ID) {
        try {
            String query = "DELETE FROM food_inventory.available_food WHERE ItemID = ?";
            PreparedStatement myStmt = dbConnect.prepareStatement(query);

            myStmt.setString(1, Integer.toString(ID));

            int rowCount = myStmt.executeUpdate();
            if (rowCount < 1){
                throw new SQLException();
            }
                        
            myStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
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