package org.example.calculator.math;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.example.calculator.math.Expression.TooFewNumbersException;

class ExpressionTest {
	
	private static Expression e;
	
	@BeforeEach
	void setUp() {
		e = new Expression();
	}

	@Test
	void simpleCase() {
		
		e.addDigit('5');
		e.addDigit('2');
		e.finishNumber();
		
		e.addOperator(Operation.ADD);
		
		e.addDigit('2');
		e.finishNumber();
		
		assertEquals(new BigDecimal(52 +2), e.calculate());
	}
	
	@Test
	void TwoOperatorsThrowsAnException() {
		e.addOperator(Operation.DIVIDE);
		assertThrows(Exception.class, () -> e.addOperator(Operation.MULTIPLY));
	}
	
	@Test
	void CalculateThrowsExceptionWhenThereIsOnlyOneNumber() {
		e.addDigit('5');
		e.addDigit('2');
		e.finishNumber();
		
		e.addOperator(Operation.ADD);
		assertThrows(TooFewNumbersException.class, () -> e.calculate());
	}
	
	@Test
	void CalculateThrowsExceptionWhenThereIsNoNumbers() {
		e.addOperator(Operation.ADD);
		assertThrows(TooFewNumbersException.class, () -> e.calculate());
	}
	
	@Test
	void CalculateThrowsExceptionWhenThereIsNoOperation() {
		e.addDigit('5');
		e.addDigit('2');
		e.finishNumber();
				
		e.addDigit('2');
		e.finishNumber();
		assertThrows(IllegalStateException.class, () -> e.calculate());
	}
	

}
