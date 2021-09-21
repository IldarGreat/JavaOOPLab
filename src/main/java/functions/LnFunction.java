package functions;

public class LnFunction implements MathFunction{  // По требованию преподавателя
    @Override
    public double apply(double x){
        return Math.log(x);
    }
}
