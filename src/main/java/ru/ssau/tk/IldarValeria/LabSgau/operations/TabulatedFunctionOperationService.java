package ru.ssau.tk.IldarValeria.LabSgau.operations;

import ru.ssau.tk.IldarValeria.LabSgau.functions.*;

public class TabulatedFunctionOperationService {

    public static Point[] asPoints(TabulatedFunction tabulatedFunction){
        Point[] points = new Point[tabulatedFunction.getCount()];
        int i = 0;
        for (Point currentPoint : tabulatedFunction) {
            points[i] = currentPoint;
            i++;
        }
        return points;
    }

}
