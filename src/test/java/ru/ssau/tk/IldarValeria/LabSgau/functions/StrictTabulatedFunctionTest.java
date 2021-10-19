package ru.ssau.tk.IldarValeria.LabSgau.functions;

import org.testng.Assert;
import org.testng.annotations.Test;


import java.util.Iterator;

public class StrictTabulatedFunctionTest {
    private static final double DELTA = 0.0001;
    private static final double[] xValues = new double[]{3.4, 5.2, 6, 7.1};
    private static final double[] yValues = new double[]{-2.4, 1.2, 3, 5.1};
    private static final double[] x = {1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 7.7};
    private static final double[] y = {8.8, 9.9, 11.1, 12.2, 13.3, 14.4, 15.5};
    private static final LinkedListTabulatedFunction linkedListTabulatedFunction = new LinkedListTabulatedFunction(x, y);
    private static final ArrayTabulatedFunction arrayTabulatedObject = new ArrayTabulatedFunction(xValues, yValues);
    private static final StrictTabulatedFunction strictArrayTabulated = new StrictTabulatedFunction(arrayTabulatedObject);
    private static final StrictTabulatedFunction strictLinkedListTabulated = new StrictTabulatedFunction(linkedListTabulatedFunction);

    @Test
    public static void testGetCount() {
        Assert.assertEquals(strictArrayTabulated.getCount(), 4);
        Assert.assertEquals(strictLinkedListTabulated.getCount(), 7);
    }

    @Test
    public static void testGetX() {
        Assert.assertEquals(strictArrayTabulated.getX(0), 3.4);
        Assert.assertEquals(strictArrayTabulated.getX(1), 5.2);
        Assert.assertEquals(strictArrayTabulated.getX(2), 6.0);
        Assert.assertEquals(strictArrayTabulated.getX(3), 7.1);
    }

    @Test
    public static void testGetY() {
        Assert.assertEquals(strictArrayTabulated.getY(0), -2.4);
        Assert.assertEquals(strictArrayTabulated.getY(1), 1.2);
        Assert.assertEquals(strictArrayTabulated.getY(2), 3.0);
        Assert.assertEquals(strictArrayTabulated.getY(3), 5.1);

    }

    @Test
    public static void testLeftBound() {
        Assert.assertEquals(strictArrayTabulated.leftBound(), 3.4, DELTA);
        Assert.assertEquals(strictLinkedListTabulated.leftBound(), 1.1);
    }

    @Test
    public static void testRightBound() {
        Assert.assertEquals(strictArrayTabulated.rightBound(), 7.1, DELTA);
        Assert.assertEquals(strictLinkedListTabulated.rightBound(), 7.7);
    }

    @Test
    public static void testIndexOfX() {
        Assert.assertEquals(strictArrayTabulated.indexOfX(3.4), 0);
        Assert.assertEquals(strictArrayTabulated.indexOfX(5.2), 1);
        Assert.assertEquals(strictArrayTabulated.indexOfX(6.0), 2);
        Assert.assertEquals(strictLinkedListTabulated.indexOfX(3.3), 2);
        Assert.assertEquals(strictLinkedListTabulated.indexOfX(12.7), -1);
        Assert.assertEquals(strictLinkedListTabulated.indexOfX(7.7), 6);
    }

    @Test
    public static void testIndexOfY() {
        Assert.assertEquals(strictArrayTabulated.indexOfY(-2.4), 0);
        Assert.assertEquals(strictArrayTabulated.indexOfY(1.2), 1);
        Assert.assertEquals(strictArrayTabulated.indexOfY(3.0), 2);
        Assert.assertEquals(strictLinkedListTabulated.indexOfY(8.8), 0);
        Assert.assertEquals(strictLinkedListTabulated.indexOfY(13.3), 4);
        Assert.assertEquals(strictLinkedListTabulated.indexOfY(7.7), -1);
    }

    @Test
    public static void testApply() {
        Assert.assertEquals(strictArrayTabulated.apply(3.4), -2.4);
        Assert.assertEquals(strictArrayTabulated.apply(5.2), 1.2);
        Assert.assertThrows(UnsupportedOperationException.class, () -> strictArrayTabulated.apply(7.4));
        Assert.assertThrows(UnsupportedOperationException.class, () -> strictArrayTabulated.apply(3.7));
        Assert.assertEquals(strictLinkedListTabulated.apply(1.1), 8.8);
        Assert.assertEquals(strictLinkedListTabulated.apply(2.2), 9.9);
        Assert.assertThrows(UnsupportedOperationException.class, () -> strictLinkedListTabulated.apply(7.4));
        Assert.assertThrows(UnsupportedOperationException.class, () -> strictLinkedListTabulated.apply(8.3));
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
    public static void testIterator() {
        Iterator<Point> iterator = strictArrayTabulated.iterator();
        int element = 0;
        while (iterator.hasNext()) {
            Point point = iterator.next();
            Assert.assertEquals(point.x, strictArrayTabulated.getX(element), DELTA);
            Assert.assertEquals(point.y, strictArrayTabulated.getY(element++), DELTA);
        }
        element = 0;
        for (Point point : strictArrayTabulated) {
            Assert.assertEquals(point.x, strictArrayTabulated.getX(element), DELTA);
            Assert.assertEquals(point.y, strictArrayTabulated.getY(element++), DELTA);
        }

        Iterator<Point> iteratorTwo = strictLinkedListTabulated.iterator();
        element = 0;
        while (iterator.hasNext()) {
            Point point = iteratorTwo.next();
            Assert.assertEquals(point.x, strictLinkedListTabulated.getX(element), DELTA);
            Assert.assertEquals(point.y, strictLinkedListTabulated.getY(element++), DELTA);
        }
        element = 0;
        for (Point point : strictLinkedListTabulated) {
            Assert.assertEquals(point.x, strictLinkedListTabulated.getX(element), DELTA);
            Assert.assertEquals(point.y, strictLinkedListTabulated.getY(element++), DELTA);
        }
    }
}