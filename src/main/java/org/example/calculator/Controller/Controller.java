package org.example.calculator.Controller;

import java.math.BigDecimal;

import org.example.calculator.application.View;
import org.example.calculator.application.View.NumberButton;
import javafx.event.*;
import javafx.scene.control.TextArea;
import org.example.calculator.math.Expression;
import org.example.calculator.math.Operation;

public class Controller {
	private NumberButton[] numberButtons;
	private View view;
	Expression currExpression;
	private TextArea resultArea;
	private boolean somethingWritten;
	
	public Controller(View view) {
		this.view = view;	
		
		numberButtons = view.getNumberButtons();
		currExpression = new Expression();
		resultArea = (TextArea) view.getRoot().getChildren().get(0);
				
		setUp();
	}
	
	
	public void setUp() {
		addFunctionalityToButtons();
	}
	
	public void addFunctionalityToButtons() {
        for (int i = 0; i < numberButtons.length; i++) {
			numberButtons[i].setOnAction(e -> numberPressed(e));
		}
        
        view.getClearButton().setOnAction(e -> clear());
        view.getPlus().setOnAction(e -> operatorButtonPressed(Operation.ADD));
        view.getMinus().setOnAction(e -> operatorButtonPressed(Operation.SUBTRACT));
        view.getMultiply().setOnAction(e -> operatorButtonPressed(Operation.MULTIPLY));
        view.getDivide().setOnAction(e -> operatorButtonPressed(Operation.DIVIDE));
        
        view.getEqualsButton().setOnAction(e -> equalsButtonPressed());
	}
	
	public void numberPressed(ActionEvent event) {
		NumberButton button = (NumberButton) event.getSource();
		currExpression.addDigit(button.getCharNumber());
		
		System.out.print(button.getNumber());
		
		if(!somethingWritten) {
			resultArea.clear();
		}
		resultArea.appendText(button.getStringNumber());
		somethingWritten = true;
	}
	
	public void operatorButtonPressed(Operation o) {
		try {
			addOperation(o);
			System.out.print(" " + o + " ");
			resultArea.appendText(o.toString());
		} catch (IllegalStateException e) {
			// If there is already an operator, we'll just ignore this
		}
	}
	
	private void addOperation(Operation o) throws IllegalStateException{
		currExpression.addOperator(o);
		currExpression.finishNumber();
	
	}
	
	
	public void clear() {
		currExpression = new Expression();
		System.out.println(" Cleared");
		resultArea.setText("0");
		somethingWritten = false;
	}
	
	public void equalsButtonPressed() {
		currExpression.finishNumber();
		BigDecimal answer = currExpression.calculate();
		printAnswer(answer);
		System.out.println();
		startOffNextExpression(answer);
	}
	
	private void printAnswer(BigDecimal answer) {
		System.out.print(" = " + answer);
		resultArea.setText(answer.toString());
	}
	
	private void startOffNextExpression(BigDecimal previousAnswer) {
		currExpression = new Expression(previousAnswer);
		System.out.print(previousAnswer);
	}
}
