package ru.ssau.tk.IldarValeria.LabSgau.functions.factory;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.ssau.tk.IldarValeria.LabSgau.functions.*;

public class LinkedListTabulatedFunctionFactoryTest {

    @Test
    public static void testCreate() {
        LinkedListTabulatedFunctionFactory linkedTabulatedFunctionFactory = new LinkedListTabulatedFunctionFactory();
        double[] xValues = new double[]{1, 2, 3, 4, 5};
        double[] yValues = new double[]{6, 7, 8, 9, 10};
        Assert.assertTrue(linkedTabulatedFunctionFactory.create(xValues, yValues) instanceof LinkedListTabulatedFunction);
    }

    @Test
    public static void testCreateStrict() {
        double[] xValues = new double[]{1, 2, 3, 4, 5};
        double[] yValues = new double[]{6, 7, 8, 9, 10};
        LinkedListTabulatedFunctionFactory linkedTabulatedFunctionFactory = new LinkedListTabulatedFunctionFactory();
        TabulatedFunction linkedStrictTabulatedFunctionFactory = linkedTabulatedFunctionFactory.createStrict(xValues, yValues);
        Assert.assertThrows(UnsupportedOperationException.class, () -> linkedStrictTabulatedFunctionFactory.apply(2.5));
    }

    @Test
    public static void testCreateUnmodifiable() {
        double[] xValues = new double[]{1, 2, 3, 4, 5};
        double[] yValues = new double[]{6, 7, 8, 9, 10};
        LinkedListTabulatedFunctionFactory linkedTabulatedFunctionFactory = new LinkedListTabulatedFunctionFactory();
        TabulatedFunction linkedUnmodifiableTabulatedFunctionFactory = linkedTabulatedFunctionFactory.createUnmodifiable(xValues, yValues);
        Assert.assertThrows(UnsupportedOperationException.class, () -> linkedUnmodifiableTabulatedFunctionFactory.setY(0, 1));
    }

    @Test
    public static void testCreateStrictUnmodifiable() {
        double[] xValues = new double[]{1, 2, 3, 4, 5};
        double[] yValues = new double[]{6, 7, 8, 9, 10};
        LinkedListTabulatedFunctionFactory linkedTabulatedFunctionFactory = new LinkedListTabulatedFunctionFactory();
        TabulatedFunction linkedStrictAndUnmodifiableTabulatedFunctionFactory = linkedTabulatedFunctionFactory.createStrictUnmodifiable(xValues, yValues);
        Assert.assertThrows(UnsupportedOperationException.class, () -> linkedStrictAndUnmodifiableTabulatedFunctionFactory.setY(0,1));
        Assert.assertThrows(UnsupportedOperationException.class, () -> linkedStrictAndUnmodifiableTabulatedFunctionFactory.apply(2.5));
    }
}