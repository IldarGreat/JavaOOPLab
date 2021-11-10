package ru.ssau.tk.IldarValeria.LabSgau.exceptions;

import java.io.Serializable;

public class DifferentLengthOfArraysException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 8501246243155325294L;

    public DifferentLengthOfArraysException() {
    }

    public DifferentLengthOfArraysException(String string) {
        super(string);
    }
}
