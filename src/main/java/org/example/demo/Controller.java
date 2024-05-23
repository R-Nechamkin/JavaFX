package org.example.demo;

import javafx.animation.*;
import javafx.collections.transformation.TransformationList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.net.URL;
import java.security.Key;
import java.util.*;

public class Controller implements Initializable {
    public VBox buttonContainer;
    public Button pushButton;
    public Button popButton;
    public Button clearButton;
    public Button peekButton;
    public BorderPane shapeDispenser;
    public Rectangle stackCoverTop;
    public Circle activeCircle = new Circle();
    public AnchorPane stackContainer;

    public AnchorPane background;
    public BorderPane shapeCollector;
    public BorderPane pushedCircleHolder;

    private Deque<StackEl> stack = new ArrayDeque<>();
    private Random rand = new Random();
    private TranslateTransition pushAnimation;
    private TranslateTransition popAnimation;
    private Timeline peekAnimation;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        createPushAnimation();
        createPopAnimation();
        createPeekAnimation();

        setNewActiveCircle();

    }



    private void setNewActiveCircle(){
        Color color = getRandomColor(rand);
        activeCircle = StackEl.getCircleFromColor(color);

        background.getChildren().add(activeCircle);
        activeCircle.setLayoutX(shapeDispenser.getLayoutX() + (11.0/6) * StackEl.radius);
        activeCircle.setLayoutY(shapeDispenser.getLayoutY() + (11.0/6) * StackEl.radius);

    }

    /*private void createPushAnimation(){
        pushAnimation = new TranslateTransition();
        pushAnimation.setDuration(Duration.seconds(1));

        KeyFrame circleFade = new KeyFrame(Duration.seconds(1),
                new KeyValue(activeCircle.opacityProperty(), .25));
        KeyFrame switchActiveCircle = new KeyFrame(Duration.seconds(5),
                e -> {
                    background.getChildren().remove(activeCircle);
                    setNewActiveCircle();
                },
                new KeyValue(activeCircle.opacityProperty(), 0.1));
        Timeline endTransition = new Timeline(circleFade, switchActiveCircle);

        System.out.println(shapeDispenser.getWidth());
        pushAnimation.setOnFinished(e -> {
            activeCircle.toFront();
            endTransition.play();
            stackContainer.toFront();
        });
    }*/


    private void createPushAnimation() {
        pushAnimation = new TranslateTransition();
        pushAnimation.setDuration(Duration.seconds(1));

        pushAnimation.setOnFinished(e -> {
            activeCircle.toFront();
            fadeOutAndRemoveCircle();
        });
    }

    private void fadeOutAndRemoveCircle() {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), activeCircle);
        fadeTransition.setToValue(0.1);
        fadeTransition.setOnFinished(e -> {
            background.getChildren().remove(activeCircle);
            setNewActiveCircle();
        });
        fadeTransition.play();
    }



    private void createPopAnimation(){
        popAnimation = new TranslateTransition();
        popAnimation.setDuration(Duration.seconds(1));

        final double[] ADJUST_FOR_CONTAINER =
                {stackContainer.getLayoutX(), stackContainer.getLayoutY()};

        double endX = shapeCollector.getLayoutX() - ADJUST_FOR_CONTAINER[0] - StackEl.radius;
        double endY = shapeCollector.getLayoutY() - ADJUST_FOR_CONTAINER[1] + StackEl.radius /2;

        popAnimation.setToX(endX);
        popAnimation.setToY(endY);
    }

    private void createPeekAnimation(){
        KeyFrame opacity = new KeyFrame(Duration.seconds(1),
                new KeyValue(stackCoverTop.opacityProperty(), .25));
        KeyFrame removeEffects = new KeyFrame(Duration.seconds(5),
                e -> {
                    new Timeline(new KeyFrame(Duration.seconds(1), new KeyValue(stackCoverTop.opacityProperty(), 1))).play();
                },
                new KeyValue(stackCoverTop.opacityProperty(), .25));
        peekAnimation = new Timeline(opacity, removeEffects);

    }

    public void onPushButtonAction(ActionEvent actionEvent) {
        stack.push(new StackEl((Color) activeCircle.getFill()));
        pushAnimation.setNode(activeCircle);


        double endX = stackContainer.getLayoutX() - activeCircle.getLayoutX() + stackContainer.getWidth() / 2;
        double endY = stackContainer.getLayoutY() - activeCircle.getLayoutY() + stackCoverTop.getHeight() / 2;
        pushAnimation.setToX(endX);
        pushAnimation.setToY(endY);


        pushAnimation.play();
    }



    public void onPeekButtonAction(ActionEvent actionEvent){

        if (!stack.isEmpty()) {
            Circle circle = StackEl.getCircleFromColor(stack.peek().circle);
            pushedCircleHolder.setCenter(circle);

            System.out.println(circle.getCenterX() + ", " + circle.getCenterY());
            peekAnimation.getKeyFrames().add(
                    new KeyFrame(Duration.seconds(5),
                            e -> {
                                pushedCircleHolder.getChildren().remove(circle);
                            },
                            new KeyValue(stackCoverTop.opacityProperty(), .25))
            );
        }
        peekAnimation.play();


    }

    public void onPopActionButton(ActionEvent actionEvent) {
        if (!stack.isEmpty()) {
            Circle circle = StackEl.getCircleFromColor(stack.pop().circle);
            pushedCircleHolder.setCenter(circle);
            popAnimation.setNode(circle);



            popAnimation.play();

        }
    }

    public void onClearActionButton(ActionEvent actionEvent){
//        for(StackEl circle: stack){
//            background.getChildren().remove(circle.circle);
//        }
        stack.clear();
    }

    private Color getRandomColor(Random rand){
        return Color.color(rand.nextDouble(), rand.nextDouble(), rand.nextDouble());
    }


//    private Circle getTopCircle() throws NullPointerException{
//        return stack.peek().circle;
//    }

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
