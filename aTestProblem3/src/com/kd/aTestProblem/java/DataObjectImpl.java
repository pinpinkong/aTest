package com.kd.aTestProblem.java;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DataObjectImpl implements DataObject {

	private Map<String, Object> currenciesData = new HashMap<>();
	private Map<String, Double> currenciesRate = new LinkedHashMap<>();
	private Map<LocalDate, List<DataText>> programmData = new TreeMap<>();

	public Map<String, Object> getCurrenciesData() {
		return currenciesData;
	}

	public void setCurrenciesData(Map<String, Object> currenciesData) {
		this.currenciesData = currenciesData;
	}

	public Map<String, Double> getCurrenciesRate() {
		return currenciesRate;
	}

	public void setCurrenciesRate(Map<String, Double> currenciesRate) {
		this.currenciesRate = currenciesRate;
	}

	public Map<LocalDate, List<DataText>> getProgrammData() {
		return programmData;
	}

	public void setProgrammData(Map<LocalDate, List<DataText>> programmData) {
		this.programmData = programmData;
	}

	@Override
	public String toString() {
		return "DataHolder [currenciesData=" + currenciesData + ", currenciesRate=" + currenciesRate + ", programmData="
				+ programmData + "]";
	}

}
