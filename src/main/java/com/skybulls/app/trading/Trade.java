package com.skybulls.app.trading;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.skybulls.app.model.Stock;

public class Trade {
	String fileloc = "src/main/resources/AXISBANK.csv";
	
	public void trading() {	
	try {
	FileReader file = new FileReader(fileloc);
	CsvToBean<Stock> csvToBean = new CsvToBeanBuilder<Stock>(file)
            .withType(Stock.class)
            .withIgnoreLeadingWhiteSpace(true)
            .build();
	List<Stock> stocks = csvToBean.parse();
	
	double arrprod[] = new double[30000];
	double arrsum[] = new double[32000];
	double sum = 0;
	double prod = 0;
	int i =0;
	for(Stock s : stocks) {
		sum = sum + s.getVolume();
		prod = prod + (s.getRate()*s.getVolume());
		arrprod[i] = prod;
		arrsum[i] = sum;
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy HH:mm");
        try
        {
            Date date = simpleDateFormat.parse(s.getDateTime());

            s.setDateTime((simpleDateFormat.format(date)));
        }
        catch (ParseException ex)
        {
            System.out.println("Exception "+ex);
        }
	    
		/*s.setCumProd(arrprod[i]);
		s.setCumSum(arrsum[i]);*/
		if(i>50) {
		double vwma5 = ((arrprod[i] - arrprod[i-50])/(arrsum[i] - arrsum[i-50]));
		s.setVwma50(vwma5);
		}
		if(i>200) {
			double vwma8 = ((arrprod[i] - arrprod[i-200])/(arrsum[i] - arrsum[i-200]));
			s.setVwma200(vwma8);
		}
		i++;
				System.out.println(s.getDateTime() + "	   " + s.getRate() + "         "
						+ s.getVolume() + "        " + s.getVwma50() + "    "
						+ s.getVwma200());
				
				
	}
	//System.out.println(stocks.get(0).getVwma50());
	calculate(stocks);
	
		
		
	
	
	
	
	}catch(FileNotFoundException f) {
		
	}
}
	private void calculate(List<Stock> stocks) {
		double buy = 0;
		double sell = 0;
		double profit = 0;
		boolean purchased = false;
		for(Stock stock:stocks) {
		if(stock.getDateTime().contains("9:30") && stock.getVwma50()< stock.getVwma200()) {
			for(Stock stoc :stocks) {
				if(stoc.getVwma50()> stoc.getVwma200() && !stock.getDateTime().matches("^1[3-5]:30$")) {
					buy = stoc.getRate();
					System.out.println("Stock purchased @ "+buy);
					purchased =true;
					
				}
				if(stoc.getVwma50() < stoc.getVwma200() && !stock.getDateTime().matches("^1[3-5]:30$") &&purchased) {
					sell = stoc.getRate();
					System.out.println("Stock sold @ "+sell);
				}
			}
			profit = sell - buy;
			sell = buy = profit =0;
			System.out.println("profit obtaines=="+profit);
			
		}	}
		}
		
		
		
		
		
		
	
	
	
	public static void main(String args[]) {
		Trade trade = new Trade();
		trade.trading();

	}
	}
