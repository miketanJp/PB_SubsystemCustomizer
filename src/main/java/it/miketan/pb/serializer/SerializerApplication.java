package it.miketan.pb.serializer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SerializerApplication extends Application {
   @Override
   public void start(Stage stage) throws IOException {

      FXMLLoader fxmlLoader = new FXMLLoader(SerializerApplication.class.getResource("main-view.fxml"));
      Scene scene = new Scene(fxmlLoader.load(), 1024, 850);
      stage.setTitle("PB Subsystem Customizer - ©2024 Miketan");
      stage.setResizable(false);
      stage.setScene(scene);
      stage.show();
   }

   public static void main(String[] args) {
      launch();
   }
}