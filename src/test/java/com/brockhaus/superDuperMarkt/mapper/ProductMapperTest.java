package com.brockhaus.superDuperMarkt.mapper;

import com.brockhaus.superDuperMarkt.model.Cheese;
import com.brockhaus.superDuperMarkt.model.DTO.ProductReportDTO;
import com.brockhaus.superDuperMarkt.model.DTO.ProductResponse;
import com.brockhaus.superDuperMarkt.model.Product;
import com.brockhaus.superDuperMarkt.model.Wine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductMapperTest {
	private ProductMapper mapper;

	@BeforeEach
	public void setUp() {
		ModelMapper modelMapper = new ModelMapper();
		this.mapper = new ProductMapper(modelMapper);
	}

	public Product generateProductOfWine(long id,
										 String designation,
										 double initialPrice, LocalDate expiryDay,
										 LocalDate importDay) {

		Product product = new Wine();
		product.setId(id);
		product.setDesignation(designation);
		product.setInitialPrice(initialPrice);
		product.setExpiryDay(expiryDay);
		product.setImportDay(importDay);
		return product;
	}

	public Product generateProductOfCheese(long id,
										   String designation,
										   double initialPrice, LocalDate expiryDay,
										   LocalDate importDay) {

		Product product = new Cheese();
		product.setId(id);
		product.setDesignation(designation);
		product.setInitialPrice(initialPrice);
		product.setExpiryDay(expiryDay);
		product.setImportDay(importDay);
		return product;
	}

	public Product getProduct() {
		return generateProductOfCheese(1L, "Test", 1.0,
				LocalDate.now().plusDays(10), LocalDate.now().minusDays(20));
	}

	public ProductReportDTO generateProductReportDTO(long id,
													 String designation,
													 double dailyPrice,
													 double initialPrice,
													 int quality,
													 LocalDate expiryDate,
													 List<Double> prices) {
		return ProductReportDTO.builder()
				.id(id)
				.designation(designation)
				.dailyPrice(dailyPrice)
				.initialPrice(initialPrice)
				.quality(quality)
				.expiryDate(expiryDate)
				.prices(prices)
				.build();
	}

	public ProductResponse generateProductResponse(long id,
												   String designation,
												   LocalDate expiryDate,
												   double basicPrice,
												   double initialPrice,
												   int quality,
												   boolean hasExpiry) {
		return ProductResponse.builder()
				.id(id)
				.designation(designation)
				.dailyPrice(basicPrice)
				.initialPrice(initialPrice)
				.quality(quality)
				.expiryDate(expiryDate)
				.hasExpiry(hasExpiry)
				.build();
	}

	public ProductResponse getProductResponse() {
		return generateProductResponse(1L, "Test",
				LocalDate.now().plusDays(20), 1.0, 1.0, 1, true);
	}

	@Test
	void convertToProductResponse() {
		Product product1 = generateProductOfCheese(1L, "Cheese", 1, LocalDate.now().plusDays(10), LocalDate.now().minusDays(20));
		Product product2 = generateProductOfWine(1L, "Wine", 1, LocalDate.now().plusDays(10), LocalDate.now().minusDays(20));
		ProductResponse productResponse1 = mapper.convertToProductResponse(product1, LocalDate.now().plusDays(10));
		ProductResponse productResponse2 = mapper.convertToProductResponse(product2, LocalDate.now().plusDays(10));
		assertEquals(productResponse1.getDailyPrice(), 2.0);
		assertEquals(productResponse2.getDailyPrice(), 1.2);

	}

	@Test
	void convertToProductReportDTO() {
		Product product = generateProductOfCheese(1L, "Cheese", 1,
				LocalDate.now().plusDays(10), LocalDate.now().minusDays(20));
		ProductReportDTO productReportDTO = mapper.convertToProductReportDTO(product);
		assertEquals(productReportDTO.getExpiryDate().getYear(), 2023);
		assertEquals(productReportDTO.getExpiryDate().getMonthValue(), 2);
		assertEquals(productReportDTO.getExpiryDate().getDayOfMonth(), 26);
	}


	@Test
	void calculateQuality() {
		Product product = generateProductOfCheese(1L, "Cheese", 1, LocalDate.now().plusDays(10), LocalDate.now());
		assertEquals(mapper.calculateQuality(LocalDate.now().plusDays(10), LocalDate.now(), product),10);
	}
}
