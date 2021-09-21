package functions;

public class CompositeFunction implements MathFunction{
    private MathFunction firstFunction;
    private MathFunction secondFunction;

    public CompositeFunction (MathFunction firstFunction, MathFunction secondFunction){
        this.firstFunction = firstFunction;
        this.secondFunction = secondFunction;
    }
    @Override
    public double apply(double x) {
        return firstFunction.apply(secondFunction.apply(x));
    }
}
