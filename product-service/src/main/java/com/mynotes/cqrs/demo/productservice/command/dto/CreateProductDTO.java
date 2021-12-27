package com.mynotes.cqrs.demo.productservice.command.dto;

import java.math.BigDecimal;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class CreateProductDTO {
    @NotBlank(message = "Product title is a required field")
    private String title;

    @Min(value = 1, message = "Price cannot be lower than 1")
    private BigDecimal price;

    @Min(value = 1, message = "Quantity cannot be lower than 1")
    private Integer quantity;
}
