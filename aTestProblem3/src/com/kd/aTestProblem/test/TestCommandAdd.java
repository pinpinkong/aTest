package com.kd.aTestProblem.test;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.kd.aTestProblem.java.AppCheckCurrencyChanger;
import com.kd.aTestProblem.java.DataText;
import com.kd.aTestProblem.java.DataTextImpl;



public class TestCommandAdd {

	AppCheckCurrencyChanger app = new AppCheckCurrencyChanger();

	@Before
	public void setUp() {

		List<DataText> dataText1 = new ArrayList<>();
		List<DataText> dataText2 = new ArrayList<>();
		dataText1.add(new DataTextImpl(22, "USD", "Gorm"));
		dataText1.add(new DataTextImpl(22, "USD", "Gorm"));
		dataText2.add(new DataTextImpl(22, "BRL", "Gorm"));

		app.getDataObject().getProgrammData().put(LocalDate.of(2001, 10, 21), dataText1);
		app.getDataObject().getProgrammData().put(LocalDate.of(2011, 10, 21), dataText2);

		app.getDataObject().setCurrenciesData(app.parseCurrencies(app.getCurrencies()));
		app.getDataObject().getCurrenciesRate().put("USD", (double) 1);
		app.getDataObject().getCurrenciesRate().putAll((Map<String, Double>) app.getDataObject().getCurrenciesData().get("rates"));
	}

	@Test
	public void test() {
		List<String> testData = new ArrayList<>();
		testData.add("add");
		testData.add("2017-12-04");
		testData.add("20");
		testData.add("USD");
		testData.add("Horn");
		app.commandAdd(testData);

		assertEquals("programData size", 3, app.getDataObject().getProgrammData().size());

		app.commandAdd(testData);
		assertEquals("programData size", 3, app.getDataObject().getProgrammData().size());
	}

}
