package com.example.ProductService.command.api.aggregate;

import com.example.ProductService.command.api.commands.CreateProductCommand;
import com.example.ProductService.command.api.commands.UpdateProductCommand;
import com.example.ProductService.command.api.events.ProductCreatedEvent;
import com.example.ProductService.command.api.events.ProductUpdatedEvent;
import com.fasterxml.jackson.databind.util.BeanUtil;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.hibernate.sql.Update;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Aggregate
public class ProductAggregate {

    @AggregateIdentifier
    private String aggregateId;
    private String name;
    private BigDecimal price;
    private Integer quantity;

    public ProductAggregate() {
    }

    @CommandHandler
    public ProductAggregate(CreateProductCommand createProductCommand) {
        ProductCreatedEvent productCreatedEvent
                = ProductCreatedEvent
                .builder()
                .aggregateId(createProductCommand.getAggregateId())
                .name(createProductCommand.getName())
                .price(createProductCommand.getPrice())
                .quantity(createProductCommand.getQuantity())
                .build();

//        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent();
//        BeanUtils.copyProperties(createProductCommand, productCreatedEvent);

        //Now publish a particular event
        AggregateLifecycle.apply(productCreatedEvent);
    }

    @CommandHandler
    public ProductAggregate(UpdateProductCommand updateProductCommand) {
        ProductUpdatedEvent productUpdatedEvent = new ProductUpdatedEvent();
        BeanUtils.copyProperties(updateProductCommand, productUpdatedEvent);

        //Now publish a particular event
        AggregateLifecycle.apply(productUpdatedEvent);
    }


    @EventSourcingHandler
    public void on(ProductCreatedEvent productCreatedEvent) {
        this.aggregateId = productCreatedEvent.getAggregateId();
        this.quantity = productCreatedEvent.getQuantity();
        this.price = productCreatedEvent.getPrice();
        this.name = productCreatedEvent.getName();
    }

    @EventSourcingHandler
    public void on(ProductUpdatedEvent productUpdatedEvent) {
        this.aggregateId = productUpdatedEvent.getAggregateId();
        this.quantity = productUpdatedEvent.getQuantity();
        this.price = productUpdatedEvent.getPrice();
        this.name = productUpdatedEvent.getName();
    }


}
