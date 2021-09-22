package ru.ssau.tk.IldarValeria.LabSgau.functions;

import org.testng.Assert;
import org.testng.annotations.Test;


public class UnitFunctionTest {

    private static final double DELTA = 0.0001;

    @Test
    public void testApply() {
        UnitFunction someObject = new UnitFunction();
        Assert.assertEquals(someObject.apply(158), 1.0,DELTA);
        Assert.assertEquals(someObject.apply(Double.NaN), 1.0);
        Assert.assertEquals(someObject.apply(Double.POSITIVE_INFINITY), 1.0);
        Assert.assertEquals(someObject.apply(Double.NEGATIVE_INFINITY), 1.0);
    }

}