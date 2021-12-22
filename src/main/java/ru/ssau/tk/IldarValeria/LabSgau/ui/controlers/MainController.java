package ru.ssau.tk.IldarValeria.LabSgau.ui.controlers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import ru.ssau.tk.IldarValeria.LabSgau.ui.SimpleOperationWindow.SimpleOperationWindow;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    public static String operation;
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
    private TableView<MyTabulated> mainTable;

    @FXML
    private TableColumn<MyTabulated, Double> xColumn;

    @FXML
    private TableColumn<MyTabulated, Double> yColumn;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        xColumn.setCellValueFactory(new PropertyValueFactory<>("X"));
        yColumn.setCellValueFactory(new PropertyValueFactory<>("Y"));
    }

    public void refresh(TableView<MyTabulated> tableView){
        for(int index=0;index < tableView.getItems().size();index++){
            mainTable.getItems().add(tableView.getItems().get(index));
        }
    }
}
