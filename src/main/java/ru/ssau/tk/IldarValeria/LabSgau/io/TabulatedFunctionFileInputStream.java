package ru.ssau.tk.IldarValeria.LabSgau.io;

import ru.ssau.tk.IldarValeria.LabSgau.functions.*;
import ru.ssau.tk.IldarValeria.LabSgau.functions.factory.*;
import ru.ssau.tk.IldarValeria.LabSgau.operations.TabulatedDifferentialOperator;

import java.io.*;

public class TabulatedFunctionFileInputStream {

    public static void main(String[] args) {
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("input/binary function.bin"));
            TabulatedFunction arrayTabulated = FunctionsIO.readTabulatedFunction(bufferedInputStream, new ArrayTabulatedFunctionFactory());
            System.out.println(arrayTabulated);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter the size and values of the function");
            TabulatedFunction listTabulated = FunctionsIO.readTabulatedFunction(bufferedReader, new LinkedListTabulatedFunctionFactory());
            TabulatedDifferentialOperator listDiff = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());
            System.out.println(listDiff.derive(listTabulated));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
