package com.brockhaus.SuperDuperMarkt.service;

import com.brockhaus.SuperDuperMarkt.model.Product;

import java.util.List;

public interface IProductService {
	List<Product> findAllProducts();
}
