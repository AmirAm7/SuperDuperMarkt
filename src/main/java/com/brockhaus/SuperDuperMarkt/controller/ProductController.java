package com.brockhaus.SuperDuperMarkt.controller;


import com.brockhaus.SuperDuperMarkt.mapper.ProductMapper;
import com.brockhaus.SuperDuperMarkt.model.DTO.ProductResponse;
import com.brockhaus.SuperDuperMarkt.model.Product;
import com.brockhaus.SuperDuperMarkt.repo.ProductRepository;
import com.brockhaus.SuperDuperMarkt.service.ProductServiceImp;
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
	private final ProductRepository productRepository;
	private final ProductMapper productMapper;
	private final ProductServiceImp productService;
	@Autowired
	public ProductController(ProductRepository productRepository, ProductMapper productMapper, ProductServiceImp productService) {
		this.productRepository = productRepository;
		this.productMapper = productMapper;
		this.productService = productService;
	}

	@GetMapping("/all")
	ResponseEntity <List<ProductResponse>> getAllProducts(){
		List<ProductResponse> products = productMapper.convertToListOfProductResponse(productService.findAllProducts());
		return new ResponseEntity<>(products, HttpStatus.FOUND);
	}

	@GetMapping("/productsByDate")
	ResponseEntity <List<ProductResponse>> getProductsByExpiryDate(@RequestParam (value = "expiryDate") LocalDate expiryDate,
																   @RequestParam (value = "importDate") LocalDate importDate){ //@Param
		List<ProductResponse> products = productService.listOfProductResponseByDate(expiryDate,importDate);
		return new ResponseEntity<>(products, HttpStatus.FOUND);
	}
}

