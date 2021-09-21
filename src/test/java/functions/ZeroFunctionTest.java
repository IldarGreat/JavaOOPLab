package functions;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ZeroFunctionTest {
    @Test
    public void testApply(){
        double someNumber = 83;
        ZeroFunction someObject = new ZeroFunction();
        Assert.assertEquals(someObject.apply(someNumber),0.0);
    }

}