package org.example.calculator.Controller;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.example.calculator.application.View;
import org.example.calculator.application.View.NumberButton;


class ControllerTest {
	
	
	Controller controller;
	View view;
	
	@BeforeEach()
	void setup() {
		new AnchorPane();
	}
	
	void simpleSetup() {
		view = new View();
		controller = new Controller(view);
	}

	@Test
	void testNumberPressedOnce() {
		simpleSetup();
		NumberButton button = view.getNumberButtons()[4];
		
		button.fire();
		
		controller.currExpression.finishNumber();
		
		assertEquals(new BigDecimal(4), controller.currExpression.getNumber(0));
	}

}
