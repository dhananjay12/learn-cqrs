package com.mynotes.cqrs.demo.productservice.query.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ProductDTO {
    private String productId;
    private String title;
    private BigDecimal price;
    private Integer quantity;
}

