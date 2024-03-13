package ObjectMethods;

public class A extends Object {

    int i = 10;

    @Override
    public boolean equals(Object a) {
        if(a instanceof A)
            return this.i==((A) a).i;
        return false;
    }
}