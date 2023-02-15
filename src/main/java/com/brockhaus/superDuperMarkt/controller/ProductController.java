package com.brockhaus.superDuperMarkt.controller;


import com.brockhaus.superDuperMarkt.mapper.ProductMapper;
import com.brockhaus.superDuperMarkt.model.DTO.ProductResponse;
import com.brockhaus.superDuperMarkt.model.Product;
import com.brockhaus.superDuperMarkt.service.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/api/products")
public class ProductController {
	private final ProductMapper productMapper;
	private final ProductServiceImp productService;
	@Autowired
	public ProductController( ProductMapper productMapper, ProductServiceImp productService) {
		this.productMapper = productMapper;
		this.productService = productService;
	}

	@GetMapping("/all")
	ResponseEntity <List<ProductResponse>> getAllProducts(){
		List<ProductResponse> products = productMapper.convertToListOfProductResponse(productService.findAllProducts());
		return new ResponseEntity<>(products, HttpStatus.FOUND);
	}

	@GetMapping("/productsForNextWeek")
	ResponseEntity <List <List<ProductResponse>>> getProductsForNextWeek(){
		List <List<ProductResponse>> products = productService.findAllProductsForNextWeek();
		return new ResponseEntity<>(products, HttpStatus.FOUND);
	}

	@GetMapping("/allInDB")
	ResponseEntity <List<Product>> getAllProductsInDB(){
		List<Product> products = productService.findAllProductsInDB();
		return new ResponseEntity<>(products, HttpStatus.FOUND);
	}

	@GetMapping("/expiryProducts")
	ResponseEntity <List <ProductResponse>> getExpiredProducts(){
		List<ProductResponse> products = productService.getExpiredProduct();
		return new ResponseEntity<>(products, HttpStatus.FOUND);
	}
}

