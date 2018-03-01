package com.kd.aTestProblem.test;

import java.time.LocalDate;
import java.util.ArrayList;
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

		app.getDataObject().setProgrammData(new TreeMap<>());
		List<DataText> dataText1 = new ArrayList<>();
		List<DataTextImpl> dataText2 = new ArrayList<>();
		dataText1.add(new DataTextImpl(22, "USD", "Gorm"));
		dataText1.add(new DataTextImpl(22, "USD", "Gorm"));
		dataText2.add(new DataTextImpl(22, "BRL", "Gorm"));

		app.getDataObject().getProgrammData().put(LocalDate.of(2001, 10, 21), dataText1);
		app.getDataObject().getProgrammData().put(LocalDate.of(2011, 10, 21), dataText1);

		app.getDataObject().setCurrenciesData(app.parseCurrencies(app.getCurrencies()));
		app.getDataObject().getCurrenciesRate().put("USD", (double) 1);
		app.getDataObject().getCurrenciesRate().putAll((Map<String, Double>) app.getDataObject().getCurrenciesData().get("rates"));
	}

	@Test
	public void commandTotal() {
		app.commandTotal("total AUD");

	}

}
