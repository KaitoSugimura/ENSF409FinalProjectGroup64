import java.sql.*;

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
    public void initializeConnection() throws SQLException{
        dbConnect = DriverManager.getConnection(this.DBURL, this.USERNAME, this.PASSWORD);         
    }
    
    // getters
    String getDburl(){
        return this.DBURL;
    }

    String getUsername(){
        return this.USERNAME;
    }
    
    String getPassword(){
        return this.PASSWORD;
    }

    public int[] getClientValues(String bodyType){
        int[] nutValues = new int[5];

        try {                    
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM food_inventory.daily_client_needs");
            
            while (results.next()){
                if(bodyType.equals(results.getString("Client"))){
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

    // PLEASE CHANGE THIS FUNCTIOn
    public String[] getNextFoodValues(int i){
        String[] foodValues = new String[7];

        try {                    
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM food_inventory.available_food");

            for (int j = 0; j < i; j++){
                results.next();
            }
            
            foodValues[0] = results.getString("ItemID"); 
            foodValues[1] = results.getString("Name");
            foodValues[2] = results.getString("GrainContent");
            foodValues[3] = results.getString("FVContent");
            foodValues[4] = results.getString("ProContent");
            foodValues[5] = results.getString("Other");
            foodValues[6] = results.getString("Calories");
            
            myStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return foodValues;
    }


    

    /* Closes the Connection and ResultSet
     */
    public void close() {
        
        try {
            results.close();
            dbConnect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }              

    }
    
}

