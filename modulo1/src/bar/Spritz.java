package bar;

import java.util.List;

public abstract class Spritz extends Cocktail{
    int proseccoL;
    int sodaL;
    int bitterL;

    public Spritz(List<String> ingredients, int proseccoL, int sodaL, int bitterL) {
        super(ingredients, 10.7);
        this.proseccoL = proseccoL;
        this.sodaL = sodaL;
        this.bitterL = bitterL;
    }
}
