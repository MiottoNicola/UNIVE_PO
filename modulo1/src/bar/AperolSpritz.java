package bar;

import java.util.List;

public class AperolSpritz extends Spritz{

    public AperolSpritz() {
        super(List.of(), 0, 0, 0);
    }
    
    private AperolSpritz(int liters) {
        super(List.of("Aperol", "Prosecco", "Soda"), liters/2, liters/3, liters/6);
    }

    @Override
    public Cocktail prepare() {
        System.err.println("Preparing Aperol Spritz");
        return new AperolSpritz(10);
    }
    
}
