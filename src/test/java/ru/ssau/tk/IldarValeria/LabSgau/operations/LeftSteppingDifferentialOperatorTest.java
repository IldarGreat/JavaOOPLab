package ru.ssau.tk.IldarValeria.LabSgau.operations;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.ssau.tk.IldarValeria.LabSgau.functions.*;

public class LeftSteppingDifferentialOperatorTest {

    @Test
    public static void testDerive() {
        LeftSteppingDifferentialOperator leftSteppingDifferentialOperator = new LeftSteppingDifferentialOperator(5);
        Assert.assertEquals(leftSteppingDifferentialOperator.derive(new SqrtFunction()).apply(9),0.2);
        Assert.assertThrows(IllegalArgumentException.class, () -> leftSteppingDifferentialOperator.setStep(-5.0));
        Assert.assertThrows(IllegalArgumentException.class, () -> new LeftSteppingDifferentialOperator(Double.NaN));
    }
}
