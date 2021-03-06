package ru.ssau.tk.IldarValeria.LabSgau.functions;

import ru.ssau.tk.IldarValeria.LabSgau.exceptions.*;

import java.io.Serializable;

public abstract class AbstractTabulatedFunction implements TabulatedFunction , Serializable {
    private static final long serialVersionUID = 2824260987755899279L;

    protected abstract int floorIndexOfX(double x);

    protected abstract double extrapolateLeft(double x);

    protected abstract double extrapolateRight(double x);

    protected abstract double interpolate(double x, int floorIndex);

    protected double interpolate(double x, double leftX, double rightX, double leftY, double rightY) {
        return leftY + (rightY - leftY) * (x - leftX) / (rightX - leftX);
    }

    protected static void checkLengthIsTheSame(double[] xValues, double[] yValues) {
        if (xValues.length != yValues.length) {
            throw new DifferentLengthOfArraysException();
        }
    }

    protected static void checkSorted(double[] xValues) {
        for (int element = 1; element < xValues.length; element++) {
            if (xValues[element - 1] >= xValues[element]) {
                throw new ArrayIsNotSortedException();
            }
        }
    }

    @Override
    public double apply(double x) {
        if (x < leftBound()) {
            return extrapolateLeft(x);
        }
        if (x > rightBound()) {
            return extrapolateRight(x);
        }
        if (indexOfX(x) != -1) {
            return getY(indexOfX(x));
        }
        return interpolate(x, floorIndexOfX(x));
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getSimpleName()).
                append(" size =").
                append(getCount());
        for (Point point : this) {
            stringBuilder.append("\n").
                    append("[").
                    append(point.x).
                    append(";").
                    append(point.y).
                    append("]");
        }
        return stringBuilder.toString();
    }

}
