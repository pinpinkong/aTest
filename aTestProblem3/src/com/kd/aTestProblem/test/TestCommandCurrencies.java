package com.kd.aTestProblem.test;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.kd.aTestProblem.java.AppCheckCurrencyChanger;

public class TestCommandCurrencies {

	AppCheckCurrencyChanger app = new AppCheckCurrencyChanger();

	@Before
	public void setCurrencies() {
		app.getDataObject().setCurrenciesData(app.parseCurrencies(app.getCurrencies()));
		app.getDataObject().getCurrenciesRate().put("USD", (double) 1);
		app.getDataObject().getCurrenciesRate().putAll((Map<String, Double>) app.getDataObject().getCurrenciesData().get("rates"));
	}

	@Test
	public void testCommandCurrencies() {
		System.out.println("===========================================================");
		System.out.println("Test commandCurrencies");
		app.commandCurrencies();
		System.out.println("===========================================================");
	}

}
