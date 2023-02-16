package com.brockhaus.superDuperMarkt.model;

import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

@Entity
@Table(name = "cheese")
@DiscriminatorValue(value = "cheese")
@NoArgsConstructor
public class Cheese extends Product {

	public Cheese(String designation, double initialPrice, LocalDate expiryDay, LocalDate importDay) {
		super(designation, initialPrice, expiryDay, importDay);
		int quality = getQuality(expiryDay, importDay);
		if (quality < 50 || quality > 100) {
			throw new RuntimeException("cheese expiry must be more than 30");
		}
	}

	/**
	 * comment
	 *
	 * @param expiryDay
	 * @param importDay
	 * @return
	 */
	@Override
	public int getQuality(LocalDate expiryDay, LocalDate importDay) {
		LocalDate today = LocalDate.now();
		long betweenTowDate = DAYS.between(importDay, expiryDay);
		long decreasedPointsUntilToday = DAYS.between(importDay, today);
		long quality = betweenTowDate - decreasedPointsUntilToday;
		return (int) quality;
	}

	@Override
	public boolean hasExpired(LocalDate expiryDay, LocalDate target) {
		LocalDate timeToRemoveProductFromTheShelf = expiryDay.minusDays(30);
		return target.isEqual(timeToRemoveProductFromTheShelf) || target.isAfter(expiryDay);
	}

	@Override
	public double getDailyPrice(double initialPrice, int quality) {
		double constance = 0.1;
		double dailyRate;
		dailyRate = initialPrice + (constance * quality);
		return Math.round(dailyRate * 100.0) / 100.0;
	}


	@Override
	public boolean hasQuality() {

		return false;
	}
}
