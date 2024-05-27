package org.example.calculator.math;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.*;

import org.junit.jupiter.api.Test;

class OperatorTest {

	@Test
	void testPriorityIsCorrect() {
		PriorityQueue<Operation> heap = new PriorityQueue<>();
		heap.add(Operation.ADD);
		heap.add(Operation.MULTIPLY);
		
		BigDecimal x = new BigDecimal(5);
		BigDecimal y = new BigDecimal(8);
		assertEquals(x.multiply(y), heap.remove().apply(x, y));
	}

}
