package Generics;

import java.util.ArrayList;
import java.util.Arrays;

public class List<T> {
    private T[] elements = null;

    public T get(int index){ return elements[index]; }
    public int length(){ return elements.length; }
    public static <T1> T1 getFirst(List<T1> l){ return l.get(0); }
    public void add(T el){
        if(elements==null){
            ArrayList<T> list = new ArrayList<>();
            list.add(el);
            elements = list.toArray(elements);
        }
        elements = Arrays.copyOf(elements, elements.length+1);
        elements[elements.length-1] = el;
    }
}
