package ru.ssau.tk.IldarValeria.LabSgau.functions;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MockTabulatedFunctionTest {

    private static final double DELTA = 0.0001;

    @Test
    public void testInterpolate() {
        MockTabulatedFunction mockObject = new MockTabulatedFunction();
        Assert.assertEquals(mockObject.interpolate(1.5, 1.3, 2.8, 1.4, 2.9), 1.5999, DELTA);
        Assert.assertEquals(mockObject.interpolate(4.2, 0.8, 3.2, 1, 3.2), 4.1166, DELTA);
    }

    @Test
    public void testApply() {
        MockTabulatedFunction mockObject = new MockTabulatedFunction();
        Assert.assertEquals(mockObject.apply(2.9), 3.0, DELTA);
        Assert.assertEquals(mockObject.apply(1.2), 1.2999, DELTA);
        Assert.assertEquals(mockObject.apply(1.4), 2.9, DELTA);
    }

}