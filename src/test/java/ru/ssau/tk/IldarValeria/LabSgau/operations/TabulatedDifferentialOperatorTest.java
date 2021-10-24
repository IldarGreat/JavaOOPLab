package ru.ssau.tk.IldarValeria.LabSgau.operations;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.ssau.tk.IldarValeria.LabSgau.functions.*;
import ru.ssau.tk.IldarValeria.LabSgau.functions.factory.*;

public class TabulatedDifferentialOperatorTest {

    @Test
    public static void testConstructorsGetterAndSetter() {
        TabulatedDifferentialOperator tabulatedDifferentialOperatorOne = new TabulatedDifferentialOperator();
        TabulatedDifferentialOperator tabulatedDifferentialOperatorTwo = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());
        Assert.assertTrue(tabulatedDifferentialOperatorOne.getFactory() instanceof ArrayTabulatedFunctionFactory);
        Assert.assertTrue(tabulatedDifferentialOperatorTwo.getFactory() instanceof LinkedListTabulatedFunctionFactory);
        tabulatedDifferentialOperatorOne.setFactory(new LinkedListTabulatedFunctionFactory());
        Assert.assertTrue(tabulatedDifferentialOperatorOne.getFactory() instanceof LinkedListTabulatedFunctionFactory);
    }

    @Test
    public static void testDerive() {
        double[] xValues = new double[]{0, 1, 2, 3, 4, 5};
        double[] yValues = new double[]{0, 3, 6, 9, 12, 15};
        TabulatedFunctionFactory arrayTabulatedFunctionFactory = new ArrayTabulatedFunctionFactory();
        TabulatedFunction arrayTabulatedFunction = arrayTabulatedFunctionFactory.create(xValues, yValues);
        TabulatedDifferentialOperator tabulatedDifferentialOperator = new TabulatedDifferentialOperator();
        TabulatedFunction arrayTabulatedAfterDerive = tabulatedDifferentialOperator.derive(arrayTabulatedFunction);
        for (int element = 0; element < arrayTabulatedFunction.getCount(); element++) {
            Assert.assertEquals(arrayTabulatedFunction.getX(element), (double) element);
            Assert.assertEquals(arrayTabulatedFunction.getY(element), (double) 3 * element);
            Assert.assertEquals(arrayTabulatedAfterDerive.getX(element), (double) element);
            Assert.assertEquals(arrayTabulatedAfterDerive.getY(element), 3.0);
        }
    }
}