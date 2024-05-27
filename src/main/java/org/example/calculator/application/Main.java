package org.example.calculator.application;
	

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.net.URL;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			String fxmlUrl = getClass().getResource("/org/example/calculator/Calculator.fxml").toExternalForm();

			FXMLLoader fxmlLoader = new FXMLLoader(new URL(fxmlUrl));
			Scene scene = new Scene(fxmlLoader.load());

////			View view = new View();
//			Controller controller = new Controller();
//
//			fxmlLoader.setController(controller);

			String cssUrl = getClass().getResource("/org/example/calculator/application.css").toExternalForm();
			scene.getStylesheets().add(cssUrl);
			primaryStage.setScene(scene);
//			controller.setUp();
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
