package com.kd.aTestProblem.java;

public class DataTextImpl implements DataText{

	private double spent;
	private String currency;
	private String description;

	public DataTextImpl(double spent, String currency, String description) {
		this.spent = spent;
		this.currency = currency;
		this.description = description;
	}

	public double getSpent() {
		return spent;
	}

	public String getCurrency() {
		return currency;
	}

	public String getProduct() {
		return description;
	}

	@Override
	public String toString() {
		return " " + spent + " " + currency + " " + description;
	}

}
