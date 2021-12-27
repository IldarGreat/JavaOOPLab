package ru.ssau.tk.IldarValeria.LabSgau.ui2;

import ru.ssau.tk.IldarValeria.LabSgau.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.IldarValeria.LabSgau.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.IldarValeria.LabSgau.functions.factory.TabulatedFunctionFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.ArrayList;

public class WindowWithSettings extends JDialog {
    JTabbedPane tabbedPane = new JTabbedPane();
    private static TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
    private static boolean check = true;

    public WindowWithSettings() {
        setModal(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());
        setSize(400, 400);

        tabbedPane.setPreferredSize(new Dimension(300, 300));
        JPanel design = new FirstPanel();
        JPanel factorySelection = new SecondPanel();
        tabbedPane.addTab("Дизайн", design);
        tabbedPane.addTab("Выбор фабрики", factorySelection);
        tabbedPane.setBackground(Color.lightGray);
        tabbedPane.setForeground(Color.white);

        getContentPane().add(tabbedPane);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static TabulatedFunctionFactory getFactory() {
        return factory;
    }

    private static class ColorOption extends JRadioButton {
        private final Color color;
        public ColorOption(String colorName, Color color) {
            super((colorName));
            this.color = color;
        }
        public Color getColor() {
            return color;
        }
    }

    class FirstPanel extends JPanel{
        ArrayList<ColorOption> colorOptions = new ArrayList<>();

        public FirstPanel() {
            colorOptions.add(new ColorOption("Черный", Color.BLACK));
            colorOptions.add(new ColorOption("Белый", Color.WHITE));
            colorOptions.add(new ColorOption("Светло-серый", Color.LIGHT_GRAY));
            colorOptions.add(new ColorOption("Серый", Color.GRAY));
            colorOptions.add(new ColorOption("Тёмно-серый", Color.DARK_GRAY));
            colorOptions.add(new ColorOption("Голубой", Color.CYAN));
            ButtonGroup buttonGroup = new ButtonGroup();
            for (ColorOption colorOption : colorOptions) {
                buttonGroup.add(colorOption);
                setLayout(new FlowLayout());
                add(colorOption);
                colorOption.addItemListener(e -> {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        getContentPane().setBackground(colorOption.getColor());
                    }
                });
            }
        }
    }

    static class SecondPanel extends JPanel {
        ButtonGroup group = new ButtonGroup();

        public SecondPanel() {
            JRadioButton fromArrays = new JRadioButton("Массивы", check);
            group.add(fromArrays);

            JRadioButton fromList = new JRadioButton("Связный список", !check);
            group.add(fromList);

            add(fromArrays);
            add(fromList);

            fromArrays.addItemListener(e -> {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    factory = new ArrayTabulatedFunctionFactory();
                    check = true;
                }
            });
            fromList.addItemListener(e -> {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    factory = new LinkedListTabulatedFunctionFactory();
                    check = false;
                }
            });
        }
    }

    public static void main(String[] args) {
        new WindowWithSettings();
    }
}