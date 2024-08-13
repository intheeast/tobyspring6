package com.intheeast.springframe.learningtest.template;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalcSumTest {
	Calculator calculator;
	String numFilepath;
	
	@BeforeEach
	public void setUp() {
		this.calculator = new Calculator();
		// '/' 의 의미는
		// C:\development\Workspace\codes\spring_legacy\tobyspring6\vol1\ch3\3.6.1\src\test\resources
		this.numFilepath = getClass().getResource("/numbers.txt").getPath();
	}
	
	@Test public void sumOfNumbers() throws IOException {
		assertEquals(calculator.calcSum(this.numFilepath), 10);
	}
	
	@Test public void multiplyOfNumbers() throws IOException {
		assertEquals(calculator.calcMultiply(this.numFilepath), 24);
	}
	
	@Test public void concatenateStrings() throws IOException {
		assertEquals(calculator.concatenate(this.numFilepath), "1234");
	}

}
