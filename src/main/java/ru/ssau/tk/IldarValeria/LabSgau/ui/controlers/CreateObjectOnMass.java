package ru.ssau.tk.IldarValeria.LabSgau.ui.controlers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.ssau.tk.IldarValeria.LabSgau.functions.ArrayTabulatedFunction;
import ru.ssau.tk.IldarValeria.LabSgau.functions.TabulatedFunction;
import ru.ssau.tk.IldarValeria.LabSgau.ui.Errors.Errors;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateObjectOnMass implements Initializable {
    @FXML
    private TextField textFieldForX;

    @FXML
    private TextField textFieldForY;

    @FXML
    private Button create;

    @FXML
    private Button addPoint;

    @FXML
    private TableView<MyTabulated> table;

    @FXML
    private TableColumn<MyTabulated, Double> xColumn;

    @FXML
    private TableColumn<MyTabulated, Double> yColumn;

    @FXML
        // for button addPoint
    void addPoint(ActionEvent event) throws IOException {
        try {
            Double pointX = Double.parseDouble(textFieldForX.getText());
            Double pointY = Double.parseDouble(textFieldForY.getText());
            table.getItems().add(new MyTabulated(pointX,pointY));
        } catch (NumberFormatException e) {
            new Errors();
        }
    }

    @FXML
        // for button create
    void createObject(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        xColumn.setCellValueFactory(new PropertyValueFactory<>("X"));
        yColumn.setCellValueFactory(new PropertyValueFactory<>("Y"));
    }
}
