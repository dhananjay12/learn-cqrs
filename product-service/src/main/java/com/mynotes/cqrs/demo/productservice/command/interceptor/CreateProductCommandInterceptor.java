package com.mynotes.cqrs.demo.productservice.command.interceptor;

import com.mynotes.cqrs.demo.productservice.command.CreateProductCommand;
import java.math.BigDecimal;
import java.util.List;
import java.util.function.BiFunction;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CreateProductCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {
    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(List<? extends CommandMessage<?>> list) {
        return (index,command)->{

            log.info("Dispatching a command {}.", command);

            return command;
        };

    }
}
