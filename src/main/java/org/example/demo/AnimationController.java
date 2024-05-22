package org.example.demo;

import javafx.animation.TranslateTransition;
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
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;
import java.util.ResourceBundle;

public class AnimationController implements Initializable {
    public VBox buttonContainer;
    public Button pushButton;
    public Button popButton;
    public Button clearButton;
    public Button peekButton;
    public BorderPane shapeDispenser;
    public Rectangle stackCoverTop;
    public Circle activeCircle;
    public AnchorPane stackContainer;

    public double circleX;
    public double circleY;
    public AnchorPane background;

    private Deque<Node> stack = new ArrayDeque<>();
    private Random rand = new Random();
    private TranslateTransition pushTransition;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createPushTransition();

        activeCircle.setFill(getRandomColor(rand));
        activeCircle.setRadius(Node.radius);

        circleX = activeCircle.getCenterX();
        circleY = activeCircle.getCenterY();

    }

    private void createPushTransition(){
        pushTransition = new TranslateTransition();
        pushTransition.setDuration(Duration.seconds(1));
        pushTransition.setToX(-(stackContainer.getLayoutX() + 2* Node.radius));
        pushTransition.setToY(stackContainer.getLayoutY());

    }

    public void onPushButtonAction(ActionEvent actionEvent) {
        stack.push(new Node((Color) activeCircle.getFill()));
        Circle pushedCircle = activeCircle;
        pushTransition.setNode(pushedCircle);


        pushTransition.play();
        System.out.println(activeCircle.getFill());
        pushTransition.setOnFinished(e -> {
            System.out.println("Event finished");
            setNewActiveCircle();});


    }

    public void setNewActiveCircle(){
        Circle circle = new Circle();
        circle.setCenterY(50); circle.setCenterX(50);
        circle.setFill(getRandomColor(rand));
        background.getChildren().addFirst(circle);
        background.layout();

//
//        circle.setViewOrder(-3);
//        shapeDispenser.setViewOrder(2);
//        shapeDispenser.layout();
//
//        activeCircle = circle;
//
//        System.out.println(activeCircle.getFill());
    }

    public void onPeekButtonAction(ActionEvent actionEvent){

    }

    public void onPopActionButton(ActionEvent actionEvent) {
    }

    public void onClearActionButton(ActionEvent actionEvent){

    }

    private Color getRandomColor(Random rand){
        return Color.color(rand.nextDouble(), rand.nextDouble(), rand.nextDouble());
    }



    class Node{
        static double radius = 20;
        Color color;

        public Node(Color color) {
            this.color = color;
        }
    }
}
