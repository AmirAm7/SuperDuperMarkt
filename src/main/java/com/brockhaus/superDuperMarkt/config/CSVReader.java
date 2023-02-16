package com.brockhaus.superDuperMarkt.config;

import com.brockhaus.superDuperMarkt.model.Product;
import com.brockhaus.superDuperMarkt.repo.ProductRepository;
import com.brockhaus.superDuperMarkt.model.Cheese;
import com.brockhaus.superDuperMarkt.model.Wine;
import com.brockhaus.superDuperMarkt.service.ProductServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CSVReader {
	private final ProductRepository productRepository;

	@Autowired
	public CSVReader(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	String line = "";

	@PostConstruct
	public void saveProductsDataFromCSVDatasetInToDB() throws IOException {

		try {
			List<String> expiryProducts = new ArrayList<>();
			BufferedReader br = new BufferedReader(new FileReader("products.csv"));

			while ((line = br.readLine()) != null) {
				String[] strArray = line.split(";");
				try {
					if (strArray[0].equalsIgnoreCase("Cheese")) {
						Cheese newProduct = new Cheese(strArray[0],
								Double.parseDouble(strArray[1]),
								LocalDate.parse(strArray[2]),
								LocalDate.parse(strArray[3]));
						productRepository.save(newProduct);
					} else if (strArray[0].equalsIgnoreCase("Wine")) {
						Wine newProduct = new Wine(strArray[0],
								Double.parseDouble(strArray[1]),
								LocalDate.parse(strArray[2]),
								LocalDate.parse(strArray[3]));
						productRepository.save(newProduct);
					}
				} catch (Exception e) {
					expiryProducts.add(line);
					log.info("This object is expiry" + line);
				}
				log.info("Product saved");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			log.info("File not found");
		}
	}
}
