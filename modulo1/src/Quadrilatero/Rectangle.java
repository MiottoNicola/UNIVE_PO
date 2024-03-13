package Quadrilatero;

public class Rectangle extends Quadrilateral{
    public Rectangle(int lenght1, int lenght2) {
        super(lenght1, lenght2, lenght1, lenght2);
    }

    @Override
    public int getArea() {
        return this.edge1*this.edge2;
    }
}
