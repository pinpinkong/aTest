package com.kd.aTestProblem.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.kd.aTestProblem.java.DataText;
import com.kd.aTestProblem.java.DataTextImpl;

public class TestDataText {

	@Test
	public void testConstructorGettersToString() {
		final double spent = 34.05;
		final String currency = "AUD";
		final String description = "Jar";
		DataText dataText = new DataTextImpl(spent, currency,description);
		
		assertEquals(34.05, dataText.getSpent(), 0.0);
		assertEquals("AUD", dataText.getCurrency());
		assertEquals("Jar", dataText.getProduct());
		assertEquals(spent, dataText.getSpent(), 0.0);
		assertEquals(currency, dataText.getCurrency());
		assertEquals(description, dataText.getProduct());
		
		assertEquals(" 34.05 AUD Jar", dataText.toString());
		
		//fail("Not yet implemented");
	}
}
