package ru.ssau.tk.IldarValeria.LabSgau.io;

import ru.ssau.tk.IldarValeria.LabSgau.functions.*;
import ru.ssau.tk.IldarValeria.LabSgau.functions.factory.*;

import java.io.*;
import java.text.*;
import java.util.*;

public final class FunctionsIO {

    private FunctionsIO() {
        throw new UnsupportedOperationException();
    }

    public static void writeTabulatedFunction(BufferedWriter writer, TabulatedFunction function) {
        PrintWriter printWriter = new PrintWriter(writer);
        printWriter.println(function.getCount());
        for (Point point : function) {
            printWriter.printf("%f %f\n", point.x, point.y);
        }
        printWriter.flush();
    }

    public static void writeTabulatedFunction(BufferedOutputStream outputStream, TabulatedFunction function) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeInt(function.getCount());
        for (Point point : function) {
            dataOutputStream.writeDouble(point.x);
            dataOutputStream.writeDouble(point.y);
        }
        dataOutputStream.flush();
    }

    public static TabulatedFunction readTabulatedFunction(BufferedReader reader, TabulatedFunctionFactory factory) throws IOException {
        int count = Integer.parseInt(reader.readLine());
        double[] xValues = new double[count];
        double[] yValues = new double[count];
        NumberFormat numberFormatter = NumberFormat.getInstance(Locale.forLanguageTag("ru"));
        String line;
        try {
            for (int index = 0; index < count; index++) {
                line = reader.readLine();
                xValues[index] = numberFormatter.parse(line.split(" ")[0]).doubleValue();
                yValues[index] = numberFormatter.parse(line.split(" ")[1]).doubleValue();
            }
        } catch (ParseException exception) {
            throw new IOException(exception);
        }
        return factory.create(xValues, yValues);
    }

}
