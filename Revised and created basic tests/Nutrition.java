import java.sql.SQLException;

public class Nutrition {
    private final int WHOLE_GRAINS;
    private final int FRUIT_VEGGIES;
    private final int PROTEIN;
    private final int OTHER;
    private final int CALORIES;

    public Nutrition(ClientType bodyType){
        Database database = new Database("jdbc:mysql://localhost/food_inventory", "student", "ensf");
        try {
            database.initializeConnection();
        } catch(SQLException e){
            e.printStackTrace();
        }
        int[] nutValues = database.getClientValues(bodyType.toString());

        this.WHOLE_GRAINS = nutValues[0];
        this.FRUIT_VEGGIES = nutValues[1];
        this.PROTEIN = nutValues[2];
        this.OTHER = nutValues[3];
        this.CALORIES = nutValues[4];

        database.close();
    }

    public Nutrition(int wholeGrains, int fruitVeggies, int protein, int other, int calories){
        this.WHOLE_GRAINS = wholeGrains;
        this.FRUIT_VEGGIES = fruitVeggies;
        this.PROTEIN = protein;
        this.OTHER = other;
        this.CALORIES = calories;
    }

    public int getWholeGrains(){
        return this.WHOLE_GRAINS;
    }

    public int getFruitVeggies(){
        return this.FRUIT_VEGGIES;
    }

    public int getProtein(){
        return this.PROTEIN;
    }

    public int getOther(){
        return this.OTHER;
    }

    public int getCalories(){
        return this.CALORIES;
    }
}
