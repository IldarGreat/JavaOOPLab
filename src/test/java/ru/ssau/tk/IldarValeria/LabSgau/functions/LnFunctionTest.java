package ru.ssau.tk.IldarValeria.LabSgau.functions;

import org.testng.Assert;
import org.testng.annotations.Test;


public class LnFunctionTest {

    private static final double DELTA = 0.0001;

    @Test
    public void testApply() {
        LnFunction someObject = new LnFunction();
        Assert.assertEquals(someObject.apply(25), 3.2188, DELTA);
        Assert.assertEquals(someObject.apply(-324.0), Double.NaN);
        Assert.assertEquals(someObject.apply(47.0 / 23.0), 0.7146, DELTA);
        Assert.assertEquals(someObject.apply(Double.POSITIVE_INFINITY), Double.POSITIVE_INFINITY);
        Assert.assertEquals(someObject.apply(Double.NEGATIVE_INFINITY), Double.NaN);
        Assert.assertEquals(someObject.apply(Double.NaN), Double.NaN);
    }



}