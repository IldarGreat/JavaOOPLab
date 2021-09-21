package functions;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LnFunctionTest {
    @Test
    public void testApply(){
        double someNumber = 1;
        LnFunction someObject = new LnFunction();
        Assert.assertEquals(someObject.apply(someNumber),0.0); // 0.0 , a не 0 поскольку тип double
    }

}