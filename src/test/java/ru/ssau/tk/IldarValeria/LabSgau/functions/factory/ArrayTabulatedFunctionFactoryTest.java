package ru.ssau.tk.IldarValeria.LabSgau.functions.factory;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.ssau.tk.IldarValeria.LabSgau.functions.*;

public class ArrayTabulatedFunctionFactoryTest {

    @Test
    public static void testCreate() {
        ArrayTabulatedFunctionFactory arrayTabulatedFunctionFactory = new ArrayTabulatedFunctionFactory();
        double[] xValues = new double[]{1, 2, 3, 4, 5};
        double[] yValues = new double[]{6, 7, 8, 9, 10};
        Assert.assertTrue(arrayTabulatedFunctionFactory.create(xValues, yValues) instanceof ArrayTabulatedFunction);
    }

    @Test
    public static void testCreateStrict() {
        double[] xValues = new double[]{1, 2, 3, 4, 5};
        double[] yValues = new double[]{6, 7, 8, 9, 10};
        ArrayTabulatedFunctionFactory arrayTabulatedFunctionFactory = new ArrayTabulatedFunctionFactory();
        TabulatedFunction arrayStrictTabulatedFunctionFactory = arrayTabulatedFunctionFactory.createStrict(xValues, yValues);
        Assert.assertThrows(UnsupportedOperationException.class, () -> arrayStrictTabulatedFunctionFactory.apply(2.5));
    }

    @Test
    public static void testCreateUnmodifiable() {
        double[] xValues = new double[]{1, 2, 3, 4, 5};
        double[] yValues = new double[]{6, 7, 8, 9, 10};
        ArrayTabulatedFunctionFactory arrayTabulatedFunctionFactory = new ArrayTabulatedFunctionFactory();
        TabulatedFunction arrayUnmodifiableTabulatedFunctionFactory = arrayTabulatedFunctionFactory.createUnmodifiable(xValues, yValues);
        Assert.assertThrows(UnsupportedOperationException.class, () -> arrayUnmodifiableTabulatedFunctionFactory.setY(0,1));
    }
}