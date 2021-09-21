package functions;

public class ConstantFunction implements MathFunction {
    private final double x;
    public ConstantFunction(double x){
        this.x=x;
    }
    @Override
    public double apply(double x){
        return this.x; // Возвращает свое х!!! А не переданное в аргумент
    }

    public double getX(){
        return this.x;
    }
}
