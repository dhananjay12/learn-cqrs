package com.mynotes.cqrs.demo.productservice.query;

import com.mynotes.cqrs.demo.productservice.events.ProductCreatedEvent;
import com.mynotes.cqrs.demo.productservice.persistence.ProductEntity;
import com.mynotes.cqrs.demo.productservice.persistence.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@ProcessingGroup("product-group")
public class ProductEventsHandler {

    private final ProductRepository productsRepository;

    public ProductEventsHandler(ProductRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @EventHandler
    public void on(ProductCreatedEvent event) {

        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(event, productEntity);

        productsRepository.save(productEntity);

        if(productEntity.getTitle().equalsIgnoreCase("ps")){
            throw new IllegalArgumentException("An error occured during ProductCreatedEvent in Query side event handler");
        }

    }

    @ExceptionHandler(resultType = IllegalArgumentException.class)
    public void handle(IllegalArgumentException ex){
        log.error(ex.getMessage());
        throw ex;
    }
}
