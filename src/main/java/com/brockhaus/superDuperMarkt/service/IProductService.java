package com.brockhaus.superDuperMarkt.service;

import com.brockhaus.superDuperMarkt.model.DTO.ProductResponse;
import com.brockhaus.superDuperMarkt.model.Product;

import java.time.LocalDate;
import java.util.List;

public interface IProductService {
	List<Product> findAllProducts();
	List<Product> findAllProductsInDB();

}
