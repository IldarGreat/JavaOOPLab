package ru.ssau.tk.IldarValeria.LabSgau.ui.controlers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ru.ssau.tk.IldarValeria.LabSgau.ui.Errors.Errors;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SimplestOperationController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        operationName.setText(MainController.operation);
        firstXColumn.setCellValueFactory(new PropertyValueFactory<>("X"));
        firstYColumn.setCellValueFactory(new PropertyValueFactory<>("Y"));
        secondXColumn.setCellValueFactory(new PropertyValueFactory<>("X"));
        secondYColumn.setCellValueFactory(new PropertyValueFactory<>("Y"));
        thirdXColumn.setCellValueFactory(new PropertyValueFactory<>("X"));
        thirdYColumn.setCellValueFactory(new PropertyValueFactory<>("Y"));
    }

    @FXML
    private Button addFirstColumnButton;

    @FXML
    private Button addSecondColumnButton;

    @FXML
    private TableView<MyTabulated> firstTable;

    @FXML
    private TableColumn<MyTabulated, Double> firstXColumn;

    @FXML
    private TableColumn<MyTabulated, Double> firstYColumn;

    @FXML
    private Label operationName;

    @FXML
    private Button resultButton;

    @FXML
    private Button saveButton;

    @FXML
    private TableView<MyTabulated> secondTable;

    @FXML
    private TableColumn<MyTabulated, Double> secondXColumn;

    @FXML
    private TableColumn<MyTabulated, Double> secondYColumn;

    @FXML
    private TableView<MyTabulated> thirdTable;

    @FXML
    private TableColumn<MyTabulated, Double> thirdXColumn;

    @FXML
    private TableColumn<MyTabulated, Double> thirdYColumn;

    @FXML
    private TextField firstXText;

    @FXML
    private TextField firstYText;

    @FXML
    private TextField secondXText;

    @FXML
    private TextField secondYText;

    @FXML
    void addFirstElement(ActionEvent event) throws IOException {
        try {
            Double pointX = Double.parseDouble(firstXText.getText());
            Double pointY = Double.parseDouble(firstYText.getText());
            firstTable.getItems().add(new MyTabulated(pointX,pointY));
        } catch (NumberFormatException e) {
            new Errors();
        }
    }

    @FXML
    void addSecondElement(ActionEvent event) throws IOException {
        try {
            Double pointX = Double.parseDouble(secondXText.getText());
            Double pointY = Double.parseDouble(secondYText.getText());
            secondTable.getItems().add(new MyTabulated(pointX,pointY));
        } catch (NumberFormatException e) {
            new Errors();
        }
    }

    @FXML
    void result(ActionEvent event) {

    }

    @FXML
    void save(ActionEvent event) {

    }
}