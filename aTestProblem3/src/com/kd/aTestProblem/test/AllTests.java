package com.kd.aTestProblem.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

//Generally a unit test is intended to exercise the public interface of a class or unit. 
//Therefore, private methods are implementation detail that you would not expect to test explicitly.
@RunWith(Suite.class)
@SuiteClasses({ TestDataText.class, TestMainUtilities.class, TestCommandHelp.class, TestCommandList.class,
		TestCommandTotal.class, TestCommandClear.class, TestCommandCurrencies.class, TestCommandExchangeRates.class,
		TestCommandAdd.class })
public class AllTests {

}

// From this article: Testing Private Methods with JUnit and SuiteRunner (Bill
// Venners) http://www.artima.com/suiterunner/privateP.html, you basically have
// 4 options:
//
// Don't test private methods.
// Give the methods package access.
// Use a nested test class.
// Use reflection.