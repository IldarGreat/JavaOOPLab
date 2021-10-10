package ru.ssau.tk.IldarValeria.LabSgau.functions;

import java.util.Objects;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction {
    private int count = 0;
    private Node head;

    public LinkedListTabulatedFunction(double[] xValues, double[] yValues) {
        for (int element = 0; element < xValues.length; element++) {
            addNode(xValues[element], yValues[element]);
        }
    }

    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        double step = (xTo - xFrom) / (count - 1);
        for (int element = 0; element < count; element++) {
            addNode(xFrom + element * step, source.apply(xFrom + element * step));
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
        if (index == count) {
            return null;
        }
        Node indexNode;
        if (index < (double) count / 2) {
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
                    return indexNode.next;
                } else {
                    indexNode = indexNode.prev;
                }
            }
        }
        return null;
    }

    public Node floorNodeOfX(double x) {
        Node currentNode = head;
        Node nearestNode = head.next;
        for (int element = 0; element < count; element++) {
            if ((x > currentNode.x && x < nearestNode.x) || (Math.abs(currentNode.x - x) < 0.0001)) {
                return currentNode;
            }
            nearestNode = nearestNode.next;
            currentNode = currentNode.next;
        }
        if (head.x > x) {
            return head;
        }
        return head.prev;
    }

    @Override
    public double apply(double x) {
        if (x < leftBound()) {
            return extrapolateLeft(x);
        }
        if (x > rightBound()) {
            return extrapolateRight(x);
        }
        Node node = floorNodeOfX(x);
        return interpolate(x, node.x, node.next.x, node.y, node.next.y);
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
        Node currentNode = head;
        Node nearestNode = head.next;
        for (int element = 0; element < count; element++) {
            if ((x > currentNode.x && x < nearestNode.x) || (Math.abs(currentNode.x - x) < 0.0001)) {
                return element;
            }
            nearestNode = nearestNode.next;
            currentNode = currentNode.next;
        }
        if (head.x > x) {
            return 0;
        }
        return count;
    }

    @Override
    public double extrapolateLeft(double x) {
        if (count == 1) {
            return Objects.requireNonNull(getNode(0)).y;
        }
        return interpolate(x, 0);
    }

    @Override
    public double extrapolateRight(double x) {
        if (count == 1) {
            return Objects.requireNonNull(getNode(0)).y;
        }
        return interpolate(x, count - 2);
    }

    @Override
    public double interpolate(double x, int floorIndex) {
        if (count == 1) {
            return Objects.requireNonNull(getNode(0)).y;
        }
        return super.interpolate(x, Objects.requireNonNull(getNode(floorIndex)).x, Objects.requireNonNull(getNode(floorIndex + 1)).x, Objects.requireNonNull(getNode(floorIndex)).y, Objects.requireNonNull(getNode(floorIndex + 1)).y);
    }
}
