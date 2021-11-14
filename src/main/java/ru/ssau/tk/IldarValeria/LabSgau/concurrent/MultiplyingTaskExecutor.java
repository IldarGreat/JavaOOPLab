package ru.ssau.tk.IldarValeria.LabSgau.concurrent;

import ru.ssau.tk.IldarValeria.LabSgau.functions.*;

import java.util.*;

public class MultiplyingTaskExecutor {

    public static void main(String[] args) throws InterruptedException {
        TabulatedFunction listTabulated = new LinkedListTabulatedFunction(new UnitFunction(), 1, 1000, 1000);
        List<Thread> threads = new ArrayList<>();
        List<MultiplyingTask> tasks = new ArrayList<>();
        for (int count = 0; count < 10; count++) {
            MultiplyingTask task = new MultiplyingTask(listTabulated);
            tasks.add(task);
            Thread thread = new Thread(task);
            threads.add(thread);
        }
        for (Thread thread : threads) {
            thread.start();
        }
        while (!tasks.isEmpty()) {
            tasks.removeIf(MultiplyingTask::isCompleted);
        }
        System.out.println(listTabulated);
    }
}
