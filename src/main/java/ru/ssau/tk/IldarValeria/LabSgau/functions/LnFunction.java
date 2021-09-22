package ru.ssau.tk.IldarValeria.LabSgau.functions;

public class LnFunction implements MathFunction {

    @Override
    public double apply(double x) {
        return Math.log(x);
    }
}
