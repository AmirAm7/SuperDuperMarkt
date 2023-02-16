package com.brockhaus.superDuperMarkt.controller;

import com.brockhaus.superDuperMarkt.mapper.ProductMapper;
import com.brockhaus.superDuperMarkt.model.DTO.ProductReportDTO;
import com.brockhaus.superDuperMarkt.model.DTO.ProductResponse;
import com.brockhaus.superDuperMarkt.service.ProductServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/api/products")
public class ProductController {
	private final ProductMapper productMapper;
	private final ProductServiceImp productService;

	@Autowired
	public ProductController(ProductMapper productMapper, ProductServiceImp productService) {
		this.productMapper = productMapper;
		this.productService = productService;
	}

	@GetMapping("/all")
	ResponseEntity<List<ProductResponse>> getAllProducts() {
		List<ProductResponse> products = productService.findAllProducts();
		log.info("Products found: {}", products.size());
		return new ResponseEntity<>(products, HttpStatus.FOUND);
	}

	@GetMapping("/productsForNextWeek")
	ResponseEntity<List<ProductReportDTO>> getProductsForNextWeek() {
		List<ProductReportDTO> products = productService.findAllProductsForNextWeek();
		log.info("Products found: {}", products.size());
		return new ResponseEntity<>(products, HttpStatus.FOUND);
	}

	@GetMapping("/expiryProducts/{date}")
	ResponseEntity<List<ProductResponse>> getExpiredProducts(@PathVariable(value = "date") String date) {
		final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate lDate = LocalDate.parse(date, dtf);
		List<ProductResponse> products = productService.getRemovableProduct(lDate);
		log.info("Products found: {}", products.size());
		return new ResponseEntity<>(products, HttpStatus.FOUND);
	}
}

