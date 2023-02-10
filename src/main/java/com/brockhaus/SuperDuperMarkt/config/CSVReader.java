package com.brockhaus.SuperDuperMarkt.config;

import com.brockhaus.SuperDuperMarkt.model.Product;
import com.brockhaus.SuperDuperMarkt.repo.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

@Slf4j
@Service
public class CSVReader {
	private final ProductRepository productRepository;
	@Autowired
	public CSVReader(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	String line="";

	@PostConstruct
	public void saveProductsDataFromCSVDatasetInToDB() throws IOException {
		try {
			BufferedReader br = new BufferedReader(new FileReader("products.csv"));
			while ((line = br.readLine()) != null) {
				String[] strArray = line.split(";");
				Product newProduct = new Product();
				newProduct.setDesignation(strArray[0]);
				newProduct.setBasicPrice(Double.parseDouble(strArray[1]));
				newProduct.setExpiryDate(LocalDate.parse(strArray[2]));
				newProduct.setImportDate(LocalDate.parse(strArray[3]));
				newProduct.setQuality(Integer.parseInt(strArray[4]));
				newProduct.setMustDisposed(Boolean.parseBoolean(strArray[5]));
				newProduct.setPricePeriod(Integer.parseInt(strArray[6]));
				newProduct.setCount(Integer.parseInt(strArray[7]));
				productRepository.save(newProduct);
				log.info("Product saved");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			log.info("File not found");
		}
	}
}
