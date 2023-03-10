package com.brockhaus.superDuperMarkt.model.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {

	private long id;
	private String designation;
	private double dailyPrice;
	private double initialPrice;
	private int quality;
	private LocalDate expiryDate;
	private boolean hasExpiry;

}
