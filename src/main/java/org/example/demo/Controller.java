package org.example.demo;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.net.URL;

import java.util.*;

public class Controller implements Initializable {
    public VBox buttonContainer;
    public Button pushButton;
    public Button popButton;
    public Button clearButton;
    public Button peekButton;
    public BorderPane shapeDispenser;
    public Rectangle stackCoverTop;
    public Circle activeCircle = new Circle(); //dummy value
    public AnchorPane stackContainer;

    public AnchorPane background;
    public BorderPane shapeCollector;
    public BorderPane pushedCircleHolder;

    private Deque<StackEl> stack = new ArrayDeque<>();
    private Random rand = new Random();
    private SequentialTransition pushAnimation;
    private SequentialTransition popAnimation;
    private SequentialTransition peekAnimation;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setNewActiveCircle();

    }

    public void setModel(Model model){
        pushAnimation = model.getPushAnimation();
        popAnimation = model.getPopAnimation();
        peekAnimation = model.getPeekAnimation();
    }

    void setNewActiveCircle(){
        Color color = getRandomColor(rand);
        activeCircle = StackEl.getCircleFromColor(color);

        background.getChildren().add(activeCircle);
        activeCircle.setLayoutX(shapeDispenser.getLayoutX() + (11.0/6) * StackEl.radius);
        activeCircle.setLayoutY(shapeDispenser.getLayoutY() + (11.0/6) * StackEl.radius);

    }

    public void onPushButtonAction(ActionEvent actionEvent) {
        popAnimation.stop();
        peekAnimation.stop();

        stack.push(new StackEl((Color) activeCircle.getFill()));
        pushAnimation.setNode(activeCircle);

        pushAnimation.play();
    }



    public void onPeekButtonAction(ActionEvent actionEvent){
        pushAnimation.stop();
        popAnimation.stop();

        if (!stack.isEmpty()) {
            Circle circle = StackEl.getCircleFromColor(stack.peek().circle);
            pushedCircleHolder.setCenter(circle);

            peekAnimation.setOnFinished(
                    e -> pushedCircleHolder.getChildren().remove(circle)
            );
        }
        peekAnimation.play();


    }

    public void onPopActionButton() {
        pushAnimation.stop();
        peekAnimation.stop();

        if (!stack.isEmpty()) {
            Circle circle = StackEl.getCircleFromColor(stack.pop().circle);
            pushedCircleHolder.setCenter(circle);
            popAnimation.setNode(circle);

            popAnimation.play();

        }
    }

    public void onClearActionButton(ActionEvent actionEvent){
        stack.clear();
    }

    private Color getRandomColor(Random rand){
        return Color.color(rand.nextDouble(), rand.nextDouble(), rand.nextDouble());
    }


    private void createDebugButton(double x, double y){
        Button b = new Button("Button");
        b.setOnAction(
                event -> {
                    Button button = (Button) event.getTarget();
                    System.out.println(button.getLayoutX() + ", " + button.getLayoutY());
                });
        background.getChildren().addFirst(b);
        b.setLayoutX(x);
        b.setLayoutY(y);
        b.toFront();
    }

    static class StackEl {
        static double radius = 20;
        Color circle;

        static Circle getCircleFromColor(Color color){
            Circle circle = new Circle(StackEl.radius, color);
            circle.setStrokeWidth(2);
            circle.setStroke(Color.BLACK);
            return circle;
        }

        public StackEl(Color circle) {
            this.circle = circle;
        }

        public Color getColor(){
            return  circle;
        }


    }
}
