package ru.ssau.tk.IldarValeria.LabSgau.io;

import ru.ssau.tk.IldarValeria.LabSgau.functions.TabulatedFunction;
import ru.ssau.tk.IldarValeria.LabSgau.functions.factory.*;

import java.io.*;

public class TabulatedFunctionFileReader {

    public static void main(String[] args) {
        try (BufferedReader arrayReader = new BufferedReader(
                new FileReader("input/function.txt"));
             BufferedReader listReader = new BufferedReader(
                     new FileReader("input/function.txt"))) {
            TabulatedFunction arrayTabulated = FunctionsIO.readTabulatedFunction(arrayReader, new ArrayTabulatedFunctionFactory());
            TabulatedFunction listTabulated = FunctionsIO.readTabulatedFunction(listReader, new LinkedListTabulatedFunctionFactory());
            System.out.println(arrayTabulated);
            System.out.println(listTabulated);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
