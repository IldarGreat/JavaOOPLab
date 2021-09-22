package ru.ssau.tk.IldarValeria.LabSgau.functions;

import org.testng.Assert;
import org.testng.annotations.Test;


public class ZeroFunctionTest {

    private static final double DELTA = 0.0001;

    @Test
    public void testApply() {
        ZeroFunction someObject = new ZeroFunction();
        Assert.assertEquals(someObject.apply(83), 0.0,DELTA);
        Assert.assertEquals(someObject.apply(-12383), 0.0,DELTA);
        Assert.assertEquals(someObject.apply(Double.NaN), 0.0,DELTA);
        Assert.assertEquals(someObject.apply(Double.POSITIVE_INFINITY),0.0);
        Assert.assertEquals(someObject.apply(Double.NEGATIVE_INFINITY),0.0);
    }

}