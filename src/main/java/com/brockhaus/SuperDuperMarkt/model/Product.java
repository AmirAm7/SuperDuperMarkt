package com.brockhaus.SuperDuperMarkt.model;


import com.sun.istack.NotNull;
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
@Table(name = "products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@Column(name = "designation")
	private String designation;
	@NotNull
	@Column(name = "expiry_date")
	private LocalDate expiryDate;
	@NotNull
	@Column(name="import_date")
	private LocalDate importDate;
	@NotNull
	@Column(name = "basic_price")
	private double basicPrice;
	@NotNull
	@Column(name = "quality")
	private int quality;
	@NotNull
	@Column(name = "must_disposed")
	private boolean mustDisposed;
	@Column(name = "count")
	private long count;
	@Column(name = "pricePeriod")
	private int pricePeriod;
	/*
	@Column(name = "decrease")
	private boolean decrease;
	@Column(name = "increase")
	private boolean increase;
	 */
}
