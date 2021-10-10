package ru.ssau.tk.IldarValeria.LabSgau.functions;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LinkedListTabulatedFunctionTest {
    private static final double DELTA = 0.0001;
    private static final double STEP = (100.0 - 1.0) / 99.0;
    private static final double[] x = {1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 7.7};
    private static final double[] y = {8.8, 9.9, 11.1, 12.2, 13.3, 14.4, 15.5};
    private static final LnFunction lnFunction = new LnFunction();
    private static final LinkedListTabulatedFunction linkedListTabulatedFunction = new LinkedListTabulatedFunction(x, y);
    private static final LinkedListTabulatedFunction linkedListTabulatedFunctionTwo = new LinkedListTabulatedFunction(lnFunction, 1, 100, 100);

    @Test
    public static void testGetCount() {
        Assert.assertEquals(linkedListTabulatedFunction.getCount(), 7);
        Assert.assertEquals(linkedListTabulatedFunctionTwo.getCount(), 100);
    }

    @Test
    public static void testLeftBound() {
        Assert.assertEquals(linkedListTabulatedFunction.leftBound(), 1.1);
        Assert.assertEquals(linkedListTabulatedFunctionTwo.leftBound(), 1.0);
    }

    @Test
    public static void testRightBound() {
        Assert.assertEquals(linkedListTabulatedFunction.rightBound(), 7.7);
        Assert.assertEquals(linkedListTabulatedFunctionTwo.rightBound(), 100.0);
    }

    @Test
    public static void testGetX() {
        Assert.assertEquals(linkedListTabulatedFunction.getX(0), 1.1);
        Assert.assertEquals(linkedListTabulatedFunction.getX(3), 4.4);
        Assert.assertEquals(linkedListTabulatedFunction.getX(5), 6.6);
        for (int element = 0; element < 100; element++) {
            Assert.assertEquals(linkedListTabulatedFunctionTwo.getX(element), 1 + STEP * element, DELTA);
        }
    }

    @Test
    public static void testGetY() {
        Assert.assertEquals(linkedListTabulatedFunction.getY(1), 9.9);
        Assert.assertEquals(linkedListTabulatedFunction.getY(6), 15.5);
        Assert.assertEquals(linkedListTabulatedFunction.getY(4), 13.3);
        for (int element = 0; element < 100; element++) {
            Assert.assertEquals(linkedListTabulatedFunctionTwo.getY(element), lnFunction.apply(1 + STEP * element), DELTA);
        }
    }

    @Test
    public static void testIndexOfX() {
        Assert.assertEquals(linkedListTabulatedFunction.indexOfX(3.3), 2);
        Assert.assertEquals(linkedListTabulatedFunction.indexOfX(12.7), -1);
        Assert.assertEquals(linkedListTabulatedFunction.indexOfX(7.7), 6);
        for (int element = 0; element < 100; element++) {
            Assert.assertEquals(linkedListTabulatedFunctionTwo.indexOfX(1 + element * STEP), element);
        }
    }

    @Test
    public static void testIndexOfY() {
        Assert.assertEquals(linkedListTabulatedFunction.indexOfY(8.8), 0);
        Assert.assertEquals(linkedListTabulatedFunction.indexOfY(13.3), 4);
        Assert.assertEquals(linkedListTabulatedFunction.indexOfY(7.7), -1);
        for (int element = 0; element < 100; element++) {
            Assert.assertEquals(linkedListTabulatedFunctionTwo.indexOfY(lnFunction.apply(1 + element * STEP)), element);
        }
    }

    @Test
    public static void testFloorIndexOfX() {
        Assert.assertEquals(linkedListTabulatedFunction.floorIndexOfX(4.4), 3);
        Assert.assertEquals(linkedListTabulatedFunction.floorIndexOfX(5.5), 4);
        Assert.assertEquals(linkedListTabulatedFunction.floorIndexOfX(5.6), 4);
        Assert.assertEquals(linkedListTabulatedFunction.floorIndexOfX(6.76), 5);
        Assert.assertEquals(linkedListTabulatedFunction.floorIndexOfX(6.6), 5);
        Assert.assertEquals(linkedListTabulatedFunction.floorIndexOfX(13.3), 7);
        Assert.assertEquals(linkedListTabulatedFunction.floorIndexOfX(0.1), 0);
    }

    @Test
    public static void testExtrapolateLeft() {
        Assert.assertEquals(linkedListTabulatedFunction.extrapolateLeft(0.325), 8.025, DELTA);
        Assert.assertEquals(linkedListTabulatedFunction.extrapolateLeft(0.154), 7.8540, DELTA);
        Assert.assertEquals(linkedListTabulatedFunction.extrapolateLeft(0.245), 7.9450, DELTA);
        Assert.assertEquals(linkedListTabulatedFunction.extrapolateLeft(Double.NEGATIVE_INFINITY), Double.NEGATIVE_INFINITY);
        Assert.assertEquals(linkedListTabulatedFunctionTwo.extrapolateLeft(0.85), -0.1039, DELTA);
        Assert.assertEquals(linkedListTabulatedFunctionTwo.extrapolateLeft(0.154), -0.5864, DELTA);
    }

    @Test
    public static void testExtrapolateRight() {
        Assert.assertEquals(linkedListTabulatedFunction.extrapolateRight(9.954), 17.7539, DELTA);
        Assert.assertEquals(linkedListTabulatedFunction.extrapolateRight(23.4), 31.1999, DELTA);
        Assert.assertEquals(linkedListTabulatedFunction.extrapolateRight(100.4), 108.1999, DELTA);
        Assert.assertEquals(linkedListTabulatedFunction.extrapolateRight(Double.POSITIVE_INFINITY), Double.POSITIVE_INFINITY);
        Assert.assertEquals(linkedListTabulatedFunctionTwo.extrapolateRight(101.3), 4.6182, DELTA);
        Assert.assertEquals(linkedListTabulatedFunctionTwo.extrapolateRight(115), 4.7559, DELTA);
    }

    @Test
    public static void testInterpolate() {
        Assert.assertEquals(linkedListTabulatedFunction.interpolate(2.9, 2), 10.7);
        Assert.assertEquals(linkedListTabulatedFunction.interpolate(4.5, 3), 12.2999, DELTA);
        Assert.assertEquals(linkedListTabulatedFunction.interpolate(6.675, 5), 14.475);
        Assert.assertEquals(linkedListTabulatedFunction.interpolate(1.345, 0), 9.045);
        Assert.assertEquals(linkedListTabulatedFunctionTwo.interpolate(4.5, 3), 1.4978, DELTA);
        Assert.assertEquals(linkedListTabulatedFunctionTwo.interpolate(15.3, 17), 2.7443, DELTA);
    }

    @Test
    public static void testFloorNodeOfX() {
        Assert.assertEquals(linkedListTabulatedFunction.floorNodeOfX(2.3).x, 2.2, DELTA);
        Assert.assertEquals(linkedListTabulatedFunction.floorNodeOfX(4.67).x, 4.4, DELTA);
        Assert.assertEquals(linkedListTabulatedFunction.floorNodeOfX(13.6).x, 7.7, DELTA);
        Assert.assertEquals(linkedListTabulatedFunction.floorNodeOfX(0).x, 1.1, DELTA);
    }

    @Test
    public static void testApply() {
        Assert.assertEquals(linkedListTabulatedFunction.apply(1.1), 8.8);
        Assert.assertEquals(linkedListTabulatedFunction.apply(3.5), 11.2999, DELTA);
        Assert.assertEquals(linkedListTabulatedFunction.apply(0.7), 8.4, DELTA);
        Assert.assertEquals(linkedListTabulatedFunction.apply(10.2), 17.9999, DELTA);
    }

    @Test
    public static void testSetY() {
        double[] x = {1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 7.7};
        double[] y = {1, 2, 3, 4, 5, 6, 7};
        LinkedListTabulatedFunction linkedListTabulatedFunctionThree = new LinkedListTabulatedFunction(x, y);
        linkedListTabulatedFunctionThree.setY(0, 1.5);
        linkedListTabulatedFunctionThree.setY(6, 5);
        Assert.assertEquals(linkedListTabulatedFunctionThree.getY(0), 1.5);
        Assert.assertEquals(linkedListTabulatedFunctionThree.getY(6), 5.0);
    }
}