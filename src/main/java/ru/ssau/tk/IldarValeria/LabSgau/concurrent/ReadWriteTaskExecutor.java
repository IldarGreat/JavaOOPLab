package ru.ssau.tk.IldarValeria.LabSgau.concurrent;

import ru.ssau.tk.IldarValeria.LabSgau.functions.*;

import java.util.*;
import java.util.concurrent.CountDownLatch;

public class ReadWriteTaskExecutor {

    public static void main(String[] args) throws InterruptedException {
        TabulatedFunction listTabulated = new LinkedListTabulatedFunction(new ZeroFunction(), 1, 10, 10);
        CountDownLatch countDownLatch = new CountDownLatch(20);
        ReadWriteTask readWriteTask = new ReadWriteTask(listTabulated, countDownLatch::countDown);
        List<Thread> threads = new ArrayList<>();
        for (int number = 0; number < 20; number++) {
            threads.add(new Thread(readWriteTask));
        }
        for (Thread thread : threads) {
            thread.start();
        }
        countDownLatch.await();
        System.out.println(listTabulated);
    }
}
