package com.mynotes.cqrs.demo.productservice.query.rest;

import com.mynotes.cqrs.demo.productservice.query.FindProductsQuery;
import com.mynotes.cqrs.demo.productservice.query.dto.ProductDTO;
import java.util.List;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductsQueryController {

    private final QueryGateway queryGateway;

    public ProductsQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping
    public List<ProductDTO> getProducts() {
        FindProductsQuery findProductsQuery = new FindProductsQuery();
        List<ProductDTO> result = queryGateway.query(findProductsQuery,
                ResponseTypes.multipleInstancesOf(ProductDTO.class)).join();

        return result;
    }
}
