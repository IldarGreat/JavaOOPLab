package ru.ssau.tk.IldarValeria.LabSgau.exceptions;

public class WrongNumberOfElementsException extends RuntimeException {

    private static final long serialVersionUID = -6579778123817550356L;

    public WrongNumberOfElementsException() {}

    public WrongNumberOfElementsException(String message) {
        super(message);
    }
}