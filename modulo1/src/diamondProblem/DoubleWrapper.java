package diamondProblem;

public interface DoubleWrapper extends Wrapper{
    public double getDouble();

    @Override
    default public String getValue(){ return String.valueOf(this.getDouble()); }
}
