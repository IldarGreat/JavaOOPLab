package ru.ssau.tk.IldarValeria.LabSgau.functions;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.ssau.tk.IldarValeria.LabSgau.exceptions.*;

import java.util.Iterator;

public class ArrayTabulatedFunctionTest {
    private static final double DELTA = 0.0001;
    private static final double STEP = (67.2 - 1.2) / 99.0;
    private static final double[] xValues = new double[]{3.4, 5.2, 6, 7.1};
    private static final double[] yValues = new double[]{-2.4, 1.2, 3, 5.1};
    private static final LnFunction lnObject = new LnFunction();
    private static final ArrayTabulatedFunction arrayTabulatedObject = new ArrayTabulatedFunction(xValues, yValues);
    private static final ArrayTabulatedFunction arrayTabulatedObjectTwo = new ArrayTabulatedFunction(lnObject, 1.2, 67.2, 100);


    @Test
    public static void testConstructorWithTwoParameters() {
        Assert.assertThrows(DifferentLengthOfArraysException.class, () -> new ArrayTabulatedFunction(new double[]{3.4, 5.2, 6, 7.1, 2.3}, yValues));
        Assert.assertThrows(ArrayIsNotSortedException.class, () -> new ArrayTabulatedFunction(new double[]{3.4, 5.2, 6, 5.2}, yValues));
        Assert.assertThrows(IllegalArgumentException.class, () -> new ArrayTabulatedFunction(new double[]{1.1}, new double[]{1.2, 1.3}));
    }

    @Test
    public static void testGetCount() {
        Assert.assertEquals(arrayTabulatedObject.getCount(), 4);
        Assert.assertEquals(arrayTabulatedObjectTwo.getCount(), 100);
    }

    @Test
    public static void testGetX() {
        Assert.assertEquals(arrayTabulatedObject.getX(0), 3.4);
        Assert.assertEquals(arrayTabulatedObject.getX(1), 5.2);
        Assert.assertEquals(arrayTabulatedObject.getX(2), 6.0);
        Assert.assertEquals(arrayTabulatedObject.getX(3), 7.1);
        for (int element = 0; element < 100; element++) {
            Assert.assertEquals(arrayTabulatedObjectTwo.getX(element), 1.2 + element * STEP, DELTA);
        }
    }

    @Test
    public static void testGetY() {
        Assert.assertEquals(arrayTabulatedObject.getY(0), -2.4);
        Assert.assertEquals(arrayTabulatedObject.getY(1), 1.2);
        Assert.assertEquals(arrayTabulatedObject.getY(2), 3.0);
        Assert.assertEquals(arrayTabulatedObject.getY(3), 5.1);
        for (int element = 0; element < 100; element++) {
            Assert.assertEquals(arrayTabulatedObjectTwo.getY(element), lnObject.apply(arrayTabulatedObjectTwo.getX(element)), DELTA);
        }
    }

    @Test
    public static void testLeftBound() {
        Assert.assertEquals(arrayTabulatedObject.leftBound(), 3.4, DELTA);
        Assert.assertEquals(arrayTabulatedObjectTwo.leftBound(), 1.2, DELTA);
    }

    @Test
    public static void testRightBound() {
        Assert.assertEquals(arrayTabulatedObject.rightBound(), 7.1, DELTA);
        Assert.assertEquals(arrayTabulatedObjectTwo.rightBound(), 67.2, DELTA);
    }

    @Test
    public static void testIndexOfX() {
        Assert.assertEquals(arrayTabulatedObject.indexOfX(3.4), 0);
        Assert.assertEquals(arrayTabulatedObject.indexOfX(5.2), 1);
        Assert.assertEquals(arrayTabulatedObject.indexOfX(6.0), 2);
        Assert.assertEquals(arrayTabulatedObjectTwo.indexOfX(1.1), -1);
    }

    @Test
    public static void testIndexOfY() {
        Assert.assertEquals(arrayTabulatedObject.indexOfY(-2.4), 0);
        Assert.assertEquals(arrayTabulatedObject.indexOfY(1.2), 1);
        Assert.assertEquals(arrayTabulatedObject.indexOfY(3.0), 2);
        Assert.assertEquals(arrayTabulatedObjectTwo.indexOfY(0.432), -1, DELTA);
        for (int element = 0; element < 100; element++) {
            Assert.assertEquals(arrayTabulatedObjectTwo.indexOfY(lnObject.apply(arrayTabulatedObjectTwo.getX(element))), element);
        }
    }

