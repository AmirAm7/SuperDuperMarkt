package com.brockhaus.superDuperMarkt.model;

import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

@Entity
@Table(name = "wine")
@DiscriminatorValue(value = "wine")
@NoArgsConstructor
public class Wine extends Product{

	public Wine(String designation, double initialPrice, int expiryDate, LocalDate expiryDay, LocalDate importDay) {
		super(designation, initialPrice, expiryDate, expiryDay, importDay);
	}

	@Override
	public boolean hasQuality() {
		return false;
	}

	@Override
	public int getQuality(LocalDate expiryDay, LocalDate importDay) {
		LocalDate today = LocalDate.now();
		long untilToday = DAYS.between(importDay, today);
		int quality = (int)untilToday / 10;
		if (quality <= 50){
			return quality;
		}
		return 50;
	}

	@Override
	public double getDailyPrice(double initialPrice, int quality) {
		double constance = 0.1;
		double rate;
		rate = initialPrice + (constance * quality);
		return Math.round(rate * 100.0) / 100.0;
	}

	@Override
	public boolean hasExpired(LocalDate expiryDay) {
		return false;
	}

}
