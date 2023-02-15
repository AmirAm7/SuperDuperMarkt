package com.brockhaus.superDuperMarkt.events;


import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ProductEventService {

    @EventListener
    public void handelProductService(final NewProductEvent productEvent) {
        //do something
        //Send email to products Owner
        System.out.println("Send email to Product's owner");
        System.out.println(productEvent.getProductList().size());
    }
}
