package ru.ssau.tk.IldarValeria.LabSgau.ui.controlers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import ru.ssau.tk.IldarValeria.LabSgau.ui.SimpleOperationWindow.SimpleOperationWindow;

import java.io.IOException;

public class MainController {
    private String operation;
    public String getOperation(){return operation;}
    @FXML
    private Button DifferenceButton;

    @FXML
    private Button DivideButton;

    @FXML
    private Button MultiplicationButton;

    @FXML
    private Button SumButton;

    @FXML
    void SumOperation(ActionEvent event) {
        try {
            operation = "Сумма";
            new SimpleOperationWindow().init(operation);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void DifferenceOperation(ActionEvent event) {
        try {
            operation = "Разность";
            new SimpleOperationWindow().init(operation);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void DivideOperation(ActionEvent event) {
        try {
            operation = "Деление";
            new SimpleOperationWindow().init(operation);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void MultiplicationOperation(ActionEvent event) {
        try {
            operation = "Умножение";
            new SimpleOperationWindow().init(operation);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
