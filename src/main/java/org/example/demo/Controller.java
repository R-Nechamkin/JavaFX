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

    private final Random rand = new Random();
    private SequentialTransition pushAnimation;
    private SequentialTransition popAnimation;
    private SequentialTransition peekAnimation;

    private Model<Element> model;
    private Animations animations;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setNewActiveCircle();
    }

    public void setModel(Model<Element> model){
        this.model = model;
        this.animations = new Animations(model);
    }


    void setNewActiveCircle(){
        Color color = getRandomColor(rand);
        activeCircle = Element.getCircleFromColor(color);

        background.getChildren().add(activeCircle);
        activeCircle.setLayoutX(shapeDispenser.getLayoutX() + (11.0/6) * Element.radius);
        activeCircle.setLayoutY(shapeDispenser.getLayoutY() + (11.0/6) * Element.radius);

    }

    public void onPushButtonAction(ActionEvent actionEvent) {
        popAnimation.stop();
        peekAnimation.stop();

        model.addElement(new Element((Color) activeCircle.getFill()));
        pushAnimation.setNode(activeCircle);

        pushAnimation.play();
    }



    public void onPeekButtonAction(ActionEvent actionEvent){
        pushAnimation.stop();
        popAnimation.stop();

        if (!model.isEmpty()) {
            Circle circle = Element.getCircleFromColor(model.getElement().color);
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

        if (!model.isEmpty()) {
            Color color = model.getElement().color;
            model.removeElement();

            Circle circle = Element.getCircleFromColor(color);
            pushedCircleHolder.setCenter(circle);
            popAnimation.setNode(circle);

            popAnimation.play();

        }
    }

    public void onClearActionButton(ActionEvent actionEvent){
        model.clear();
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

    static class Element {
        static double radius = 20;
        Color color;

        static Circle getCircleFromColor(Color color){
            Circle circle = new Circle(Element.radius, color);
            circle.setStrokeWidth(2);
            circle.setStroke(Color.BLACK);
            return circle;
        }

        public Element(Color color) {
            this.color = color;
        }

        public Color getColor(){
            return color;
        }


    }

    public class Animations {
        Model<Element> circleHolder;


        public Animations(Model<Element> model){
            this.circleHolder = model;

            pushAnimation = getPushAnimation();
            popAnimation = getPopAnimation();
            peekAnimation = getPeekAnimation();

        }

        public SequentialTransition getPushAnimation() {

            TranslateTransition move = createMoveCircleTransition();
            FadeTransition fade = createFadeCircleTransition();

            return new SequentialTransition(move, fade);
        }

        private TranslateTransition createMoveCircleTransition(){
            TranslateTransition move = new TranslateTransition();
            move.setDuration(Duration.seconds(1));

            final double[] ADJUST_FOR_CONTAINER =
                    {shapeDispenser.getLayoutX(), shapeDispenser.getLayoutY()};
            double endX = stackContainer.getLayoutX() - ADJUST_FOR_CONTAINER[0] + Element.radius;
            System.out.println(endX);
            double endY = stackContainer.getLayoutY() - ADJUST_FOR_CONTAINER[1] - Element.radius/2;
            System.out.println(endY);

            move.setToX(endX);
            move.setToY(endY);

            move.setOnFinished(e -> {
                activeCircle.toFront();
            });
            return move;
        }

        private FadeTransition createFadeCircleTransition() {
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1));
            fadeTransition.setToValue(0.1);
            fadeTransition.setOnFinished(e -> {
                background.getChildren().remove(activeCircle);
                setNewActiveCircle();
            });
            return fadeTransition;
        }

        public SequentialTransition getPopAnimation(){
            TranslateTransition popAnimation = new TranslateTransition();
            popAnimation.setDuration(Duration.seconds(1));

            final double[] ADJUST_FOR_CONTAINER =
                    {stackContainer.getLayoutX(), stackContainer.getLayoutY()};

            double endX = shapeCollector.getLayoutX() - ADJUST_FOR_CONTAINER[0] - Element.radius;
            double endY = shapeCollector.getLayoutY() - ADJUST_FOR_CONTAINER[1] + Element.radius /2;

            popAnimation.setToX(endX);
            popAnimation.setToY(endY);

            return new SequentialTransition(popAnimation);
        }

        public SequentialTransition getPeekAnimation(){

            final Duration FADE_DURATION = Duration.seconds(1.5);

            FadeTransition startFade = new FadeTransition(FADE_DURATION,  stackCoverTop);
            startFade.setFromValue(1.0);
            startFade.setToValue(.25);

            FadeTransition reverseFade = new FadeTransition(FADE_DURATION, stackCoverTop);
            reverseFade.setFromValue(.25);
            reverseFade.setToValue(1.0);

            PauseTransition pause = new PauseTransition(FADE_DURATION);


            return new SequentialTransition(startFade, pause, reverseFade);
        }
    }
}
