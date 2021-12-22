package ru.ssau.tk.IldarValeria.LabSgau.ui.Errors;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class DifferentValues {

    public DifferentValues() throws IOException {
        Stage stage = new Stage();
        InputStream iconStream = getClass().getResourceAsStream("/icons/error.png");
        Image image = new Image(iconStream);
        stage.getIcons().add(image);
        stage.setTitle("Ошибка");
        stage.initModality(Modality.APPLICATION_MODAL);
        // stage.initOwner(new Main().getPrimaryStage());
        stage.setResizable(false);
        stage.show();
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/Errors/DifferentValues.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();
        stage.setScene(new Scene(root));
    }
}
