package com.kd.aTestProblem.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AppCheckCurrencyChanger implements AppStarter {
	private final String urlPath = "http://api.fixer.io/latest?base=USD";
	private boolean working = true;
	private Map<String, Object> currenciesData;
	private Map<String, Double> currenciesRate;
	private Map<LocalDate, List<DataText>> programmData;

	public String reader() {
		String data = "";
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(isr);
		try {
			data = reader.readLine();
			System.out.println(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

	public void appRun() {
		initialize();
		String data;
		while (working) {

			data = reader();
			if (isCommand(data)) {
				String command = cutCommand(data);

				switch (command) {

				case "add":
					commandAdd(addParser(data));
					break;

				case "list":
					commandList();
					break;

				case "clear":
					commandClear(data);
					break;

				case "total":
					commandTotal(data);
					break;

				case "currencies":
					commandCurrencies();
					break;

				case "exchangeRates":
					commandExchangeRates();
					break;

				case "help":
					commandHelp();
					break;

				case "exit":
					working = false;
					break;

				}
			}
		}
	}

	public String cutCommand(String data) {
		String[] temp = data.split(" ");
		String command = "";
		if (temp.length > 0) {
			command = temp[0];
		}
		return command;
	}

	public List<String> addParser(String data) {
		List<String> dataArray = new ArrayList<>();
		List<String> tempArray = Arrays.asList(data.split(" "));

		if (tempArray.size() >= 5 && isValidDate(tempArray.get(1)) && isDouble(tempArray.get(2))
				&& isCurrency(tempArray.get(3)) && isCorrectLength(tempArray.get(4))) {
			if (tempArray.size() > 5) {

				String tempString = "";
				for (int i = 4; i < tempArray.size(); i++) {
					tempString += tempArray.get(i) + " ";
				}
				if (isCorrectLength(tempString)) {
					dataArray.add(tempArray.get(0));
					dataArray.add(tempArray.get(1));
					dataArray.add(tempArray.get(2));
					dataArray.add(tempArray.get(3));
					dataArray.add(tempString.trim());
				}
			} else {
				dataArray = tempArray;
			}
			return dataArray;
		}
		System.out.println("Error: wrong add format: " + data);
		System.out.println("Please, use correct format: add 2017-04-25 12 USD Jogurt");
		return new ArrayList<String>();
	}

	public void commandAdd(List<String> data) {
		if (data.size() > 0) {
			List<String> dateList = new ArrayList<>(Arrays.asList(data.get(1).split("-")));
			
			int year = Integer.parseInt(dateList.get(0));
			int month = Integer.parseInt(dateList.get(1));
			int dayOfMonth = Integer.parseInt(dateList.get(2));

			LocalDate date = LocalDate.of(year, month, dayOfMonth);

			DataText text = new DataTextImpl(Double.parseDouble(data.get(2)), data.get(3), data.get(4));

			if (programmData.containsKey(date)) {
				programmData.get(date).add(text);
				System.out.println(date);
				for (DataText dataText : programmData.get(date)) {
					System.out.println("\t" + dataText);
				}
			} else {
				List<DataText> textArray = new ArrayList<>();
				textArray.add(text);
				programmData.put(date, textArray);
				System.out.println(date);
				System.out.println("\t" + text);
			}
		}
	}

	public void commandList() {
		System.out.println("===========================================================");
		for (Entry<LocalDate, List<DataText>> entry : programmData.entrySet()) {

			System.out.println(entry.getKey());
			for (DataText text : entry.getValue()) {
				System.out.println("\t" + text);
			}
		}
		System.out.println("===========================================================");
	}

	public void commandClear(String data) {
		List<String> dataArray = new ArrayList<>();
		dataArray = Arrays.asList(data.split(" "));
		if (dataArray.size() == 2 && isValidDate(dataArray.get(1))) {
			List<String> dateList = new ArrayList<>(Arrays.asList(dataArray.get(1).split("-")));
			LocalDate date = LocalDate.of(Integer.parseInt(dateList.get(0)), Integer.parseInt(dateList.get(1)),
					Integer.parseInt(dateList.get(2)));
			if (programmData.containsKey(date)) {
				programmData.remove(date);
				System.out.println(date + " successfully removed");
			} else {
				System.out.println("Can't find " + date + " in the list");
			}

		} else {
			System.out.println("Error: wrong clear format " + data);
			System.out.println("Please, use correct format: clear 2017-10-22");
		}
	}

	public void commandTotal(String data) {
		List<String> dataArray = new ArrayList<>(Arrays.asList(data.split(" ")));
		if (dataArray.size() == 2) {
			double total = 0;
			String currency = dataArray.get(1);
			if (isCurrency(currency)) {
				for (Entry<LocalDate, List<DataText>> entry : programmData.entrySet()) {
					List<DataText> listData = entry.getValue();
					for (DataText dataText : listData) {
						if (dataText.getSpent() > 0) {
							if (dataText.getCurrency().equals("USD")) {
								total += dataText.getSpent();
							} else {
								double tempValue = currenciesRate.get(dataText.getCurrency());

								total += dataText.getSpent() / tempValue;
							}
						}
					}
				}
				if (!currency.equals("USD")) {
					double tempValue = currenciesRate.get(currency);

					total = total * tempValue;
				}

				String result = "0";
				if (total != 0) {
					DecimalFormat df = new DecimalFormat("#.##");
					result = df.format(total);
				}
				System.out.println(result + " " + currency);
			}
		} else {
			System.out.println("Error: wrong total format: " + data);
			System.out.println("Please, use correct format: total PLN");
		}
	}

	public void commandCurrencies() {
		for (Entry<String, Double> entry : currenciesRate.entrySet()) {
			System.out.print(entry.getKey() + " ");
		}
	}

	public void commandExchangeRates() {
		for (Entry<String, Double> entry : currenciesRate.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
	}

	public void commandHelp() {
		System.out.println("===========================================================");
		System.out.println("Commands: add, list, clear, total, currencies, exchangeRates, help, exit");
		System.out.println("Correct commands format example");
		System.out.println("add 2017-04-25 12 USD Jogurt");
		System.out.println("list");
		System.out.println("clear 2017-04-25");
		System.out.println("total PLN");
		System.out.println("currencies");
		System.out.println("exchangeRates");
		System.out.println("help");
		System.out.println("exit");
		System.out.println("===========================================================");
	}

	public String getCurrencies() {
		String result = "";

		HttpURLConnection httpURLConnection = null;
		InputStreamReader reader = null;

		try {
			URL url = new URL(urlPath);
			httpURLConnection = (HttpURLConnection) url.openConnection();

			reader = new InputStreamReader(httpURLConnection.getInputStream());

			int inputData = reader.read();

			while (inputData > -1) {
				char current = (char) inputData;
				result += current;
				inputData = reader.read();
			}
			reader.close();
			// System.out.println("Result string " + result);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (httpURLConnection != null) {
				httpURLConnection.disconnect();
			}
		}
		return result;
	}

	public Map<String, Object> parseCurrencies(String currencies) {
		Map<String, Object> currenciesData = new HashMap<>();

		ObjectMapper objectMapper = new ObjectMapper();

		try {
			Map<String, Object> mapObject = objectMapper.readValue(currencies,
					new TypeReference<Map<String, Object>>() {
					});
			Map<String, Integer> rates = (Map<String, Integer>) mapObject.get("rates");

			currenciesData.put("base", mapObject.get("base"));
			currenciesData.put("date", mapObject.get("date"));
			currenciesData.put("rates", mapObject.get("rates"));

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return currenciesData;
	}

	public boolean isCommand(String data) {
		boolean result = false;
		if (data.startsWith("add ") || data.equals("list") || data.startsWith("clear ") || data.startsWith("total ")
				|| data.equals("currencies") || data.equals("exchangeRates") || data.equals("help")
				|| data.equals("exit")) {
			result = true;
		} else {
			System.out.println("Error: wrong format or no such command: " + data);
			System.out.println("Type help for list of proper commands format");
		}
		return result;
	}

	public boolean isInteger(String number) {
		try {
			Integer.parseInt(number);
		} catch (NumberFormatException e) {
			return false;
		} catch (NullPointerException e) {
			return false;
		}
		return true;
	}

	public boolean isDouble(String number) {
		try {
			Double.parseDouble(number);
		} catch (NumberFormatException e) {
			return false;
		} catch (NullPointerException e) {
			return false;
		}
		return true;
	}

	public boolean isValidDate(String date) {

		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate thisDate = LocalDate.parse(date, formatter);
			System.out.println(date + " : " + thisDate);
			if (thisDate.getYear() >= 2000 && thisDate.getYear() <= 2050) {
				return true;
			}

		} catch (Exception e) {
			System.out.println(date + " : wrong date");
			// e.printStackTrace();
		}
		return false;
	}

	public boolean isCurrency(String currency) {
		if (currenciesRate.containsKey(currency)) {
			return true;
		} else {
			System.out.println("Error: Currency invalid, please choose next time one of ");
			for (Entry<String, Double> entry : currenciesRate.entrySet()) {
				System.out.print(entry.getKey() + " ");
			}
			System.out.println();
		}

		return false;
	}

	public boolean isCorrectLength(String product) {
		if (product.length() <= 30) {
			return true;
		} else {
			System.out.println("Error: wrong products description format: " + product);
			System.out.println("Please, don't choose products description longer than 30 symbols");
		}
		return false;
	}

	public void initialize() {		
		currenciesData = parseCurrencies(getCurrencies());
		currenciesRate = new LinkedHashMap<>();
		programmData = new TreeMap<>();

		currenciesRate.put("USD", (double) 1);
		currenciesRate.putAll((Map<String, Double>) currenciesData.get("rates"));
		
		System.out.println("App is running. type help for list of commands");
	}

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
}
