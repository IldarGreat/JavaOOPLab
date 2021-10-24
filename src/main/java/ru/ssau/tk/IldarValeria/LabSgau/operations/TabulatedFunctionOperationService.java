package ru.ssau.tk.IldarValeria.LabSgau.operations;

import ru.ssau.tk.IldarValeria.LabSgau.exceptions.*;
import ru.ssau.tk.IldarValeria.LabSgau.functions.*;
import ru.ssau.tk.IldarValeria.LabSgau.functions.factory.*;

public class TabulatedFunctionOperationService {
    private TabulatedFunctionFactory factory;

    public TabulatedFunctionOperationService(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public TabulatedFunctionOperationService() {
        factory = new ArrayTabulatedFunctionFactory();
    }

    public TabulatedFunctionFactory getFactory() {
        return factory;
    }

    public void setFactory(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public static Point[] asPoints(TabulatedFunction tabulatedFunction) {
        Point[] points = new Point[tabulatedFunction.getCount()];
        int i = 0;
        for (Point currentPoint : tabulatedFunction) {
            points[i] = currentPoint;
            i++;
        }
        return points;
    }

    private interface BiOperation {
        double apply(double u, double v);
    }

    private TabulatedFunction doOperation(TabulatedFunction firstFunction, TabulatedFunction secondFunction, BiOperation operation) {
        if (firstFunction.getCount() != secondFunction.getCount()) {
            throw new InconsistentFunctionsException("The sizes of the tabulated functions do not match");
        }
        Point[] firstPoints = asPoints(firstFunction);
        Point[] secondPoints = asPoints(secondFunction);
        double[] xValues = new double[firstFunction.getCount()];
        double[] yValues = new double[firstFunction.getCount()];
        for (int element = 0; element < firstFunction.getCount(); element++) {
            if (firstPoints[element].x != secondPoints[element].x) {
                throw new InconsistentFunctionsException("The values x of the functions are different");
            }
            xValues[element] = firstPoints[element].x;
            yValues[element] = operation.apply(firstPoints[element].y, secondPoints[element].y);
        }
        return factory.create(xValues, yValues);
    }

    public TabulatedFunction sum(TabulatedFunction firstFunction, TabulatedFunction secondFunction) {
        return doOperation(firstFunction, secondFunction, Double::sum);
    }

    public TabulatedFunction subtraction(TabulatedFunction firstFunction, TabulatedFunction secondFunction) {
        return doOperation(firstFunction, secondFunction, (first, second) -> first - second);
    }

}
