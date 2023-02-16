package com.brockhaus.superDuperMarkt.service;

import com.brockhaus.superDuperMarkt.mapper.ProductMapper;
import com.brockhaus.superDuperMarkt.model.Cheese;
import com.brockhaus.superDuperMarkt.model.DTO.ProductReportDTO;
import com.brockhaus.superDuperMarkt.model.DTO.ProductResponse;
import com.brockhaus.superDuperMarkt.model.Product;
import com.brockhaus.superDuperMarkt.model.Wine;
import com.brockhaus.superDuperMarkt.repo.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(value = MockitoExtension.class)
class ProductServiceImpTest {

	@InjectMocks
	private ProductServiceImp productServiceImp;

	@Mock
	private ProductRepository mockProductRepository;

	@Mock
	private ProductMapper productMapper;

	public Product generateProductOfWine (long id,
									String designation,
									double initialPrice, LocalDate expiryDay,
									LocalDate importDay ){

		Product product = new Wine();
		product.setId(id);
		product.setDesignation(designation);
		product.setInitialPrice(initialPrice);
		product.setExpiryDay(expiryDay);
		product.setImportDay(importDay);
				return product;
	}
	public Product generateProductOfCheese (long id,
									String designation,
									double initialPrice, LocalDate expiryDay,
									LocalDate importDay ){

		Product product = new Cheese();
		product.setId(id);
		product.setDesignation(designation);
		product.setInitialPrice(initialPrice);
		product.setExpiryDay(expiryDay);
		product.setImportDay(importDay);
		return product;
	}

	public ProductReportDTO generateProductReportDTO (long id, String designation,
													  double dailyPrice, double initialPrice, int quality , LocalDate expiryDate ,
													  List<Double> prices){
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

	public ProductResponse generateProductResponseOfWine (long id,
													String designation,
													LocalDate expiryDate, double initialPrice,
													double basicPrice, int quality, boolean mustDisposed){
		ProductResponse productResponse = new ProductResponse();
		productResponse.setId(id);
		productResponse.setDesignation(designation);
		productResponse.setDailyPrice(basicPrice);
		productResponse.setInitialPrice(initialPrice);
		productResponse.setQuality(quality);
		productResponse.setExpiryDate(expiryDate);
		productResponse.setHasExpiry(mustDisposed);
		return productResponse;
	}

	public List<Product> getAllProducts (){
		List<Product> products = new ArrayList<>();
		products.add(generateProductOfCheese(1L, "Cheese", 3,  LocalDate.of(2023, 1, 25), LocalDate.of(2021, 1, 4)));
		products.add(generateProductOfWine(2L, "Wine", 5,  LocalDate.of(2023, 1, 11), LocalDate.of(2021, 1, 6)));
		products.add(generateProductOfWine(3L, "Wine", 7,  LocalDate.of(2023, 3, 10), LocalDate.of(2021, 1, 8)));
		return products;
	}

	public List<ProductResponse> productResponses (){
		List<ProductResponse> productResponses = new ArrayList<>();
		productResponses.add(generateProductResponseOfWine(1L, "Cheese", LocalDate.of(2023, 1, 4), 1.5, 1.5, 3, false));
		productResponses.add(generateProductResponseOfWine(2L, "Wine", LocalDate.of(2023, 1, 6), 1.5, 1.5, 5, false));
		productResponses.add(generateProductResponseOfWine(3L, "Wine", LocalDate.of(2023, 1, 8), 1.5, 1.5, 7, false));
		return productResponses;
	}

	@Test
	void findAllProductsForNextWeek() {
		when(mockProductRepository.findAll()).thenReturn(getAllProducts());
		when(productMapper.convertToProductReportDTO(any())).thenReturn(generateProductReportDTO(1,"Wine", 22, 23, 10,
				LocalDate.now().plusDays(5), new ArrayList<>()));
		List<ProductReportDTO> productResponses = productServiceImp.findAllProductsForNextWeek();
		assertEquals(productResponses.size(), 3);
	}

	@Test
	void getExpiredProduct() {
		when(mockProductRepository.findAll()).thenReturn(getAllProducts());
		when(productMapper.convertToProductResponse(any(), any())).thenReturn(new ProductResponse());
		List<ProductResponse> productResponses = productServiceImp.getRemovableProduct(LocalDate.now().plusDays(5));
		assertEquals(productResponses.size(), 2);
	}
}