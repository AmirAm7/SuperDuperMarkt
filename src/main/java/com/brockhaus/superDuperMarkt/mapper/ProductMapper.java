package com.brockhaus.superDuperMarkt.mapper;


import com.brockhaus.superDuperMarkt.model.DTO.ProductResponse;
import com.brockhaus.superDuperMarkt.model.Product;
import com.brockhaus.superDuperMarkt.service.ProductServiceImp;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapper {

	private final ModelMapper modelMapper;

	@Autowired
	public ProductMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}


	public ProductResponse convertToProductResponse(Product product) {
		ProductResponse productResponse = modelMapper.map(product, ProductResponse.class);
		int quality = calculateQuality(product.getExpiryDay(), product.getImportDay(), product);
		productResponse.setQuality(quality);
		productResponse.setDailyPrice(product.getDailyPrice(product.getInitialPrice(), quality)); // ??????
		productResponse.setExpiryDate(product.getExpiryDay());
		productResponse.setHasExpiry(product.hasExpired(product.getExpiryDay()));
		return productResponse;
	}

	public Product convertToProduct(ProductResponse productResponse) {
		return modelMapper.map(productResponse, Product.class);
	}

	public List<ProductResponse> convertToListOfProductResponse(List<Product> products) {

		List<ProductResponse> productResponses = new ArrayList<>();
		for (Product p : products
		) {
			productResponses.add(convertToProductResponse(p));
		}
		return productResponses;
	}


	public double getDailyPrice(double initialPrice, int quality) {
		return getDailyPrice(initialPrice, quality);
	}

	public int calculateQuality(LocalDate expiryDay, LocalDate importDay, Product product) {
		return product.getQuality(expiryDay, importDay);

	}
}