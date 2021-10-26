package ru.ssau.tk.IldarValeria.LabSgau.functions.factory;

import ru.ssau.tk.IldarValeria.LabSgau.functions.*;

public class LinkedListTabulatedFunctionFactory implements TabulatedFunctionFactory {

    @Override
    public LinkedListTabulatedFunction create(double[] xValues, double[] yValues) {
        return new LinkedListTabulatedFunction(xValues, yValues);
    }
}
