package ru.ssau.tk.IldarValeria.LabSgau.ui2;

import ru.ssau.tk.IldarValeria.LabSgau.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.IldarValeria.LabSgau.functions.TabulatedFunction;
import ru.ssau.tk.IldarValeria.LabSgau.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.IldarValeria.LabSgau.functions.factory.TabulatedFunctionFactory;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class Window extends JDialog {

    private final List<String> xValues = new ArrayList<>();
    private final List<String> yValues = new ArrayList<>();

    private final AbstractTableModel tableModel = new Table(xValues, yValues);
    private final JTable table = new JTable(tableModel);
    private final JLabel label = new JLabel("Количество точек:");

    private final JTextField textField = new JTextField("2");
    private final JButton addButton = new JButton("Добавить");
    private final JButton createButton = new JButton("Создать");
    private final JButton refreshButton = new JButton("Очистить");

    private TabulatedFunction function;
    private final TabulatedFunctionFactory factory;

    public Window(TabulatedFunctionFactory factory) {
        this.factory = factory;

        setModal(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());
        setSize(500, 300);

        addButton.setFocusPainted(false);
        refreshButton.setFocusPainted(false);
        createButton.setFocusPainted(false);

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        addButtonListeners();
        compose();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public TabulatedFunction getFunction() {
        return function;
    }

    private void compose() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        JScrollPane tableScrollPane = new JScrollPane(table);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(label)
                        .addComponent(textField)
                        .addComponent(addButton)
                )
                .addComponent(tableScrollPane)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(createButton)
                        .addComponent(refreshButton)
                )
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(label)
                        .addComponent(textField)
                        .addComponent(addButton)
                )
                .addComponent(tableScrollPane)
                .addGroup(layout.createParallelGroup()
                        .addComponent(createButton)
                        .addComponent(refreshButton)
                )
        );
    }

    private void addButtonListeners() {
        class AddingAction implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int size = Integer.parseInt(textField.getText());
                    if (size <= 0) {
                        ExceptionHandler.showMessage("Введите положительное число.");
                    }
                    for (int i = 0; i < size; i++) {
                        xValues.add(" ");
                        yValues.add(" ");
                    }
                    refreshButton.setEnabled(true);
                    tableModel.fireTableDataChanged();
                } catch (NumberFormatException exception) {
                    ExceptionHandler.showMessage("Введите целое число.");
                }
            }
        }
        refreshButton.setEnabled(false);
        textField.addActionListener(new AddingAction());
        addButton.addActionListener(new AddingAction());
        createButton.addActionListener(e -> {
            try {
                int size = xValues.size();
                double[] x = new double[size];
                double[] y = new double[size];
                for (int i = 0; i != size; i++) {
                    x[i] = Double.parseDouble(xValues.get(i));
                    y[i] = Double.parseDouble(yValues.get(i));
                }
                function =  factory.create(x, y);
                System.out.println(function);
                dispose();
            } catch (NumberFormatException exp) {
                ExceptionHandler.showMessage("Введите число в виде десятичной дроби через точку");
            } catch (ArrayIsNotSortedException exp) {
                ExceptionHandler.showMessage("Некорректные данные: значения X должны располагаться по возрастанию");
            } catch (IllegalArgumentException exp) {
                ExceptionHandler.showMessage(exp.getMessage());
            }
        });

        refreshButton.addActionListener(e -> {
            int flag = JOptionPane.showConfirmDialog(null, "Вы уверены?", "Предупреждение", JOptionPane.YES_NO_OPTION);
            if (flag == 0) {
                int size = xValues.size();
                xValues.clear();
                yValues.clear();
                for (int i = 0; i < size; i++) {
                    xValues.add(" ");
                    yValues.add(" ");
                }
                tableModel.fireTableDataChanged();
            }
        });

        table.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        JPopupMenu popupMenu = new JPopupMenu();
                        JMenuItem deleteItem = new JMenuItem("Удалить");
                        JMenuItem cleanItem = new JMenuItem("Очистить");
                        deleteItem.addActionListener(ee -> {
                            xValues.remove(table.getSelectedRow());
                            yValues.remove(table.getSelectedRow());
                            if (xValues.isEmpty()) {
                                refreshButton.setEnabled(false);
                            }
                            tableModel.fireTableDataChanged();
                        });
                        cleanItem.addActionListener(ee -> {
                            xValues.set(table.getSelectedRow(), " ");
                            yValues.set(table.getSelectedRow(), " ");
                            tableModel.fireTableDataChanged();
                        });
                        popupMenu.add(deleteItem);
                        popupMenu.add(cleanItem);
                        popupMenu.show(e.getComponent(), e.getX(), e.getY());
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

        });
    }

    public static void main(String[] args) {
        new Window(new ArrayTabulatedFunctionFactory());
    }
}