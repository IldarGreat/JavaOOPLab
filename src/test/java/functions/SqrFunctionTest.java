package functions;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SqrFunctionTest {
    @Test
    public void testApply(){
        Assert.assertEquals(new SqrFunction().apply(12),144.0);
    }

}