package ru.ssau.tk.IldarValeria.LabSgau.io;

import ru.ssau.tk.IldarValeria.LabSgau.functions.*;
import ru.ssau.tk.IldarValeria.LabSgau.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.IldarValeria.LabSgau.operations.*;

import java.io.*;

public class ArrayTabulatedFunctionSerialization {

    public static void main(String[] args) {
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("output/serialized array functions.bin"));
            TabulatedFunction arrayTabulated = new ArrayTabulatedFunction(new double[]{1, 2, 3, 4, 5}, new double[]{6, 7, 8, 9, 10});
            TabulatedDifferentialOperator tabulatedDifferentialOperator = new TabulatedDifferentialOperator(new ArrayTabulatedFunctionFactory());
            TabulatedFunction firstDerivative = tabulatedDifferentialOperator.derive(arrayTabulated);
            TabulatedFunction secondDerivative = tabulatedDifferentialOperator.derive(firstDerivative);
            FunctionsIO.serialize(bufferedOutputStream, arrayTabulated);
            FunctionsIO.serialize(bufferedOutputStream, firstDerivative);
            FunctionsIO.serialize(bufferedOutputStream, secondDerivative);
            bufferedOutputStream.close();

            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("output/serialized array functions.bin"));
            System.out.println(FunctionsIO.deserialize(bufferedInputStream));
            System.out.println(FunctionsIO.deserialize(bufferedInputStream));
            System.out.println(FunctionsIO.deserialize(bufferedInputStream));
            bufferedInputStream.close();
        } catch (ClassNotFoundException | IOException exception) {
            exception.printStackTrace();
        }
    }
}
