package com.brockhaus.SuperDuperMarkt.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDate;


@Slf4j
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dailyPrice")
public class DailyPriceId {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "date")
	private LocalDate date;
	@Column(name = "price_of_the_day")
	private double price;
	@ManyToOne(fetch = FetchType.LAZY)
	private Product product;
}
