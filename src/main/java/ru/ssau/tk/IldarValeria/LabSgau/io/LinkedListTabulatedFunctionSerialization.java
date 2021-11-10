package ru.ssau.tk.IldarValeria.LabSgau.io;

import ru.ssau.tk.IldarValeria.LabSgau.functions.*;
import ru.ssau.tk.IldarValeria.LabSgau.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.IldarValeria.LabSgau.operations.TabulatedDifferentialOperator;

import java.io.*;

public class LinkedListTabulatedFunctionSerialization {

    public static void main(String[] args) {
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
                new FileOutputStream("output/serialized linked list functions.bin"));
             BufferedInputStream bufferedInputStream = new BufferedInputStream(
                     new FileInputStream("output/serialized linked list functions.bin"))) {
            TabulatedFunction listTabulated = new LinkedListTabulatedFunction(new double[]{1, 2, 3, 4, 5}, new double[]{6, 7, 8, 9, 10});
            TabulatedDifferentialOperator tabulatedDifferentialOperator = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());
            TabulatedFunction firstDerivative = tabulatedDifferentialOperator.derive(listTabulated);
            TabulatedFunction secondDerivative = tabulatedDifferentialOperator.derive(firstDerivative);
            FunctionsIO.serialize(bufferedOutputStream, listTabulated);
            FunctionsIO.serialize(bufferedOutputStream, firstDerivative);
            FunctionsIO.serialize(bufferedOutputStream, secondDerivative);

            System.out.println(FunctionsIO.deserialize(bufferedInputStream));
            System.out.println(FunctionsIO.deserialize(bufferedInputStream));
            System.out.println(FunctionsIO.deserialize(bufferedInputStream));
        } catch (ClassNotFoundException | IOException exception) {
            exception.printStackTrace();
        }
    }
}
