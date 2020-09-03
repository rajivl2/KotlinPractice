package com.ford.xapi.kataspractice.practice

import org.junit.Test
import org.junit.Assert.assertEquals;

class OddEvenKotlinTest {
	
	@Test fun testOdd() {
		var oddEven = FindOddEvenKt();
		assertEquals("Odd", oddEven.findOddEven(1));
	}
	
	@Test fun testEven() {
		var oddEven = FindOddEvenKt();
		assertEquals("Even", oddEven.findOddEven(2));
	}
	
	@Test fun testZero() {
		var oddEven = FindOddEvenKt();
		assertEquals("Even", oddEven.findOddEven(0));
	}
}