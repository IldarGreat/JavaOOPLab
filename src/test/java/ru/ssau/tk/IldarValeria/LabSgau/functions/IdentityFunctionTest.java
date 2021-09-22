package ru.ssau.tk.IldarValeria.LabSgau.functions;

import org.testng.Assert;
import org.testng.annotations.Test;


public class IdentityFunctionTest {

    private static final double DELTA = 0.0001;

    @Test
    public void testApply() {
        IdentityFunction someObject = new IdentityFunction();
        Assert.assertEquals(someObject.apply(2423), 2423.0,DELTA);
        Assert.assertEquals(someObject.apply(Double.NaN), Double.NaN);
        Assert.assertEquals(someObject.apply(Double.POSITIVE_INFINITY), Double.POSITIVE_INFINITY);
        Assert.assertEquals(someObject.apply(Double.NEGATIVE_INFINITY), Double.NEGATIVE_INFINITY);
    }

}