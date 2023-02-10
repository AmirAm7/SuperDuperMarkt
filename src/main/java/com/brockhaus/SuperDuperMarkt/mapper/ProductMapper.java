package com.brockhaus.SuperDuperMarkt.mapper;


import com.brockhaus.SuperDuperMarkt.model.DTO.ProductResponse;
import com.brockhaus.SuperDuperMarkt.model.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapper {

	private  final ModelMapper modelMapper;
	@Autowired
	public ProductMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}
	public ProductResponse convertToProductResponse(Product product){
		return modelMapper.map(product, ProductResponse.class);
	}

	public Product convertToProduct(ProductResponse productResponse){
		return modelMapper.map(productResponse, Product.class);
	}

	public List<ProductResponse> convertToListOfProductResponse(List<Product> products){

		List<ProductResponse> productResponses = new ArrayList<>();
		for (Product p : products
			 ) {
			productResponses.add(convertToProductResponse(p));
		}
		return productResponses;
	}
}