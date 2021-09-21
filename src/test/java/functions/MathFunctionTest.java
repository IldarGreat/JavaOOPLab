package functions;

import org.testng.Assert;
import org.testng.annotations.Test;



public class MathFunctionTest {
    @Test
    public void testAndThen(){

        // Создаем обьекты
        ConstantFunction constantObject = new ConstantFunction(256);
        IdentityFunction identityObject = new IdentityFunction();
        LnFunction lnObject = new LnFunction();
        SqrFunction sqrObject = new SqrFunction();
        SqrtFunction sqrtObject = new SqrtFunction();
        double someNumber = 1 ;
        // Создаем обьекты

        // Создаем цепочки
        Assert.assertEquals( identityObject.andThen(lnObject).andThen(sqrObject).apply(someNumber),0.0); // Сначала берем корень от 1 , потом от этого натуральный логарфифм и затем просто все возвращаем
        Assert.assertEquals( constantObject.andThen(sqrtObject).andThen(sqrObject).apply(someNumber),256.0); // Сначала 1 возводится в квадрат , потом из него извлекается корень ,
        // а затем полученное число идет в обьект constantObject , который изначально инициализирован 256 и соответсвенно своей реализации выведет 256
        // Создаем цепочки
    }

}