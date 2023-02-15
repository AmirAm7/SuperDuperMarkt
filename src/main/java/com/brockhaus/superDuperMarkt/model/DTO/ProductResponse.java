package com.brockhaus.superDuperMarkt.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

	private long id;
	private String designation;
	private double dailyPrice;
	private double initialPrice;
	private int quality;
	private LocalDate expiryDate;
	private boolean hasExpiry;

}
