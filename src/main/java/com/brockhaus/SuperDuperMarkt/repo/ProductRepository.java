package com.brockhaus.SuperDuperMarkt.repo;

import com.brockhaus.SuperDuperMarkt.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> getProductsByExpiryDate(LocalDate date);

	@Query(value = "SELECT * FROM Product where expiry_date>=?1 and expiry_date <?2", nativeQuery = true)
	List<Product> getProductsByExpiryDates (LocalDate asExpiryDate, LocalDate toExpiryDate);

}
