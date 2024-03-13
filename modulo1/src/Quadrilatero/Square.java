package Quadrilatero;

public class Square extends Rhombus{

    public Square(int lenght) {
        super(lenght, 0, 0); //TODO: calcolo delle diagolanli
    }

    @Override
    public int getArea() {
        return this.edge1*this.edge1;
    }
    
}
