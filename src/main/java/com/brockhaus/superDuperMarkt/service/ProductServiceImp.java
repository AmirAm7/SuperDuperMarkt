package com.brockhaus.superDuperMarkt.service;
import com.brockhaus.superDuperMarkt.repo.ProductRepository;
import com.brockhaus.superDuperMarkt.mapper.ProductMapper;
import com.brockhaus.superDuperMarkt.model.DTO.ProductResponse;
import com.brockhaus.superDuperMarkt.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
	public List<Product> findAllProductsInDB() {
		return productRepository.findAll();
	}

	@Override
	public List<Product> findAllProducts() {
		return productRepository.findAll();
	}

	public List<ProductResponse> getExpiredProduct(){
		List<ProductResponse> listOfExpiredProductResponse = new ArrayList<>();
		List <Product> 	listOfProducts = findAllProducts();
		for (Product p: listOfProducts
		) {
			if (p.hasExpired(p.getExpiryDay())){
				ProductResponse productResponse = productMapper.convertToProductResponse(p);
				listOfExpiredProductResponse.add(productResponse);
			}
		}
		return listOfExpiredProductResponse;
	}

	public List <List<ProductResponse>> findAllProductsForNextWeek (){
		List <List<ProductResponse>> listOfProductsForNextWeek = new ArrayList<>();
		List <Product> 	listOfProducts = findAllProducts();
		for (Product p: listOfProducts
		) {
			List<ProductResponse> listOfProductResponse = new ArrayList<>();
			for (int i = 0; i < 100; i++) {
				ProductResponse productResponse = productMapper.convertToProductResponse(p);
				productResponse.setDailyPrice(p.getDailyPrice(p.getInitialPrice(), p.getQuality(p.getExpiryDay().minusDays(i), p.getImportDay())));
				listOfProductResponse.add(productResponse);
			}
			listOfProductsForNextWeek.add(listOfProductResponse);
		}
		return listOfProductsForNextWeek;
	}

}
