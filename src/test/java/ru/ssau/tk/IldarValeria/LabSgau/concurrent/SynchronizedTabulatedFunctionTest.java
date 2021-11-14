package ru.ssau.tk.IldarValeria.LabSgau.concurrent;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.ssau.tk.IldarValeria.LabSgau.functions.*;

public class SynchronizedTabulatedFunctionTest {
    private final SynchronizedTabulatedFunction synchronizedArrayTabulated = new SynchronizedTabulatedFunction(
            new ArrayTabulatedFunction(new double[]{3.4, 5.2, 6, 7.1}, new double[]{-2.4, 1.2, 3, 5.1}));
    private final SynchronizedTabulatedFunction synchronizedListTabulated = new SynchronizedTabulatedFunction(
            new LinkedListTabulatedFunction(new double[]{1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 7.7}, new double[]{8.8, 9.9, 11.1, 12.2, 13.3, 14.4, 15.5}));

    @Test
    public void testGetCount() {
        Assert.assertEquals(synchronizedArrayTabulated.getCount(), 4);
        Assert.assertEquals(synchronizedListTabulated.getCount(), 7);
    }

    @Test
    public void testGetX() {
        Assert.assertEquals(synchronizedArrayTabulated.getX(0), 3.4);
        Assert.assertEquals(synchronizedArrayTabulated.getX(1), 5.2);
        Assert.assertEquals(synchronizedArrayTabulated.getX(2), 6.0);
        Assert.assertEquals(synchronizedArrayTabulated.getX(3), 7.1);
        Assert.assertEquals(synchronizedListTabulated.getX(0), 1.1);
        Assert.assertEquals(synchronizedListTabulated.getX(1), 2.2);
        Assert.assertEquals(synchronizedListTabulated.getX(2), 3.3);
        Assert.assertEquals(synchronizedListTabulated.getX(3), 4.4);
        Assert.assertEquals(synchronizedListTabulated.getX(4), 5.5);
        Assert.assertEquals(synchronizedListTabulated.getX(5), 6.6);
        Assert.assertEquals(synchronizedListTabulated.getX(6), 7.7);
    }

    @Test
    public void testGetY() {
        Assert.assertEquals(synchronizedArrayTabulated.getY(0), -2.4);
        Assert.assertEquals(synchronizedArrayTabulated.getY(1), 1.2);
        Assert.assertEquals(synchronizedArrayTabulated.getY(2), 3.0);
        Assert.assertEquals(synchronizedArrayTabulated.getY(3), 5.1);
        Assert.assertEquals(synchronizedListTabulated.getY(0), 8.8);
        Assert.assertEquals(synchronizedListTabulated.getY(1), 9.9);
        Assert.assertEquals(synchronizedListTabulated.getY(2), 11.1);
        Assert.assertEquals(synchronizedListTabulated.getY(3), 12.2);
        Assert.assertEquals(synchronizedListTabulated.getY(4), 13.3);
        Assert.assertEquals(synchronizedListTabulated.getY(5), 14.4);
        Assert.assertEquals(synchronizedListTabulated.getY(6), 15.5);
    }

    @Test
    public void testSetY() {
        SynchronizedTabulatedFunction synchronizedArrayTabulated = new SynchronizedTabulatedFunction(
                new ArrayTabulatedFunction(new double[]{3.4, 4.2}, new double[]{-2.4, 0}));
        SynchronizedTabulatedFunction synchronizedListTabulated = new SynchronizedTabulatedFunction(
                new LinkedListTabulatedFunction(new double[]{1.1, 2.3}, new double[]{8.8, 13.2}));
        synchronizedArrayTabulated.setY(0, 13.3);
        synchronizedListTabulated.setY(0, 15.2);
        Assert.assertEquals(synchronizedArrayTabulated.getY(0), 13.3);
        Assert.assertEquals(synchronizedListTabulated.getY(0), 15.2);
    }

    @Test
    public void testIndexOfX() {
        Assert.assertEquals(synchronizedArrayTabulated.indexOfX(3.4), 0);
        Assert.assertEquals(synchronizedArrayTabulated.indexOfX(5.2), 1);
        Assert.assertEquals(synchronizedArrayTabulated.indexOfX(6.0), 2);
        Assert.assertEquals(synchronizedArrayTabulated.indexOfX(7.1), 3);
        Assert.assertEquals(synchronizedListTabulated.indexOfX(1.1), 0);
        Assert.assertEquals(synchronizedListTabulated.indexOfX(2.2), 1);
        Assert.assertEquals(synchronizedListTabulated.indexOfX(3.3), 2);
        Assert.assertEquals(synchronizedListTabulated.indexOfX(4.4), 3);
        Assert.assertEquals(synchronizedListTabulated.indexOfX(5.5), 4);
        Assert.assertEquals(synchronizedListTabulated.indexOfX(6.6), 5);
        Assert.assertEquals(synchronizedListTabulated.indexOfX(7.7), 6);
    }

    @Test
    public void testIndexOfY() {
        Assert.assertEquals(synchronizedArrayTabulated.indexOfY(-2.4), 0);
        Assert.assertEquals(synchronizedArrayTabulated.indexOfY(1.2), 1);
        Assert.assertEquals(synchronizedArrayTabulated.indexOfY(3.0), 2);
        Assert.assertEquals(synchronizedArrayTabulated.indexOfY(5.1), 3);
        Assert.assertEquals(synchronizedListTabulated.indexOfY(8.8), 0);
        Assert.assertEquals(synchronizedListTabulated.indexOfY(9.9), 1);
        Assert.assertEquals(synchronizedListTabulated.indexOfY(11.1), 2);
        Assert.assertEquals(synchronizedListTabulated.indexOfY(12.2), 3);
        Assert.assertEquals(synchronizedListTabulated.indexOfY(13.3), 4);
        Assert.assertEquals(synchronizedListTabulated.indexOfY(14.4), 5);
        Assert.assertEquals(synchronizedListTabulated.indexOfY(15.5), 6);
    }

    @Test
    public void testLeftBound() {
        Assert.assertEquals(synchronizedArrayTabulated.leftBound(), 3.4);
        Assert.assertEquals(synchronizedListTabulated.leftBound(), 1.1);
    }

    @Test
    public void testRightBound() {
        Assert.assertEquals(synchronizedArrayTabulated.rightBound(), 7.1);
        Assert.assertEquals(synchronizedListTabulated.rightBound(), 7.7);
    }

    @Test
    public void testIterator() {
    }

    @Test
    public void testApply() {
        Assert.assertEquals(synchronizedArrayTabulated.apply(3.4), -2.4);
        Assert.assertEquals(synchronizedListTabulated.apply(1.1), 8.8);
    }
}