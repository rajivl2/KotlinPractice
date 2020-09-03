package com.ford.xapi.kataspractice.practice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class OddEvenTest {

	@Test
	public void testEvenNumber(){
		FindOddEven oe = new FindOddEven();
		assertEquals("It's Even", oe.isEvenOrOdd(2));
	}
	
	@Test
	public void testOddNumber(){
		FindOddEven oe = new FindOddEven();
		assertEquals("It's Odd", oe.isEvenOrOdd(3));
	}
	
	@Test
	public void testZero(){
		FindOddEven oe = new FindOddEven();
		assertEquals("It's Even", oe.isEvenOrOdd(0));
	}
	
}
