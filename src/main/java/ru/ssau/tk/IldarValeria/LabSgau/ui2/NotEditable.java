package ru.ssau.tk.IldarValeria.LabSgau.ui2;

import java.util.List;

public class NotEditable extends Table {

    private static final long serialVersionUID = 3264684362156617638L;

    public NotEditable(List<String> xValues, List<String> yValues) {
        super(xValues, yValues);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}
