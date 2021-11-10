package ru.ssau.tk.IldarValeria.LabSgau.io;

import ru.ssau.tk.IldarValeria.LabSgau.functions.*;

import java.io.*;

public class TabulatedFunctionFileOutputStream {

    public static void main(String[] args) {
        try (BufferedOutputStream bufferedArray = new BufferedOutputStream(
                new FileOutputStream("output/array function.bin"));
             BufferedOutputStream bufferedList = new BufferedOutputStream(
                     new FileOutputStream("output/linked list function.bin"))) {
            TabulatedFunction arrayTabulated = new ArrayTabulatedFunction(new double[]{1.2, 2.2, 3.2, 4.2}, new double[]{5.7, 6.7, 7.7, 8.7});
            TabulatedFunction listTabulated = new LinkedListTabulatedFunction(new double[]{5, 6, 7, 8, 9}, new double[]{13, 14, 15, 16, 17});
            FunctionsIO.writeTabulatedFunction(bufferedArray, arrayTabulated);
            FunctionsIO.writeTabulatedFunction(bufferedList, listTabulated);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
