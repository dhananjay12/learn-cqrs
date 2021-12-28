package com.mynotes.cqrs.demo.productservice.query;

import com.mynotes.cqrs.demo.productservice.persistence.ProductEntity;
import com.mynotes.cqrs.demo.productservice.persistence.ProductsRepository;
import com.mynotes.cqrs.demo.productservice.query.dto.ProductDTO;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProductsQueryHandler {

    private final ProductsRepository productsRepository;

    public ProductsQueryHandler(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @QueryHandler
    public List<ProductDTO> findProducts(FindProductsQuery query) {

        List<ProductDTO> productsResult = new ArrayList<>();

        List<ProductEntity> storedProducts =  productsRepository.findAll();

        for(ProductEntity productEntity: storedProducts) {
            ProductDTO productRestModel = new ProductDTO();
            BeanUtils.copyProperties(productEntity, productRestModel);
            productsResult.add(productRestModel);
        }
        return productsResult;

    }
}
