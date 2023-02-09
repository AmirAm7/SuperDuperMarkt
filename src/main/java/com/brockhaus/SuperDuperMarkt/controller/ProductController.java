package com.brockhaus.SuperDuperMarkt.controller;


import com.brockhaus.SuperDuperMarkt.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/products")
public class ProductController {
	private final ProductRepository productRepository;
	@Autowired
	public ProductController(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

}
