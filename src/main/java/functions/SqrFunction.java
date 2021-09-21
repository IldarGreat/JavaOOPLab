package functions;

public class SqrFunction implements MathFunction {
    @Override
    public double apply(double x){ // Возвращает квадрат
        return Math.pow(x,2); // Обращаемся к классу java.lang.Math (как и указано в методичке!)
    }
}
