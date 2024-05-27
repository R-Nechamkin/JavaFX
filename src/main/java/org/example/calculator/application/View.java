package org.example.calculator.application;

import java.util.ArrayList;
import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;

public class View {
	Scene scene;
	NumberButton[] numberButtons;
	private Button clearButton;
	private Button equalsButton;
	private Button multiply;
	private Button plus;
	private Button minus;
	private Button divide;
	private VBox root;
	private List<Button> otherButtons;
	
	public View() {
		setUp();
	}

	
	public void setUp() {
        root = new VBox();
        root.setPrefSize(296.0, 375.0);

        TextArea resultArea = new TextArea("0");
        resultArea.setEditable(false);
        resultArea.setPrefSize(285.0, 95.0);
        resultArea.getStyleClass().add("resultArea");
        root.getChildren().add(resultArea);

        GridPane gridPane = new GridPane();
        gridPane.setPrefSize(309.0, 282.0);

        // Column Constraints
        for (int i = 0; i < 4; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setHgrow(i == 3 ? null : javafx.scene.layout.Priority.SOMETIMES);
            column.setMinWidth(10.0);
            column.setPrefWidth(100.0);
            gridPane.getColumnConstraints().add(column);
        }

        // Row Constraints
        for (int i = 0; i < 4; i++) {
            RowConstraints row = new RowConstraints();
            row.setVgrow(i == 3 ? null : javafx.scene.layout.Priority.SOMETIMES);
            row.setMinHeight(30.0);
            row.setPrefHeight(30.0);
            gridPane.getRowConstraints().add(row);
        }

        // Number Buttons Array
        numberButtons = new NumberButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new NumberButton(i);
            numberButtons[i].getStyleClass().addAll("button", "number-button");
        }

        // Adding Number Buttons to GridPane
        gridPane.add(numberButtons[1], 0, 0);
        gridPane.add(numberButtons[2], 1, 0);
        gridPane.add(numberButtons[3], 2, 0);
        gridPane.add(numberButtons[4], 0, 1);
        gridPane.add(numberButtons[5], 1, 1);
        gridPane.add(numberButtons[6], 2, 1);
        gridPane.add(numberButtons[7], 0, 2);
        gridPane.add(numberButtons[8], 1, 2);
        gridPane.add(numberButtons[9], 2, 2);
        gridPane.add(numberButtons[0], 1, 3);

        otherButtons = new ArrayList<>();
        
        clearButton = new Button("Clear");
        gridPane.add(clearButton, 0, 3);
        otherButtons.add(clearButton);

        equalsButton = new Button("=");
        gridPane.add(equalsButton, 2, 3);
        otherButtons.add(equalsButton);

        multiply = new Button("*");
        gridPane.add(multiply, 3, 2);
        otherButtons.add(multiply);
        
        plus = new Button("+");
        gridPane.add(plus, 3, 0);
        otherButtons.add(plus);

        minus = new Button("-");
        gridPane.add(minus, 3, 1);
        otherButtons.add(minus);
        
        divide = new Button("/");
        gridPane.add(divide, 3, 3);
        otherButtons.add(divide);
        
        for(Button b: otherButtons) {
        	b.getStyleClass().addAll("button", "other-button");
       
        }

        root.getChildren().add(gridPane);

        scene = new Scene(root);
	}
	
	
	
	
	
	public Scene getScene() {
		return scene;
	}
	
	public NumberButton[] getNumberButtons(){
		return numberButtons;
	}
	
	
	
	public Button getClearButton() {
		return clearButton;
	}


	public Button getEqualsButton() {
		return equalsButton;
	}


	public Button getMultiply() {
		return multiply;
	}


	public Button getPlus() {
		return plus;
	}


	public Button getMinus() {
		return minus;
	}


	public Button getDivide() {
		return divide;
	}


	public VBox getRoot() {
		return root;
	}


	


	public class NumberButton extends Button{
		int number;
		
		public NumberButton(int number) {
			super(String.valueOf(number));
			this.number = number;
		}
		
		public int getNumber() {
			return number;
		}
		
		public char getCharNumber() {
			return getStringNumber().charAt(0);
		}
		
		
		public String getStringNumber() {
			return String.valueOf(number);
		}
	
	}

}
