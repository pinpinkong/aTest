package com.kd.aTestProblem.test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

import com.kd.aTestProblem.java.AppCheckCurrencyChanger;
import com.kd.aTestProblem.java.Main;

public class TestCommandHelp {
	
	AppCheckCurrencyChanger app = new AppCheckCurrencyChanger();

	@Test
	public void testCommandHelp() {
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		final String string1 = "===========================================================";
		final String string2 = "===========================================================";
		final String string3 = "===========================================================";
		final String string4 = "===========================================================";
		final String string5 = "list";
		final String string6 = "===========================================================";
		final String string7 = "===========================================================";
		final String string8 = "===========================================================";
		final String string9 = "===========================================================";
		final String string10 = "===========================================================";
		final String string11 = "===========================================================";
		final String string12 = "===========================================================";

		System.setOut(new PrintStream(outContent));

		app.commandHelp();
		// trim() used to cutoff "line separator" after System.out.println
		// and each line needs to be tested separately
		// assertEquals(string1, outContent.toString().trim());

		// commented because makes all output prints finds themselves null
		// System.setOut(null);
	}

}
