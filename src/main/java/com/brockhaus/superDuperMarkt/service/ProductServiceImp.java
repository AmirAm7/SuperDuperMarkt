package com.brockhaus.superDuperMarkt.service;

import com.brockhaus.superDuperMarkt.model.DTO.ProductReportDTO;
import com.brockhaus.superDuperMarkt.repo.ProductRepository;
import com.brockhaus.superDuperMarkt.mapper.ProductMapper;
import com.brockhaus.superDuperMarkt.model.DTO.ProductResponse;
import com.brockhaus.superDuperMarkt.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author amir
 * @version 0.1
 * @since 0.1
 */
@Slf4j
@Service
public class ProductServiceImp implements IProductService {
	private final ProductMapper productMapper;
	private final ProductRepository productRepository;

	@Autowired
	public ProductServiceImp(ProductMapper productMapper, ProductRepository productRepository) {
		this.productMapper = productMapper;
		this.productRepository = productRepository;
	}

	@Override
	public List<ProductResponse> findAllProducts() {
		return productMapper.convertToListOfProductResponse(productRepository.findAll(), LocalDate.now());
	}

	@Override
	public List<ProductResponse> getRemovableProduct(LocalDate targetDate) {
		List<ProductResponse> listOfExpiredProductResponse = new ArrayList<>();
		List<Product> listOfProducts = productRepository.findAll();
		for (Product p : listOfProducts
		) {
			if (p.hasExpired(p.getExpiryDay(), targetDate)) {
				ProductResponse productResponse = productMapper.convertToProductResponse(p, targetDate);
				listOfExpiredProductResponse.add(productResponse);
			}
		}
		return listOfExpiredProductResponse;
	}

	@Override
	public List<ProductReportDTO> findAllProductsForNextWeek() {
		List<ProductReportDTO> listOfProductReportDTOS = new ArrayList<>();
		List<Product> listOfProducts = productRepository.findAll();

		for (Product p : listOfProducts
		) {
			ProductReportDTO productReportDTO = productMapper.convertToProductReportDTO(p);

			for (int i = 1; i <= 7; i++) {
				double d = p.getDailyPrice(p.getInitialPrice(), p.getQuality(p.getExpiryDay().minusDays(i), p.getImportDay()));
				productReportDTO.getPrices().add(d);
			}
			listOfProductReportDTOS.add(productReportDTO);
		}
		return listOfProductReportDTOS;
	}
}
