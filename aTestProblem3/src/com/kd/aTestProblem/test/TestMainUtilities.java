package com.kd.aTestProblem.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.kd.aTestProblem.java.AppCheckCurrencyChanger;

public class TestMainUtilities {

	AppCheckCurrencyChanger app = new AppCheckCurrencyChanger();

	@Test
	public void testCutCommand() {
		assertEquals("", app.cutCommand(""));
		assertEquals("", app.cutCommand(" "));

		assertEquals("add", app.cutCommand("add"));
		assertEquals("add", app.cutCommand("add "));
		assertEquals("add", app.cutCommand("add dsdasd"));
		assertEquals("addsadasdasd", app.cutCommand("addsadasdasd"));

		assertEquals("list", app.cutCommand("list"));
		assertEquals("list", app.cutCommand("list "));
		assertEquals("list", app.cutCommand("list lsadas"));
		assertEquals("listlsadas", app.cutCommand("listlsadas"));
	}

	@Test
	public void testIsCommand() {
		final String correctCommand1 = "add ";
		final String correctCommand2 = "list";
		final String correctCommand3 = "clear ";
		final String correctCommand4 = "total ";
		final String correctCommand5 = "currencies";
		final String correctCommand6 = "exchangeRates";
		final String correctCommand7 = "help";
		final String correctCommand8 = "exit";
		final String incorrectCommand1 = "add";
		final String incorrectCommand2 = "addd";
		final String incorrectCommand3 = "addsdas";

		assertEquals(correctCommand1, true, app.isCommand(correctCommand1));
		assertEquals(correctCommand2, true, app.isCommand(correctCommand2));
		assertEquals(correctCommand3, true, app.isCommand(correctCommand3));
		assertEquals(correctCommand4, true, app.isCommand(correctCommand4));
		assertEquals(correctCommand5, true, app.isCommand(correctCommand5));
		assertEquals(correctCommand6, true, app.isCommand(correctCommand6));
		assertEquals(correctCommand7, true, app.isCommand(correctCommand7));
		assertEquals(correctCommand8, true, app.isCommand(correctCommand8));

		assertEquals(incorrectCommand1, false, app.isCommand(incorrectCommand1));
		assertEquals(incorrectCommand2, false, app.isCommand(incorrectCommand2));
		assertEquals(incorrectCommand3, false, app.isCommand(incorrectCommand3));
	}

	@Test
	public void testIsInteger() {
		final String correctInt = "22";
		final String incorrectInt1 = "a22";
		final String incorrectInt2 = "a";
		final String incorrectInt3 = " ";
		final String incorrectInt4 = "";
		final String incorrectInt5 = null;

		assertEquals("22", true, app.isInteger(correctInt));
		assertEquals("a22", false, app.isInteger(incorrectInt1));
		assertEquals("a", false, app.isInteger(incorrectInt2));
		assertEquals(" ", false, app.isInteger(incorrectInt3));
		assertEquals("", false, app.isInteger(incorrectInt4));
		assertEquals("null1", false, app.isInteger(incorrectInt5));
		assertEquals("null2", false, app.isInteger(null));
	}

	@Test
	public void testIsDouble() {
		final String correctInt1 = "22";
		final String correctInt2 = "22.";
		final String correctInt3 = "22.2";
		final String incorrectInt1 = "a22";
		final String incorrectInt2 = "a";
		final String incorrectInt3 = " ";
		final String incorrectInt4 = "";
		final String incorrectInt5 = null;

		assertEquals("22", true, app.isDouble(correctInt1));
		assertEquals("22.", true, app.isDouble(correctInt2));
		assertEquals("22.1", true, app.isDouble(correctInt3));
		assertEquals("a22", false, app.isDouble(incorrectInt1));
		assertEquals("a", false, app.isDouble(incorrectInt2));
		assertEquals(" ", false, app.isDouble(incorrectInt3));
		assertEquals("", false, app.isDouble(incorrectInt4));
		assertEquals("null1", false, app.isDouble(incorrectInt5));
		assertEquals("null2", false, app.isDouble(null));
	}

