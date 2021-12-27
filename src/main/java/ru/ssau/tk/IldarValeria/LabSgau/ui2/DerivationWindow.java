package ru.ssau.tk.IldarValeria.LabSgau.ui2;

import ru.ssau.tk.IldarValeria.LabSgau.functions.TabulatedFunction;
import ru.ssau.tk.IldarValeria.LabSgau.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.IldarValeria.LabSgau.functions.factory.TabulatedFunctionFactory;
import ru.ssau.tk.IldarValeria.LabSgau.io.FunctionsIO;
import ru.ssau.tk.IldarValeria.LabSgau.operations.TabulatedDifferentialOperator;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.ArrayList;

public class DerivationWindow extends JDialog {

    private final ArrayList<String> xValues = new ArrayList<>();
    private final ArrayList<String> yValues = new ArrayList<>();
    private final ArrayList<String> resultXValues = new ArrayList<>();
    private final ArrayList<String> resultYValues = new ArrayList<>();

    private final AbstractTableModel tableModel = new PartiallyEditable(xValues, yValues);
    private final JTable table = new JTable(tableModel);

    private final AbstractTableModel resultTableModel = new NotEditable(resultXValues, resultYValues);
    private final JTable resultTable = new JTable(resultTableModel);

    private final JButton saveButton = new JButton("Сохранить");
    private final JButton uploadButton = new JButton("Загрузить");
    private final JButton createButton = new JButton("Создать");
    private final JButton resultSaveButton = new JButton("Сохранить");
    private final JButton resultButton = new JButton("Дифференцировать");

    private JFileChooser fileChooser;

    private final TabulatedFunctionFactory factory;
    private TabulatedFunction function;
    private TabulatedFunction resultFunction;

    public DerivationWindow(TabulatedFunctionFactory factory) {
        this.factory = factory;

        setModal(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());
        setSize(800, 400);

        saveButton.setFocusPainted(false);
        uploadButton.setFocusPainted(false);
        createButton.setFocusPainted(false);
        resultSaveButton.setFocusPainted(false);
        resultButton.setFocusPainted(false);

        saveButton.setEnabled(false);
        resultSaveButton.setEnabled(false);
        resultButton.setEnabled(false);

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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

        JScrollPane scrollPane = new JScrollPane(table);
        JScrollPane resultScrollPane = new JScrollPane(resultTable);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGap(110)
                        .addComponent(resultButton))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(scrollPane)
                        .addGap(40)
                        .addComponent(resultScrollPane))
                .addGroup(layout.createSequentialGroup()
                        .addGap(25)
                        .addComponent(createButton)
                        .addComponent(uploadButton)
                        .addComponent(saveButton)
                        .addGap(200)
                        .addComponent(resultSaveButton)));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(resultButton)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(scrollPane)
                        .addComponent(resultScrollPane))
                .addGroup(layout.createParallelGroup()
                        .addComponent(createButton)
                        .addComponent(uploadButton)
                        .addComponent(saveButton)
                        .addComponent(resultSaveButton)));
    }

    private void setValues(ArrayList<String> xValues, ArrayList<String> yValues, TabulatedFunction function) {
        xValues.clear();
        yValues.clear();
        for (int i = 0; i < function.getCount(); i++) {
            xValues.add(Double.toString(function.getX(i)));
            yValues.add(Double.toString(function.getY(i)));
        }
    }

    private void setTable(TabulatedFunction function) {
        if (function != null) {
            this.function = function;
            saveButton.setEnabled(true);
            resultButton.setEnabled(true);
            setValues(xValues, yValues, this.function);
            tableModel.fireTableDataChanged();
        }
    }

    private void addButtonListeners() {
        createButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    JPopupMenu popupMenu = new JPopupMenu();
                    JMenuItem fromTable = new JMenuItem("из таблицы");
                    JMenuItem fromFunction = new JMenuItem("из функции");

                    fromTable.addActionListener(ee -> {
                        Window window = new Window(factory);
                        setTable(window.getFunction());
                    });

                    fromFunction.addActionListener(ee -> {
                        SecondWindow window = new SecondWindow(factory);
                        setTable(window.getFunction());
                    });

                    popupMenu.add(fromTable);
                    popupMenu.addSeparator();
                    popupMenu.add(fromFunction);
                    popupMenu.show(createButton, createButton.getWidth() + 1, createButton.getHeight() / 30);
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
                TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator(factory);
                resultFunction = operator.derive(function);
                setValues(resultXValues, resultYValues, resultFunction);
                resultSaveButton.setEnabled(true);
                resultTableModel.fireTableDataChanged();
            } catch (NullPointerException exp) {
                ExceptionHandler.showMessage("Введите функцию");
            }
        });

        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
        fileChooser.setAcceptAllFileFilterUsed(false);

        uploadButton.addActionListener(e -> {
            fileChooser.showOpenDialog(null);
            File file = fileChooser.getSelectedFile();

            if (file != null) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    TabulatedFunction function = FunctionsIO.readTabulatedFunction(reader, factory);
                    this.function = function;
                    setValues(xValues, yValues, function);
                    saveButton.setEnabled(true);
                    resultButton.setEnabled(true);
                    tableModel.fireTableDataChanged();

                } catch (IOException exp) {
                    ExceptionHandler.showMessage(exp.getMessage());
                } catch (NumberFormatException exp) {
                    ExceptionHandler.showMessage("Некорректные данные");
                }
            }
        });

        saveButton.addActionListener(e -> writeFunction(function));
        resultSaveButton.addActionListener(e -> writeFunction(resultFunction));
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
        new DerivationWindow(new ArrayTabulatedFunctionFactory());
    }
}
