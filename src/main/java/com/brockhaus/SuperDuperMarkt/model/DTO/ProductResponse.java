package com.brockhaus.SuperDuperMarkt.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
	private String designation;
	private double basicPrice;
	private double lastPrice;
	private LocalDate importDate;
	private LocalDate expiryDate;
	private int quality;
	private int pricePeriod;
	private int restDays;
}
