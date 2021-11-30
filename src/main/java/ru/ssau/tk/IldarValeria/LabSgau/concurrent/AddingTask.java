package ru.ssau.tk.IldarValeria.LabSgau.concurrent;

import ru.ssau.tk.IldarValeria.LabSgau.functions.TabulatedFunction;

public class AddingTask implements Runnable {
    private final TabulatedFunction tabulatedFunction;
    private final Runnable postRunAction;

    public AddingTask(TabulatedFunction tabulatedFunction, Runnable postRunAction) {
        this.tabulatedFunction = tabulatedFunction;
        this.postRunAction = postRunAction;
    }

    @Override
    public void run() {
        double x;
        double y;
        for (int index = 0; index < tabulatedFunction.getCount(); index++) {
            x = tabulatedFunction.getX(index);
            synchronized (tabulatedFunction) {
                y = tabulatedFunction.getY(index);
                System.out.printf("%s, i = %d, x = %f, old y = %f\n", Thread.currentThread().getName(), index, x, y);
                tabulatedFunction.setY(index, y + 3);
                y = tabulatedFunction.getY(index);
            }
            System.out.printf("%s, i = %d, x = %f, new y = %f \n", Thread.currentThread().getName(), index, x, y);
        }
        if (postRunAction != null) {
            postRunAction.run();
        }
    }
}
