package com.brockhaus.superDuperMarkt.config;

import com.brockhaus.superDuperMarkt.events.NewProductEvent;
import com.brockhaus.superDuperMarkt.mapper.ProductMapper;
import com.brockhaus.superDuperMarkt.model.DTO.ProductResponse;
import com.brockhaus.superDuperMarkt.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.util.List;

@Configuration
@EnableScheduling
public class ReportSchedule {

	@Autowired
	private IProductService productService;

	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@Scheduled(cron = "0 0 19 * * *")
	public void scheduleFixedDelayTask() {

		System.out.println("Starting getReport ...");
		List<ProductResponse> p =
				productService.getRemovableProduct(LocalDate.now().plusDays(1));
		applicationEventPublisher.publishEvent(new NewProductEvent(this, p));
	}
}
