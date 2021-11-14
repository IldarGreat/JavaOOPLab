package ru.ssau.tk.IldarValeria.LabSgau.concurrent;

import ru.ssau.tk.IldarValeria.LabSgau.functions.*;

public class WriteTask implements Runnable {
    private final TabulatedFunction tabulatedFunction;
    private final double value;

    public WriteTask(TabulatedFunction tabulatedFunction, double value) {
        this.tabulatedFunction = tabulatedFunction;
        this.value = value;
    }

    @Override
    public void run() {
        for (int index = 0; index < tabulatedFunction.getCount(); index++) {
            tabulatedFunction.setY(index, value);
            System.out.printf("Writing for index %d complete\n", index);
        }
    }
}
