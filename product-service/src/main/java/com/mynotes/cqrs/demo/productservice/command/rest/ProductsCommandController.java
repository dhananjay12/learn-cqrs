package com.mynotes.cqrs.demo.productservice.command.rest;

import com.mynotes.cqrs.demo.productservice.command.CreateProductCommand;
import com.mynotes.cqrs.demo.productservice.command.dto.CreateProductDTO;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/product")
@Slf4j
public class ProductsCommandController {

    private final CommandGateway commandGateway;

    public ProductsCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public void createProduct(@Valid @RequestBody CreateProductDTO createProductDTO){
        log.info(createProductDTO.toString());
        CreateProductCommand createProductCommand = CreateProductCommand.builder()
                .price(createProductDTO.getPrice())
                .quantity(createProductDTO.getQuantity())
                .title(createProductDTO.getTitle())
                .productId(UUID.randomUUID().toString()).build();

        String result;
        try {
            result = commandGateway.sendAndWait(createProductCommand);
        }catch (Exception ex){
            result = ex.getLocalizedMessage();
        }
        log.info("Result==>"+result);
    }
}
