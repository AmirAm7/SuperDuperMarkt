package com.brockhaus.SuperDuperMarkt.service;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
public class PriceCalculate {

	public double getPrice( double price, int quality, int day ) {
		double constance = 0.1;
		double dailyRate = 0;
		while (day >= 0) {
			dailyRate = price + constance * quality;
			price = dailyRate;
		}
		return Math.round(dailyRate * 100.0) / 100.0;
	}

	public int getExpiryDays(LocalDate expiryDate, LocalDate importDate) {
		double countMonth = Period.between(importDate, expiryDate).getMonths();
		double countDays = Period.between(importDate, expiryDate).getDays();
		double days = countMonth * 30 + countDays;
		return (int) days;
	}
}
