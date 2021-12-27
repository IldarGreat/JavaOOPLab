package ru.ssau.tk.IldarValeria.LabSgau.ui2;

import javax.swing.*;

public class ExceptionHandler {
    private ExceptionHandler() {
    }
    public static void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Ошибка", JOptionPane.ERROR_MESSAGE);
    }
}
