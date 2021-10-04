package ru.ssau.tk.IldarValeria.LabSgau.functions;

import java.util.Objects;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction {
    private int count=0;
    private Node head;

    public LinkedListTabulatedFunction(double[] xValues, double[] yValues) {
        for (int element = 0; element < xValues.length; element++) {
            addNode(xValues[element], yValues[element]);
            count ++;
        }
    }

    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        double intervalSplittingStep = (xTo - xFrom) / (count - 1);
        for (int element = 0; element < count - 1; element++) {
            addNode(xFrom + element * intervalSplittingStep, source.apply(xFrom + element * intervalSplittingStep));
        }
    }

    private void addNode(double x, double y) {
        Node newNode = new Node();
        newNode.x = x;
        newNode.y = y;
        if (head == null) {
            head = newNode;
            newNode.next = newNode;
            newNode.prev = newNode;
        } else {
            Node last = head.prev;
            last.next = newNode;
            head.prev = newNode;
            newNode.prev = last;
            newNode.next = head;
        }
        count++;
    }

    private Node getNode(int index) {
        Node indexNode;
        if (index < count / 2) {
            indexNode = head;
            for (int element = 0; element <= count / 2; element++) {
                if (element == index) {
                    return indexNode;
                } else {
                    indexNode = indexNode.next;
                }
            }
        } else {
            indexNode = head.prev;
            for (int element = count; element >= count / 2; element--) {
                if (element == index) {
                    return indexNode;
                } else {
                    indexNode = indexNode.prev;
                }
            }
        }
        return null;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public double leftBound() {
        return head.x;
    }

    @Override
    public double rightBound() {
        return head.prev.x;
    }


    @Override
    public double getX(int index) {
        return Objects.requireNonNull(getNode(index)).x;
    }

    @Override
    public double getY(int index) {
        return Objects.requireNonNull(getNode(index)).y;
    }

    @Override
    public void setY(int index, double value) {
        Objects.requireNonNull(getNode(index)).y = value;
    }

    @Override
    public int indexOfX(double x) {
        Node indexNode = head;
        for (int index = 0; index < count; index++) {
            if (indexNode.x == x) {
                return index;
            }
            indexNode = indexNode.next;
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        Node indexNode = head;
        for (int index = 0; index < count; index++) {
            if (indexNode.y == y) {
                return index;
            }
            indexNode = indexNode.next;
        }
        return -1;
    }

    @Override
    public int floorIndexOfX(double x) {
        if (indexOfX(x) != -1) {
            return indexOfX(x);
        }
        Node node = head;
        for (int element = 0; element < count; element++) {
            if (node.x > x) {
                return element;
            }
        }
        return count;
    }

    @Override
    public double extrapolateLeft(double x) {
        return head.y + (head.next.y - head.y) * (x - head.y) / (head.next.y - head.y);
    }

    @Override
    public double extrapolateRight(double x) {
        return head.prev.prev.y + (head.prev.y - head.prev.prev.y) * (x - head.prev.prev.x) / (head.prev.x - head.prev.prev.x);
    }

    @Override
    public double interpolate(double x, int floorIndex) {
        return Objects.requireNonNull(getNode(floorIndex)).y + (Objects.requireNonNull(getNode(floorIndex + 1)).y - Objects.requireNonNull(getNode(floorIndex)).y) * (x - Objects.requireNonNull(getNode(floorIndex)).x) / (Objects.requireNonNull(getNode(floorIndex + 1)).x - Objects.requireNonNull(getNode(floorIndex)).x);
    }
}
