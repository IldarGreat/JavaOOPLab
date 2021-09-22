package ru.ssau.tk.IldarValeria.LabSgau.functions;

import org.testng.Assert;
import org.testng.annotations.Test;


public class ConstantFunctionTest {

    private static final double DELTA = 0.0001;

    @Test
    public void testApply() {
        ConstantFunction someObject = new ConstantFunction(666);
        Assert.assertEquals(someObject.apply(-4324), 666.0,DELTA);
        Assert.assertEquals(someObject.apply(Double.NaN), 666.0);
        Assert.assertEquals(someObject.apply(Double.POSITIVE_INFINITY), 666.0);
        Assert.assertEquals(someObject.apply(Double.NEGATIVE_INFINITY), 666.0);
    }

}