package ru.ssau.tk.IldarValeria.LabSgau.concurrent;

import ru.ssau.tk.IldarValeria.LabSgau.functions.*;

import java.util.concurrent.CountDownLatch;

public class AddingMultiplyingTaskExecutor {

    public static void main(String[] args) throws InterruptedException {
        TabulatedFunction listTabulated = new LinkedListTabulatedFunction(new ConstantFunction(2), 1, 100, 100);
        CountDownLatch countDownLatch = new CountDownLatch(3);
        Thread threadOne = new Thread(new MultiplyingTask(listTabulated,countDownLatch::countDown));
        Thread threadTwo = new Thread(new MultiplyingTask(listTabulated,countDownLatch::countDown));
        Thread threadThree = new Thread(new AddingTask(listTabulated,countDownLatch::countDown));
        threadOne.start();
        threadTwo.start();
        threadThree.start();
        countDownLatch.await();
        System.out.println(listTabulated);
    }

}
