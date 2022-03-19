public class Nutrition {
    private final int WHOLE_GRAINS;
    private final int FRUITS_VEGGIES;
    private final int PROTEIN;
    private final int OTHER;
    private final int CALORIES;

    public Nutrition(String type){
        int imaginaryDatabase1 = 0;
        int imaginaryDatabase2 = 0;
        int imaginaryDatabase3 = 0;
        int imaginaryDatabase4 = 0;
        int imaginaryDatabase5 = 0;


        this.WHOLE_GRAINS = imaginaryDatabase1;
        this.FRUITS_VEGGIES = imaginaryDatabase2;
        this.PROTEIN = imaginaryDatabase3;
        this.OTHER = imaginaryDatabase4;
        this.CALORIES = imaginaryDatabase5;
    }

    public Nutrition(int wholeGrains, int fruitsVeggies, int protein, int other, int calories){
        this.WHOLE_GRAINS = wholeGrains;
        this.FRUITS_VEGGIES = fruitsVeggies;
        this.PROTEIN = protein;
        this.OTHER = other;
        this.CALORIES = calories;
    }

    public int getWholeGrains(){
        return this.WHOLE_GRAINS;
    }

    public int getFruitsVeggies(){
        return this.FRUITS_VEGGIES;
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
