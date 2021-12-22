package ru.ssau.tk.IldarValeria.LabSgau.ui.controlers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.ssau.tk.IldarValeria.LabSgau.functions.TabulatedFunction;
import ru.ssau.tk.IldarValeria.LabSgau.ui.Errors.Errors;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class CreateObjectOnMass {
    @FXML
    private TextField textFieldForX;

    @FXML
    private TextField textFieldForY;

    @FXML
    private Button create;

    @FXML
    private Button addPoint;

    @FXML
    private TableView<TabulatedFunction> table;

    @FXML
    private TableColumn<TabulatedFunction, Double> xColumn;

    @FXML
    private TableColumn<TabulatedFunction, Double> yColumn;

    @FXML
        // for button addPoint
    void addPoint(ActionEvent event) throws IOException {
        try {
            Double pointX = Double.parseDouble(textFieldForX.getText());
            Double pointY = Double.parseDouble(textFieldForY.getText());
           // xColumn.cellFactoryProperty()
        } catch (NumberFormatException e) {
            new Errors();
        }
    }

    @FXML
        // for button create
    void createObject(ActionEvent event) {

    }
}
