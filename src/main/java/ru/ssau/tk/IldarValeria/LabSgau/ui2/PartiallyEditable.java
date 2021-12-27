package ru.ssau.tk.IldarValeria.LabSgau.ui2;

import java.util.List;

public class PartiallyEditable extends Table {
    private static final long serialVersionUID = 4319180793901191382L;

    public PartiallyEditable(List<String> xValues, List<String> yValues) {
        super(xValues, yValues);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case X_COLUMN_NUMBER:
                return false;
            case Y_COLUMN_NUMBER:
                return true;
        }
        return false;
    }
}
