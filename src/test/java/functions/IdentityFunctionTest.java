package functions;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class IdentityFunctionTest {

    @Test
    public void testApply(){
        IdentityFunction someObject = new IdentityFunction();
        double someNumber = 83;
        Assert.assertEquals(someObject.apply(someNumber),someNumber);
    }

}