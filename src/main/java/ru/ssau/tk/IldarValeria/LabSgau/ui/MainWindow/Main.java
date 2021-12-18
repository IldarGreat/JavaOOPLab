package ru.ssau.tk.IldarValeria.LabSgau.ui.MainWindow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

public class Main extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout; // Основное приложение , содержит меню

    public Main(){}

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        initMainLayout();
        showOverview();
    }

    private void showOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/MainScene/MainScene.fxml"));
            AnchorPane overview = (AnchorPane) loader.load();
            rootLayout.setCenter(overview);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initMainLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/MainScene/WrapperMainScene.fxml"));
            rootLayout = (BorderPane) loader.load();
            InputStream iconStream = getClass().getResourceAsStream("/icons/mainIcon.png");
            Image image = new Image(iconStream);
            primaryStage.getIcons().add(image);
            Scene scene = new Scene(rootLayout);
            primaryStage.setTitle("Lab 5 by Ildar&Valeria");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage(){
        return this.primaryStage;
    }

    public static void main(String[] args) {
        launch();
    }
}
