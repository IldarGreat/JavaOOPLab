package ru.ssau.tk.IldarValeria.LabSgau.ui.controlers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class HowOtherCreateController {
    @FXML
    private Button function;

    @FXML
    private Button mass;

    @FXML
    void createOnFunction(ActionEvent event) {

    }

    @FXML
    void createOnMass(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        InputStream iconStream = getClass().getResourceAsStream("/icons/howCreate.png");
        Image image = new Image(iconStream);
        stage.getIcons().add(image);
        stage.setTitle("Количество точек");
        stage.initModality(Modality.APPLICATION_MODAL);
        // stage.initOwner(new Main().getPrimaryStage());
        stage.setResizable(false);
        stage.show();
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/Other/NumberOfPoints.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();
        stage.setScene(new Scene(root));
    }
}
