package com.mynotes.cqrs.demo.productservice.command.interceptor;

import com.mynotes.cqrs.demo.productservice.command.CreateProductCommand;
import com.mynotes.cqrs.demo.productservice.persistence.ProductLookupEntity;
import com.mynotes.cqrs.demo.productservice.persistence.ProductLookupRepository;
import java.util.List;
import java.util.function.BiFunction;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CreateProductCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {

    private final ProductLookupRepository productLookupRepository;

    public CreateProductCommandInterceptor(ProductLookupRepository productLookupRepository) {
        this.productLookupRepository = productLookupRepository;
    }

    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(List<? extends CommandMessage<?>> list) {
        return (index,command)->{

            log.info("Dispatching a command {}.", command);
            if(CreateProductCommand.class.equals(command.getPayloadType())) {

                CreateProductCommand createProductCommand = (CreateProductCommand)command.getPayload();

                ProductLookupEntity productLookupEntity =  productLookupRepository.findByProductIdOrTitle(createProductCommand.getProductId(),
                        createProductCommand.getTitle());

                if(productLookupEntity != null) {
                    throw new IllegalStateException(
                            String.format("Product with productId %s or title %s already exist",
                                    createProductCommand.getProductId(), createProductCommand.getTitle())
                    );
                }

            }

            return command;
        };

    }
}
