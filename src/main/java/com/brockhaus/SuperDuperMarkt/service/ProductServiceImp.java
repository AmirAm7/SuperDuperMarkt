package com.brockhaus.SuperDuperMarkt.service;

import com.brockhaus.SuperDuperMarkt.model.DTO.ProductResponse;
import com.brockhaus.SuperDuperMarkt.model.DailyPriceId;
import com.brockhaus.SuperDuperMarkt.model.Product;
import com.brockhaus.SuperDuperMarkt.repo.DailyPriceIdRepository;
import com.brockhaus.SuperDuperMarkt.repo.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ProductServiceImp implements IProductService {

	private final PriceCalculate priceCalculate;
	private final ProductRepository productRepository;

	private final DailyPriceIdRepository dailyPriceIdRepository;

	@Autowired
	public ProductServiceImp(PriceCalculate priceCalculate, ProductRepository productRepository, DailyPriceIdRepository dailyPriceIdRepository) {
		this.priceCalculate = priceCalculate;
		this.productRepository = productRepository;
		this.dailyPriceIdRepository = dailyPriceIdRepository;
	}


	public List<ProductResponse> listOfProductResponse (LocalDate asExpiryDate, LocalDate toExpiryDate){
		List<ProductResponse> productResponseList = new ArrayList<>();
		List <Product> products = productRepository.getProductsByExpiryDates(asExpiryDate, toExpiryDate);
		for (Product p: products
			 ) {
			ProductResponse newProduct = new ProductResponse();
			int day = priceCalculate.getExpiryDays(p.getExpiryDate(),p.getImportDate());
			double lPrice = priceCalculate.getPrice(p.getBasicPrice(), p.getQuality(), day);
			newProduct.setLastPrice(lPrice);
			newProduct.setDesignation(p.getDesignation());
			newProduct.setQuality(p.getQuality());
			newProduct.setImportDate(p.getImportDate());
			newProduct.setExpiryDate(p.getExpiryDate());
			newProduct.setBasicPrice(p.getBasicPrice());
			newProduct.setRestDays(day);
			productResponseList.add(newProduct);
		}
		return productResponseList;
	} // TODO: TEST - CSV - Design P -


	@Override
	public List<Product> findAllProducts() {
		return productRepository.findAll();
	}

	@PostConstruct
	public List<DailyPriceId> getDailyPriceByProductId() {
		List<Product> products = productRepository.findAll();

		for (Product product : products) {
			for (int i = priceCalculate.getExpiryDays(product.getImportDate(), product.getExpiryDate()); i > 0; i--) {
				DailyPriceId dailyPriceId = new DailyPriceId();
				dailyPriceId.setDate(product.getImportDate());
				dailyPriceId.setPrice(product.getBasicPrice());
				dailyPriceId.setProduct(product);
				dailyPriceIdRepository.save(dailyPriceId);
			}
		}
		return dailyPriceIdRepository.findAll();
	}

}
