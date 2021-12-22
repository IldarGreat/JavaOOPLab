package ru.ssau.tk.IldarValeria.LabSgau.ui.controlers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import ru.ssau.tk.IldarValeria.LabSgau.ui.Errors.DifferentValues;
import ru.ssau.tk.IldarValeria.LabSgau.ui.Errors.Errors;

import java.io.File;
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
    private final FileChooser fileChooser = new FileChooser();

    @FXML
    private AnchorPane pane;

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
            firstTable.getItems().add(new MyTabulated(pointX, pointY));
        } catch (NumberFormatException e) {
            new Errors();
        }
    }

    @FXML
    void addSecondElement(ActionEvent event) throws IOException {
        try {
            Double pointX = Double.parseDouble(secondXText.getText());
            Double pointY = Double.parseDouble(secondYText.getText());
            secondTable.getItems().add(new MyTabulated(pointX, pointY));
        } catch (NumberFormatException e) {
            new Errors();
        }
    }

    @FXML
    void result(ActionEvent event) throws IOException {
        if (firstTable.getItems().size() != secondTable.getItems().size()) {
            new DifferentValues();
        } else {
            double[] firstColumnXValues = new double[firstTable.getItems().size()];
            double[] firstColumnYValues = new double[firstColumnXValues.length];
            double[] secondColumnXValues = new double[firstTable.getItems().size()];
            double[] secondColumnYValues = new double[firstColumnXValues.length];
            for (int index = 0; index < firstTable.getItems().size(); index++) {
                firstColumnXValues[index] = firstTable.getItems().get(index).getX();
                firstColumnYValues[index] = firstTable.getItems().get(index).getY();
                secondColumnXValues[index] = secondTable.getItems().get(index).getX();
                secondColumnYValues[index] = secondTable.getItems().get(index).getY();
            }
            switch (MainController.operation) {
                case "Сумма":
                    for (int index = 0; index < firstTable.getItems().size(); index++) {
                        thirdTable.getItems().add(new MyTabulated(firstColumnXValues[index] + secondColumnXValues[index], firstColumnYValues[index] + secondColumnYValues[index]));
                    }
                    break;
                case "Разность":
                    for (int index = 0; index < firstTable.getItems().size(); index++) {
                        thirdTable.getItems().add(new MyTabulated(firstColumnXValues[index] - secondColumnXValues[index], firstColumnYValues[index] - secondColumnYValues[index]));
                    }
                    break;
                case "Деление":
                    for (int index = 0; index < firstTable.getItems().size(); index++) {
                        thirdTable.getItems().add(new MyTabulated(firstColumnXValues[index] / secondColumnXValues[index], firstColumnYValues[index] / secondColumnYValues[index]));
                    }
                    break;
                case "Умножение":
                    for (int index = 0; index < firstTable.getItems().size(); index++) {
                        thirdTable.getItems().add(new MyTabulated(firstColumnXValues[index] * secondColumnXValues[index], firstColumnYValues[index] * secondColumnYValues[index]));
                    }
                    break;
            }


        }
    }

    @FXML
    void save(ActionEvent event) {
        Window stage = pane.getScene().getWindow();
        fileChooser.setInitialDirectory(new File("C:\\"));
        fileChooser.setTitle("Сохранить объект");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt")
        );
        File file = fileChooser.showOpenDialog(stage); //its open
        System.out.println(file);
    }
}