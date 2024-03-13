package bar;

import java.util.List;

public abstract class Cocktail {
    List<String> ingredients;
    double alcoholPercentage;

    public Cocktail(List<String> ingredients, double alcoholPercentage) {
        this.ingredients = ingredients;
        this.alcoholPercentage = alcoholPercentage;
    }

    public abstract Cocktail prepare();


    public static void main(String[] args) {
        Cocktail aperol = new AperolSpritz();
        aperol.prepare();
    }
}