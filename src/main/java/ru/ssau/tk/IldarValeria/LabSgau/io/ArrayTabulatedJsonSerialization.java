package ru.ssau.tk.IldarValeria.LabSgau.io;

import ru.ssau.tk.IldarValeria.LabSgau.functions.ArrayTabulatedFunction;

import java.io.*;

public class ArrayTabulatedJsonSerialization {

    public static void main(String[] args) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("output/serialized array json.Json"));
            ArrayTabulatedFunction arrayTabulated = new ArrayTabulatedFunction(new double[]{1.2, 2.2, 3.2, 4.2}, new double[]{5.7, 6.7, 7.7, 8.7});
            FunctionsIO.serializeJson(bufferedWriter, arrayTabulated);

            BufferedReader bufferedReader = new BufferedReader(new FileReader("output/serialized array json.Json"));
            ArrayTabulatedFunction newArrayTabulated = FunctionsIO.deserializeJson(bufferedReader);
            System.out.println(newArrayTabulated);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
