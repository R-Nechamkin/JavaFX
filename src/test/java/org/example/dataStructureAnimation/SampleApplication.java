package org.example.dataStructureAnimation;

import javafx.application.Application;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class SampleApplication extends Application {
    public static AnchorPane background;
    public static Scene scene;

    public Button[] buttons;
    public Circle circle;
    public Shape[] shapes;
    public static void main(String[] args) {
        launch(args);
    }


        @Override
    public void start(Stage stage) throws Exception {
        //FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("StackAnimation.fxml"));

        background = new AnchorPane();
        background.setPrefSize(400, 500);
        scene = new Scene(background );

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

            Controller controller = new Controller();
            controller.initialize(null, null);

    }



    void paneBorders(){
        buttons = new Button[] {
                new Button("0, height"),
                new Button("0, -height"),
                new Button("width, 0"),
                new Button("-width, 0"),
                new Button("0,0"),
                new Button("30, 40"),
                new Button("200, 300"),
                new Button("400, 500"),
                new Button("near edge"),
                new Button("almost")
        };
        setLayout(buttons[0], 0, background.getHeight());
        setLayout(buttons[1], 0 , -background.getHeight());
        setLayout(buttons[2], background.getWidth(), 0);
        setLayout(buttons[3], -background.getWidth(), 0);
        setLayout(buttons[4], 0, 0);
        setLayout(buttons[5], 30, 40);
        setLayout(buttons[6], 200 + background.getLayoutX(), 300 + background.getLayoutY());
        setLayout(buttons[7], 400, 500);
        setLayout(buttons[8], 340, 470);

        Arrays.stream(buttons).forEach(b -> b.setOnAction(
                event -> {
                    Button button = (Button) event.getTarget();
                    System.out.println(button.getLayoutX() + ", " + button.getLayoutY());
                }));

        background.getChildren().addAll(buttons);

        setLayout(buttons[9], 400 - buttons[9].getWidth(), 480);

    }

    void setLayout(Node node, double x, double y){
        System.out.println("Set node layout to " + x + ", " + y);
        node.setLayoutX(x);
        node.setLayoutY(y);
    }

    void makeCircle(){
        circle = new Circle(15, Color.RED);
        background.getChildren().add(circle);
        setLayout(circle, background.getWidth() - circle.getRadius(), background.getHeight() - circle.getRadius());
    }

    class Controller implements Initializable{

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            paneBorders();
            makeCircle();

            System.out.println(circle.getRadius());
        }
    }
}
