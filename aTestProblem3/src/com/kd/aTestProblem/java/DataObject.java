package com.kd.aTestProblem.java;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface DataObject {

	public Map<String, Object> getCurrenciesData();

	public void setCurrenciesData(Map<String, Object> currenciesData);

	public Map<String, Double> getCurrenciesRate();

	public void setCurrenciesRate(Map<String, Double> currenciesRate);

	public Map<LocalDate, List<DataText>> getProgrammData();

	public void setProgrammData(Map<LocalDate, List<DataText>> programmData);

}
