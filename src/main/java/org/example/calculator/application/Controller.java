package org.example.calculator.application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.example.calculator.application.NumberButton;

import org.example.calculator.math.Expression;
import org.example.calculator.math.Operation;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
	@FXML
	private VBox root;

	@FXML
	private TextArea resultArea;

	@FXML
	private Button button0;
	@FXML
	private Button button1;
	@FXML
	private Button button2;
	@FXML
	private Button button3;
	@FXML
	private Button button4;
	@FXML
	private Button button5;
	@FXML
	private Button button6;
	@FXML
	private Button button7;
	@FXML
	private Button button8;
	@FXML
	private Button button9;
	@FXML
	private Button clearButton;
	@FXML
	private Button equalsButton;
	@FXML
	private Button multiplyButton;
	@FXML
	private Button plusButton;
	@FXML
	private Button minusButton;
	@FXML
	private Button divideButton;
	@FXML
	private GridPane buttonsContainer;


	private NumberButton[] numberButtons;
//	private View view;
	Expression currExpression;

	private boolean somethingWritten;
	
	public Controller() {

		currExpression = new Expression();
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		setUp();
	}
	
	public void setUp() {
		addNumberButtons();
		addFunctionalityToButtons();
	}


	public void addNumberButtons(){

		// Number Buttons Array
		numberButtons = new NumberButton[10];
		for (int i = 0; i < 10; i++) {
			numberButtons[i] = new NumberButton(i);
			numberButtons[i].getStyleClass().addAll("button", "number-button");
		}

		// Adding Number Buttons to GridPane
		buttonsContainer.add(numberButtons[1], 0, 0);
		buttonsContainer.add(numberButtons[2], 1, 0);
		buttonsContainer.add(numberButtons[3], 2, 0);
		buttonsContainer.add(numberButtons[4], 0, 1);
		buttonsContainer.add(numberButtons[5], 1, 1);
		buttonsContainer.add(numberButtons[6], 2, 1);
		buttonsContainer.add(numberButtons[7], 0, 2);
		buttonsContainer.add(numberButtons[8], 1, 2);
		buttonsContainer.add(numberButtons[9], 2, 2);
		buttonsContainer.add(numberButtons[0], 1, 3);

	}
	
	public void addFunctionalityToButtons() {


		if(numberButtons == null || numberButtons[0] == null){
			throw new IllegalStateException("Number buttons have not been added yet.");
		}
        for (int i = 0; i < numberButtons.length; i++) {
			numberButtons[i].setOnAction(this::numberPressed);
		}
        
        clearButton.setOnAction(e -> clear());
        plusButton.setOnAction(e -> operatorButtonPressed(Operation.ADD));
        minusButton.setOnAction(e -> operatorButtonPressed(Operation.SUBTRACT));
        multiplyButton.setOnAction(e -> operatorButtonPressed(Operation.MULTIPLY));
        divideButton.setOnAction(e -> operatorButtonPressed(Operation.DIVIDE));
        
        equalsButton.setOnAction(e -> equalsButtonPressed());
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

	public NumberButton[] getNumberButtons(){
		return numberButtons;
	}

}
