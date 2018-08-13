package com.skybulls.app.model;

import com.opencsv.bean.CsvBindByPosition;

public class Stock {
	
	@CsvBindByPosition(position = 0)
	private String dateTime;
	@CsvBindByPosition(position = 1)
	private double rate;
	@CsvBindByPosition(position = 2)
	private double volume;
	
	private double vwma50;
	public double getVwma50() {
		return vwma50;
	}

	public void setVwma50(double vwma50) {
		this.vwma50 = vwma50;
	}

	public double getVwma200() {
		return vwma200;
	}

	public void setVwma200(double vwma200) {
		this.vwma200 = vwma200;
	}

	private double vwma200;
	
	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	
	

}
