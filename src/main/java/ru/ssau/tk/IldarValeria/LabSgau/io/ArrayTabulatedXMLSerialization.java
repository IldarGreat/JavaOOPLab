package ru.ssau.tk.IldarValeria.LabSgau.io;

import com.thoughtworks.xstream.security.ForbiddenClassException;
import ru.ssau.tk.IldarValeria.LabSgau.functions.*;

import java.io.*;

public class ArrayTabulatedXMLSerialization {

    public static void main(String[] args) {
        try(BufferedWriter arrayWriter = new BufferedWriter(
                new FileWriter("output/arrayXML function.xml"));
            BufferedReader bufferedReader = new BufferedReader(
                    new FileReader("output/arrayXML function.xml"))) {
            ArrayTabulatedFunction arrayTabulated = new ArrayTabulatedFunction(new double[]{1.2, 2.2, 3.2, 4.2}, new double[]{5.7, 6.7, 7.7, 8.7});
            FunctionsIO.serializeXml(arrayWriter, arrayTabulated);

            TabulatedFunction newArrayTabulated = FunctionsIO.deserializeXml(bufferedReader);
            System.out.println(newArrayTabulated);
        } catch (IOException | ForbiddenClassException e) {
            e.printStackTrace();
        }
    }
}
