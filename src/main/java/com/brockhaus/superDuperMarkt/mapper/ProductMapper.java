package com.brockhaus.superDuperMarkt.mapper;


import com.brockhaus.superDuperMarkt.model.DTO.ProductReportDTO;
import com.brockhaus.superDuperMarkt.model.DTO.ProductResponse;
import com.brockhaus.superDuperMarkt.model.Product;
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

	public ProductResponse convertToProductResponse(Product product, LocalDate targetDate) {
		ProductResponse productResponse = modelMapper.map(product, ProductResponse.class);
		int quality = calculateQuality(product.getExpiryDay(), product.getImportDay(), product);
		productResponse.setQuality(quality);
		productResponse.setDailyPrice(product.getDailyPrice(product.getInitialPrice(), quality));
		productResponse.setExpiryDate(product.getExpiryDay());
		productResponse.setHasExpiry(product.hasExpired(product.getExpiryDay(), targetDate));
		return productResponse;
	}

	public ProductReportDTO convertToProductReportDTO(Product product) {
		ProductReportDTO productReportDTO = modelMapper.map(product, ProductReportDTO.class);
		int quality = calculateQuality(product.getExpiryDay(), product.getImportDay(), product);
		productReportDTO.setQuality(quality);
		productReportDTO.setDailyPrice(product.getDailyPrice(product.getInitialPrice(), quality)); // ??????
		productReportDTO.setExpiryDate(product.getExpiryDay());
		return productReportDTO;
	}

	public Product convertToProduct(ProductResponse productResponse) {
		return modelMapper.map(productResponse, Product.class);
	}

	public List<ProductResponse> convertToListOfProductResponse(List<Product> products, LocalDate targetDate) {

		List<ProductResponse> productResponses = new ArrayList<>();
		for (Product p : products
		) {
			productResponses.add(convertToProductResponse(p, targetDate));
		}
		return productResponses;
	}

	public List<Product> convertToListOfProduct(List<ProductResponse> productResponses) {

		List<Product> products = new ArrayList<>();
		for (ProductResponse p : productResponses
		) {
			products.add(convertToProduct(p));
		}
		return products;
	}

	public int calculateQuality(LocalDate expiryDay, LocalDate importDay, Product product) {
		return product.getQuality(expiryDay, importDay);
	}
}