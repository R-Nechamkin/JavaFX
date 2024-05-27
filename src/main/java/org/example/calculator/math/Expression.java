package org.example.calculator.math;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class Expression {
	private Operation operator;
	private StringBuilder currNumber;
	private List<BigDecimal> numbers;
	
	
	public Expression() {
		operator = null;
		numbers = new LinkedList<>();
		currNumber = new StringBuilder();
	}
	
	public Expression(BigDecimal firstNumber) {
		operator = null;
		numbers = new LinkedList<>();
		currNumber = new StringBuilder(firstNumber.toString());
	}
	
	
	public void addDigit(char digit) {
		if(numbers.size() > 2) {
			throw new TooManyNumbersException();
		}
		
		currNumber.append(digit);
	}
	
	public void finishNumber() {
		numbers.add(new BigDecimal(currNumber.toString()));
		currNumber = new StringBuilder();
	}
	
	/**
	 * 
	 * @param operator
	 * @throws IllegalStateException If the expression already has an operator
	 */
	public void addOperator(Operation operator) {
		if(hasOperator()) {
			throw new IllegalStateException("This expression already has an operator");
		}
		
		this.operator = operator;
	}
	
	public boolean hasOperator() {
		return operator != null;
	}
	
	
	public BigDecimal calculate(){
		if(numbers.size() < 2) {
			throw new TooFewNumbersException();
		}
		if(!hasOperator()) {
			throw new IllegalStateException();
		}
				
		return operator.apply(numbers.get(0), numbers.get(1));
	}
	
	/**
	 * This method returns one of the numbers in the expression.
	 * @param index	Uses 0-based indexing
	 * @return
	 */
	public BigDecimal getNumber(int index) {
		return numbers.get(index);
	}
	
	public Operation getOperator() {
		return operator;
	}
	
	public class TooFewNumbersException extends IllegalStateException{

		/**
		 * 
		 */
		private static final long serialVersionUID = -5664477948510066193L;

		public TooFewNumbersException() {
			super("This expression has too few numbers to calculate.");
		}
		
		public TooFewNumbersException(String message) {
			super(message);
		}
		
	}
	
	public class TooManyNumbersException extends IllegalStateException{

		/**
		 * 
		 */
		private static final long serialVersionUID = -6921483665740640591L;
		
		
		public TooManyNumbersException() {
			super("This expression has too many numbers to calculate.");
		}
		
		public TooManyNumbersException(String message) {
			super(message);
		}
	}
}
