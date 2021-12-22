package ru.ssau.tk.IldarValeria.LabSgau.ui.controlers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.ssau.tk.IldarValeria.LabSgau.ui.MainWindow.Main;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class MenuController {
    @FXML
    private MenuItem about;

    @FXML
    private MenuItem createObject;

    @FXML
    private MenuItem exit;

    @FXML
    private MenuItem openObject;

    @FXML
    private MenuItem saveObject;

    @FXML
    private MenuItem whichFabric;

    @FXML
    void exit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void about(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        InputStream iconStream = getClass().getResourceAsStream("/icons/about.png");
        Image image = new Image(iconStream);
        stage.getIcons().add(image);
        stage.setTitle("О программе");
        stage.initModality(Modality.APPLICATION_MODAL);
        // stage.initOwner(new Main().getPrimaryStage());
        stage.setResizable(false);
        stage.show();
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/Other/About.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();
        stage.setScene(new Scene(root));
    }

    @FXML
    void createObject(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        InputStream iconStream = getClass().getResourceAsStream("/icons/howCreate.png");
        Image image = new Image(iconStream);
        stage.getIcons().add(image);
        stage.setTitle("С помощью чего создать табулированную функцию");
        stage.initModality(Modality.APPLICATION_MODAL);
        // stage.initOwner(new Main().getPrimaryStage());
        stage.setResizable(false);
        stage.show();
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/Other/HowCreate.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();
        stage.setScene(new Scene(root));
    }
}
