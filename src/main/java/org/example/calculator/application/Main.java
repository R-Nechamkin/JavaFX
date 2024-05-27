package org.example.calculator.application;
	

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import org.example.calculator.Controller.Controller;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			View view = new View();
			Controller controller = new Controller(view);
			Scene scene = view.getScene();

			String cssUrl = getClass().getResource("/org/example/calculator/application.css").toExternalForm();
			scene.getStylesheets().add(cssUrl);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
