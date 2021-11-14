package ru.ssau.tk.IldarValeria.LabSgau.concurrent;

import ru.ssau.tk.IldarValeria.LabSgau.functions.*;

public class MultiplyingTask implements Runnable {
    private final TabulatedFunction tabulatedFunction;
    private boolean isCompleted = false;

    public MultiplyingTask(TabulatedFunction tabulatedFunction) {
        this.tabulatedFunction = tabulatedFunction;
    }

    @Override
    public void run() {
        for (int index = 0; index < tabulatedFunction.getCount(); index++) {
            synchronized (tabulatedFunction) {
                tabulatedFunction.setY(index, tabulatedFunction.getY(index) * 2);
            }
        }
        System.out.println("Thread " + Thread.currentThread().getName() + " has completed the task");
        isCompleted = true;
    }

    public boolean isCompleted() {
        return isCompleted;
    }
}
