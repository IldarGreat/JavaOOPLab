package ru.ssau.tk.IldarValeria.LabSgau.functions.factory;

import ru.ssau.tk.IldarValeria.LabSgau.functions.*;

public class ArrayTabulatedFunctionFactory implements TabulatedFunctionFactory{

    @Override
    public ArrayTabulatedFunction create(double[] xValues, double[] yValues) {
        return new ArrayTabulatedFunction(xValues, yValues);
    }
}
