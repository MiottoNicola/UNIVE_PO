package Avogador_es.ListDuplicates;

import java.util.List;

class Item {
    private final String name;
    private final int price;
    private final int weight;

    public Item(String name, int price, int weight) {
        this.name = name;
        this.price = price;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getWeight() {
        return weight;
    }

    public boolean equals(Object o){
        if(o == null || o.getClass() != this.getClass()) return false;
        if(this == o) return true;
        Item i = (Item) o;
        return this.name.equals(i.getName()) && this.price == i.getPrice() && this.weight == i.getWeight();
    }
    public int hashCode(){
        return this.name.hashCode() + this.price + this.weight;
    }

}

class DuplicatesFinder {
    public static int numDuplicates(List<Item> items) {
        int max=0;
        for(int i=0; i<items.size(); i++){
            int c=0;
            for(int j=i+1; j<items.size(); j++){
                if(items.get(i)==items.get(j)){
                    c++;
                }
            }
            if(c>max) max=c;
        }
        return max;
    }
}