	@Test
	public void testIsValidDate() {
		final String correctDate1 = "2017-05-25";
		final String correctDate2 = "2017-5-25";
		final String correctDate3 = "2016-02-29";
		final String correctDate4 = "2015-02-28";
		final String incorrectDate1 = "1999-05-25";
		final String incorrectDate2 = "2051-05-25";
		final String incorrectDate3 = "2017/05/25";
		final String incorrectDate4 = "201gfdfg25";
		final String incorrectDate5 = "2015-2-29";

		assertEquals("2017-05-25", true, app.isValidDate(correctDate1));
		assertEquals("2017-5-25", false, app.isValidDate(correctDate2));
		assertEquals("2016-02-29", true, app.isValidDate(correctDate3));
		assertEquals("2015-02-28", true, app.isValidDate(correctDate4));

		assertEquals("1999-05-25", false, app.isValidDate(incorrectDate1));
		assertEquals("2051-05-25", false, app.isValidDate(incorrectDate2));
		assertEquals("2017/05/25", false, app.isValidDate(incorrectDate3));
		assertEquals("201gfdfg25", false, app.isValidDate(incorrectDate4));
		assertEquals("201gfdfg25", false, app.isValidDate(incorrectDate4));
		assertEquals("2015-2-29", false, app.isValidDate(incorrectDate5));
	}

	@Test
	public void testIsCurrency() {
		final String correctCurrency = "AUD";
		final String incorrectCurrency1 = "aud";
		final String incorrectCurrency2 = "dfsdffds";

		app.getDataObject().setCurrenciesData(app.parseCurrencies(app.getCurrencies()));
		app.getDataObject().getCurrenciesRate().put("USD", (double) 1);
		app.getDataObject().getCurrenciesRate().putAll((Map<String, Double>) app.getDataObject().getCurrenciesData().get("rates"));

		assertEquals("correct", true, app.isCurrency(correctCurrency));
		assertEquals("incorrect", false, app.isCurrency(incorrectCurrency1));
		assertEquals("incorrect", false, app.isCurrency(incorrectCurrency2));
	}

	@Test
	public void testAddParser() {
		app.getDataObject().setCurrenciesData(app.parseCurrencies(app.getCurrencies()));
		app.getDataObject().getCurrenciesRate().put("USD", (double) 1);
		app.getDataObject().getCurrenciesRate().putAll((Map<String, Double>) app.getDataObject().getCurrenciesData().get("rates"));

		final String correctInput1 = "add 2018-01-01 100 AUD milk";
		final String correctInput2 = "add 2018-01-01 150 AUD milk cream butter";
		final String incorrectInput1 = "add 2018/01/01 100 AUD milk";
		final String incorrectInput2 = "add 2018-01-01 150 AUD milk cream butterffffffffffffffffffffffffffffffffffffffffff";

		final List<String> correctDataArray1 = new ArrayList<>();
		correctDataArray1.add("add");
		correctDataArray1.add("2018-01-01");
		correctDataArray1.add("100");
		correctDataArray1.add("AUD");
		correctDataArray1.add("milk");

		final List<String> correctDataArray2 = new ArrayList<>();
		correctDataArray2.add("add");
		correctDataArray2.add("2018-01-01");
		correctDataArray2.add("150");
		correctDataArray2.add("AUD");
		correctDataArray2.add("milk cream butter");

		List<String> correctDataInput1 = app.addParser(correctInput1);
		List<String> correctDataInput2 = app.addParser(correctInput2);
		List<String> incorrectDataInput1 = app.addParser(incorrectInput1);
		List<String> incorrectDataInput2 = app.addParser(incorrectInput2);

		assertEquals("addParserCorrectArray1", correctDataArray1, correctDataInput1);
		assertEquals("addParserCorrectArray2", correctDataArray2, correctDataInput2);

		assertEquals("addParserInCorrectArray1", new ArrayList<String>(), incorrectDataInput1);
		assertEquals("addParserInCorrectArray2", new ArrayList<String>(), incorrectDataInput2);
	}

	@Test
	public void testIsCorrectLength() {
		final String correctString = "Mary had a little lamb";
		final String toLongString = "asddddddddddddddddddddddddddddddddddddddddddsadasd";

		assertEquals(true, app.isCorrectLength(correctString));
		assertEquals(false, app.isCorrectLength(toLongString));
	}

}
