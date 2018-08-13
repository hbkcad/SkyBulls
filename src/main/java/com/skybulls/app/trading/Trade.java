package com.skybulls.app.trading;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.skybulls.app.model.Stock;

public class Trade {
	String fileloc = "src/main/resources/json.csv";
	
	@SuppressWarnings("unchecked")
	public void trading() {	
	try {
	FileReader file = new FileReader(fileloc);
	CsvToBean<Stock> csvToBean = new CsvToBeanBuilder<Stock>(file)
            .withType(Stock.class)
            .withIgnoreLeadingWhiteSpace(true)
            .build();
	List<Stock> stocks = csvToBean.parse();
	
	
	JSONArray json = new JSONArray();
	int count =0;
	JSONObject jobject = null;
	for(Stock stock : stocks) {
		count++;
		if(count>8) {
			stock.setVwma50(vwma50(stock,json));
		}
	jobject = new JSONObject(stock);
	
	int date = Integer.parseInt(stock.getDateTime().substring(0, 5).replace("/",""));
	System.out.println(date);
	try {
		
		json.put(date,jobject);
		//System.out.println(json);
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	/*if(count>8) {
		int t = count;
		double vwma = Double.parseDouble(json.get(t))*Double.parseDouble((Double) json.get(t));
		json.put(vwma);
	}*/
	
	}
	
	//System.out.println(jobject);
	/*try {
		for(int i =0; i<10;i++) {
			
		}
		System.out.println(json.get(0));
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/
	//System.out.println(json.optString(0));
	
	//TreeMap<String,Double> tmap = new TreeMap<>();
	//List<String> list_volume = new ArrayList<String>(); 
	/*for(Stock stock: stocks) {
	    System.out.println("DateTime : " + stock.getDateTime());
	    System.out.println("Rate : " + stock.getRate());
	    System.out.println("Volume : " + stock.getVolume());
	    System.out.println("==========================");*/
	    //list_volume.add(stock.getDateTime());
	    //tmap.put(stock.getDateTime(),(stock.getRate())*stock.getVolume());
	
	/*int i=0;
	for(Entry<String, Double> entry : tmap.entrySet()) {
		
	}*/
	}catch(FileNotFoundException f) {
		
	}
}
	private double vwma50(Stock stock, JSONArray json) {
		
		return 0;
	}
	
	public static void main(String args[]) {
		Trade trade = new Trade();
		trade.trading();

	}
	}
