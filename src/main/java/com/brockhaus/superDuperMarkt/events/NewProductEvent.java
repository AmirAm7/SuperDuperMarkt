package com.brockhaus.superDuperMarkt.events;

import com.brockhaus.superDuperMarkt.model.DTO.ProductResponse;
import org.springframework.context.ApplicationEvent;

import java.util.List;

public class NewProductEvent extends ApplicationEvent {

	private List<ProductResponse> productList;

	public NewProductEvent(Object source, List<ProductResponse> productList) {
		super(source);
		this.productList = productList;
	}

	public List<ProductResponse> getProductList() {
		return productList;
	}
}
