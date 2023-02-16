package com.brockhaus.superDuperMarkt.service;

import com.brockhaus.superDuperMarkt.model.DTO.ProductReportDTO;
import com.brockhaus.superDuperMarkt.model.DTO.ProductResponse;

import java.time.LocalDate;
import java.util.List;

public interface IProductService {
	List<ProductResponse> findAllProducts();
	List<ProductResponse> getRemovableProduct(LocalDate targetDate);
	List<ProductReportDTO> findAllProductsForNextWeek();

}
