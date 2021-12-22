package ru.ssau.tk.IldarValeria.LabSgau.ui.controlers;

import com.thoughtworks.xstream.security.ForbiddenClassException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.ssau.tk.IldarValeria.LabSgau.functions.ArrayTabulatedFunction;
import ru.ssau.tk.IldarValeria.LabSgau.functions.TabulatedFunction;
import ru.ssau.tk.IldarValeria.LabSgau.io.FunctionsIO;

import java.io.*;
import java.net.URL;

public class OpenFileController {
    @FXML
    private AnchorPane pane;

    @FXML
    private Button openButton;

    @FXML
    private TextField textFiled;

    @FXML
    void openObject(ActionEvent event) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(
                new FileReader("output/" + textFiled.getText() + ".xml"))) {
            TabulatedFunction newArrayTabulated = FunctionsIO.deserializeXml(bufferedReader);
            System.out.println(newArrayTabulated);
            if(newArrayTabulated!=null){
                Stage thisStage = (Stage) pane.getScene().getWindow();
                thisStage.close();
            }
        } catch (IOException | ForbiddenClassException e) {
            Stage stage = new Stage();
            InputStream iconStream = getClass().getResourceAsStream("/icons/error.png");
            Image image = new Image(iconStream);
            stage.getIcons().add(image);
            stage.setTitle("Такого объекта не существует");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.show();
            FXMLLoader loader = new FXMLLoader();
            URL xmlUrl = getClass().getResource("/Errors/FileNotFound.fxml");
            loader.setLocation(xmlUrl);
            Parent root = loader.load();
            stage.setScene(new Scene(root));
        }
    }
}