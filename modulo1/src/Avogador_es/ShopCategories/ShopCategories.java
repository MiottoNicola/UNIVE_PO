package Avogador_es.ShopCategories;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

abstract class StoreItem {

    private final String name; 
    
    private final String category; 
    
    public StoreItem(String name) {
        this.name = name;
        // this retrieves the category from the Category annotation 
        Category annot = this.getClass().getAnnotation(Category.class);
        if (annot != null)
            category = annot.name() + " - code " + annot.code();
        else 
            category = "unassigned";
    }
    
    @Override
    public String toString() {
        return name + " (in " + category + ")";
    }
}

@Category(name="food", code=5)
class Pasta extends StoreItem {
    public Pasta() {
        super("pasta");
    }
}

@Category(name="personal care", code=7)
class Toothbrush extends StoreItem {
    public Toothbrush() {
        super("toothbrush");
    }
}

@Category(name="wardrobe", code=15)
class Shoes extends StoreItem {
    public Shoes() {
        super("shoes");
    }
}

@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface Category {
    String name();
    int code();
}

