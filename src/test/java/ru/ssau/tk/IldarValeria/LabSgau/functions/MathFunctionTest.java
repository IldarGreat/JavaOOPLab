package ru.ssau.tk.IldarValeria.LabSgau.functions;

import org.testng.Assert;
import org.testng.annotations.Test;


public class MathFunctionTest {

    private static final double DELTA = 0.0001;

    @Test
    public void testAndThen() {
        ConstantFunction constantObject = new ConstantFunction(256);
        IdentityFunction identityObject = new IdentityFunction();
        LnFunction lnObject = new LnFunction();
        SqrFunction sqrObject = new SqrFunction();
        SqrtFunction sqrtObject = new SqrtFunction();
        Assert.assertEquals(identityObject.andThen(lnObject).andThen(sqrObject).apply(1), 0.0,DELTA);
        Assert.assertEquals(constantObject.andThen(sqrtObject).andThen(sqrObject).apply(1), 256.0,DELTA);
        Assert.assertEquals(sqrObject.andThen(identityObject).andThen(lnObject).apply(36.76), 7.2088,DELTA);
        Assert.assertEquals(sqrtObject.andThen(lnObject).andThen(sqrObject).apply(-42.9), Double.NaN);
        Assert.assertEquals(sqrObject.andThen(identityObject).andThen(sqrtObject).apply(57.0/37.2), 1.5322,DELTA);

    }

}