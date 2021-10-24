package ru.ssau.tk.IldarValeria.LabSgau.operations;

import ru.ssau.tk.IldarValeria.LabSgau.functions.*;

public interface DifferentialOperator<T extends MathFunction> {

    T derive(T function);
}
