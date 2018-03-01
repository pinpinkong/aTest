package com.kd.aTestProblem.test;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.kd.aTestProblem.java.AppCheckCurrencyChanger;

public class TestCommandExchangeRates {

	AppCheckCurrencyChanger app = new AppCheckCurrencyChanger();

	@Before
	public void setCurrencies() {
		app.getDataObject().setCurrenciesData(app.parseCurrencies(app.getCurrencies()));
		app.getDataObject().getCurrenciesRate().put("USD", (double) 1);
		app.getDataObject().getCurrenciesRate().putAll((Map<String, Double>) app.getDataObject().getCurrenciesData().get("rates"));
	}

	@Test
	public void testCommandExchangeRates() {
		System.out.println("===========================================================");
		System.out.println("Test commandExchangeRates");
		app.commandExchangeRates();
		System.out.println("===========================================================");
	}

}
