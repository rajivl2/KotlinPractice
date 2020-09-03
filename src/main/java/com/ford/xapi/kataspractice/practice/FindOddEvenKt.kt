package com.ford.xapi.kataspractice.practice

class FindOddEvenKt {
	
	fun findOddEven (num : Int) : String {
		if (num % 2 == 0) {
			return "Even"
		} else {
			return "Odd"
		}
	}
}