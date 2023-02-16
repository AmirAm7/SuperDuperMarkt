package com.brockhaus.superDuperMarkt.model;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import javax.persistence.*;
import java.time.LocalDate;


@Slf4j
@Getter
@Setter
@Entity
@Table(name = "product")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "product_type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("product")
@NoArgsConstructor

public abstract class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public Product(String designation, double initialPrice, LocalDate expiryDay, LocalDate importDay) {
		this.designation = designation;
		this.initialPrice = initialPrice;
		this.expiryDay = expiryDay;
		this.importDay = importDay;
	}

	private String designation;
	private double initialPrice;
	private LocalDate expiryDay;
	private LocalDate importDay;


	public abstract boolean hasQuality();

	public abstract int getQuality(LocalDate expiryDay, LocalDate importDay);

	public abstract boolean hasExpired(LocalDate expiryDay, LocalDate targetDay);

	public abstract double getDailyPrice(double initialPrice, int quality);


}

