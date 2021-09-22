package ru.ssau.tk.IldarValeria.LabSgau.functions;

import org.testng.Assert;
import org.testng.annotations.Test;



public class SqrFunctionTest {

    private static final double DELTA = 0.0001;

    @Test
    public void testApply(){
        SqrFunction someObject = new SqrFunction();
        Assert.assertEquals(someObject.apply(-0.16),0.0256,DELTA);
        Assert.assertEquals(someObject.apply(15.2),231.04,DELTA);
        Assert.assertEquals(someObject.apply(0.0432),0.0018,DELTA);
        Assert.assertEquals(someObject.apply(Double.POSITIVE_INFINITY),Double.POSITIVE_INFINITY);
        Assert.assertEquals(someObject.apply(Double.NEGATIVE_INFINITY),Double.POSITIVE_INFINITY);
        Assert.assertEquals(someObject.apply(Double.NaN),Double.NaN);
    }

}