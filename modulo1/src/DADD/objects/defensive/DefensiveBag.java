package DADD.objects.defensive;

import DADD.figures.Bag;

public class DefensiveBag<T extends DefensiveObject> extends Bag<T> {


    public T getBestDefensiveObject() {
        T result = null;
        for(T el : this) {
            if(result == null)
                result = el;
            else {
                if(el.getInfoDefense() > result.getInfoDefense())
                    result = el;
            }
        }
        return result;
    }
}
