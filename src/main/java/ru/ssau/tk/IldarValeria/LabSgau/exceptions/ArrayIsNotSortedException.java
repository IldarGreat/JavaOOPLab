package ru.ssau.tk.IldarValeria.LabSgau.exceptions;

import java.io.Serializable;

public class ArrayIsNotSortedException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -789603861808395719L;

    public ArrayIsNotSortedException() {
    }

    public ArrayIsNotSortedException(String string) {
        super(string);
    }
}
