package ru.ssau.tk.IldarValeria.LabSgau.concurrent;

import ru.ssau.tk.IldarValeria.LabSgau.functions.*;

public class ReadTask implements Runnable {
    private final TabulatedFunction tabulatedFunction;

    public ReadTask(TabulatedFunction tabulatedFunction) {
        this.tabulatedFunction = tabulatedFunction;
    }

    @Override
    public void run() {
        for (int element = 0; element < tabulatedFunction.getCount(); element++) {
            System.out.printf("After read: i = %d, x = %f, y = %f\n", element, tabulatedFunction.getX(element), tabulatedFunction.getY(element));
        }
    }
}
