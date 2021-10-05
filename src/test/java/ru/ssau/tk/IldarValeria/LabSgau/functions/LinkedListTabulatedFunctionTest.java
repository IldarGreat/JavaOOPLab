package ru.ssau.tk.IldarValeria.LabSgau.functions;

import org.testng.Assert;
import org.testng.annotations.Test;


public class LinkedListTabulatedFunctionTest {

    public static final double DELTA = 0.0001;

    @Test
    public static void testGetCount(){
        double[] x = {1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 7.7};
        double[] y = {8.8, 9.9, 11.1, 12.2, 13.3, 14.4, 15.5};
        LinkedListTabulatedFunction linkedListTabulatedFunction = new LinkedListTabulatedFunction(x, y);
        Assert.assertEquals(linkedListTabulatedFunction.getCount(), 7);
        LinkedListTabulatedFunction linkedListTabulatedFunctionTwo = new LinkedListTabulatedFunction(new LnFunction(), 1, 100, 100);
        Assert.assertEquals(linkedListTabulatedFunctionTwo.getCount(), 100);
    }

    @Test
    public static void testLeftBound(){
        double[] x = {1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 7.7};
        double[] y = {8.8, 9.9, 11.1, 12.2, 13.3, 14.4, 15.5};
        LinkedListTabulatedFunction linkedListTabulatedFunction = new LinkedListTabulatedFunction(x, y);
        Assert.assertEquals(linkedListTabulatedFunction.leftBound(), 1.1);
    }

    @Test
    public static void testRightBound() {
        double[] x = {1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 7.7};
        double[] y = {8.8, 9.9, 11.1, 12.2, 13.3, 14.4, 15.5};
        LinkedListTabulatedFunction linkedListTabulatedFunction = new LinkedListTabulatedFunction(x, y);
        Assert.assertEquals(linkedListTabulatedFunction.rightBound(), 7.7);
    }

    @Test
    public static void testGetX() {
        double[] x = {1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 7.7};
        double[] y = {8.8, 9.9, 11.1, 12.2, 13.3, 14.4, 15.5};
        LinkedListTabulatedFunction linkedListTabulatedFunction = new LinkedListTabulatedFunction(x, y);
        Assert.assertEquals(linkedListTabulatedFunction.getX(0), 1.1);
        Assert.assertEquals(linkedListTabulatedFunction.getX(3), 4.4);
        Assert.assertEquals(linkedListTabulatedFunction.getX(5), 6.6);
    }

    @Test
    public static void testGetY() {
        double[] x = {1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 7.7};
        double[] y = {8.8, 9.9, 11.1, 12.2, 13.3, 14.4, 15.5};
        LinkedListTabulatedFunction linkedListTabulatedFunction = new LinkedListTabulatedFunction(x, y);
        Assert.assertEquals(linkedListTabulatedFunction.getY(1), 9.9);
        Assert.assertEquals(linkedListTabulatedFunction.getY(6), 15.5);
        Assert.assertEquals(linkedListTabulatedFunction.getY(4), 13.3);
    }

    @Test
    public static void testSetY() {
        double[] x = {1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 7.7};
        double[] y = {8.8, 9.9, 11.1, 12.2, 13.3, 14.4, 15.5};
        LinkedListTabulatedFunction linkedListTabulatedFunction = new LinkedListTabulatedFunction(x, y);
        linkedListTabulatedFunction.setY(1, 4.5);
        linkedListTabulatedFunction.setY(0, 34.6);
        linkedListTabulatedFunction.setY(6, 55.5);
        Assert.assertEquals(linkedListTabulatedFunction.getY(1), 4.5);
        Assert.assertEquals(linkedListTabulatedFunction.getY(0), 34.6);
        Assert.assertEquals(linkedListTabulatedFunction.getY(6), 55.5);
    }

