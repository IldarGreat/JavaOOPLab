package ru.ssau.tk.IldarValeria.LabSgau.ui2;

import ru.ssau.tk.IldarValeria.LabSgau.exceptions.InconsistentFunctionsException;
import ru.ssau.tk.IldarValeria.LabSgau.functions.TabulatedFunction;
import ru.ssau.tk.IldarValeria.LabSgau.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.IldarValeria.LabSgau.functions.factory.TabulatedFunctionFactory;
import ru.ssau.tk.IldarValeria.LabSgau.io.FunctionsIO;
import ru.ssau.tk.IldarValeria.LabSgau.operations.TabulatedFunctionOperationService;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SimpleOperationsWindow extends JDialog {

    private static final int FIRST_FUNCTION = 0;
    private static final int SECOND_FUNCTION = 1;

    private final List<String> xValues = new ArrayList<>();
    private final List<String> yValues = new ArrayList<>();

    private final List<String> secondXValues = new ArrayList<>();
    private final List<String> secondYValues = new ArrayList<>();

    private final List<String> resultXValues = new ArrayList<>();
    private final List<String> resultYValues = new ArrayList<>();

    private final AbstractTableModel firstTableModel = new PartiallyEditable(xValues, yValues);
    private final JTable firstTable = new JTable(firstTableModel);

    private final AbstractTableModel secondTableModel = new PartiallyEditable(secondXValues, secondYValues);
    private final JTable secondTable = new JTable(secondTableModel);

    private final AbstractTableModel resultTableModel = new NotEditable(resultXValues, resultYValues);
    private final JTable resultTable = new JTable(resultTableModel);

    private final JComboBox<String> comboBox = new JComboBox<>(new String[]{"+", "-", "*", "÷"});

    private final JButton saveButton = new JButton("Сохранить");
    private final JButton uploadButton = new JButton("Загрузить");
    private final JButton createButton = new JButton("Создать");

    private final JButton saveButtonTwo = new JButton("Сохранить");
    private final JButton uploadButtonTwo = new JButton("Загрузить");
    private final JButton createButtonTwo = new JButton("Создать");

    private final JButton resultSaveButton = new JButton("Сохранить");
    private final JButton resultButton = new JButton("=");

    private final TabulatedFunctionFactory factory;
    private TabulatedFunction firstFunction;
    private TabulatedFunction secondFunction;
    private TabulatedFunction resultFunction;

    private JFileChooser fileChooser;

    public SimpleOperationsWindow(TabulatedFunctionFactory factory) {
        this.factory = factory;
        setModal(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        setLayout(new FlowLayout());
        setSize(950, 400);

        saveButton.setFocusPainted(false);
        uploadButton.setFocusPainted(false);
        createButton.setFocusPainted(false);
        saveButtonTwo.setFocusPainted(false);
        uploadButtonTwo.setFocusPainted(false);
        createButtonTwo.setFocusPainted(false);
        resultSaveButton.setFocusPainted(false);
        resultButton.setFocusPainted(false);

        comboBox.setPreferredSize(new Dimension(2, 2));
        comboBox.setFont(new Font("Consolas", Font.PLAIN, 18));
        resultButton.setFont(new Font("Consolas", Font.PLAIN, 20));

        saveButton.setEnabled(false);
        saveButtonTwo.setEnabled(false);
        resultSaveButton.setEnabled(false);

        firstTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        addButtonListeners();
        compose();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void compose() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JScrollPane scrollPane = new JScrollPane(firstTable);
        JScrollPane scrollPaneTwo = new JScrollPane(secondTable);
        JScrollPane resultScrollPane = new JScrollPane(resultTable);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGap(8)
                        .addComponent(scrollPane)
                        .addComponent(comboBox)
                        .addComponent(scrollPaneTwo)
                        .addComponent(resultButton)
                        .addComponent(resultScrollPane))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(createButton)
                        .addComponent(uploadButton)
                        .addComponent(saveButton)
                        .addGap(33)
                        .addComponent(createButtonTwo)
                        .addComponent(uploadButtonTwo)
                        .addComponent(saveButtonTwo)
                        .addGap(125)
                        .addComponent(resultSaveButton))
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(scrollPane)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(120)
                                .addComponent(comboBox)
                                .addGap(110)
                        )
                        .addComponent(scrollPaneTwo)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(130)
                                .addComponent(resultButton)
                        )
                        .addComponent(resultScrollPane))
                .addGroup(layout.createParallelGroup()
                        .addComponent(createButton)
                        .addComponent(uploadButton)
                        .addComponent(saveButton)
                        .addGap(60)
                        .addComponent(createButtonTwo)
                        .addComponent(uploadButtonTwo)
                        .addComponent(saveButtonTwo)
                        .addGap(60)
                        .addComponent(resultSaveButton))
        );
    }

    private void setValues(List<String> xValues, List<String> yValues, TabulatedFunction function) {
        xValues.clear();
        yValues.clear();
        for (int i = 0; i < function.getCount(); i++) {
            xValues.add(Double.toString(function.getX(i)));
            yValues.add(Double.toString(function.getY(i)));
        }
    }

    private void getPopupMenu(JButton button, int flag) {
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem fromTable = new JMenuItem("из таблицы");
        JMenuItem fromFunction = new JMenuItem("из функции");

        fromTable.addActionListener(ee -> {
            Window window = new Window(factory);
            setTable(window.getFunction(), flag);
        });

        fromFunction.addActionListener(ee -> {
            SecondWindow window = new SecondWindow(factory);
            setTable(window.getFunction(), flag);
        });

        popupMenu.add(fromTable);
        popupMenu.addSeparator();
        popupMenu.add(fromFunction);
        popupMenu.show(button, button.getWidth() + 1, button.getHeight() / 30);
    }

    private void setTable(TabulatedFunction function, int flag) {
        if (function != null) {
            switch (flag) {
                case FIRST_FUNCTION:
                    firstFunction = function;
                    saveButton.setEnabled(true);
                    setValues(xValues, yValues, firstFunction);
                    firstTableModel.fireTableDataChanged();
                    break;
                case SECOND_FUNCTION:
                    secondFunction = function;
                    saveButtonTwo.setEnabled(true);
                    setValues(secondXValues, secondYValues, secondFunction);
                    secondTableModel.fireTableDataChanged();
            }
        }
    }

    private void addButtonListeners() {
        createButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    getPopupMenu(createButton, FIRST_FUNCTION);
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

        createButtonTwo.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    getPopupMenu(createButtonTwo, SECOND_FUNCTION);
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

        resultButton.addActionListener(e -> {
            try {
                TabulatedFunctionOperationService operation = new TabulatedFunctionOperationService(factory);
                switch ((String) comboBox.getSelectedItem()) {
                    case "+":
                        resultFunction = operation.sum(firstFunction, secondFunction);
                        break;
                    case "-":
                        resultFunction = operation.subtraction(firstFunction, secondFunction);
                        break;
                    case "*":
                        resultFunction = operation.multiplication(firstFunction, secondFunction);
                        break;
                    case "÷":
                        resultFunction = operation.divide(firstFunction, secondFunction);
                }
                setValues(resultXValues, resultYValues, resultFunction);
                resultSaveButton.setEnabled(true);
                resultTableModel.fireTableDataChanged();
            } catch (InconsistentFunctionsException exp) {
                ExceptionHandler.showMessage(exp.getMessage());
            } catch (NullPointerException exp) {
                ExceptionHandler.showMessage("Введите обе функции");
            }
        });

        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
        fileChooser.setAcceptAllFileFilterUsed(false);

        uploadButton.addActionListener(e -> readFunction(FIRST_FUNCTION));
        uploadButtonTwo.addActionListener(e -> readFunction(SECOND_FUNCTION));

        saveButton.addActionListener(e -> writeFunction(firstFunction));
        saveButtonTwo.addActionListener(e -> writeFunction(secondFunction));
        resultSaveButton.addActionListener(e -> writeFunction(resultFunction));
    }

    private void readFunction(int flag) {
        fileChooser.showOpenDialog(null);
        File file = fileChooser.getSelectedFile();

        if (file != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                TabulatedFunction function = FunctionsIO.readTabulatedFunction(reader, factory);
                switch (flag) {
                    case FIRST_FUNCTION:
                        firstFunction = function;
                        setValues(xValues, yValues, function);
                        saveButton.setEnabled(true);
                        firstTableModel.fireTableDataChanged();
                        break;
                    case SECOND_FUNCTION:
                        secondFunction = function;
                        setValues(secondXValues, secondYValues, function);
                        saveButtonTwo.setEnabled(true);
                        secondTableModel.fireTableDataChanged();
                }
            } catch (IOException e) {
                ExceptionHandler.showMessage("Некорректные данные!");
            } catch (NumberFormatException exp) {
                ExceptionHandler.showMessage(exp.getMessage());
            }
        }
    }

    private void writeFunction(TabulatedFunction function) {
        fileChooser.showSaveDialog(null);
        File file = fileChooser.getSelectedFile();

        if (file != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                FunctionsIO.writeTabulatedFunction(writer, function);
            } catch (IOException e) {
                ExceptionHandler.showMessage(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new SimpleOperationsWindow(new ArrayTabulatedFunctionFactory());
    }
}
