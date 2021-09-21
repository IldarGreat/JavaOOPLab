package functions;

public class SqrtFunction implements MathFunction {
    @Override
    public double apply (double x){
        return Math.sqrt(x);
    }
}
