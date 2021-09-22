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
        Assert.assertEquals(compositeObject.apply(-3), Double.NaN);
        Assert.assertEquals(compositeObject.apply(148.0/3.0), 1.9744, DELTA);
        Assert.assertEquals(compositeObject.apply(Double.NaN), Double.NaN);
        Assert.assertEquals(compositeObject.apply(Double.POSITIVE_INFINITY), Double.POSITIVE_INFINITY);
        Assert.assertEquals(compositeObject.apply(Double.NEGATIVE_INFINITY), Double.NaN);

        SqrFunction sqrObject = new SqrFunction();
        CompositeFunction compositeObjectTwo = new CompositeFunction(sqrObject, lnObject);
        Assert.assertEquals(compositeObjectTwo.apply(63), 8.2862,DELTA);
        Assert.assertEquals(compositeObjectTwo.apply(9.0/5.0), 1.1755,DELTA);
        Assert.assertEquals(compositeObjectTwo.apply(Double.NaN), Double.NaN);
        Assert.assertEquals(compositeObjectTwo.apply(Double.POSITIVE_INFINITY), Double.POSITIVE_INFINITY);
        Assert.assertEquals(compositeObjectTwo.apply(Double.NEGATIVE_INFINITY),Double.POSITIVE_INFINITY);

        IdentityFunction identityObject = new IdentityFunction();
        ConstantFunction constantObject = new ConstantFunction(-2763);
        CompositeFunction compositeObjectThree = new CompositeFunction(identityObject, constantObject);
        Assert.assertEquals(compositeObjectThree.apply(63), -2763.0);
        Assert.assertEquals(compositeObjectThree.apply(Double.NaN), -2763.0);
        Assert.assertEquals(compositeObjectThree.apply(Double.POSITIVE_INFINITY), -2763.0);
        Assert.assertEquals(compositeObjectThree.apply(Double.NEGATIVE_INFINITY), -2763.0);

        ZeroFunction zeroObject = new ZeroFunction();
        CompositeFunction compositeObjectFour = new CompositeFunction(sqrObject, zeroObject);
        Assert.assertEquals(compositeObjectFour.apply(63), 0.0);
        Assert.assertEquals(compositeObjectFour.apply(Double.NaN), 0.0);
        Assert.assertEquals(compositeObjectFour.apply(Double.POSITIVE_INFINITY), 0.0);
        Assert.assertEquals(compositeObjectFour.apply(Double.NEGATIVE_INFINITY), 0.0);
    }

}