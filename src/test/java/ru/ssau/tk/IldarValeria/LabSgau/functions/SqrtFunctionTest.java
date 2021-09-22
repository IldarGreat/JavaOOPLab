package ru.ssau.tk.IldarValeria.LabSgau.functions;

import org.testng.Assert;
import org.testng.annotations.Test;



public class SqrtFunctionTest {

    private static final double DELTA = 0.0001;

    @Test
    public void testApply(){
        SqrtFunction someObject = new SqrtFunction();
        Assert.assertEquals(someObject.apply(29),5.3851,DELTA);
        Assert.assertEquals(someObject.apply(Double.POSITIVE_INFINITY),Double.POSITIVE_INFINITY);
        Assert.assertEquals(someObject.apply(Double.NEGATIVE_INFINITY),Double.NaN);
        Assert.assertEquals(someObject.apply(Double.NaN),Double.NaN);
    }

}