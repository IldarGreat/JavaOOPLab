package ru.ssau.tk.IldarValeria.LabSgau.exceptions;

import java.io.Serializable;

public class InconsistentFunctionsException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 3190345557457963551L;

    public InconsistentFunctionsException() {
    }

    public InconsistentFunctionsException(String string) {
        super(string);
    }
}
