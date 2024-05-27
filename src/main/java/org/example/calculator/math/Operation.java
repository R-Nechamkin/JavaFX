package org.example.calculator.math;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.BinaryOperator;

public class Operation implements Comparable<Operation> {
	public static final int PRECISION = 8;
	
	public static Operation ADD = new Operation((x, y) -> x.add(y), 4, "+");
	public static Operation SUBTRACT = new Operation((x, y) -> x.subtract(y), 4, "-");
	public static Operation MULTIPLY = new Operation((x, y) -> x.multiply(y), 3, "*");
	public static Operation DIVIDE = new Operation((x, y) -> x.divide(y, PRECISION, RoundingMode.HALF_UP), 3, "/");
	

	private String stringRep;
	private BinaryOperator<BigDecimal> function;
	private int priority;
	
	Operation(BinaryOperator<BigDecimal> function, int priority, String stringRep) {
		this.function = function;
		this.priority = priority;
		this.stringRep = stringRep;
	}
	
	public BigDecimal apply(BigDecimal x, BigDecimal y) {
		return function.apply(x, y);
	}
	
	@Override
	public int compareTo(Operation o) {
		return this.priority - o.priority;
		
	}
	
	@Override
	public String toString() {
		return stringRep;
	}
}
