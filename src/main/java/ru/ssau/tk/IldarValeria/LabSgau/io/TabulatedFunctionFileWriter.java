package ru.ssau.tk.IldarValeria.LabSgau.io;

import ru.ssau.tk.IldarValeria.LabSgau.functions.*;

import java.io.*;

public class TabulatedFunctionFileWriter {

    public static void main(String[] args) {
        try (BufferedWriter arrayWriter = new BufferedWriter(
                new FileWriter("output/array function.txt"));
             BufferedWriter listWriter = new BufferedWriter(
                     new FileWriter("output/linked list function.txt"))) {
            TabulatedFunction arrayTabulated = new ArrayTabulatedFunction(new double[]{1.2, 2.2, 3.2, 4.2}, new double[]{5.7, 6.7, 7.7, 8.7});
            TabulatedFunction listTabulated = new LinkedListTabulatedFunction(new double[]{5, 6, 7, 8, 9}, new double[]{13, 14, 15, 16, 17});
            FunctionsIO.writeTabulatedFunction(arrayWriter, arrayTabulated);
            FunctionsIO.writeTabulatedFunction(listWriter, listTabulated);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
