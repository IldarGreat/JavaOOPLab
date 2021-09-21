package functions;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UnitFunctionTest {
    @Test
    public void testApply(){
        double someNumber = 83;
        UnitFunction someObject = new UnitFunction();
        Assert.assertEquals(someObject.apply(someNumber),1.0);
    }

}