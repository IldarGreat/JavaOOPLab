package ru.ssau.tk.IldarValeria.LabSgau.concurrent;

import ru.ssau.tk.IldarValeria.LabSgau.functions.*;

import java.util.*;

public class MultiplyingTaskExecutor {

    public static void main(String[] args) throws InterruptedException {
        TabulatedFunction listTabulated = new LinkedListTabulatedFunction(new UnitFunction(), 1, 1000, 1000);
        List<Thread> threads = new ArrayList<>();
        for (int count = 0; count < 10; count++) {
            MultiplyingTask multiplyingTask = new MultiplyingTask(listTabulated);
            Thread thread = new Thread(multiplyingTask);
            threads.add(thread);
        }
        for (Thread thread : threads) {
            thread.start();
        }
        Thread.sleep(5_000);
        System.out.println(listTabulated);
    }
}
