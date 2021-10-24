package ru.ssau.tk.IldarValeria.LabSgau.functions.factory;

import ru.ssau.tk.IldarValeria.LabSgau.functions.*;

public interface TabulatedFunctionFactory {

    TabulatedFunction create(double[] xValues, double[] yValues);

    default TabulatedFunction createStrict(double[] xValues, double[] yValues){
        return new StrictTabulatedFunction(create(xValues, yValues));
    }
}
