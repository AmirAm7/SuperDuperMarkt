package com.brockhaus.superDuperMarkt.events;

import com.brockhaus.superDuperMarkt.model.Product;
import org.springframework.context.ApplicationEvent;

import java.util.List;

public class NewProductEvent extends ApplicationEvent {

    private List<Product> productList;

    public NewProductEvent(Object source, List<Product> productList) {
        super(source);
        this.productList = productList;
    }

    public List<Product> getProductList() {
        return productList;
    }
}
