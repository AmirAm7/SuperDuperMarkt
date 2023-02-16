package com.brockhaus.superDuperMarkt.model.DTO;

import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductReportDTO {
	private  long id;
	private String designation;
	private double dailyPrice;
	private double initialPrice;
	private int quality;
	private LocalDate expiryDate;
	private List<Double> prices = new ArrayList<>();
}
