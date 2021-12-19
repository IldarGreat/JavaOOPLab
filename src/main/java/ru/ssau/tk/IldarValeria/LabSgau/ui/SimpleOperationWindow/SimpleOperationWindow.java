package ru.ssau.tk.IldarValeria.LabSgau.ui.SimpleOperationWindow;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.ssau.tk.IldarValeria.LabSgau.ui.MainWindow.Main;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class SimpleOperationWindow implements Initializable {
    public Stage stage;

    public void init(String operation) throws IOException {
        stage = new Stage();
        InputStream iconStream = getClass().getResourceAsStream("/icons/operationIcon.png");
        Image image = new Image(iconStream);
        stage.getIcons().add(image);
        stage.setTitle(operation);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(new Main().getPrimaryStage());
        stage.setResizable(false);
        stage.show();
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/Operations/SimplestOperation.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();
        stage.setScene(new Scene(root));

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
