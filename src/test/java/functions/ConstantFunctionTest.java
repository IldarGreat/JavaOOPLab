package functions;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ConstantFunctionTest {
    @Test
    public void testApply(){
        double someNumber = 83;
        double constantNumber = 666;
        ConstantFunction someObject = new ConstantFunction(constantNumber);
        Assert.assertEquals(someObject.apply(someNumber),constantNumber);
    }

}