    @Test
    public static void testIndexOfX() {
        double[] x = {1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 7.7};
        double[] y = {8.8, 9.9, 11.1, 12.2, 13.3, 14.4, 15.5};
        LinkedListTabulatedFunction linkedListTabulatedFunction = new LinkedListTabulatedFunction(x, y);
        Assert.assertEquals(linkedListTabulatedFunction.indexOfX(3.3), 2);
        Assert.assertEquals(linkedListTabulatedFunction.indexOfX(12.7), -1);
        Assert.assertEquals(linkedListTabulatedFunction.indexOfX(7.7), 6);
    }

    @Test
    public static void testIndexOfY() {
        double[] x = {1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 7.7};
        double[] y = {8.8, 9.9, 11.1, 12.2, 13.3, 14.4, 15.5};
        LinkedListTabulatedFunction linkedListTabulatedFunction = new LinkedListTabulatedFunction(x, y);
        Assert.assertEquals(linkedListTabulatedFunction.indexOfY(8.8), 0);
        Assert.assertEquals(linkedListTabulatedFunction.indexOfY(13.3), 4);
        Assert.assertEquals(linkedListTabulatedFunction.indexOfY(7.7), -1);
    }

    @Test
    public static void testFloorIndexOfX() {
        double[] x = {1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 7.7};
        double[] y = {8.8, 9.9, 11.1, 12.2, 13.3, 14.4, 15.5};
        LinkedListTabulatedFunction linkedListTabulatedFunction = new LinkedListTabulatedFunction(x, y);
        Assert.assertEquals(linkedListTabulatedFunction.floorIndexOfX(4.4), 3);
        Assert.assertEquals(linkedListTabulatedFunction.floorIndexOfX(13.3), 7);
        Assert.assertEquals(linkedListTabulatedFunction.floorIndexOfX(0.1), 0);
    }

    @Test
    public static void testExtrapolateLeft() {
        double[] x = {1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 7.7};
        double[] y = {8.8, 9.9, 11.1, 12.2, 13.3, 14.4, 15.5};
        LinkedListTabulatedFunction linkedListTabulatedFunction = new LinkedListTabulatedFunction(x, y);
        Assert.assertEquals(linkedListTabulatedFunction.extrapolateLeft(0.325), 0.3249, DELTA);
        Assert.assertEquals(linkedListTabulatedFunction.extrapolateLeft(0.154), 0.1539, DELTA);
        Assert.assertEquals(linkedListTabulatedFunction.extrapolateLeft(0.245), 0.2449, DELTA);
        Assert.assertEquals(linkedListTabulatedFunction.extrapolateLeft(Double.NEGATIVE_INFINITY), Double.NEGATIVE_INFINITY);
    }

    @Test
    public static void testExtrapolateRight() {
        double[] x = {1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 7.7};
        double[] y = {8.8, 9.9, 11.1, 12.2, 13.3, 14.4, 15.5};
        LinkedListTabulatedFunction linkedListTabulatedFunction = new LinkedListTabulatedFunction(x, y);
        Assert.assertEquals(linkedListTabulatedFunction.extrapolateRight(9.954), 17.7539, DELTA);
        Assert.assertEquals(linkedListTabulatedFunction.extrapolateRight(23.4), 31.1999, DELTA);
        Assert.assertEquals(linkedListTabulatedFunction.extrapolateRight(100.4), 108.1999, DELTA);
        Assert.assertEquals(linkedListTabulatedFunction.extrapolateRight(Double.POSITIVE_INFINITY), Double.POSITIVE_INFINITY);
    }

    @Test
    public static void testInterpolate() {
        double[] x = {1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 7.7};
        double[] y = {8.8, 9.9, 11.1, 12.2, 13.3, 14.4, 15.5};
        LinkedListTabulatedFunction linkedListTabulatedFunction = new LinkedListTabulatedFunction(x, y);
        Assert.assertEquals(linkedListTabulatedFunction.interpolate(2.9, 2), 10.7);
        Assert.assertEquals(linkedListTabulatedFunction.interpolate(4.5, 3), 12.2999, DELTA);
        Assert.assertEquals(linkedListTabulatedFunction.interpolate(6.675, 5), 14.475);
        Assert.assertEquals(linkedListTabulatedFunction.interpolate(1.345, 0), 9.045);
    }
}