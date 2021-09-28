package ru.ssau.tk.IldarValeria.LabSgau.functions;


import org.testng.Assert;
import org.testng.annotations.Test;


public class ArrayTabulatedFunctionTest {
    static final double DELTA = 0.0001;
    static double[] xValues = new double[]{3.4, 5.2, 6, 2.1};
    static double[] yValues = new double[]{-2.4, 1.2, 3, 5.1};
    static LnFunction lnObject = new LnFunction();
    static ArrayTabulatedFunction arrayTabulatedObject = new ArrayTabulatedFunction(xValues, yValues);
    static ArrayTabulatedFunction arrayTabulatedObjectTwo = new ArrayTabulatedFunction(lnObject, 1.2, 67.2, 100);

    @Test
    public static void testArrayTabulatedFunctionWithTwoParameters() {
        Assert.assertEquals(arrayTabulatedObject.getCount(), 4);
    }

    @Test
    public static void testGetCount() {
        Assert.assertEquals(arrayTabulatedObjectTwo.getCount(), 100);
    }

    @Test
    public static void testGetX() {
        for (int element = 0; element < 99; element++) {
            Assert.assertEquals(arrayTabulatedObjectTwo.getX(element), element * (67.2 - 1.2) / 100.0 + 1.2, DELTA);
        }
    }

    @Test
    public static void testGetY() {
        for (int element = 0; element < 99; element++) {
            Assert.assertEquals(arrayTabulatedObjectTwo.getY(element), lnObject.apply(arrayTabulatedObjectTwo.getX(element)), DELTA);
        }
    }

    @Test
    public static void testLeftBound() {
        Assert.assertEquals(arrayTabulatedObjectTwo.leftBound(), 1.2, DELTA);
    }

    @Test
    public static void testRightBound() {
        Assert.assertEquals(arrayTabulatedObjectTwo.rightBound(), 67.2, DELTA);
    }

    @Test
    public static void testIndexOfX() {
        Assert.assertEquals(arrayTabulatedObjectTwo.indexOfX(1.1), -1, DELTA);
        for (int element = 0; element < 99; element++) {
            Assert.assertEquals(arrayTabulatedObjectTwo.indexOfX(1.2 + element * 0.66), element);
        }
    }

    @Test
    public static void testIndexOfY() {
        Assert.assertEquals(arrayTabulatedObjectTwo.indexOfY(0.432), -1, DELTA);
        for (int element = 0; element < 99; element++) {
            Assert.assertEquals(arrayTabulatedObjectTwo.indexOfY(lnObject.apply(arrayTabulatedObjectTwo.getX(element))), element);
        }
    }

    @Test
    public static void testFloorIndexOfX() {
        for (int element = 0; element < 99; element++) {
            Assert.assertEquals(arrayTabulatedObjectTwo.floorIndexOfX(1.2 + element * 0.66), element);
        }
        Assert.assertEquals(arrayTabulatedObjectTwo.floorIndexOfX(1.1), 0);
        Assert.assertEquals(arrayTabulatedObjectTwo.floorIndexOfX(4.6), 5);
        Assert.assertEquals(arrayTabulatedObjectTwo.floorIndexOfX(67.3), 100);
    }

    @Test
    public static void testExtrapolateLeft() {
        Assert.assertEquals(arrayTabulatedObjectTwo.extrapolateLeft(1.1), 0.1159, DELTA);
        Assert.assertEquals(arrayTabulatedObjectTwo.extrapolateLeft(0.9), -0.0168, DELTA);
        Assert.assertEquals(arrayTabulatedObjectTwo.extrapolateLeft(Double.NEGATIVE_INFINITY), Double.NEGATIVE_INFINITY);
    }

    @Test
    public static void testExtrapolateRight() {
        Assert.assertEquals(arrayTabulatedObjectTwo.extrapolateRight(67.3), 4.2091, DELTA);
        Assert.assertEquals(arrayTabulatedObjectTwo.extrapolateRight(69), 4.2347, DELTA);
        Assert.assertEquals(arrayTabulatedObjectTwo.extrapolateRight(Double.POSITIVE_INFINITY), Double.POSITIVE_INFINITY);
    }
}