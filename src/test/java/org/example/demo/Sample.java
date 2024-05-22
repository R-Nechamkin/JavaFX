package org.example.demo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Sample extends Application {
    public static AnchorPane background;
    public static Scene scene;
    public static void main(String[] args) {
        launch(args);
    }


        @Override
        public void start(Stage stage) throws Exception {
            //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("StackAnimation.fxml"));

            background = new AnchorPane();
            scene = new Scene(background);
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();
        }

}
