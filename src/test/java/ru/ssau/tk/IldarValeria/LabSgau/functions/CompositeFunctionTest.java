package ru.ssau.tk.IldarValeria.LabSgau.functions;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CompositeFunctionTest {

    private static final double DELTA = 0.0001;

    @Test
    public void testApply() {
        LnFunction lnObject = new LnFunction();
        SqrtFunction sqrtObject = new SqrtFunction();
        CompositeFunction compositeObject = new CompositeFunction(lnObject, sqrtObject);
        Assert.assertEquals(compositeObject.apply(63), 2.0354, DELTA);
        Assert.assertEquals(compositeObject.apply(Double.NaN), Double.NaN);
        Assert.assertEquals(compositeObject.apply(Double.POSITIVE_INFINITY), Double.POSITIVE_INFINITY);
        Assert.assertEquals(compositeObject.apply(Double.NEGATIVE_INFINITY), Double.NaN);

        IdentityFunction identityObject = new IdentityFunction();
        ConstantFunction constantObject = new ConstantFunction(-2763);
        CompositeFunction compositeObjectTwo = new CompositeFunction(identityObject, constantObject);
        Assert.assertEquals(compositeObjectTwo.apply(63), -2763.0);
        Assert.assertEquals(compositeObjectTwo.apply(Double.NaN), -2763.0);
        Assert.assertEquals(compositeObjectTwo.apply(Double.POSITIVE_INFINITY), -2763.0);
        Assert.assertEquals(compositeObjectTwo.apply(Double.NEGATIVE_INFINITY), -2763.0);

        SqrFunction sqrObject = new SqrFunction();
        ZeroFunction zeroObject = new ZeroFunction();
        CompositeFunction compositeObjectThree = new CompositeFunction(sqrObject, zeroObject);
        Assert.assertEquals(compositeObjectThree.apply(63), 0.0);
        Assert.assertEquals(compositeObjectThree.apply(Double.NaN), 0.0);
        Assert.assertEquals(compositeObjectThree.apply(Double.POSITIVE_INFINITY), 0.0);
        Assert.assertEquals(compositeObjectThree.apply(Double.NEGATIVE_INFINITY), 0.0);
    }

}