package ru.ssau.tk.IldarValeria.LabSgau.functions;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Iterator;

public class UnmodifiableTabulatedFunctionTest {
    private static final double DELTA = 0.0001;
    private static final double[] xValues = new double[]{3.4, 5.2, 6, 7.1};
    private static final double[] yValues = new double[]{-2.4, 1.2, 3, 5.1};
    private static final double[] x = {1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 7.7};
    private static final double[] y = {8.8, 9.9, 11.1, 12.2, 13.3, 14.4, 15.5};
    private static final LinkedListTabulatedFunction linkedListTabulatedFunction = new LinkedListTabulatedFunction(x, y);
    private static final ArrayTabulatedFunction arrayTabulatedObject = new ArrayTabulatedFunction(xValues, yValues);
    private static final UnmodifiableTabulatedFunction unmodifiableArrayTabulated = new UnmodifiableTabulatedFunction(arrayTabulatedObject);
    private static final UnmodifiableTabulatedFunction unmodifiableListTabulated = new UnmodifiableTabulatedFunction(linkedListTabulatedFunction);

    @Test
    public static void testGetCount() {
        Assert.assertEquals(unmodifiableArrayTabulated.getCount(), 4);
        Assert.assertEquals(unmodifiableListTabulated.getCount(), 7);
    }

    @Test
    public static void testGetX() {
        Assert.assertEquals(unmodifiableArrayTabulated.getX(0), 3.4);
        Assert.assertEquals(unmodifiableArrayTabulated.getX(1), 5.2);
        Assert.assertEquals(unmodifiableArrayTabulated.getX(2), 6.0);
        Assert.assertEquals(unmodifiableListTabulated.getX(0), 1.1);
        Assert.assertEquals(unmodifiableListTabulated.getX(1), 2.2);
        Assert.assertEquals(unmodifiableListTabulated.getX(2), 3.3);

    }

    @Test
    public static void testGetY() {
        Assert.assertEquals(unmodifiableArrayTabulated.getY(0), -2.4);
        Assert.assertEquals(unmodifiableArrayTabulated.getY(1), 1.2);
        Assert.assertEquals(unmodifiableArrayTabulated.getY(2), 3.0);
        Assert.assertEquals(unmodifiableListTabulated.getY(0), 8.8);
        Assert.assertEquals(unmodifiableListTabulated.getY(1), 9.9);
        Assert.assertEquals(unmodifiableListTabulated.getY(2), 11.1);
    }

    @Test
    public static void testLeftBound() {
        Assert.assertEquals(unmodifiableArrayTabulated.leftBound(), 3.4);
        Assert.assertEquals(unmodifiableListTabulated.leftBound(), 1.1);
    }

    @Test
    public static void testRightBound() {
        Assert.assertEquals(unmodifiableArrayTabulated.rightBound(), 7.1);
        Assert.assertEquals(unmodifiableListTabulated.rightBound(), 7.7);
    }

    @Test
    public static void testIndexOfX() {
        Assert.assertEquals(unmodifiableArrayTabulated.indexOfX(3.4), 0);
        Assert.assertEquals(unmodifiableArrayTabulated.indexOfX(5.2), 1);
        Assert.assertEquals(unmodifiableArrayTabulated.indexOfX(1.1), -1);
        Assert.assertEquals(unmodifiableListTabulated.indexOfX(1.1), 0);
        Assert.assertEquals(unmodifiableListTabulated.indexOfX(2.2), 1);
        Assert.assertEquals(unmodifiableListTabulated.indexOfX(3), -1);
    }

    @Test
    public static void testIndexOfY() {
        Assert.assertEquals(unmodifiableArrayTabulated.indexOfY(-2.4), 0);
        Assert.assertEquals(unmodifiableArrayTabulated.indexOfY(1.2), 1);
        Assert.assertEquals(unmodifiableArrayTabulated.indexOfY(3.0), 2);
        Assert.assertEquals(unmodifiableListTabulated.indexOfY(8.8), 0);
        Assert.assertEquals(unmodifiableListTabulated.indexOfY(9.9), 1);
        Assert.assertEquals(unmodifiableListTabulated.indexOfY(11.1), 2);
    }


    @Test
    public static void testApply() {
        Assert.assertEquals(unmodifiableArrayTabulated.apply(3.4), -2.4);
        Assert.assertEquals(unmodifiableArrayTabulated.apply(2.4), -4.3999, DELTA);
        Assert.assertEquals(unmodifiableArrayTabulated.apply(7.4), 5.6727, DELTA);
        Assert.assertEquals(unmodifiableListTabulated.apply(1.1), 8.8);
        Assert.assertEquals(unmodifiableListTabulated.apply(3.5), 11.2999, DELTA);
        Assert.assertEquals(unmodifiableListTabulated.apply(10.2), 17.9999, DELTA);
    }

    @Test
    public static void testSetY() {
        Assert.assertThrows(UnsupportedOperationException.class, () -> unmodifiableArrayTabulated.setY(2, 2));
        Assert.assertThrows(UnsupportedOperationException.class, () -> unmodifiableListTabulated.setY(2, 2));
    }


    @Test
    public static void testIterator() {
        Iterator<Point> iterator = unmodifiableArrayTabulated.iterator();
        int element = 0;
        while (iterator.hasNext()) {
            Point point = iterator.next();
            Assert.assertEquals(point.x, unmodifiableArrayTabulated.getX(element), DELTA);
            Assert.assertEquals(point.y, unmodifiableArrayTabulated.getY(element++), DELTA);
        }
        element = 0;
        for (Point point : unmodifiableArrayTabulated) {
            Assert.assertEquals(point.x, unmodifiableArrayTabulated.getX(element), DELTA);
            Assert.assertEquals(point.y, unmodifiableArrayTabulated.getY(element++), DELTA);
        }

        Iterator<Point> iteratorTwo = unmodifiableListTabulated.iterator();
        element = 0;
        while (iterator.hasNext()) {
            Point point = iteratorTwo.next();
            Assert.assertEquals(point.x, unmodifiableListTabulated.getX(element), DELTA);
            Assert.assertEquals(point.y, unmodifiableListTabulated.getY(element++), DELTA);
        }
        element = 0;
        for (Point point : unmodifiableListTabulated) {
            Assert.assertEquals(point.x, unmodifiableListTabulated.getX(element), DELTA);
            Assert.assertEquals(point.y, unmodifiableListTabulated.getY(element++), DELTA);
        }
    }
}