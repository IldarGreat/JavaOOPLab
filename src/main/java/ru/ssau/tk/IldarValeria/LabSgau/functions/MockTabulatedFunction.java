package ru.ssau.tk.IldarValeria.LabSgau.functions;

public class MockTabulatedFunction extends AbstractTabulatedFunction {
    public final double x0 = 1.3;
    public final double x1 = 2.8;
    public final double y0 = 1.4;
    public final double y1 = 2.9;

    @Override
    public int floorIndexOfX(double x) {
        if (x == this.x0) return 0;
        return 1;
    }

    @Override
    public double extrapolateLeft(double x) {
        return interpolate(x, 0);
    }

    @Override
    public double extrapolateRight(double x) {
        return interpolate(x, 0);
    }

    @Override
    public double interpolate(double x, int floorIndex) {
        return super.interpolate(x, x0, x1, y0, y1);
    }

    //////////////////////////////////////////////////////////
    @Override
    public int indexOfY(double y) {
        if (y == this.y0) {
            return 0;
        }
        return 1;
    }

    @Override
    public int indexOfX(double x) {
        if (x == this.x0) {
            return 0;
        }
        return 1;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public double getY(int index) {
        if (index == 0) {
            return this.y0;
        }
        return this.y1;
    }

    @Override
    public double getX(int index) {
        if (index == 0) {
            return this.x0;
        }
        return this.x1;
    }

    @Override
    public double leftBound() {
        return this.x0;
    }

    @Override
    public double rightBound() {
        return this.x1;
    }

    @Override
    public void setY(int index, double value) {
    }
}
