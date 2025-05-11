package it.miketan.pb.serializer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.ResourceBundle;

public class Main extends Application {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }

    @Override
    public void start(Stage stage) throws IOException {

        // Loading application.properties
        ResourceBundle bundle = ResourceBundle.getBundle("application");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/it/miketan/pb/serializer/main-view.fxml"), bundle);
        Scene scene = new Scene(fxmlLoader.load(), 800, 830);
        stage.setTitle("PB Subsystem Customizer");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}