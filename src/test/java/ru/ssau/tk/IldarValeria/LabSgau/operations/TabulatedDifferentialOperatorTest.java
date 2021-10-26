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
        TabulatedDifferentialOperator tabulatedDifferentialOperator = new TabulatedDifferentialOperator(arrayTabulatedFunctionFactory);
        TabulatedFunction arrayTabulatedAfterDerive = tabulatedDifferentialOperator.derive(arrayTabulatedFunction);
        Assert.assertTrue(arrayTabulatedAfterDerive instanceof ArrayTabulatedFunction);
        for (int element = 0; element < arrayTabulatedFunction.getCount(); element++) {
            Assert.assertEquals(arrayTabulatedFunction.getX(element), (double) element);
            Assert.assertEquals(arrayTabulatedFunction.getY(element), (double) 3 * element);
            Assert.assertEquals(arrayTabulatedAfterDerive.getX(element), (double) element);
            Assert.assertEquals(arrayTabulatedAfterDerive.getY(element), 3.0);
        }

        TabulatedFunctionFactory linkedTabulatedFunctionFactory = new LinkedListTabulatedFunctionFactory();
        TabulatedFunction linkedTabulatedFunction = linkedTabulatedFunctionFactory.create(xValues, yValues);
        TabulatedDifferentialOperator tabulatedDifferentialOperatorTwo = new TabulatedDifferentialOperator(linkedTabulatedFunctionFactory);
        TabulatedFunction linkedTabulatedAfterDerive = tabulatedDifferentialOperatorTwo.derive(linkedTabulatedFunction);
        Assert.assertTrue(linkedTabulatedAfterDerive instanceof LinkedListTabulatedFunction);
        for (int element = 0; element < arrayTabulatedFunction.getCount(); element++) {
            Assert.assertEquals(linkedTabulatedFunction.getX(element), (double) element);
            Assert.assertEquals(linkedTabulatedFunction.getY(element), (double) 3 * element);
            Assert.assertEquals(linkedTabulatedAfterDerive.getX(element), (double) element);
            Assert.assertEquals(linkedTabulatedAfterDerive.getY(element), 3.0);
        }
    }
}