package com.mynotes.cqrs.demo.productservice.query;

import com.mynotes.cqrs.demo.productservice.events.ProductCreatedEvent;
import com.mynotes.cqrs.demo.productservice.persistence.ProductEntity;
import com.mynotes.cqrs.demo.productservice.persistence.ProductsRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@ProcessingGroup("product-group")
public class ProductEventsHandler {

    private final ProductsRepository productsRepository;

    public ProductEventsHandler(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @EventHandler
    public void on(ProductCreatedEvent event) {

        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(event, productEntity);

        try {
            productsRepository.save(productEntity);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
    }
}