    @Test
    public static void testFloorIndexOfX() {
        Assert.assertEquals(arrayTabulatedObject.floorIndexOfX(5.5), 1);
        Assert.assertThrows(IllegalArgumentException.class, () -> arrayTabulatedObjectTwo.floorIndexOfX(1.1));
        Assert.assertEquals(arrayTabulatedObjectTwo.floorIndexOfX(4.6), 5);
        Assert.assertEquals(arrayTabulatedObjectTwo.floorIndexOfX(67.3), 100);
    }

    @Test
    public static void testExtrapolateLeft() {
        Assert.assertEquals(arrayTabulatedObjectTwo.extrapolateLeft(1.1), 0.1160, DELTA);
        Assert.assertEquals(arrayTabulatedObjectTwo.extrapolateLeft(0.9), -0.0165, DELTA);
        Assert.assertEquals(arrayTabulatedObjectTwo.extrapolateLeft(Double.NEGATIVE_INFINITY), Double.NEGATIVE_INFINITY);
    }

    @Test
    public static void testExtrapolateRight() {
        Assert.assertEquals(arrayTabulatedObjectTwo.extrapolateRight(67.3), 4.2091, DELTA);
        Assert.assertEquals(arrayTabulatedObjectTwo.extrapolateRight(69), 4.2345, DELTA);
        Assert.assertEquals(arrayTabulatedObjectTwo.extrapolateRight(Double.POSITIVE_INFINITY), Double.POSITIVE_INFINITY);
    }

    @Test
    public static void testInterpolate() {
        Assert.assertEquals(arrayTabulatedObject.interpolate(4.2, 0), -0.7999, DELTA);
        Assert.assertEquals(arrayTabulatedObject.interpolate(5.7, 1), 2.325, DELTA);
        Assert.assertThrows(InterpolationException.class, () -> arrayTabulatedObject.interpolate(3.5, 3));
    }

    @Test
    public static void testApply() {
        Assert.assertEquals(arrayTabulatedObject.apply(3.4), -2.4);
        Assert.assertEquals(arrayTabulatedObject.apply(2.4), -4.3999, DELTA);
        Assert.assertEquals(arrayTabulatedObject.apply(7.4), 5.6727, DELTA);
        Assert.assertEquals(arrayTabulatedObject.apply(3.7), -1.7999, DELTA);
        SqrFunction sqrObject = new SqrFunction();
        ArrayTabulatedFunction arrayTabulated = new ArrayTabulatedFunction(sqrObject, 1, 10, 10);
        LinkedListTabulatedFunction linkedListTabulated = new LinkedListTabulatedFunction(sqrObject, 1, 100, 10);
        CompositeFunction compositeObject = new CompositeFunction(arrayTabulated, linkedListTabulated);
        Assert.assertEquals(compositeObject.apply(10), 10000.0);
    }

    @Test
    public static void testSetY() {
        double[] xValues = new double[]{3.4, 5.2, 6, 7.1};
        double[] yValues = new double[]{1.1, 2.2, 3.3, 4.4};
        ArrayTabulatedFunction arrayTabulatedObjectThree = new ArrayTabulatedFunction(xValues, yValues);
        arrayTabulatedObjectThree.setY(0, 1);
        arrayTabulatedObjectThree.setY(1, 2);
        Assert.assertEquals(arrayTabulatedObjectThree.getY(0), 1.0);
        Assert.assertEquals(arrayTabulatedObjectThree.getY(1), 2.0);
    }

    @Test
    public static void testCheckLengthIsTheSame() {
        Assert.assertThrows(DifferentLengthOfArraysException.class, () -> ArrayTabulatedFunction.checkLengthIsTheSame(new double[4], new double[5]));
    }

    @Test
    public static void testCheckSorted() {
        Assert.assertThrows(ArrayIsNotSortedException.class, () -> ArrayTabulatedFunction.checkSorted(new double[]{2.3, 4.5, 2.3}));
    }

    @Test
    public static void testIterator() {
        Iterator<Point> iterator = arrayTabulatedObject.iterator();
        int element = 0;
        while (iterator.hasNext()) {
            Point point = iterator.next();
            Assert.assertEquals(point.x, arrayTabulatedObject.getX(element), DELTA);
            Assert.assertEquals(point.y, arrayTabulatedObject.getY(element++), DELTA);
        }
        element = 0;
        for (Point point : arrayTabulatedObject) {
            Assert.assertEquals(point.x, arrayTabulatedObject.getX(element), DELTA);
            Assert.assertEquals(point.y, arrayTabulatedObject.getY(element++), DELTA);
        }

    }
}