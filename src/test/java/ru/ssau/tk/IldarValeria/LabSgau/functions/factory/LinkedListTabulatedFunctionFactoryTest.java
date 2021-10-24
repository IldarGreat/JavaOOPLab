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

}