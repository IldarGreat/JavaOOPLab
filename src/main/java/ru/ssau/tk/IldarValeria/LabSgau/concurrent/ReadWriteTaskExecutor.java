package ru.ssau.tk.IldarValeria.LabSgau.concurrent;

import ru.ssau.tk.IldarValeria.LabSgau.functions.*;

public class ReadWriteTaskExecutor {

    public static void main(String[] args) {
        TabulatedFunction listTabulated = new LinkedListTabulatedFunction(new ConstantFunction(-1), 1, 1000, 1000);
        ReadTask readTask = new ReadTask(listTabulated);
        WriteTask writeTask = new WriteTask(listTabulated, 0.5);
        Thread threadRead = new Thread(readTask);
        Thread threadWrite = new Thread(writeTask);
        threadRead.start();
        threadWrite.start();
    }
}
