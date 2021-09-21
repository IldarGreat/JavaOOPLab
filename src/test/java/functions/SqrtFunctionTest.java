package functions;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SqrtFunctionTest {
    @Test
    public void testApply(){
        SqrtFunction someObject = new SqrtFunction();
        double someNumber = 9;
        Assert.assertEquals(someObject.apply(someNumber),3.0);
    }

}