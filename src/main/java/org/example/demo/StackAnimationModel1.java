package org.example.demo;

import javafx.animation.*;
import javafx.util.Duration;

public class StackAnimationModel1 implements Model{

    Controller controller;

    public StackAnimationModel1(Controller controller){
        this.controller = controller;
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
                {controller.shapeDispenser.getLayoutX(), controller.shapeDispenser.getLayoutY()};


        double endX = controller.stackContainer.getLayoutX(); //- ADJUST_FOR_CONTAINER[0]
              //  - controller.activeCircle.getLayoutX() + Controller.StackEl.radius;

        double endY = controller.stackContainer.getLayoutY() //- ADJUST_FOR_CONTAINER[1]
                - controller.activeCircle.getLayoutY() - Controller.StackEl.radius /2;
        move.setToX(endX);
        move.setToY(endY);

        move.setOnFinished(e -> {
            controller.activeCircle.toFront();
        });
        return move;
    }

    private FadeTransition createFadeCircleTransition() {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1));
        fadeTransition.setToValue(0.1);
        fadeTransition.setOnFinished(e -> {
            controller.background.getChildren().remove(controller.activeCircle);
            controller.setNewActiveCircle();
        });
        return fadeTransition;
    }

    public SequentialTransition getPopAnimation(){
        TranslateTransition popAnimation = new TranslateTransition();
        popAnimation.setDuration(Duration.seconds(1));

        final double[] ADJUST_FOR_CONTAINER =
                {controller.stackContainer.getLayoutX(), controller.stackContainer.getLayoutY()};

        double endX = controller.shapeCollector.getLayoutX() - ADJUST_FOR_CONTAINER[0] - Controller.StackEl.radius;
        double endY = controller.shapeCollector.getLayoutY() - ADJUST_FOR_CONTAINER[1] + Controller.StackEl.radius /2;

        popAnimation.setToX(endX);
        popAnimation.setToY(endY);

        return new SequentialTransition(popAnimation);
    }

    public SequentialTransition getPeekAnimation(){

        final Duration FADE_DURATION = Duration.seconds(1.5);

        FadeTransition startFade = new FadeTransition(FADE_DURATION,  controller.stackCoverTop);
        startFade.setFromValue(1.0);
        startFade.setToValue(.25);

        FadeTransition reverseFade = new FadeTransition(FADE_DURATION, controller.stackCoverTop);
        reverseFade.setFromValue(.25);
        reverseFade.setToValue(1.0);

        PauseTransition pause = new PauseTransition(FADE_DURATION);


        return new SequentialTransition(startFade, pause, reverseFade);
    }
}
