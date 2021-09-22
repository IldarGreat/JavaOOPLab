package ru.ssau.tk.IldarValeria.LabSgau.functions;

public class ConstantFunction implements MathFunction {
    private final double x;

    public ConstantFunction(double x) {
        this.x = x;
    }

    @Override
    public double apply(double x) {
        return this.x;
    }
}
