/**
@author Danny Duong, Kaito Sugimura, Kevin Johnson, Joshua Walters
@version 2.1
@since 1.0
*/

import java.sql.SQLException;

/*
 * Extended by client and food item to store common nutrition values and methods
 * shared between the two.
 */
public abstract class Nutrition {

    //Fields
    private final int WHOLE_GRAINS;
    private final int FRUIT_VEGGIES;
    private final int PROTEIN;
    private final int OTHER;
    private final int CALORIES;

    /**
     * Contructor 
     * Initiate database and obtains nutritional values
     * Calculates nutritional values
     * @param bodyType Specified enum ClientType
     */
    public Nutrition(ClientType bodyType){
        Database database = new Database("jdbc:mysql://localhost/food_inventory", "student", "ensf");
        
        try {
            database.initializeConnection();
        } catch(SQLException e){
            e.printStackTrace();
        }
        
        int[] nutValues = database.getClientValues(bodyType.toString());
        
        // Calculate values from the percentage and total calories
        this.WHOLE_GRAINS = (int)Math.ceil(nutValues[0] / 100.0 * nutValues[4]);
        this.FRUIT_VEGGIES = (int)Math.ceil(nutValues[1] / 100.0 * nutValues[4]);
        this.PROTEIN = (int)Math.ceil(nutValues[2] / 100.0 * nutValues[4]);
        this.OTHER = (int)Math.ceil(nutValues[3] / 100.0 * nutValues[4]);
        this.CALORIES = nutValues[4];
        
        database.close();
    }
    
    /**
     * Constructor
     * Calculates nutritional values from given arguements
     * @param wholeGrains wholegrain nutritional value
     * @param fruitVeggies fruitsVeggies nutritional value
     * @param protein protein nutritional value
     * @param other other nutritional value
     * @param calories calories nutritional value
     */
    public Nutrition(int wholeGrains, int fruitVeggies, int protein, int other, int calories){
        // Calculate values from the percentage and total calories
        this.WHOLE_GRAINS = (int)Math.ceil(wholeGrains / 100.0 * calories);
        this.FRUIT_VEGGIES = (int)Math.ceil(fruitVeggies / 100.0 * calories);
        this.PROTEIN = (int)Math.ceil(protein / 100.0 * calories);
        this.OTHER = (int)Math.ceil(other / 100.0 * calories);
        this.CALORIES = calories;
    }

    // Getters
    public int getWholeGrains(){ return this.WHOLE_GRAINS; }
    public int getFruitVeggies(){ return this.FRUIT_VEGGIES; }
    public int getProtein(){ return this.PROTEIN; }
    public int getOther(){ return this.OTHER; }
    public int getCalories(){ return this.CALORIES; }
}
