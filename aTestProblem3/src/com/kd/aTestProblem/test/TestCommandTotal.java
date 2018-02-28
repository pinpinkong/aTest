package com.kd.aTestProblem.test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;

import com.kd.aTestProblem.java.AppCheckCurrencyChanger;
import com.kd.aTestProblem.java.DataText;
import com.kd.aTestProblem.java.DataTextImpl;

public class TestCommandTotal {

	AppCheckCurrencyChanger app = new AppCheckCurrencyChanger();

	@Before
	public void setUp() {

		app.setProgrammData(new TreeMap<>());
		List<DataText> dataText1 = new ArrayList<>();
		List<DataTextImpl> dataText2 = new ArrayList<>();
		dataText1.add(new DataTextImpl(22, "USD", "Gorm"));
		dataText1.add(new DataTextImpl(22, "USD", "Gorm"));
		dataText2.add(new DataTextImpl(22, "BRL", "Gorm"));

		app.getProgrammData().put(LocalDate.of(2001, 10, 21), dataText1);
		app.getProgrammData().put(LocalDate.of(2011, 10, 21), dataText1);

		app.setCurrenciesData(app.parseCurrencies(app.getCurrencies()));
		app.setCurrenciesRate(new LinkedHashMap<>());
		app.getCurrenciesRate().put("USD", (double) 1);
		app.getCurrenciesRate().putAll((Map<String, Double>) app.getCurrenciesData().get("rates"));
	}

	@Test
	public void commandTotal() {
		app.commandTotal("total AUD");

	}

}
