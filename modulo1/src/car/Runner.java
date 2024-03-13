package car;
public class Runner {
    public static void main(String[] args) {
        car myCar = new car();
        myCar.speed = 0;
        myCar.fuel = 100;
        myCar.accelerate(10);
        System.out.println(myCar.speed);
        System.out.println(myCar.fuel);
        myCar.fullbrake();
        System.out.println(myCar.speed);
        myCar.refuel(10);
        System.out.println(myCar.fuel);
    }
}