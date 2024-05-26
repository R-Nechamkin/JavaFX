package org.example.dataStructureAnimation;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LearningJavaFX {
    Pane background;

    @BeforeEach
    void setUp(){
        background = setUpBackground();
    }


   Pane setUpBackground(){
        SampleApplication.main(null);
        return SampleApplication.background;
    }
    @Test
    void testLayout(){
        Button b1 = new Button();
        background.getChildren().add(b1);

        assertTrue(background.getChildren().contains(b1));
    }

    @Test
    void whereAreTheCoordinates(){
        System.out.println("Test started");

        Button b1 = new Button("0,0");
        background.getChildren().add(b1);
        System.out.println("Added");

        setLayout(b1, 0,0);
    }

    void setLayout(Node node, double x, double y){
        node.setLayoutX(x);
        node.setLayoutY(y);
    }

    @Test
    void runSample(){
        SampleApplication.main(null);
    }



}