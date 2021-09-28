package ru.ssau.tk.IldarValeria.LabSgau.functions;

import java.util.Arrays;

public class ArrayTabulatedFunction extends AbstractTabulatedFunction {
    private final double[] xValues;
    private final double[] yValues;
    private final int count;

    public ArrayTabulatedFunction(double[] xValues, double[] yValues) {
        this.count = xValues.length;
        this.xValues = Arrays.copyOf(xValues, count);
        this.yValues = Arrays.copyOf(yValues, count);
    }

    public ArrayTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        double intervalSplittingStep = (xTo - xFrom) / count;
        double[] xValues = new double[count];
        double[] yValues = new double[count];
        xValues[0] = xFrom;
        yValues[0] = source.apply(xFrom);
        xValues[count - 1] = xTo;
        yValues[count - 1] = source.apply(xTo);
        for (int element = 1; element < count - 1; element++) {
            xValues[element] = xValues[element - 1] + intervalSplittingStep;
            yValues[element] = source.apply(xValues[element]);
        }
        this.count = count;
        this.xValues = Arrays.copyOf(xValues, count);
        this.yValues = Arrays.copyOf(yValues, count);
    }

    @Override
    public int getCount() {
        return this.count;
    }

    @Override
    public double getX(int index) {
        return xValues[index];
    }

    @Override
    public double getY(int index) {
        return yValues[index];
    }

    @Override
    public void setY(int index, double value) {
        yValues[index] = value;
    }

    @Override
    public double leftBound() {
        return xValues[0];
    }

    @Override
    public double rightBound() {
        return xValues[count - 1];
    }

    @Override
    public int indexOfX(double x) {
        for (int element = 0; element < this.count; element++) {
            if (Math.abs(xValues[element] - x) < 0.0001) return element;
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        for (int element = 0; element < this.count; element++) {
            if (Math.abs(yValues[element] - y) < 0.0001) return element;
        }
        return -1;
    }

    @Override
    public int floorIndexOfX(double x) {
        for (int element = 0; element < this.count; element++) {
            if (Math.abs(xValues[element] - x) < 0.0001) return element;
        }
        for (int element = 0; element < this.count; element++) {
            if (x < xValues[element] && element != 0) return element - 1;
            if (x < xValues[element]) return 0;
        }
        return this.count;
    }

    @Override
    public double extrapolateLeft(double x) {
        return yValues[0] + (yValues[1] - yValues[0]) * (x - xValues[0]) / (xValues[1] - xValues[0]);
    }

    @Override
    public double extrapolateRight(double x) {
        return yValues[count - 2] + (yValues[count - 1] - yValues[count - 2]) * (x - xValues[count - 2]) / (xValues[count - 1] - xValues[count - 2]);
    }

    @Override
    public double interpolate(double x, int floorIndex) {
        return yValues[floorIndex] + (yValues[floorIndex + 1] - yValues[floorIndex]) * (x - xValues[floorIndex]) / (xValues[floorIndex + 1] - xValues[0]);
    }
}
