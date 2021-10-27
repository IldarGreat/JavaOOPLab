package ru.ssau.tk.IldarValeria.LabSgau.operations;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.ssau.tk.IldarValeria.LabSgau.exceptions.*;
import ru.ssau.tk.IldarValeria.LabSgau.functions.*;
import ru.ssau.tk.IldarValeria.LabSgau.functions.factory.*;

public class TabulatedFunctionOperationServiceTest {

    @Test
    public static void testAsPoints() {
        double[] xValuesOne = new double[]{1, 2, 3, 4, 5, 6};
        double[] yValuesOne = new double[]{7, 8, 9, 10, 11, 12};
        double[] xValuesTwo = new double[]{2, 3, 4, 7, 9, 13};
        double[] yValuesTwo = new double[]{4, 3, 2, -1, 1, 12.3};
        ArrayTabulatedFunctionFactory arrayTabulatedFunctionFactory = new ArrayTabulatedFunctionFactory();
        LinkedListTabulatedFunctionFactory linkedTabulatedFunctionFactory = new LinkedListTabulatedFunctionFactory();
        TabulatedFunction arrayTabulatedFunction = arrayTabulatedFunctionFactory.create(xValuesOne, yValuesOne);
        TabulatedFunction linkedListTabulatedFunction = linkedTabulatedFunctionFactory.create(xValuesTwo, yValuesTwo);
        Assert.assertTrue(arrayTabulatedFunction instanceof ArrayTabulatedFunction);
        Assert.assertTrue(linkedListTabulatedFunction instanceof LinkedListTabulatedFunction);
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

    @Test
    public static void testConstructorsGetterAndSetter() {
        TabulatedFunctionOperationService tabulatedFunctionOperationServiceOne = new TabulatedFunctionOperationService(new LinkedListTabulatedFunctionFactory());
        TabulatedFunctionOperationService tabulatedFunctionOperationServiceTwo = new TabulatedFunctionOperationService();
        Assert.assertTrue(tabulatedFunctionOperationServiceOne.getFactory() instanceof LinkedListTabulatedFunctionFactory);
        Assert.assertTrue(tabulatedFunctionOperationServiceTwo.getFactory() instanceof ArrayTabulatedFunctionFactory);
        tabulatedFunctionOperationServiceTwo.setFactory(new LinkedListTabulatedFunctionFactory());
        Assert.assertTrue(tabulatedFunctionOperationServiceTwo.getFactory() instanceof LinkedListTabulatedFunctionFactory);
    }

    @Test
    public static void testSum() {
        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService(new LinkedListTabulatedFunctionFactory());
        TabulatedFunctionFactory arrayFactory = new ArrayTabulatedFunctionFactory();
        TabulatedFunctionFactory linkedFactory = new LinkedListTabulatedFunctionFactory();
        TabulatedFunction result = service.sum(arrayFactory.create(new double[]{1, 2, 3}, new double[]{1, 2, 3}), linkedFactory.create(new double[]{1, 2, 3}, new double[]{-1, -2, -3}));
        Assert.assertEquals(result.getX(0),1.0);
        Assert.assertEquals(result.getX(1),2.0);
        Assert.assertEquals(result.getX(2),3.0);
        Assert.assertEquals(result.getY(0),0.0);
        Assert.assertEquals(result.getY(1),0.0);
        Assert.assertEquals(result.getY(2),0.0);
        Assert.assertTrue(result instanceof LinkedListTabulatedFunction);
        Assert.assertThrows(InconsistentFunctionsException.class, () -> service.sum(arrayFactory.create(new double[]{1, 2, 3}, new double[]{1, 2, 3}), linkedFactory.create(new double[]{1, 2, 4}, new double[]{-1, -2, -3})));
        Assert.assertThrows(InconsistentFunctionsException.class, () -> service.sum(arrayFactory.create(new double[]{1, 2, 3, 4}, new double[]{1, 2, 3, 4}), linkedFactory.create(new double[]{1, 2, 3}, new double[]{-1, -2, -3})));
    }

    @Test
    public static void testSubtraction() {
        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService(new LinkedListTabulatedFunctionFactory());
        TabulatedFunctionFactory arrayFactory = new ArrayTabulatedFunctionFactory();
        TabulatedFunctionFactory linkedFactory = new LinkedListTabulatedFunctionFactory();
        TabulatedFunction result = service.subtraction(arrayFactory.create(new double[]{1, 2, 3}, new double[]{5, 2, 3}), linkedFactory.create(new double[]{1, 2, 3}, new double[]{-4, -7, -6}));
        Assert.assertEquals(result.getX(0),1.0);
        Assert.assertEquals(result.getX(1),2.0);
        Assert.assertEquals(result.getX(2),3.0);
        Assert.assertEquals(result.getY(0),9.0);
        Assert.assertEquals(result.getY(1),9.0);
        Assert.assertEquals(result.getY(2),9.0);
        Assert.assertTrue(result instanceof LinkedListTabulatedFunction);
        Assert.assertThrows(InconsistentFunctionsException.class, () -> service.subtraction(arrayFactory.create(new double[]{1, 2, 3}, new double[]{1, 2, 3}), linkedFactory.create(new double[]{1, 2, 4}, new double[]{-1, -2, -3})));
        Assert.assertThrows(InconsistentFunctionsException.class, () -> service.subtraction(arrayFactory.create(new double[]{1, 2, 3, 4}, new double[]{1, 2, 3, 4}), linkedFactory.create(new double[]{1, 2, 3}, new double[]{-1, -2, -3})));
    }

    @Test
    public static void testMultiplication() {
        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService();
        TabulatedFunctionFactory arrayFactory = new ArrayTabulatedFunctionFactory();
        TabulatedFunctionFactory linkedFactory = new LinkedListTabulatedFunctionFactory();
        TabulatedFunction result = service.multiplication(arrayFactory.create(new double[]{1, 2, 3}, new double[]{1, 2, 3}), linkedFactory.create(new double[]{1, 2, 3}, new double[]{0, 0, 0}));
        Assert.assertEquals(result.getX(0),1.0);
        Assert.assertEquals(result.getX(1),2.0);
        Assert.assertEquals(result.getX(2),3.0);
        Assert.assertEquals(result.getY(0),0.0);
        Assert.assertEquals(result.getY(1),0.0);
        Assert.assertEquals(result.getY(2),0.0);
        Assert.assertTrue(result instanceof ArrayTabulatedFunction);
        Assert.assertThrows(InconsistentFunctionsException.class, () -> service.multiplication(arrayFactory.create(new double[]{1, 2, 3}, new double[]{1, 2, 3}), linkedFactory.create(new double[]{1, 2, 4}, new double[]{-1, -2, -3})));
        Assert.assertThrows(InconsistentFunctionsException.class, () -> service.multiplication(arrayFactory.create(new double[]{1, 2, 3, 4}, new double[]{1, 2, 3, 4}), linkedFactory.create(new double[]{1, 2, 3}, new double[]{-1, -2, -3})));
    }

    @Test
    public static void testDivide() {
        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService();
        TabulatedFunctionFactory arrayFactory = new ArrayTabulatedFunctionFactory();
        TabulatedFunctionFactory linkedFactory = new LinkedListTabulatedFunctionFactory();
        TabulatedFunction result = service.divide(arrayFactory.create(new double[]{1, 2, 3}, new double[]{1, 2, 3}), linkedFactory.create(new double[]{1, 2, 3}, new double[]{-1, -2, -3}));
        Assert.assertEquals(result.getX(0),1.0);
        Assert.assertEquals(result.getX(1),2.0);
        Assert.assertEquals(result.getX(2),3.0);
        Assert.assertEquals(result.getY(0),-1.0);
        Assert.assertEquals(result.getY(1),-1.0);
        Assert.assertEquals(result.getY(2),-1.0);
        Assert.assertTrue(result instanceof ArrayTabulatedFunction);
        Assert.assertThrows(InconsistentFunctionsException.class, () -> service.divide(arrayFactory.create(new double[]{1, 2, 3}, new double[]{1, 2, 3}), linkedFactory.create(new double[]{1, 2, 4}, new double[]{-1, -2, -3})));
        Assert.assertThrows(InconsistentFunctionsException.class, () -> service.divide(arrayFactory.create(new double[]{1, 2, 3, 4}, new double[]{1, 2, 3, 4}), linkedFactory.create(new double[]{1, 2, 3}, new double[]{-1, -2, -3})));
    }

}