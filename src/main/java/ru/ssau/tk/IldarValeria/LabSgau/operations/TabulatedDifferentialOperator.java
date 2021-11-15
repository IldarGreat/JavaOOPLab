package ru.ssau.tk.IldarValeria.LabSgau.operations;

import ru.ssau.tk.IldarValeria.LabSgau.concurrent.SynchronizedTabulatedFunction;
import ru.ssau.tk.IldarValeria.LabSgau.functions.*;
import ru.ssau.tk.IldarValeria.LabSgau.functions.factory.*;

public class TabulatedDifferentialOperator implements DifferentialOperator<TabulatedFunction> {
    private TabulatedFunctionFactory factory;

    public TabulatedDifferentialOperator() {
        this.factory = new ArrayTabulatedFunctionFactory();
    }

    public TabulatedDifferentialOperator(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public TabulatedFunctionFactory getFactory() {
        return factory;
    }

    public void setFactory(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    @Override
    public TabulatedFunction derive(TabulatedFunction function) {
        Point[] points = TabulatedFunctionOperationService.asPoints(function);
        double[] xValues = new double[points.length];
        double[] yValues = new double[points.length];
        for (int element = 0; element < points.length - 1; element++) {
            xValues[element] = points[element].x;
            yValues[element] = (points[element + 1].y - points[element].y) / (points[element + 1].x - points[element].x);
        }
        yValues[points.length - 1] = yValues[points.length - 2];
        xValues[points.length - 1] = points[points.length - 1].x;
        return factory.create(xValues, yValues);
    }

    public TabulatedFunction deriveSynchronously(TabulatedFunction function) {
        if (function instanceof SynchronizedTabulatedFunction) {
            return ((SynchronizedTabulatedFunction) function).doSynchronously(this::derive);
        }
        return new SynchronizedTabulatedFunction(function).doSynchronously(this::derive);
    }

}
