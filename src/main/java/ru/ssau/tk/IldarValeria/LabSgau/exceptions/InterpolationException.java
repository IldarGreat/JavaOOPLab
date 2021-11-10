package ru.ssau.tk.IldarValeria.LabSgau.exceptions;

import java.io.Serializable;

public class InterpolationException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -2278429670352485291L;

    public InterpolationException() {
    }

    public InterpolationException(String string) {
        super(string);
    }
}
