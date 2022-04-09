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
        
        this.WHOLE_GRAINS = (int)Math.ceil(nutValues[0] / 100.0 * nutValues[4]);
        this.FRUIT_VEGGIES = (int)Math.ceil(nutValues[1] / 100.0 * nutValues[4]);
        this.PROTEIN = (int)Math.ceil(nutValues[2] / 100.0 * nutValues[4]);
        this.OTHER = (int)Math.ceil(nutValues[3] / 100.0 * nutValues[4]);
        this.CALORIES = nutValues[4];

        database.close();
    }

    public Nutrition(int wholeGrains, int fruitVeggies, int protein, int other, int calories){
        this.WHOLE_GRAINS = (int)Math.ceil(wholeGrains / 100.0 * calories);
        this.FRUIT_VEGGIES = (int)Math.ceil(fruitVeggies / 100.0 * calories);
        this.PROTEIN = (int)Math.ceil(protein / 100.0 * calories);
        this.OTHER = (int)Math.ceil(other / 100.0 * calories);
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

    public static void main(String[] args) {
        Nutrition test = new Nutrition(ClientType.ADULT_MALE);
        System.out.println(test.getCalories());
        System.out.println(test.getProtein());
    }
}
