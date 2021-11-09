package ru.ssau.tk.IldarValeria.LabSgau.functions;

import ru.ssau.tk.IldarValeria.LabSgau.exceptions.*;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction implements Serializable {
    private static final long serialVersionUID = -4337454737791403234L;
    private int count = 0;
    private Node head;

    public LinkedListTabulatedFunction(double[] xValues, double[] yValues) {
        if (xValues.length < 2) {
            throw new IllegalArgumentException("Length of array less than minimum length (2)");
        }
        checkLengthIsTheSame(xValues, yValues);
        checkSorted(xValues);
        for (int element = 0; element < xValues.length; element++) {
            addNode(xValues[element], yValues[element]);
        }
    }

    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (count < 2) {
            throw new IllegalArgumentException("The count of points is less than the minimum count (2)");
        }
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
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException();
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
        throw new UnsupportedOperationException();
    }

    public Node floorNodeOfX(double x) {
        if (x < head.x) {
            throw new IllegalArgumentException("Argument x less than minimal x");
        }
        Node currentNode = head;
        Node nearestNode = head.next;
        for (int element = 0; element < count; element++) {
            if ((x > currentNode.x && x < nearestNode.x) || (currentNode.x == x)) {
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
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("Index is out of bounds");
        }
        return Objects.requireNonNull(getNode(index)).x;
    }

    @Override
    public double getY(int index) {
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("Index is out of bounds");
        }
        return Objects.requireNonNull(getNode(index)).y;
    }

    @Override
    public void setY(int index, double value) {
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("Index is out of bounds");
        }
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
        if (x < head.x) {
            throw new IllegalArgumentException("Argument x less than minimal x");
        }
        Node currentNode = head;
        Node nearestNode = head.next;
        for (int element = 0; element < count; element++) {
            if ((x > currentNode.x && x < nearestNode.x) || currentNode.x == x) {
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
        return super.interpolate(x, head.x, head.next.x, head.y, head.next.y);
    }

    @Override
    public double extrapolateRight(double x) {
        return super.interpolate(x, head.prev.prev.x, head.prev.x, head.prev.prev.y, head.prev.y);
    }

    @Override
    public double interpolate(double x, int floorIndex) {
        Node before = getNode(floorIndex);
        Node after = Objects.requireNonNull(before).next;
        if (x < before.x || x > after.x) {
            throw new InterpolationException();
        }
        return super.interpolate(x, Objects.requireNonNull(getNode(floorIndex)).x, Objects.requireNonNull(getNode(floorIndex + 1)).x, Objects.requireNonNull(getNode(floorIndex)).y, Objects.requireNonNull(getNode(floorIndex + 1)).y);
    }

    @Override
    public Iterator<Point> iterator() {
        return new Iterator<>() {
            private Node node = head;

            @Override
            public boolean hasNext() {
                return (node != null);
            }

            @Override
            public Point next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                Point point = new Point(node.x, node.y);
                if (node == head.prev) {
                    node = null;
                } else {
                    node = node.next;
                }
                return point;
            }
        };
    }

    protected static class Node implements Serializable {
        private static final long serialVersionUID = -2669138153577587522L;
        public Node next;
        public Node prev;
        public double x;
        public double y;
    }
}
