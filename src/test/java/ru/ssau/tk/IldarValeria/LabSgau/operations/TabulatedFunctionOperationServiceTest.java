package ru.ssau.tk.IldarValeria.LabSgau.operations;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.ssau.tk.IldarValeria.LabSgau.functions.*;

public class TabulatedFunctionOperationServiceTest {

    @Test
    public static void testAsPoints() {
        double[] xValuesOne = new double[]{1, 2, 3, 4, 5, 6};
        double[] yValuesOne = new double[]{7, 8, 9, 10, 11, 12};
        double[] xValuesTwo = new double[]{2, 3, 4, 7, 9, 13};
        double[] yValuesTwo = new double[]{4, 3, 2, -1, 1, 12.3};
        ArrayTabulatedFunction arrayTabulatedFunction = new ArrayTabulatedFunction(xValuesOne, yValuesOne);
        LinkedListTabulatedFunction linkedListTabulatedFunction = new LinkedListTabulatedFunction(xValuesTwo, yValuesTwo);
        Point[] pointOne = TabulatedFunctionOperationService.asPoints(arrayTabulatedFunction);
        Point[] pointTwo = TabulatedFunctionOperationService.asPoints(linkedListTabulatedFunction);
        int element = 0;
        for (Point currentPoint : pointOne) {
            Assert.assertEquals(currentPoint.x, arrayTabulatedFunction.getX(element));
            Assert.assertEquals(currentPoint.y, arrayTabulatedFunction.getY(element++));
        }
        element = 0;
        for (Point currentPoint : pointTwo) {
            Assert.assertEquals(currentPoint.x, linkedListTabulatedFunction.getX(element));
            Assert.assertEquals(currentPoint.y, linkedListTabulatedFunction.getY(element++));
        }

    }
}