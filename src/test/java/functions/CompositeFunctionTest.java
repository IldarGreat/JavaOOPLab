package functions;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CompositeFunctionTest {
    @Test
    public void testApply(){
        LnFunction lnObject = new LnFunction();
        SqrtFunction sqrtObject = new SqrtFunction();
        CompositeFunction compositeObject = new CompositeFunction(lnObject,sqrtObject); // Поля типа SqrtFunction и LnFunction (внутрення функция КОРЕНЬ!)
        Assert.assertEquals(compositeObject.apply(1),0.0); // В начале берется корень из 1 , а потом натуральный логарифм
    }

    public static void main(String[] args) {
        ConstantFunctionTest d = new ConstantFunctionTest();
        d.testApply();
    }
}