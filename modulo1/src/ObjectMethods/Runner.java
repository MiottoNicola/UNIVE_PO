package ObjectMethods;

public class Runner {

    public static void main(String[] args) {
        A obj1 = new A();
        A obj2 = new A();
        System.out.println(obj1==obj2);
        System.out.println(obj1.equals(obj2));
    }
}